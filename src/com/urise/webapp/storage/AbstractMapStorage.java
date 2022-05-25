//package com.urise.webapp.storage;
//
//import com.urise.webapp.model.Resume;
//
//import java.util.*;
//
////public abstract class AbstractMapStorage extends AbstractStorage<String> {
//    protected final Map<String, Resume> storage = new HashMap<>();
//
//    protected abstract String findIndex(String uuid);
//
//    protected abstract Resume getFromStorage(String key);
//
//    @Override
//    protected abstract void delFormStorage(String key);
//
//    @Override
//    protected boolean isExist(String key) {
//        return key != null;
//    }
//
//    @Override
//    protected void saveToStorage(Resume r) {
//        storage.put(r.getUuid(), r);
//    }
//
//    @Override
//    public void clear() {
//        storage.clear();
//    }
//
//    public List<Resume> getListToAllSorted() {
//        return Arrays.asList(storage.values().toArray(Resume[]::new));
//    }
//
//    @Override
//    public int size() {
//        return storage.size();
//    }
//}
