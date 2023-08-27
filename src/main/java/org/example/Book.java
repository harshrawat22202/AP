package org.example;
import  java.util.Comparator;

public class Book {

    String Name;
    private int copies_available;
    private int total_copies;
    String Author;
    String BookID;
    public Book(String Name,String Author,int copies){
        this.Author=Author;
        this.Name=Name;
        this.copies_available=this.total_copies=copies;
        this.BookID=Integer.toString(this.hashCode());
    }

//    public static Book deleteBook(String BookID){}
    public int getCopies_available(){
        return this.copies_available;
    }

    public String getAuthor() {
        return this.Author;
    }

    public int getTotal_copies() {
        return this.total_copies;
    }
    public void setTotal_copies(int c){
        this.total_copies=c;
    }
    public void setCopies_available(int copies_available) {
        this.copies_available = copies_available;
    }
}