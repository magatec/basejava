package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
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

    public void updateStorage(Resume r, Object index) {
        storage[(Integer) index] = r;
    }

    public void delFormStorage(Object index) {
        int i = (Integer) index;
        storage[i] = null;
        if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
        size--;
    }

    public Resume getFromStorage(Object index) {
        return storage[(Integer) index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
