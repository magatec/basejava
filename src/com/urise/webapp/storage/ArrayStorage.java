package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage implements Storage{
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];
    private int count = 0;

    //удаляем все значения в массиве, обнуляем счетчик
    public void clear() {
        Arrays.fill(storage, 0, count, null);
        count = 0;
    }

    //если массив не заполнен и резюме в нем нет, добавляем резюме, увеличиваем счетчик на 1.
    public void save(Resume r) {
        if (count < STORAGE_LIMIT) {
            if (findIndex(r.getUuid()) < 0) {
                storage[count] = r;
                count++;
            } else {
                System.out.println("ERROR: Резюме uuid = " + r.getUuid() + " уже есть в хранилище.");
            }
        } else {
            System.out.println("ERROR: Резюме uuid = " + r.getUuid() + " невозможно сохранить. Хранилище переполнено.");
        }
    }

    //возвращаем резюме по его uuid, если резюме с заданным uuid в массиве нет, возвращаем null.
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Резюме uuid = " + uuid + " нет в хранилище.");
            return null;
        }
        return storage[index];
    }

    //обновляем резюме
    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index >= 0) {
            storage[index] = r;
        } else {
            System.out.println("ERROR: Резюме с uuid = "+ r.getUuid() + " нет в хранилище.");
        }
    }

    //удаляем элемент массива, сдвигаем все элементы стоящие после него на -1, уменьшаем счетчик на 1
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            if (count - 1 - index >= 0) System.arraycopy(storage, index + 1, storage, index, count - 1 - index);
            count--;
        } else {
            System.out.println("ERROR: Указанного резюме с uuid = "+ uuid +" не существует.");
        }
    }

    //проверяем наличие резюме в массиве, возвращаем его индекс или -1
    private int findIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    // возвращааем массив резюме без null
    public Resume[] getAll() {
            return Arrays.copyOf(storage, count);
    }

    public int size() {
        return count;
    }


}
