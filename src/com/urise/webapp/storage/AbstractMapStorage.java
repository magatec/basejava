package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    protected abstract Object findIndex(String uuid);

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
    public List<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(storage.values().toArray(Resume[]::new));
        list.sort(fullNameComparator);
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
