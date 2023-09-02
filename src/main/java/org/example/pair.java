package org.example;
public class pair{
    Book book;
    long time;
    public pair(Book book,long time){
        this.book=book;
        this.time=time;
    }

    public  String toString(){
        return "Details of books : "+this.book.toString()+"\n"
                +"Time of Issue : "+ this.time;
    }
}
