package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    protected Object findIndex(String uuid) {
        return storage.containsKey(uuid) && storage.get(uuid).getFullName() != null ? uuid : null;
    }
}
