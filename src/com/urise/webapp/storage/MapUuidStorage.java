package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    protected Object findIndex(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    protected Resume getFromStorage(Object uuid) {
        return storage.get((String) uuid);
    }

}
