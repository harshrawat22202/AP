package org.example;

import java.util.TreeSet;

public class Library {
    Librarian librarian;
    private TreeSet<Book> shelf;
    public  Library(String  Name){
        this.librarian=new Librarian (Name);
        this.shelf=new TreeSet<>((book1,book2)->{return Integer.compare(book1.BookID.compareTo(book2.BookID),0);});
    }
    public Library(){//need to change
        this.librarian=new Librarian("ABC");
    }
}
