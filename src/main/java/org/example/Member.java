package org.example;
import java.util.ArrayList;
public class Member {
    int due=0;
    ArrayList <Book> issued=new ArrayList<>();
    String phonenumber;
    String Name;
    int age;
    public  Member(String Name,String phonenumber,int age) {
        this.age = age;
        this.Name = Name;
        this.phonenumber = phonenumber;
    }

    @Override
    public String toString() {
        return "Name : "+this.Name+"\n"+"Phone No : "+this.phonenumber+"\n"+"Books Issued : "+issued.toString()
                +"\n"+"Age : "+this.age+"\n"+"dues : "+this.due;
    }

    public void viewBooks(){
        System.out.println("------");
        System.out.println("issued books : ");
        for (Book k:this.issued)
            System.out.println(k);
        System.out.println("------");
    }
    public void payFine(int money,Librarian l){
        l.collectFine(this,money);
    }

    public void returnBook(Library Lib,Librarian L,String BookID){
        L.collectBook(this,Lib.getShelf(),BookID);
    }
}
