package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.SaveStorageStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;
    private final SaveStorageStrategy saveStorageStrategy;

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
            throw new StorageException("Error write file", file.getName(), e);
        }
    }

    @Override
    protected void deleteFormStorage(File file) {
        if (!file.delete()) {
            throw new StorageException("Error delete file", file.getName());
        }
    }

    @Override
    protected Resume getFromStorage(File file) {
        try {
            return saveStorageStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error read file", file.getName(), e);
        }
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    public int size() {
        return getFileList().length;
    }

    @Override
    public void clear() {
        for (File file : getFileList()) {
            deleteFormStorage(file);
        }
    }

    public void saveToStorage(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Error save file", file.getName(), e);
        }
        updateStorage(r, file);
    }

    public List<Resume> getListToAllSorted() {
        List<Resume> resumes = new ArrayList<>();
        for (File file : getFileList()) {
            try {
                resumes.add(saveStorageStrategy.doRead(new BufferedInputStream(new FileInputStream(directory + "/" + file.getName()))));
            } catch (IOException e) {
                throw new StorageException("Error read file", file.getName(), e);
            }
        }
        return resumes;
    }

    private File[] getFileList() {
        if (directory.exists()) {
            return directory.listFiles();
        }
        throw new StorageException("Error read file", directory.toString());
    }
}
