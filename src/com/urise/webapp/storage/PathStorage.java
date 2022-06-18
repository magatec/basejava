package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.SaveStorageStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;
    private final SaveStorageStrategy saveStorageStrategy;

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
        return directory.resolve(uuid);
    }

    @Override
    protected void updateStorage(Resume r, Path path) {
        try {
            saveStorageStrategy.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Error update path", path.toString(), e);
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
        return getPathList().toArray().length;
    }

    @Override
    public void clear() {
        getPathList().forEach(this::deleteFormStorage);
    }

    public void saveToStorage(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Error write path", path.toString(), e);
        }
        updateStorage(r, path);
    }

    public List<Resume> getListToAllSorted() {
        return getPathList()
                .map(this::getFromStorage)
                .collect(Collectors.toList());
    }

    private Stream<Path> getPathList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Error read path", directory.toString(), e);
        }
    }
}

