package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    public Storage storage;

    private static final String UUID_0 = "uuid0";
    private static final String FULLNAME_0 = "fullName0";
    private static final Resume RESUME_0 = new Resume(UUID_0, FULLNAME_0);
    private static final String UUID_1 = "uuid1";
    private static final String FULLNAME_1 = "fullName1";
    private static final Resume RESUME_1 = new Resume(UUID_1, FULLNAME_1);
    private static final String UUID_2 = "uuid2";
    private static final String FULLNAME_2 = "fullName2";
    private static final Resume RESUME_2 = new Resume(UUID_2, FULLNAME_2);
    private static final String UUID_3 = "uuid3";
    private static final String FULLNAME_3 = "fullName3";
    private static final Resume RESUME_3 = new Resume(UUID_3, FULLNAME_3);

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
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
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_2));
    }

    @Test
    void save() {
        storage.save(RESUME_0);
        assertThrows(ExistStorageException.class, () -> storage.save(RESUME_0));
        assertEquals(4, storage.size());
    }

    @Test
    void update() {
        storage.update(RESUME_1);
        assertSame(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void updateNotExistStorageException() {
        assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_0));
    }

    @Test
    void delete() {
        storage.delete(UUID_1);
        assertEquals(2, storage.size());
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    void get() {
        assertEquals(RESUME_1, storage.get(UUID_1));
    }

    @Test
    void getNotExistStorageException() {
        assertThrows(NotExistStorageException.class, () -> storage.get(UUID_0));
    }

    @Test
    void getAllSorted() {
        List<Resume> actual = storage.getAllSorted();
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertEquals(actual, expected);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }
}