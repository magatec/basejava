package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object findIndex(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    @Override
    protected boolean isExist(Object key) {
        return key != null;
    }

    @Override
    protected void saveToStorage(Resume r) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void updateStorage(Resume r, Object uuid) {
        storage.put((String) uuid, r);
    }

    @Override
    protected void delFormStorage(Object uuid) {
        storage.remove((String) uuid);
    }

    @Override
    protected Resume getFromStorage(Object uuid) {
        return storage.get((String) uuid);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
