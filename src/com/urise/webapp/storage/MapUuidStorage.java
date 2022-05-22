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

    protected void delFormStorage(Object key) {
        storage.remove((String) key);
    }

    @Override
    protected void updateStorage(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }
}
