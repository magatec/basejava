package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1,o2) -> o1.getUuid().compareTo(o2.getUuid());
    @Override
    protected void saveResume(Resume r) {
        int index = -(Integer) findIndex(r.getUuid()) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;
    }

    @Override
    protected Integer findIndex(String uuid) {
        Resume searchKey = new Resume(uuid, "");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
    }
}
