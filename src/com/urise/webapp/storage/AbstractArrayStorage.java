package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.*;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void saveToStorage(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено.", r.getUuid());
        }
        saveResume(r);
        size++;
    }

    protected abstract void saveResume(Resume r);

    public void updateStorage(Resume r, Integer index) {
        storage[index] = r;
    }

    public void deleteFormStorage(Integer index) {
        storage[index] = null;
        if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    public Resume getFromStorage(Integer index) {
        return storage[index];
    }

    public List<Resume> getListToAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Integer index) {
        return index >= 0;
    }
}
