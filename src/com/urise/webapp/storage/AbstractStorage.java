package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object findIndex(String uuid);

    protected abstract void saveToStorage(Resume r);

    protected abstract void updateStorage(Resume r, Object searchKey);

    protected abstract void delFormStorage(Object searchKey);

    protected abstract Resume getFromStorage(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    public void save(Resume r) {
        if (!findNotExistedIndex(r.getUuid())) {
            saveToStorage(r);
        }
    }

    public void update(Resume r) {
        Object searchKey = findExistedIndex(r.getUuid());
        updateStorage(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = findExistedIndex(uuid);
        delFormStorage(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = findExistedIndex(uuid);
        return getFromStorage(searchKey);
    }

    public boolean findNotExistedIndex(String uuid) {
        Object searchKey = findIndex(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return false;
    }

    public Object findExistedIndex(String uuid) {
        Object searchKey = findIndex(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }
}
