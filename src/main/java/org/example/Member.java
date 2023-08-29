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
}
