package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class ListStorage extends AbstractStorage<Integer> {
    private final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    public List<Resume> getListToAllSorted() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Integer findSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    protected void saveToStorage(Resume r) {
        storage.add(r);
    }

    @Override
    protected void updateStorage(Resume r, Integer index) {
        storage.set(index, r);
    }

    @Override
    protected void deleteFormStorage(Integer index) {
        storage.remove((int) index);
    }

    @Override
    protected Resume getFromStorage(Integer index) {
        return storage.get(index);
    }
}
