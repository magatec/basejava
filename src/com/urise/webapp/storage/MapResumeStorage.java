package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Object findIndex(String uuid) {
        return storage.get(uuid);
    }

    protected Resume getFromStorage(Object resume) {
        return (Resume) resume;
    }

    protected void delFormStorage(Object key) {
        Resume r = (Resume) key;
        storage.remove(r.getUuid());
    }

    @Override
    protected void updateStorage(Resume r, Object key) {
        Resume res = (Resume) key;
        storage.put(res.getUuid(), r);
    }
}
