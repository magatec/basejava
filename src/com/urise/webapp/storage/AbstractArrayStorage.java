package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int count = 0;

    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    public void save(Resume r) {
        if (count < STORAGE_LIMIT) {
            if (findIndex(r.getUuid()) < 0) {
                saveResume(r);
                count++;
            } else {
                System.out.println("ERROR: Резюме uuid = " + r.getUuid() + " уже есть в хранилище.");
            }
        } else {
            System.out.println("ERROR: Резюме uuid = " + r.getUuid() + " невозможно сохранить. Хранилище переполнено.");
        }
    }

    protected abstract void saveResume(Resume r);

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: Резюме с uuid = " + r.getUuid() + " нет в хранилище.");
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            if (count - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, count - 1 - index);
            count--;
        } else {
            System.out.println("ERROR: Указанного резюме с uuid = " + uuid + " не существует.");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Резюме uuid = " + uuid + " нет в хранилище.");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, count);
    }

    public int size() {
        return count;
    }

    protected abstract int findIndex(String uuid);
}
