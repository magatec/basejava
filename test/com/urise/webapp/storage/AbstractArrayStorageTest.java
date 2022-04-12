package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class AbstractArrayStorageTest {
    public Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(SortedArrayStorage sortedArrayStorage) {
        this.storage = sortedArrayStorage;
    }

    public AbstractArrayStorageTest(ArrayStorage arrayStorage) {
        this.storage = arrayStorage;
    }


    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }


    @Test
    void clear() {
        storage.clear();
        Assertions.assertEquals(0, storage.size());
    }

    @Test
    void save() {
        Assertions.assertThrows(ExistStorageException.class, () ->
                storage.save(RESUME_2));

        try {
            for (int i = 4; i <= 10000; i++) {
                StringBuilder sb = new StringBuilder("uuid").append(i);
                storage.save(new Resume(sb.toString()));
            }
        } catch (Exception e) {
            fail("Переполнение произошло раньше времени.");
        }

        Assertions.assertThrows(StorageException.class, () ->
                storage.save(new Resume("uuid0")));
    }

    @Test
    void update() {
        storage.update(RESUME_3);
        Assertions.assertEquals(RESUME_3, storage.get(RESUME_3.getUuid()));
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get("dummy"));

    }

    @Test
    void delete() {
        storage.delete(RESUME_1.getUuid());

        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get(RESUME_1.getUuid()));
    }

    @Test
    void get() {
        Assertions.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get("dummy"));
    }

    @Test
    void getAll() {
        Resume[] all = storage.getAll();

        Assertions.assertEquals(storage.get(RESUME_1.getUuid()), all[0]);
        Assertions.assertEquals(storage.get(RESUME_2.getUuid()), all[1]);
        Assertions.assertEquals(storage.get(RESUME_3.getUuid()), all[2]);
        Assertions.assertTrue(storage.size() > 0);
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
    }
}