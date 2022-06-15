package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private SaveStorageStrategy saveStorageStrategy;

    protected FileStorage(File directory, SaveStorageStrategy saveStorageStrategy) {
        this.saveStorageStrategy = saveStorageStrategy;
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;

    }

    @Override
    protected File findSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void updateStorage(Resume r, File file) {
        try {
            saveStorageStrategy.doWrite(r, new FileOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteFormStorage(File file) {
        if (!file.exists() || !file.delete()) {
            throw new StorageException("IO error", file.getName());
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        try {
            return saveStorageStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public int size() {
        return Objects.requireNonNull(directory.list()).length;
    }

    @Override
    public void clear() {
        File[] files = Objects.requireNonNull(directory.listFiles(), "there are no files in the directory");
        for (File file : files) {
            deleteFormStorage(file);
        }
    }

    public void saveToStorage(Resume r) {
        File file = findSearchKey(r.getUuid());
        try {
            file.createNewFile();
            saveStorageStrategy.doWrite(r, new FileOutputStream(file));
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    public List<Resume> getListToAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        for (String name : Objects.requireNonNull(directory.list(), "there are no files in the directory")) {
            try {
                resumes.add(saveStorageStrategy.doRead(new BufferedInputStream(new FileInputStream(directory + "/" + name))));
            } catch (IOException e) {
                throw new StorageException("File read error", name, e);
            }
        }
        return resumes;
    }
}
