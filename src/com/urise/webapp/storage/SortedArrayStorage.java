package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int tempIndex = findIndex(r.getUuid());
        if (count < STORAGE_LIMIT) {
            if (findIndex(r.getUuid()) < 0) {
                if (count == 0) {
                    storage[count] = r;
                } else {
                    tempIndex = (tempIndex + 1) * (-1);
                    int i = count;
                    while (i >= tempIndex) {
                        storage[i + 1] = storage[i];
                        i--;
                    }
                    storage[tempIndex] = r;
                }
                count++;
            } else {
                System.out.println("ERROR: Резюме uuid = " + r.getUuid() + " уже есть в хранилище.");
            }
        } else {
            System.out.println("ERROR: Резюме uuid = " + r.getUuid() + " невозможно сохранить. Хранилище переполнено.");
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, count, searchKey);
    }
}
