package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected Object findIndex(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

}
