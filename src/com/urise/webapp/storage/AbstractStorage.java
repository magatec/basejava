package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    public void save(Resume r) {
        uuidExistStorage(r.getUuid());
        saveResume(r);
    }

    protected abstract void saveResume(Resume r);

    public abstract void update(Resume r);

    public abstract void delete(String uuid);

    public abstract Resume get(String uuid);

    public void uuidExistStorage(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    public int uuidNotExistStorage(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}
