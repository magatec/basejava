package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MainCollections {
    private static final String UUID_0 = "uuid0";
    private static final String FULLNAME_0 = "fullName0";

    private static final Resume RESUME_0 = new Resume(UUID_0, FULLNAME_0);
    private static final String UUID_1 = "uuid1";
    private static final String FULLNAME_1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);
    private static final String UUID_2 = "uuid2";
    private static final String FULLNAME_2 = "fullName2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);
    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_3 = "fullName3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_1);
        collection.add(RESUME_2);
        collection.add(RESUME_3);

        for (Resume r : collection) {
            System.out.println(r);
        }

        Iterator<Resume> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Resume r = iterator.next();
            System.out.println(r);
            if (Objects.equals(r.getUuid(), UUID_1)) {
                iterator.remove();
            }
        }

        System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, RESUME_1);
        map.put(UUID_2, RESUME_2);
        map.put(UUID_3, RESUME_3);

        for (String uuid : map.keySet()) {
            System.out.println(map.get(uuid));
        }

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        System.out.println(map.get(UUID_1).getFullName());
    }
}
