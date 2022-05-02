package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private final ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    protected void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    public void update(Resume r) {
        storage.set(uuidNotExistStorage(r.getUuid()), r);
    }

    @Override
    public void delete(String uuid) {
        storage.remove(uuidNotExistStorage(uuid));
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(uuidNotExistStorage(uuid));
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
}
