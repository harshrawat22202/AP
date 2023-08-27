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
}
