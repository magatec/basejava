package com.urise.webapp.storage;

public class FileStreamStorageTest extends AbstractStorageTest {

    public FileStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}

