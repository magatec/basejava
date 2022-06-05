package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ResumeTestData {
    public static Resume doTest(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

//        EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
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
        Period period = new Period(LocalDate.of(2017, 3, 1), LocalDate.now(), "Org_last", "descriptionOrg_last");
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
//    public static void main(String[] args) {
//        String tel = "+7(921) 855-0482";
//        String skype = "skype:grigory.kislin";
//        String mail = "gkislin@yandex.ru";
//        String linkedin = "https://www.linkedin.com/in/gkislin";
//        String github = "https://github.com/gkislin";
//        String stack = "https://stackoverflow.com/users/548473";
//        String homepage = "http://gkislin.ru/";
//
//        EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
//        contacts.put(ContactType.TELEPHONE, tel);
//        contacts.put(ContactType.SKYPE, skype);
//        contacts.put(ContactType.MAIL, mail);
//        contacts.put(ContactType.LINKEDIN, linkedin);
//        contacts.put(ContactType.GITHUB, github);
//        contacts.put(ContactType.STACKOVERFLOW, stack);
//        contacts.put(ContactType.HOMEPAGE, homepage);
//
//        AbstractSection textSection = new TextSection("Аналитический склад ума, сильная логика," +
//                " креативность, инициативность. Пурист кода и архитектуры.");
//        AbstractSection textSection1 = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
//        List<String> list = new ArrayList<>();
//        list.add("""
//                Организация команды и успешная реализация Java проектов для сторонних заказчиков:
//                 приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга
//                показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2,
//                 многомодульный Spring Boot + Vaadin проект для комплексных DIY смет""");
//        list.add("""
//                Организация команды и успешная реализация Java проектов для сторонних заказчиков:
//                 приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга
//                показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2,
//                 многомодульный Spring Boot + Vaadin проект для комплексных DIY смет""");
//        list.add("""
//                С 2013 года: разработка проектов "Разработка Web приложения","Java Enterprise",
//                 "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP).
//                 Удаленное взаимодействие (JMS/AKKA)". Организация онлайн стажировок и ведение
//                 проектов. Более 3500 выпускников.""");
//        list.add("""
//                Реализация двухфакторной аутентификации для онлайн платформы управления
//                проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.""");
//        list.add("""
//                Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM.
//                 Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления
//                 окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и
//                 авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.""");
//        list.add("""
//                Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring,
//                 Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.""");
//        list.add("""
//                Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных
//                 сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов
//                  и информации о состоянии через систему мониторинга Nagios. Реализация онлайн
//                   клиента для администрирования и мониторинга системы по JMX (Jython/ Django).""");
//        list.add("""
//                Реализация протоколов по приему платежей всех основных платежных системы России
//                 (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.""");
//
//        List<String> qualifications = new ArrayList<>();
//        qualifications.add("""
//                JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2""");
//        qualifications.add("""
//                Version control: Subversion, Git, Mercury, ClearCase, Perforce""");
//        qualifications.add("""
//                DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB""");
//
//        qualifications.add("""
//                Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy""");
//        qualifications.add("""
//                XML/XSD/XSLT, SQL, C/C++, Unix shell scripts""");
//        qualifications.add("""
//                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security,
//                 Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin,
//                 Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).""");
//        qualifications.add("""
//                Python: Django.""");
//        qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
//        qualifications.add("""
//                Scala: SBT, Play2, Specs2, Anorm, Spray, Akka""");
//        qualifications.add("""
//                Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT,
//                 MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\s""");
//        qualifications.add("""
//                Инструменты: Maven + plugin development, Gradle, настройка Ngnix""");
//        qualifications.add("""
//                администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,
//                 OpenCmis, Bonita, pgBouncer\s""");
//        qualifications.add("""
//                Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов,
//                 UML, функционального программирования""");
//        qualifications.add("""
//                Родной русский, английский "upper intermediate\"""");
//
//        List<Organization> organizationList = new ArrayList<>();
//
//        Period workPeriod = new Period(LocalDate.of(2013, 10, 1), LocalDate.now(), "Автор проекта.\n", "Создание, организация и проведение Java онлайн проектов и стажировок.");
//        List<Period> workPeriodList = new ArrayList<>();
//        workPeriodList.add(workPeriod);
//        Organization org = new Organization("Java Online Projects", "http://javaops.ru/", workPeriodList);
//        organizationList.add(org);
//
//        Period workPeriod1 = new Period(LocalDate.of(2014, 10, 1), LocalDate.of(2016, 1, 1), "Старший разработчик (backend)\n", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
//        List<Period> workPeriod1List = new ArrayList<>();
//        workPeriod1List.add(workPeriod1);
//        Organization org1 = new Organization("Wrike", "https://www.wrike.com/", workPeriod1List);
//        organizationList.add(org1);
//
//        Period workPeriod2 = new Period(LocalDate.of(2012, 4, 1), LocalDate.of(2014, 10, 1), "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
//        List<Period> workPeriod2List = new ArrayList<>();
//        workPeriod2List.add(workPeriod2);
//        Organization org2 = new Organization("RIT Center", workPeriod2List);
//        organizationList.add(org2);
//
//        Period workPeriod3 = new Period(LocalDate.of(2010, 12, 1), LocalDate.of(2012, 4, 1), "Ведущий программист\n", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
//        List<Period> workPeriod3List = new ArrayList<>();
//        workPeriod3List.add(workPeriod3);
//        Organization org3 = new Organization("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/", workPeriod3List);
//        organizationList.add(org3);
//
//        Period workPeriod4 = new Period(LocalDate.of(2008, 6, 1), LocalDate.of(2010, 12, 1), "Ведущий специалист\n", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
//        List<Period> workPeriod4List = new ArrayList<>();
//        workPeriod4List.add(workPeriod4);
//        Organization org4 = new Organization("Yota", "https://www.yota.ru/", workPeriod4List);
//        organizationList.add(org4);
//
//        Period workPeriod5 = new Period(LocalDate.of(2007, 3, 1), LocalDate.of(2008, 6, 1), "Разработчик ПО\n", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
//        List<Period> workPeriod5List = new ArrayList<>();
//        workPeriod5List.add(workPeriod5);
//        Organization org5 = new Organization("Enkata", "http://enkata.com/", workPeriod5List);
//        organizationList.add(org5);
//
//        Period workPeriod6 = new Period(LocalDate.of(2005, 1, 1), LocalDate.of(2007, 2, 1), "Разработчик ПО\n", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
//        List<Period> workPeriod6List = new ArrayList<>();
//        workPeriod6List.add(workPeriod6);
//        Organization org6 = new Organization("Siemens AG", "https://www.siemens.com/ru/ru/home.html", workPeriod6List);
//        organizationList.add(org6);
//
//        Period workPeriod7 = new Period(LocalDate.of(1997, 9, 1), LocalDate.of(2005, 1, 1), "Инженер по аппаратному и программному тестированию\n", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
//        List<Period> workPeriod7List = new ArrayList<>();
//        workPeriod7List.add(workPeriod7);
//        Organization org7 = new Organization("Alcatel", "http://www.alcatel.ru/", workPeriod7List);
//        organizationList.add(org7);
//
//        List<Organization> educOrganizationList = new ArrayList<>();
//
//        Period educPeriod = new Period(LocalDate.of(2013, 3, 1), LocalDate.of(2013, 5, 1), "", "'Functional Programming Principles in Scala' by Martin Odersky");
//        List<Period> educPeriodList = new ArrayList<>();
//        educPeriodList.add(educPeriod);
//        Organization educOrg = new Organization("Coursera", "https://www.coursera.org/course/progfun", educPeriodList);
//        educOrganizationList.add(educOrg);
//
//        Period educPeriod1 = new Period(LocalDate.of(2011, 3, 1), LocalDate.of(2011, 4, 1), "", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
//        List<Period> educPeriodList1 = new ArrayList<>();
//        educPeriodList1.add(educPeriod1);
//        Organization educOrg1 = new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", educPeriodList1);
//        educOrganizationList.add(educOrg1);
//
//        Period educPeriod2 = new Period(LocalDate.of(2005, 1, 1), LocalDate.of(2005, 4, 1), "", "3 месяца обучения мобильным IN сетям (Берлин)");
//        List<Period> educPeriodList2 = new ArrayList<>();
//        educPeriodList2.add(educPeriod2);
//        Organization educOrg2 = new Organization("Siemens AG", "http://www.siemens.ru/", educPeriodList2);
//        educOrganizationList.add(educOrg2);
//
//        Period educPeriod3 = new Period(LocalDate.of(1997, 9, 1), LocalDate.of(1998, 3, 1), "", "6 месяцев обучения цифровым телефонным сетям (Москва)");
//        List<Period> educPeriodList3 = new ArrayList<>();
//        educPeriodList3.add(educPeriod3);
//        Organization educOrg3 = new Organization("Alcatel", "http://www.alcatel.ru/", educPeriodList3);
//        educOrganizationList.add(educOrg3);
//
//        Period educPeriod4 = new Period(LocalDate.of(1993, 9, 1), LocalDate.of(1996, 7, 1), "", "Аспирантура (программист С, С++)");
//        Period educPeriod4_1 = new Period(LocalDate.of(1987, 9, 1), LocalDate.of(1993, 7, 1), "", "Инженер (программист Fortran, C)");
//        List<Period> educPeriodList4 = new ArrayList<>();
//        educPeriodList4.add(educPeriod4);
//        educPeriodList4.add(educPeriod4_1);
//        Organization educOrg4 = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", educPeriodList4);
//        educOrganizationList.add(educOrg4);
//
//        Period educPeriod5 = new Period(LocalDate.of(1984, 9, 1), LocalDate.of(1987, 6, 1), "", "Закончил с отличием");
//        List<Period> educPeriodList5 = new ArrayList<>();
//        educPeriodList5.add(educPeriod5);
//        Organization educOrg5 = new Organization("Заочная физико-техническая школа при МФТИ", "http://www.school.mipt.ru/", educPeriodList5);
//        educOrganizationList.add(educOrg5);
//
//        AbstractSection listSection = new ListSection(list);
//        AbstractSection qualificationList = new ListSection(qualifications);
//        AbstractSection experience = new OrganizationSection(organizationList);
//        AbstractSection education = new OrganizationSection(educOrganizationList);
//
//        EnumMap<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
//        sections.put(SectionType.PERSONAL, textSection);
//        sections.put(SectionType.OBJECTIVE, textSection1);
//        sections.put(SectionType.ACHIEVEMENT, listSection);
//        sections.put(SectionType.QUALIFICATIONS, qualificationList);
//        sections.put(SectionType.EXPERIENCE, experience);
//        sections.put(SectionType.EDUCATION, education);
//
//        Resume resume = new Resume("uuid1", "Григорий Кислин", contacts, sections);
//
//        System.out.println(resume.getFullName());
//        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
//            System.out.println(entry.getKey().getTitle() + "  " + entry.getValue());
//        }
//
//        for (Map.Entry<SectionType, AbstractSection> entry : sections.entrySet()) {
//            System.out.println(entry.getKey().getTitle());
//            System.out.println(entry.getValue().getContent());
//        }
//    }
}

