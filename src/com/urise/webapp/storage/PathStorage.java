package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private Path directory;
    private SaveStorageStrategy saveStorageStrategy;

    protected PathStorage(String dir, SaveStorageStrategy saveStorageStrategy) {
        this.saveStorageStrategy = saveStorageStrategy;
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }

    }

    @Override
    protected Path findSearchKey(String uuid) {
        return Paths.get(String.valueOf(directory), uuid);
    }

    @Override
    protected void updateStorage(Resume r, Path path) {
        try {
            saveStorageStrategy.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    @Override
    protected void deleteFormStorage(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Error path delete", path.toString(), e);
        }
    }

    @Override
    protected Resume getFromStorage(Path path) {
        try {
            return saveStorageStrategy.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("File read error", path.toString(), e);
        }
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    public int size() {
        try {
            return Objects.requireNonNull(Files.list(directory)).toArray().length;
        } catch (IOException e) {
            throw new StorageException("Error get size", directory.toString(), e);
        }
    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::deleteFormStorage);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    public void saveToStorage(Resume r) {
        Path path = findSearchKey(r.getUuid());
        try {
            Files.createFile(path);
            saveStorageStrategy.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("IO error", path.toString(), e);
        }
    }

    public List<Resume> getListToAllSorted() {
//        List<Resume> resumes = new ArrayList<>();
        try {
            return Files.list(directory)
                    .map(this::getFromStorage)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Error path read", null, e);
        }
//            return resumes;
    }
}

