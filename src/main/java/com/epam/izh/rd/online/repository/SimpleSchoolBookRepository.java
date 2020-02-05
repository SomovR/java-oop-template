package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBooks = schoolBooks;
        schoolBooks = new SchoolBook[count() + 1];
        System.arraycopy(newSchoolBooks, 0, schoolBooks, 0, newSchoolBooks.length);
        schoolBooks[newSchoolBooks.length] = book;
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int counter = 0;
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName().equals(name)) {
                counter++;
            }
        }
        SchoolBook[] newSchoolBooks = new SchoolBook[counter];
        for (int i = 0, j = 0; i < count(); i++) {
            if (schoolBooks[i].getName().equals(name)) {
                newSchoolBooks[j] = schoolBooks[i];
                j++;
            }
        }
        return newSchoolBooks;
    }

    @Override
    public boolean removeByName(String name) {
        int counter = 0;
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName().equals(name)) {
                counter++;
            }
        }
        schoolBooks = new SchoolBook[count() - counter];
        for (int i = 0, j = 0; i < count(); i++) {
            if (!(schoolBooks[i].getName().equals(name))) {
                schoolBooks[j] = schoolBooks[i];
                j++;
            }
        }
        return true;

    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
