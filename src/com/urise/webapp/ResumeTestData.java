package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");
        resume.contacts = new HashMap<>();
        resume.achievement = new ArrayList<>();
        resume.qualifications = new ArrayList<>();
        resume.experience = new HashMap<>();
        resume.education = new HashMap<>();

        resume.contacts.put("Тел.", "+7(921) 855-0482");
        resume.contacts.put("Skype", "skype:grigory.kislin");
        resume.contacts.put("Почта", "gkislin@yandex.ru");
        resume.contacts.put("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin");
        resume.contacts.put("Профиль GitHub", "https://github.com/gkislin");
        resume.contacts.put("Профиль Stackoverflow", "https://stackoverflow.com/users/548473");
        resume.contacts.put("Домашняя страница", "http://gkislin.ru/");

        resume.personal = "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.";

        resume.objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";


        resume.achievement.add("""
                Организация команды и успешная реализация Java проектов для сторонних заказчиков:
                 приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга
                показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2,
                 многомодульный Spring Boot + Vaadin проект для комплексных DIY смет""");
        resume.achievement.add("""
                С 2013 года: разработка проектов "Разработка Web приложения","Java Enterprise",
                 "Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP).
                 Удаленное взаимодействие (JMS/AKKA)". Организация онлайн стажировок и ведение
                 проектов. Более 3500 выпускников.""");
        resume.achievement.add("""
                Реализация двухфакторной аутентификации для онлайн платформы управления
                проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.""");
        resume.achievement.add("""
                Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM.
                 Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления
                 окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и
                 авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.""");
        resume.achievement.add("""
                Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring,
                 Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.""");
        resume.achievement.add("""
                Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных
                 сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов
                  и информации о состоянии через систему мониторинга Nagios. Реализация онлайн
                   клиента для администрирования и мониторинга системы по JMX (Jython/ Django).""");
        resume.achievement.add("""
                Реализация протоколов по приему платежей всех основных платежных системы России
                 (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.""");



        resume.qualifications.add("""
                JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2""");
        resume.qualifications.add("""
                Version control: Subversion, Git, Mercury, ClearCase, Perforce""");
        resume.qualifications.add("""
                DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB""");

        resume.qualifications.add("""
                Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy""");
        resume.qualifications.add("""
                XML/XSD/XSLT, SQL, C/C++, Unix shell scripts""");
        resume.qualifications.add("""
                Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security,
                 Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin,
                 Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).""");
        resume.qualifications.add("""
                Python: Django.""");
        resume.qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        resume.qualifications.add("""
                Scala: SBT, Play2, Specs2, Anorm, Spray, Akka""");
        resume.qualifications.add("""
                Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT,
                 MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.\s""");
        resume.qualifications.add("""
                Инструменты: Maven + plugin development, Gradle, настройка Ngnix""");
        resume.qualifications.add("""
                администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport,
                 OpenCmis, Bonita, pgBouncer\s""");
        resume.qualifications.add("""
                Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов,
                 UML, функционального программирования""");
        resume.qualifications.add("""
                Родной русский, английский "upper intermediate\"""");

        Map<String, String> t = new HashMap<>();
        t.put("10/2013 - Сейчас","Автор проекта.\n" +
                "Создание, организация и проведение Java онлайн проектов и стажировок.");
        resume.experience.put("Java Online Projects", t);

        Map<String, String> t1 = new HashMap<>();
        t1.put("10/2014 - 01/2016", "Старший разработчик (backend)\n" +
                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        resume.experience.put("RIT Center", t1);

        Map<String, String> t2 = new HashMap<>();
        t2.put("12/2010 - 04/2012", "Ведущий программист\n" +
                "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        resume.experience.put("Luxoft (Deutsche Bank)", t2);

        Map<String, String> t3 = new HashMap<>();
        t3.put("06/2008 - 12/2010", "Ведущий специалист\n" +
                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        resume.experience.put("Yota", t3);

        Map<String, String> t4 = new HashMap<>();
        t4.put("03/2007 - 06/2008", "Разработчик ПО\n" +
                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        resume.experience.put("Enkata", t4);

        Map<String, String> t5 = new HashMap<>();
        t5.put("01/2005 - 02/2007", "Разработчик ПО\n" +
                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        resume.experience.put("Siemens AG", t5);

        Map<String, String> t6 = new HashMap<>();
        t6.put("09/1997 - 01/2005", "Инженер по аппаратному и программному тестированию\n" +
                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        resume.experience.put("Alcatel", t6);

        Map<String, String> ed = new HashMap<>();
        ed.put("03/2013 - 05/2013", "'Functional Programming Principles in Scala' by Martin Odersky");
        resume.education.put("Coursera", ed);

        Map<String, String> ed1 = new HashMap<>();
        ed1.put("03/2011 - 04/2011", "\n" +
                "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
        resume.education.put("Luxoft", ed1);

        Map<String, String> ed2 = new HashMap<>();
        ed2.put("01/2005 - 04/2005", "3 месяца обучения мобильным IN сетям (Берлин)");
        resume.education.put("Siemens AG", ed2);

        Map<String, String> ed3 = new HashMap<>();
        ed3.put("09/1997 - 03/1998", "\n" +
                "6 месяцев обучения цифровым телефонным сетям (Москва)");
        resume.education.put("Alcatel", ed3);

        Map<String, String> ed4 = new HashMap<>();
        ed4.put("09/1993 - 07/1996", "Аспирантура (программист С, С++)");
        ed4.put("09/1987 - 07/1993", "Инженер (программист Fortran, C)");
        resume.education.put("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", ed4);

        Map<String, String> ed5 = new HashMap<>();
        ed5.put("09/1984 - 06/1987", "Закончил с отличием");
        resume.education.put("Заочная физико-техническая школа при МФТИ", ed5);

        System.out.println(resume.getFullName() + "\n");

        for (Map.Entry<String, String> entry : resume.contacts.entrySet()) {
            System.out.println(entry.getKey() + ":  " + entry.getValue());
        }
        System.out.println("\n");

        System.out.println(SectionType.OBJECTIVE.getTitle());
        System.out.println(resume.objective + "\n");

        System.out.println(SectionType.PERSONAL.getTitle());
        System.out.println(resume.personal + "\n");

        System.out.println(SectionType.ACHIEVEMENT.getTitle());
        for (String s: resume.achievement) {
            System.out.println(s + "\n");
        }

        System.out.println(SectionType.QUALIFICATIONS.getTitle());
        for (String s: resume.qualifications) {
            System.out.println(s + "\n");
        }

        System.out.println(SectionType.EXPERIENCE.getTitle());
        for (Map.Entry<String, Map<String, String>> entry : resume.experience.entrySet()) {
            System.out.println(entry.getKey());
            for(Map.Entry<String, String > entry1 : entry.getValue().entrySet()) {
                System.out.println(entry1.getKey() + ":  " + entry1.getValue() + "\n");
            }
        }

        System.out.println(SectionType.EDUCATION.getTitle());
        for (Map.Entry<String, Map<String, String>> entry : resume.education.entrySet()) {
            System.out.println(entry.getKey());
            for(Map.Entry<String, String > entry1 : entry.getValue().entrySet()) {
                System.out.println(entry1.getKey() + ":  " + entry1.getValue() + "\n");
            }
        }
    }
}

