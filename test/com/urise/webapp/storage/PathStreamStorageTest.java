package com.urise.webapp.storage;

public class PathStreamStorageTest extends AbstractStorageTest{

    public PathStreamStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new ObjectStreamStorage()));
    }
}
