package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object findIndex(String uuid);

    protected abstract void saveToStorage(Resume r);

    protected abstract void updateStorage(Resume r, Object index);

    protected abstract void delFormStorage(Object index);

    protected abstract Resume getFromStorage(Object index);

    protected abstract boolean isExist(Object index);

    public void save(Resume r) {
        if (!findNotExistedIndex(r.getUuid())) {
            saveToStorage(r);
        }
    }

    public void update(Resume r) {
        Object index = findExistedIndex(r.getUuid());
        updateStorage(r, index);
    }

    public void delete(String uuid) {
        Object index = findExistedIndex(uuid);
        delFormStorage(index);
    }

    public Resume get(String uuid) {
        Object index = findExistedIndex(uuid);
        return getFromStorage(index);
    }

    public boolean findNotExistedIndex(String uuid) {
        Object index = findIndex(uuid);
        if (isExist(index)) {
            throw new ExistStorageException(uuid);
        }
        return false;
    }

    public Object findExistedIndex(String uuid) {
        Object index = findIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}
