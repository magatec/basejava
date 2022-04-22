package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    public void resumeExistStorage(Resume r) {
        if (findIndex(r.getUuid()) >= 0) {
            throw new ExistStorageException(r.getUuid());
        }
    }

    public void resumeNotExistStorage(Resume r) {
        if (findIndex(r.getUuid()) < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    public void uuidNotExistStorage(String uuid) {
        if (findIndex(uuid) < 0) {
            throw new NotExistStorageException(uuid);
        }
    }
}
