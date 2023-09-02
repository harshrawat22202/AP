package org.example;

import java.util.*;

public class Librarian {
    String Name;
    String ID;
    public Librarian(String Name){
        this.Name=Name;
        this.ID=Integer.toString(hashCode());
    }

    public boolean isMember(HashMap<String,Member> t,String Ph){
        return t.containsKey(Ph);
    }

    public Member findMember(HashMap<String,Member> T,String Ph){
        return T.get(Ph);
    }
    public Book findBook(HashMap<String,Book> shelf,String ID){
        return shelf.get(ID);
    }

    public Book findBook(HashMap<String,Book> shelf,String Name,String Author){
        for (String ID: shelf.keySet()){
            if (shelf.get(ID).getName().compareTo(Name)==0 && shelf.get(ID).getAuthor().compareTo(Author)==0){
                return shelf.get(ID);
            }
        }
        return null;
    }
    public void deleteBook(HashMap<String,Book> shelf,String BookID){
        if (shelf.containsKey(BookID)){
            Book b=shelf.get(BookID);
            if (b.issued){
                System.out.println("Get book back from member first");
                return ;
            }
            shelf.remove(BookID);
            System.out.println("successfully removed");
        }else{
            System.out.println("book does not exist");
        }
    }

    public void deleteMember(HashMap<String,Member> t,String Ph){
        Scanner sc=new Scanner(System.in);
        if (t.containsKey(Ph)){
            Member m=t.get(Ph);
            if (m.issued[0]!=null || m.issued[1]!=null){
                System.out.println("con not remove member collect books first");
                return;
            }
            if (m.due>0){
                System.out.println("cannot remove member collect dues first");
                return ;
            }
            t.remove(Ph);
            System.out.println("deleted successfully");
        }else{
            System.out.println("member does not exist");
        }
    }
    public void seeBooks(HashMap<String,Book> shelf){
        for (String i:shelf.keySet())
            System.out.println(shelf.get(i));
    }

    public void seeMember(HashMap<String,Member> T) {
        calculateFineOfEachMember(T);
        for (String i : T.keySet())
            System.out.println(T.get(i));
    }
    public void issueBook(HashMap<String,Book> shelf,Member m){
        if (m.due>0){
            System.out.println("Pay dues first !");
            return ;
        }
        Scanner sc=new Scanner(System.in);
        System.out.println("enter BookID");
        Book b=shelf.get(sc.nextLine());
        if (b==null){
            System.out.println("book doesn't exist");
        }else if (b.issued){
            System.out.println("Book not available currently");
        }else{
            if (m.issued[0]==null){
                b.issued=true;
                m.issued[0]=new pair(b,System.currentTimeMillis());
            }else if (m.issued[1]==null){
                b.issued=true;
                m.issued[1]=new pair(b,System.currentTimeMillis());
            }else {
                System.out.println("Return existing books first");
            }
        }
    }

    public void addMember(HashMap<String,Member> MembersRecord){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter name , phone number & age");
        String name=sc.nextLine();
        String ph= sc.nextLine();
        if (MembersRecord.containsKey(ph)){
            System.out.println("Member already exists");
            return ;
        }
        int age=sc.nextInt();
        sc.nextLine();
        Member m=new Member(name,ph,age);
        MembersRecord.put(ph,m);
    }
    public void addBook(HashMap<String,Book> shelf){
        Scanner sc = new Scanner(System.in);
        System.out.println("enter name author and copies");
        String name_of_book = sc.nextLine();
        String name_of_author = sc.nextLine();
        int qty= sc.nextInt();
        if (qty==0){
            System.out.println("invalid");
            return ;
        }
        sc.nextLine();
        while(qty>0){
            Book b=new Book(name_of_book,name_of_author);
            shelf.put(b.BookID,b);
            qty--;
        }
        System.out.println("Book added");
    }

    public void calculateIndividualMembersFine(HashMap<String,Member> MemberRecord,String MemberID){
        if (!MemberRecord.containsKey(MemberID)){
            System.out.println("invalid id");
        }else{
            long time0=0;
            long time1=0;
            Member m=MemberRecord.get(MemberID);
            if (m.issued[0]!=null)
                time0=m.issued[0].time;
            if (m.issued[1]!=null)
                time1=m.issued[1].time;
            if (time0>0){
                if (System.currentTimeMillis()-time0>10000){
                    m.due=+3*(System.currentTimeMillis()-time0)/1000;
                }
            }
            if (time1>0){
                if (System.currentTimeMillis()-time1>10000){
                    m.due=+3*(System.currentTimeMillis()-time1)/1000;
                }
            }
        }
    }
    public void calculateFineOfEachMember(HashMap<String,Member> MemberRecord){
        for (String ID:MemberRecord.keySet()){
            calculateIndividualMembersFine(MemberRecord,ID);
        }
    }

    public void collectFine(Member M){
        System.out.println("enter amount");
        Scanner sc=new Scanner(System.in);
        long money=sc.nextLong();
        sc.nextLine();
        if (M.due==0){
            System.out.println("You do not have any dues");
        }else if (M.due<money){
            System.out.println("money more than your dues");
        }else{
            M.due=M.due-money;
            System.out.println("dues paid successfully");
        }
    }

    public void collectBook(Member M,HashMap<String,Book> shelf){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter BookID");
        String ID=sc.nextLine();
        if (!shelf.containsKey(ID)){
            System.out.println("This book doesn't exist");
            return;
        }
        Book b=shelf.get(ID);
        boolean has=false;
        int index=-1;
        if (M.issued[0]!=null){
            if (M.issued[0].book.BookID.compareTo(b.BookID)==0){
                index=0;
                has=true;
            }
        }
        if (M.issued[1]!=null) {
            if (M.issued[1].book.BookID.compareTo(b.BookID)==0){
                index=1;
                has=true;
            }
        }
        if (!has){
            System.out.println("this book was not issued to you");
        }else{
            b.issued=false;
            long t=M.issued[index].time;
            if (System.currentTimeMillis()-t<=10000){
                M.issued[index]=null;
                System.out.println("Return time : "+System.currentTimeMillis()+"\nIssued time : "+t);
                System.out.println("Returned successfully");
            }else{
                System.out.println("Return time : "+System.currentTimeMillis()+"\nIssue time : "+t);
                M.due=+((System.currentTimeMillis()-M.issued[index].time-10000)/1000)*3;
                b.issued=false;
                M.issued[index]=null;
                System.out.println("Book returned successfully total fine of "+M.due+" is due");
            }
        }

    }

    public void showAvailableBooks(HashMap<String,Book> shelf){
        for (String I : shelf.keySet()){
            if (!shelf.get(I).issued){
                System.out.println(shelf.get(I));
            }
        }
    }
}