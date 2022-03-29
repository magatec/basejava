package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int count = 0;

    //удаляем все значения в массиве, обнуляем счетчик
    public void clear() {
        Arrays.fill(storage, null);
        count = 0;
    }

    //если массив не заполнен и резюме в нем нет, добавляем резюме, увеличиваем счетчик на 1.
    public void save(Resume r) {
        if (count < storage.length && findIndex(r.toString()) < 0) {
            storage[count] = r;
            count++;
        } else {
            System.out.println("ERROR: Резюме уже есть в хранилище.");
        }
    }

    //возвращаем резюме по его uuid, если резюме с заданным uuid в массиве нет, возвращаем null.
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
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
            System.out.println("ERROR: Нет доступного резюме для обновления.");
        }
    }

    //удаляем элемент массива, сдвигаем все элементы стоящие после него на -1, уменьшаем счетчик на 1
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = null;
            for (int i = index; i < count - 1; i++) {
                storage[i] = storage[i + 1];
            }
            count--;
        } else {
            System.out.println("ERROR: Указанного резюме не существует.");
        }
    }

    //проверяем наличие резюме в массиве, возвращаем его индекс или -1
    private int findIndex(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].toString())) {
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
        Resume[] resumes = Arrays.copyOf(storage, count);
        return resumes;
    }

    public int size() {
        return count;
    }


}
