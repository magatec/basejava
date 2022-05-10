package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Object findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    @Override
    protected void saveToStorage(Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateStorage(Resume r, Object index) {
        storage.set((Integer) index, r);
    }

    @Override
    protected void delFormStorage(Object index) {
        storage.remove((Integer) index);
    }

    @Override
    protected Resume getFromStorage(Object index) {
        return storage.get((Integer) index);
    }
}
