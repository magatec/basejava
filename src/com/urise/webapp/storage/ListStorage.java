package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    private ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    public void save(Resume r) {
        resumeExistStorage(r);
        saveResume(r);
    }

    protected void saveResume(Resume r) {
        storage.add(r);
    }

    @Override
    public void update(Resume r) {
        resumeNotExistStorage(r);
        storage.set(storage.indexOf(r), r);
    }

    @Override
    public void delete(String uuid) {
        uuidNotExistStorage(uuid);
        storage.remove(findIndex(uuid));
    }

    @Override
    public Resume get(String uuid) {
        uuidNotExistStorage(uuid);
        return storage.get(findIndex(uuid));
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
        for (Resume r : storage) {
            if (uuid.equals(r.getUuid())) {
                return storage.indexOf(r);
            }
        }
        return -1;
    }
}
