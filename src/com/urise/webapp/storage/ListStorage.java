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
        Resume[] resumes = new Resume[storage.size()];
        resumes = storage.toArray(resumes);
        return resumes;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void toSave(Resume r) {
        storage.add(r);
    }

    @Override
    protected void toUpdate(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected void toDelete(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume toGet(int index) {
        return storage.get(index);
    }
}
