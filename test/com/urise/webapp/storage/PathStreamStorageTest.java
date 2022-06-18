package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.ObjectStreamStorage;

public class PathStreamStorageTest extends AbstractStorageTest{

    public PathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()));
    }
}
