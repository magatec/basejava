package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {

    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected boolean isExist(String key) {
        return key != null;
    }

    @Override
    protected void saveToStorage(Resume r, String key) {
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
    protected String findSearchKey(String uuid) {
        return storage.containsKey(uuid) ? uuid : null;
    }

    protected Resume getFromStorage(String uuid) {
        return storage.get(uuid);
    }

    protected void deleteFormStorage(String key) {
        storage.remove(key);
    }

    protected void updateStorage(Resume r, String uuid) {
        storage.put(uuid, r);
    }
}
