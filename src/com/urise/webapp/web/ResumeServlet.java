package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.HtmlUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage storage;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
                r = storage.get(uuid);
                break;
            case "edit":
                r = storage.get(uuid);
                for (SectionType type : SectionType.values()) {
                    AbstractSection section = r.getSections().get(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = TextSection.EMPTY;
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = ListSection.EMPTY;
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            OrganizationSection organizationSection = (OrganizationSection) r.getSections().get(type);
                            List<Organization> emptyFirstOrganizations = new ArrayList<>();
                            emptyFirstOrganizations.add(Organization.EMPTY);
                            if (organizationSection != null) {
                                for (Organization org : organizationSection.getList()) {
                                    List<Period> emptyFirstPeriod = new ArrayList<>();
                                    emptyFirstPeriod.add(Period.EMPTY);
                                    emptyFirstPeriod.addAll(org.getPeriods());
                                    emptyFirstOrganizations.add(new Organization(org.getName(), emptyFirstPeriod));
                                }
                            }
                            section = new OrganizationSection(emptyFirstOrganizations);
                            break;
                    }
                    r.setSections(type, section);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (!HtmlUtil.isEmpty(value)) {
                r.setContacts(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                r.getSections().remove(type);
            }
            else {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.setSections(type, new TextSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.setSections(type, new ListSection(value.split("\n")));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Organization> org = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "_url");
                        for (int i = 0; i < values.length; i++) {
                            List<Period> periods = new ArrayList<>();
                            String name = values[i];
                            if (!HtmlUtil.isEmpty(name)) {
                                String pref = type.name() + i;
                                String[] startDates  = request.getParameterValues(pref + "startDate");
                                String[] endDates  = request.getParameterValues(pref + "endDate");
                                String[] titles  = request.getParameterValues(pref + "title");
                                String[] descriptions  = request.getParameterValues(pref + "description");
                                for (int j = 0; j < titles.length; j++) {
                                    periods.add(new Period(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                }
                            }
                            org.add(new Organization(name, urls[i], periods ));
                        }
                        break;
                }
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
    }
}
