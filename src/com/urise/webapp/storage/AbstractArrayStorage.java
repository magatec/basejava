package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
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

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Хранилище переполнено.", r.getUuid());
        }
        uuidExistStorage(r.getUuid());
        saveResume(r);
        size++;
    }

    protected abstract void saveResume(Resume r);

    public void update(Resume r) {
        storage[uuidNotExistStorage(r.getUuid())] = r;
    }

    public void delete(String uuid) {
        int index = uuidNotExistStorage(uuid);
        storage[index] = null;
        if (size - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        size--;
    }

    public Resume get(String uuid) {
        return storage[uuidNotExistStorage(uuid)];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
