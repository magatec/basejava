package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {

        super("ERROR: Резюме uuid = " + uuid + " уже есть в хранилище.", uuid);
    }
}
