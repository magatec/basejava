package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void recurciv(String pathName) {
        File dir = new File(pathName);
        String[] list = dir.list();
        if (list != null) {
            for (String s : list) {
                File file1 = new File(pathName + "/" + s);
                if (file1.isFile()) {
                    System.out.println("  " + s);
                } else {
                    System.out.println(s);
                    recurciv(pathName + "/" + s);
                }
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "./.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("Результат метода \"recurciv\":\n");
        recurciv("./");

    }
}
