package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    protected abstract Object findIndex(String uuid);

    protected abstract Resume getFromStorage(Object key);

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
    public void clear() {
        storage.clear();
    }

    public List<Resume> getListToAllSorted() {
        return Arrays.asList(storage.values().toArray(Resume[]::new));
    }

    @Override
    public int size() {
        return storage.size();
    }
}
