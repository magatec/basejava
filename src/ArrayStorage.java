/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int count = 0;

    void clear() {  //удаляем все значения в массиве, обнуляем счетчик
        for (int i = 0; i < count; i++) {
            storage[i] = null;
        }
        count = 0;
    }

    void save(Resume r) {  //добавляем резюме в массив, увеличиваем счетчик на 1.
        storage[count] = r;
        count++;
    }

    Resume get(String uuid) {  //возвращаем резюме по его uuid
        return storage[Integer.parseInt(uuid)];
    }

    void delete(String uuid) {   //удаляем элемент массива, сдвигаем все элементы стоящие после него на -1, уменьшаем счетчик на 1
        int id = Integer.parseInt(uuid);
        storage[id] = null;
        for (int i = id; i < count; i++) {
            storage[i] = storage[i + 1];
        }
        count--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {  // возвращааем массив резюме без null
        Resume[] resumes = new Resume[count];
        for (int i = 0; i < count; i++)
            resumes[i] = storage[i];
        return resumes;
    }

    int size() {
        return count;
    }
}
