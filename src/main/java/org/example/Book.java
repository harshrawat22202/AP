package org.example;
public class Book {
    long issued=-1;//need to change it can give wrong result get it to member along with book
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
    public void setIssueTime(long time){
        this.issued=time;
    }

    @Override
    public String toString() {
        String s=(this.issued!=-1)?"not issued":"issued";
        return "Book name : "+this.Name+"\n"
                +"Author's name :"+this.Author+"\n"
                +s+"Available copies"+"\n"
                +this.copies_available+"\n"
                +"Total copies"+this.total_copies;
    }
}