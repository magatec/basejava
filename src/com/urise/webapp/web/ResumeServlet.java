package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {
    private Storage storage = Config.get().getStorage();

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ?  "Hello Resumes!" : "Hello " + name + "!");
        Writer writer = response.getWriter();
        writer.write(
                "<html>" +
                        "<head>" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                        "<link rel=\"stylesheet\" href=\"css/style.css\"" +
                        "<title>Список всех резюме</title>" +
                        "</head>" +
                        "<body>" +
                        "<section>" +
                        "<table border=\"1\" cellpadding=\"8\" cellspasing=\"0\">" +
                        "<tr>" +
                        "<th>Имя</th>" +
                        "<th>Телефон</th>" +
                        "</tr>"
        );
        for (Resume resume : storage.getAllSorted()) {
            writer.write(
                    "<tr>" +
                            "<td><a href=\"resume?uuid=" + resume.getUuid() + "\">" + resume.getFullName() + "</a></td>" +
                            "<td>" + resume.getContacts().get(ContactType.TELEPHONE) + "</td>" +
                            "</tr>"
            );
            writer.write(
                    "/table>" +
                            "</section>" +
                            "</body>" +
                            "</html>"
            );
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
