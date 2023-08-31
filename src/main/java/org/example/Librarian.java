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
    public void addBooks(HashMap<String,Book> shelf,Book book){
        shelf.put(book.BookID,book);
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
        //calculateFineOfEachMember(T);//change this function gives invalid result
        for (String i : T.keySet())
            System.out.println(T.get(i));
    }
    public void issueBook(TreeSet<Book> shelf,TreeSet<Member> M,String Ph){
    }

    public void addMember(HashMap<String,Member> MembersRecord){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("enter name phone no. and age");
            this.addMember(MembersRecord,new Member(sc.next(), sc.next(), sc.nextInt()));
        } catch (IllegalStateException e) {
            System.out.println("error");
        } catch (NoSuchElementException e) {
            System.out.println("invalid input");
        }
    }
    public void addBook(HashMap<String,Book> shelf){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("enter name author and copies");
            Book b=new Book(sc.next(), sc.next(), sc.nextInt());
            if (this.findBook(shelf,b.BookID).Author.compareTo(b.Author)!=0 ||
                    this.findBook(shelf,b.BookID).Name.compareTo(b.Name)!=0)
                this.addBooks(shelf,b);
            else{
                System.out.println("this book already exists so adding quantity of the existing books");
                int x=this.findBook(shelf,b.BookID).getTotal_copies();
                int y=this.findBook(shelf,b.BookID).getCopies_available();
                this.findBook(shelf,b.BookID).setTotal_copies(x+b.getTotal_copies());
                this.findBook(shelf,b.BookID).setCopies_available(y+b.getTotal_copies());
            }
        } catch (IllegalStateException e) {
            System.out.println("error");
        } catch (NoSuchElementException e) {
            System.out.println("invalid input");
        }
    }

    public void calculateIndividualMembersFine(TreeSet<Member> MemberRecord,String MemberID){
    }
    public void calculateFineOfEachMember(TreeSet<Member> MemberList){

    }

    public void collectFine(Member M,int money){
    }

    public void collectBook(Member M,TreeSet<Book> shelf,String BookID){
    }
}