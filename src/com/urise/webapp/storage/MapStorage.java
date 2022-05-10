package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Object findIndex(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
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
    protected void updateStorage(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    protected void delFormStorage(Object index) {
        storage.remove((String) index);
    }

    @Override
    protected Resume getFromStorage(Object index) {
        return storage.get((String) index);
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
