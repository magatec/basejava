package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.ObjectStreamStorage;

public class FileStreamStorageTest extends AbstractStorageTest {

    public FileStreamStorageTest() {
        super(new FileStorage(STORAGE_DIR, new ObjectStreamStorage()));
    }
}

