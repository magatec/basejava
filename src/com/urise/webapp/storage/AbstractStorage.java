package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    protected abstract Object findIndex(String uuid);

    protected abstract void saveToStorage(Resume r);

    protected abstract void updateStorage(Resume r, Object searchKey);

    protected abstract void delFormStorage(Object searchKey);

    protected abstract Resume getFromStorage(Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract List<Resume> getListToAllSorted();


    public void save(Resume r) {
        if (!findNotExistedSearchKey(r.getUuid())) {
            saveToStorage(r);
        }
    }

    public void update(Resume r) {
        Object searchKey = findExistedSearchKey(r.getUuid());
        updateStorage(r, searchKey);
    }

    public void delete(String uuid) {
        Object searchKey = findExistedSearchKey(uuid);
        delFormStorage(searchKey);
    }

    public Resume get(String uuid) {
        Object searchKey = findExistedSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    private boolean findNotExistedSearchKey(String uuid) {
        Object searchKey = findIndex(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return false;
    }

    public Object findExistedSearchKey(String uuid) {
        Object searchKey = findIndex(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getListToAllSorted();
        Comparator<Resume> fullNameComparator = (o1, o2) -> o1.getFullName().equals(o2.getFullName()) ?
                o1.getUuid().compareTo(o2.getUuid()) : o1.getFullName().compareTo(o2.getFullName());

        list.sort(fullNameComparator);
        return list;
    }
}
