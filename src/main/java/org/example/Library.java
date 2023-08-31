package org.example;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

public class Library {
    Librarian librarian;
    private HashMap<String,Member> MembersRecord;
    private HashMap<String,Book> shelf;
    public  Library(String  Name){
        this.librarian=new Librarian (Name);
        this.shelf=new HashMap<>();
    }
    public Library(){//need to change
        this.librarian=new Librarian("ABC");
    }//change

    public HashMap<String,Book> getShelf(){
        return this.shelf;
    }

    public void enterAsMember(){}

    public void enterAsLibrarian(){}
}
