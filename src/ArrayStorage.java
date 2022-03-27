/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count = 0;

    //удаляем все значения в массиве, обнуляем счетчик
    void clear() {
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
        count = 0;
    }

    //добавляем резюме в массив, увеличиваем счетчик на 1.
    void save(Resume r) {
        storage[count] = r;
        count++;
    }

    //возвращаем резюме по его uuid, если резюме с заданным uuid в массиве нет, возвращаем null.
    Resume get(String uuid) {
        int index = indexResume(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    //удаляем элемент массива, сдвигаем все элементы стоящие после него на -1, уменьшаем счетчик на 1
    void delete(String uuid) {
        int index = indexResume(uuid);
        if (index >= 0) {
            storage[index] = null;
            for (int i = index; i < count; i++) {
                storage[i] = storage[i + 1];
            }
            count--;
        }
    }

    int indexResume(String uuid) {
        for (int i = 0; i < count; i++) {
            if (uuid.equals(storage[i].uuid)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    // возвращааем массив резюме без null
    Resume[] getAll() {
        Resume[] resumes = new Resume[count];
        for (int i = 0; i < count; i++)
            resumes[i] = storage[i];
        return resumes;
    }

    int size() {
        return count;
    }


}
