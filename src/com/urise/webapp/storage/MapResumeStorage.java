package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<Resume> {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected void saveToStorage(Resume r, Resume key) {
        storage.put(r.getUuid(), r);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    public List<Resume> getListToAllSorted() {
        return Arrays.asList(storage.values().toArray(Resume[]::new));
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected Resume findSearchKey(String uuid) {
        return storage.get(uuid);
    }

    protected Resume getFromStorage(Resume resume) {
        return resume;
    }

    protected void deleteFormStorage(Resume key) {
        storage.remove(key.getUuid());
    }

    protected void updateStorage(Resume r, Resume key) {
        storage.put(key.getUuid(), r);
    }
}
