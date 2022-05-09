package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int findIndex(String uuid);

    protected abstract void toSave(Resume r);

    protected abstract void toUpdate(Resume r, int index);

    protected abstract void toDelete(int index);

    protected abstract Resume toGet(int index);

    public void save(Resume r) {
        if (!findExistedIndex(r.getUuid())) {
            toSave(r);
        }
    }

    public void update(Resume r) {
        int index = findNotExistedIndex(r.getUuid());
        toUpdate(r, index);
    }

    public void delete(String uuid) {
        int index = findNotExistedIndex(uuid);
        toDelete(index);
    }

    public Resume get(String uuid) {
        int index = findNotExistedIndex(uuid);
        return toGet(index);
    }

    public boolean findExistedIndex(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return false;
    }

    public int findNotExistedIndex(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }
}
