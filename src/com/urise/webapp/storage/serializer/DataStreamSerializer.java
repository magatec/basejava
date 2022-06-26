package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SaveStorageStrategy {

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, EelmentWriter<T> write) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            write.write(t);
        }
    }

    private interface EelmentWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType type = entry.getKey();
                AbstractSection section = entry.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) section).getContent());
                    case ACHIEVEMENT, QUALIFICATIONS ->
                            writeCollection(dos, ((ListSection) section).getItems(), dos::writeUTF);
                    case EXPERIENCE, EDUCATION ->
                            writeCollection(dos, ((OrganizationSection) section).getList(), organization -> {
                                dos.writeUTF(organization.getTitle());
                                dos.writeUTF(organization.getUrl());
                                writeCollection(dos, organization.getPeriods(), period -> {
                                    writeLocalDate(dos, period.getStart());
                                    writeLocalDate(dos, period.getEnd());
                                    dos.writeUTF(period.getTitle());
                                    dos.writeUTF(period.getDescription());
                                });
                            });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSections(sectionType, readSection(dis, sectionType));
            });

            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(
                    readList(dis, () -> new Organization(
                            dis.readUTF(), dis.readUTF(),
                            readList(dis, () -> new Period(
                                    readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()))
                    )));
        };

    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
}
