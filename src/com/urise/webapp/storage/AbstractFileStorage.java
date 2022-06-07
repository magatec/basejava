package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected abstract void doWrite(Resume r, File file) throws IOException;

    protected abstract Resume doRead(File file);

    protected AbstractFileStorage(File directory) {
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
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected void deleteFormStorage(File file) {
        if (!file.exists()) {
            throw new StorageException("file does not exist", file.getName());
        }
        file.delete();
    }

    @Override
    protected Resume getFromStorage(File file) {
        return doRead(file);
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
        File[] files =  Objects.requireNonNull(directory.listFiles(), "there are no files in the directory");
        for (File file : files) {
            file.delete();
        }
    }

    public void saveToStorage(Resume r) {
        File file = findSearchKey(r.getUuid());
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    public List<Resume> getListToAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        for (String name : Objects.requireNonNull(directory.list(), "there are no files in the directory")) {
            resumes.add(doRead(new File(name)));
        }
        return resumes;
    }
}
