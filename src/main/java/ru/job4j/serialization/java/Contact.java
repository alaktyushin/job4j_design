package ru.job4j.serialization.java;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {

    @Serial
    private static final long serialVersionUID = 28L;

    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        /* Запись объекта во временный файл, который удалится системой */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
             ObjectOutputStream oos =
                     new ObjectOutputStream(fos)) {
            oos.writeLong(serialVersionUID);
            oos.writeObject(contact);
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois =
                     new ObjectInputStream(fis)) {
            final long version = ois.readLong();
            if (version == serialVersionUID) {
                System.out.println("Everything is OK, version matches: " + version);
                final Contact contactFromFile = (Contact) ois.readObject();
                System.out.println(contactFromFile);
            } else {
                System.out.println("Version doesn't match. Abort reading data.");
            }
        }
    }
}
