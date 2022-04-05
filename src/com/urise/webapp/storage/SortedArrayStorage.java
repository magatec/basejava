package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        int index = findIndex(r.getUuid());
        if (count < STORAGE_LIMIT) {
            if (index < 0) {
                index = -index - 1;
                int i = count;
                System.arraycopy(storage,index,storage,index+1,count-index);
                storage[index] = r;
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
