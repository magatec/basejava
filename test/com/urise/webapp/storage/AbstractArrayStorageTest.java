package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    public Storage storage;

    private static final String UUID_0 = "uuid0";
    private static final Resume RESUME_0 = new Resume(UUID_0);
    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage anyStorage) {
        this.storage = anyStorage;
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
        assertEquals(0, storage.size());
    }

    @Test
    void saveExistStorageException() {
        assertThrows(ExistStorageException.class, () ->
                storage.save(RESUME_2));
    }

    @Test
    void save() {
        try {
            for (int i = 4; i <= 10000; i++) {
                StringBuilder sb = new StringBuilder("uuid").append(i);
                storage.save(new Resume(sb.toString()));
            }
        } catch (Exception e) {
            fail("Переполнение произошло раньше времени.");
        }
        assertEquals(10000, storage.size());
    }

    @Test
    void saveOverSizeStorageException() {
        try {
            for (int i = 4; i <= 10000; i++) {
                StringBuilder sb = new StringBuilder("uuid").append(i);
                storage.save(new Resume(sb.toString()));
            }
        } catch (Exception e) {
            fail("Переполнение произошло раньше времени.");
        }

        assertThrows(StorageException.class, () ->
                storage.save(RESUME_0));

    }

    @Test
    void update() {
        storage.update(RESUME_1);
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test
    void updateNotExistStorageException() {
        assertThrows(NotExistStorageException.class, () ->
                storage.update(RESUME_0));
    }

    @Test
    void delete() {
        storage.delete(RESUME_1.getUuid());
        assertEquals(2, storage.size());
    }

    @Test
    void deleteNotExistStorageException() {
        assertThrows(NotExistStorageException.class, () ->
                storage.delete(RESUME_0.getUuid()));
    }

    @Test
    void get() {
        assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
    }

    @Test
    void getNotExistStorageException() {
        assertThrows(NotExistStorageException.class, () ->
                storage.get(RESUME_0.getUuid()));
    }

    @Test
    void getAll() {
        Resume[] all = storage.getAll();
        Resume[] standart = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(all, standart);
        assertTrue(storage.size() > 0);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }
}