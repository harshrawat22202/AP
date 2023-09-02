package org.example;
public class Member {
    long due=0;
    pair[] issued={null,null};
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
        return "Name : "+this.Name+"\n"+"Phone No : "+this.phonenumber+"\n"
                +"Books Issued : \n"+issued[0]+"\n"+issued[1]
                +"\n"+"Age : "+this.age+"\n"+"dues : "+this.due;
    }

    public void viewBooks(){
        System.out.println("------");
        System.out.println("issued books : ");
        System.out.println(issued[0]);
        System.out.println(issued[1]);
        System.out.println("------");
    }
}
