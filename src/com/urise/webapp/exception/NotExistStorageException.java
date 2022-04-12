package com.urise.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {

        super("ERROR: Резюме uuid = " + uuid + " нет в хранилище.", uuid);
    }
}
