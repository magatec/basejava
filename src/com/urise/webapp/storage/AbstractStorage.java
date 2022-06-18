package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK findSearchKey(String uuid);

    protected abstract void saveToStorage(Resume r, SK searchKey);

    protected abstract void updateStorage(Resume r, SK searchKey);

    protected abstract void deleteFormStorage(SK searchKey);

    protected abstract Resume getFromStorage(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> getListToAllSorted();


    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = findNotExistedSearchKey(r.getUuid());
        saveToStorage(r, searchKey);
    }

    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = findExistedSearchKey(r.getUuid());
        updateStorage(r, searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = findExistedSearchKey(uuid);
        deleteFormStorage(searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = findExistedSearchKey(uuid);
        return getFromStorage(searchKey);
    }

    private SK findNotExistedSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    public SK findExistedSearchKey(String uuid) {
        SK searchKey = findSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
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
