package com.urise.webapp;

import com.urise.webapp.storage.SqlStorage;
import com.urise.webapp.storage.Storage;

import java.io.*;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final File PROPS = new File("/home/magatec/BaseJava/basejava/config/resumes.properties");
    private final File storageDir;
    private final Storage storage;

    public static Config get() {return INSTANCE;}

    public Storage getStorage() {return storage;}

    public File getStorageDir() {
        return storageDir;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            System.out.println(storageDir);
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }
}
