package org.example;

import java.util.*;

public class Librarian {
    String Name;
    String ID;
    public Librarian(String Name){
        this.Name=Name;
        this.ID=Integer.toString(hashCode());
    }

    public Librarian(String Name,String ID){
        this.Name=Name;
        this.ID=ID;
    }

    public Book findBook(HashMap<String,Book> shelf,Book book){
        return shelf.get(book.BookID);
    }

    public void addMember(HashMap<String,Member> MembersRecord,Member m){
        if (!MembersRecord.containsKey(m.phonenumber))
            MembersRecord.put(m.phonenumber,m);
        else System.out.println("member with same phone number already exists");
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
            shelf.remove(BookID);
            System.out.println("successfully removed");
        }else{
            System.out.println("book does not exist");
        }
    }

    public void deleteMember(HashMap<String,Member> t,String Ph){
        if (t.containsKey(Ph)){
            t.remove(Ph);
            System.out.println("deleted successfully");
        }else{
            System.out.println("member does not exist");
        }
    }
    public void addQty(HashMap<String,Book> shelf){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter BookID and number by which it is to be incremented");
        String BookID=sc.next();
        int new_qty=sc.nextInt();
        sc.close();
        if (new_qty<0){
            System.out.println("invalid number");
        }else if (!shelf.containsKey(BookID)){
            System.out.println("this Book does not exist");
        }else{
            Book i=shelf.get(BookID);
            i.setCopies_available(i.getCopies_available()+new_qty);
            i.setTotal_copies(i.getTotal_copies()+new_qty);
        }
    }

    public void decreaseQty(HashMap<String,Book> shelf){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter BookID and number by which it is to be decremented");
        String BookID=sc.next();
        int new_qty=sc.nextInt();
        sc.close();
        if (!shelf.containsKey(BookID)){
            System.out.println("this Book does not exist");
        }else{
            Book b=shelf.get(BookID);
            if (b.getCopies_available()>new_qty) {
                b.setCopies_available(b.getCopies_available() - new_qty);
            }else{
                b.setCopies_available(0);
                if (b.getTotal_copies()<new_qty){
                    b.setTotal_copies(0);
                }else{
                    b.setTotal_copies(b.getTotal_copies()-new_qty);
                }
            }
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
    public void issueBook(HashMap<String,Book> shelf,HashMap<String,Member> MemberRecord,String Ph){
        Member m=MemberRecord.get(Ph);
        if (m==null){
            System.out.println("member doesn't exist");
            return ;
        }
        Scanner sc=new Scanner(System.in);
        System.out.println("enter BookID");
        Book b=shelf.get(sc.next());
        if (b==null){
            System.out.println("book doesn't exist");
            return ;
        }//add code to check due and number of book issued
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
        int qty = sc.nextInt();
        sc.nextLine();
        boolean found=false;
        Book b=new Book(name_of_book,name_of_author, qty);
        for (String ID: shelf.keySet()){
            if (shelf.get(ID).getAuthor().compareTo(name_of_author)==0
                    && shelf.get(ID).getName().compareTo(name_of_book)==0)
                found=!found;
        }
        if (!found) {
            shelf.put(b.BookID, b);
        }
        else{
            System.out.println("this book already exists so adding quantity of the existing books");
            int x=this.findBook(shelf,b.getName(),b.getAuthor()).getTotal_copies();
            int y=this.findBook(shelf,b.getName(),b.getAuthor()).getCopies_available();
            this.findBook(shelf,b.getName(),b.getAuthor()).setTotal_copies(x+b.getTotal_copies());
            this.findBook(shelf,b.getName(),b.getAuthor()).setCopies_available(y+b.getTotal_copies());
        }
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

    public void collectFine(Member M,int money){
    }

    public void collectBook(Member M,TreeSet<Book> shelf,String BookID){
    }
}