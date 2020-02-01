package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] newAuthors = authors;
        authors = new Author[newAuthors.length + 1];
        for (int i = 0; i < newAuthors.length; i++) {
            authors[i] = newAuthors[i];
        }
        authors[newAuthors.length] = author;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (int i = 0; i < count(); i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastname)) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
        }
        Author[] newAuthors = authors;
        authors = new Author[count() - 1];
        for (int i = 0; i < count(); i++) {
            if ((author.getName() + author.getLastName()).equals(newAuthors[i].getName() + newAuthors[i].getLastName())) {
                continue;
            } else {
                authors[i] = newAuthors[i];
            }
        }
        return true;




    }

    @Override
    public int count() {
        return authors.length;
    }
}
