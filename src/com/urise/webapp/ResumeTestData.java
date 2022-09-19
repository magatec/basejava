package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ResumeTestData {
    public static Resume doTest(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.setContacts(ContactType.TELEPHONE, "111");
        resume.setContacts(ContactType.HOMEPAGE, "url_1");
        resume.setContacts(ContactType.MAIL, "mail_1@mail");
        resume.setContacts(ContactType.GITHUB, "git.com");
        resume.setContacts(ContactType.LINKEDIN, "linkedin.com");
        resume.setContacts(ContactType.SKYPE, "skype.com");
        resume.setContacts(ContactType.STACKOVERFLOW, "stac.com");

        resume.setSections(SectionType.PERSONAL, new TextSection("Personal"));

        resume.setSections(SectionType.OBJECTIVE, new TextSection("Objective"));

        List<String> achievList = new ArrayList<>();
        achievList.add("Achivement_1");
        achievList.add("Achivement_2");
        achievList.add("Achivement_3");
        resume.setSections(SectionType.ACHIEVEMENT, new ListSection(achievList));

        List<String> qualificationList = new ArrayList<>();
        qualificationList.add("Q_1");
        qualificationList.add("Q_2");
        resume.setSections(SectionType.QUALIFICATIONS, new ListSection(qualificationList));

        List<Organization> organizationList = new ArrayList<>();
        Period period = new Period(LocalDate.of(2017, 3, 1), LocalDate.of(2022, 2, 1), "Org_last", "descriptionOrg_last");
        Organization org_Last = new Organization("Org_Last", "www.orgLast.com", List.of(period));
        Period period1 = new Period(LocalDate.of(2010, 2, 1), LocalDate.of(2016, 12, 1), "Org_start", "descriptionOrg_start");
        Organization org_Start = new Organization("Org_Start", "www.orgstart.com", List.of(period1));
        organizationList.add(org_Last);
        organizationList.add(org_Start);
        resume.setSections(SectionType.EXPERIENCE, new OrganizationSection(organizationList));

        List<Organization> educOrgList = new ArrayList<>();
        Period educPeriod = new Period(LocalDate.of(2009, 3, 1), LocalDate.of(2009, 12, 1), "educFinal", "descriptionEducFinal");
        Organization educOrg = new Organization("Educ_Final", "www.educfinal.org", List.of(educPeriod));
        Period educPeriod_1 = new Period(LocalDate.of(2004, 9, 1), LocalDate.of(2009, 7, 1), "EducStart", "descriptionEducStart");
        Organization educOrgStart = new Organization("Educ_Start", "www.educstart.org", List.of(educPeriod_1));
        educOrgList.add(educOrg);
        educOrgList.add(educOrgStart);
        resume.setSections(SectionType.EDUCATION, new OrganizationSection(educOrgList));

        return resume;
    }
}

