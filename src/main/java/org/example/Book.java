package org.example;
public class Book {
    static int number=0;
    String Name;
    String Author;
    String BookID;
    boolean issued=false;
    public Book(String Name,String Author){
        this.Author=Author;
        this.Name=Name;
        this.BookID=Integer.toString(number++);
    }


    public String getAuthor() {
        return this.Author;
    }

    public String getName(){ return this.Name; }
    @Override
    public String toString() {
        return "BookID : "+this.BookID+"\n"
                +"Book name : "+this.Name+"\n"
                +"Author's name : "+this.Author+"\n"
                +"Issued : " + issued;
    }
}