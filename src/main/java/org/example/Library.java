package org.example;

import java.util.HashMap;
import java.util.Scanner;

public class Library {
    Librarian librarian;
    private HashMap<String,Member> MembersRecord;
    private HashMap<String,Book> shelf;
    public  Library(String  Name){
        this.librarian=new Librarian (Name);
        this.shelf=new HashMap<>();
        this.MembersRecord=new HashMap<>();
    }
    public Library(){
        this.shelf=new HashMap<>();
        this.MembersRecord=new HashMap<>();
        this.librarian=new Librarian("ABC");
    }
    public HashMap<String,Book> getShelf(){
        return this.shelf;
    }

    public Member loginMember(){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter name and phone no.");
        String name= sc.nextLine();
        String Ph= sc.nextLine();
        Member m=MembersRecord.get(Ph);
        if (m==null){
            System.out.println("invalid");
        }else if (m.Name.compareTo(name)!=0){
            System.out.println("invalid name");
        }else{
            return m;
        }
        return null;
    }
    public void enterAsMember(Member M){
        if (M==null){
            System.out.println("This member does not exist");
            return ;
        }
        Scanner sc=new Scanner(System.in);
        int c=0;
        do{
            System.out.println("wish to continue ? press 1");
            c=sc.nextInt();
            sc.nextLine();
            if (c!=1){
                break;
            }
            System.out.println("----------------------------");
            System.out.print("To : View profile = 1"+"\n"
                    +"To : View issued books = 2"+"\n"
                    +"To : Issue book = 3"+"\n"
                    +"To : Return Book = 4"+"\n"
                    +"To : Pay Fine = 5"+"\n"
                    +"To : Show available books = 6"+"\n"
                    +"To : See all books = 7"+"\n");
            System.out.println("----------------------------");
            System.out.println("enter choice");
            int c1=sc.nextInt();
            if (c1==1){
                librarian.calculateIndividualMembersFine(MembersRecord,M.phonenumber);
                System.out.println(M);
            }else if (c1==2){
                M.viewBooks();
            }else if (c1==3){
                System.out.println("Here is list of available books");
                System.out.println("----------------");
                librarian.showAvailableBooks(shelf);
                System.out.println("----------------");
                librarian.calculateIndividualMembersFine(MembersRecord,M.phonenumber);
                librarian.issueBook(shelf,M);
            }else if (c1==4){
                librarian.collectBook(M,shelf);
                System.out.println("Now here is list of books not returned by you");
                System.out.println("---------");
                M.viewBooks();
                System.out.println("---------");
            }else if (c1==5){
                librarian.collectFine(M);
            }else if (c1==6){
                System.out.println("------------------");
                librarian.showAvailableBooks(shelf);
                System.out.println("------------------");
            }else if (c1==7){
                System.out.println("-------");
                librarian.seeBooks(shelf);
                System.out.println("-------");
            }else{
                System.out.println("invalid number");
            }
        }while(c==1);
    }
    public void enterAsLibrarian() {
        Scanner sc=new Scanner(System.in);
        int c=0;
        do {
            System.out.println("wish to continue ? press 1");
            c=sc.nextInt();
            sc.nextLine();
            if (c!=1){
                break;
            }
            System.out.println("----------------------------");
            System.out.print("To : addBook = 1"+"\n"
                    +"To : addMember = 2"+"\n"
                    +"To : calculate fine of each Member = 3"+"\n"
                    +"To : calculate individual members fine = 4"+"\n"
                    +"To : List books = 5"+"\n"
                    +"To : List Members = 6"+"\n");
            System.out.println("----------------------------");
            System.out.println("enter choice");
            int c1=sc.nextInt();
            sc.nextLine();
            if (c1==1)
                librarian.addBook(shelf);
            else if (c1==2)
                librarian.addMember(MembersRecord);
            else if (c1==3)
                librarian.calculateFineOfEachMember(MembersRecord);
            else if (c1==4) {
                System.out.println("enter ID of member");
                String ID= sc.nextLine();
                librarian.calculateIndividualMembersFine(MembersRecord,ID);
            }else if (c1==5){
                librarian.seeBooks(shelf);
            }else if (c1==6){
                librarian.calculateFineOfEachMember(MembersRecord);
                librarian.seeMember(MembersRecord);
            }else{
                System.out.println("invalid number");
            }
        } while (c==1);
    }
}