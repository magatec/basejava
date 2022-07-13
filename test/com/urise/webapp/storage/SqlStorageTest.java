package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.sql.ConnectionFactory;

public class SqlStorageTest extends AbstractStorageTest{

    public SqlStorageTest() {super(Config.get().getStorage());}
}
