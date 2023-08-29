package org.example;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

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
    public void addBooks(TreeSet<Book> shelf,Book ...book){
        shelf.addAll(Arrays.asList(book));
    }

    public boolean findBook(TreeSet<Book> shelf,Book book){
        return shelf.contains(book);
    }

    public void addMember(TreeSet<Member> MembersRecord,Member ...m){
        MembersRecord.addAll(Arrays.asList(m));
    }

    public boolean isMember(TreeSet<Member> t,String Ph){
        for (Member m:t){
            if (m.phonenumber.compareTo(Ph)==0){
                return true;
            }
        }
        return false;
    }

    public boolean isMember(TreeSet<Member> t,Member m){
        return t.contains(m);
    }

    public Member findMember(TreeSet<Member> T,String Ph){
        for (Member i:T){
            if (i.phonenumber.compareTo(Ph)==0){
                return i;
            }
        }
        return new Member("None","None",-1);
    }
    public Book findBook(TreeSet<Book> shelf,String ID){
        for (Book i:shelf){
            if (i.BookID.compareTo(ID)==0){
                return i;
            }
        }
        return new Book("None","None",-1);
    }

    public void deleteBook(TreeSet<Book> shelf,String BookID){
        for (Book i:shelf){
            if (i.BookID.compareTo(BookID)==0){
                shelf.remove(i);
            }
        }
    }

    public void deleteMember(TreeSet<Member> t,String Ph){
        for (Member m:t){
            if (m.phonenumber.compareTo(Ph)==0){
                t.remove(m);
            }
        }
    }
    public void changeQty(TreeSet<Book> shelf,String BookID,int new_num){
        for (Book i:shelf){
            if (i.BookID.compareTo(BookID)==0){
                if (i.getTotal_copies()<new_num){
                    i.setCopies_available(new_num-i.getTotal_copies());
                    i.setTotal_copies(new_num);
                }else{
                    if (i.getCopies_available()<=i.getTotal_copies()-new_num){
                        i.setCopies_available(0);
                    }else{
                        i.setCopies_available(i.getTotal_copies()-new_num);
                    }
                }
            }
        }
    }

    public void seeBooks(TreeSet<Book> shelf){
        for (Book b:shelf)
            System.out.println(b);
    }

    public void seeMember(TreeSet<Member> T){
        calculateFineOfEachMember(T);
        for (Member i:T)
            System.out.println(i);
    }

    public void issueBook(TreeSet<Book> shelf,TreeSet<Member> M,String Ph){
        Scanner sc=new Scanner(System.in);
        if (!isMember(M,Ph)){
            System.out.println("Not a member");
            return;
        }
        Member m=findMember(M,Ph);
        calculateIndividualMembersFine(M,m.phonenumber);
        if (m.due!=0){
            System.out.println("Pay dues first !");
            return ;
        }else if(m.issued.size()>2){
            System.out.println("Return books issued previously");
            return ;
        }
        System.out.println(shelf);
        String bookID=sc.next();
        Book b=findBook(shelf,bookID);
        if (b.getCopies_available()>0 && !m.issued.contains(b)){
            b.setTotal_copies(b.getCopies_available()-1);
            b.setIssueTime(System.currentTimeMillis());
            m.issued.add(b);
        }else if (m.issued.contains(b)){
            System.out.println("you already have one copy");
        }else{
            System.out.println("Not available now");
        }
    }

    public void addMember(TreeSet<Member> MembersRecord){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("enter name phone no. and age");
            this.addMember(MembersRecord,new Member(sc.next(), sc.next(), sc.nextInt()));
        } catch (IllegalStateException e) {
            System.out.println("error");
        } catch (NoSuchElementException e) {
            System.out.println("invalid input");
        }
    }
    public void addBook(TreeSet<Book> shelf){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("enter name author and copies");
            Book b=new Book(sc.next(), sc.next(), sc.nextInt());
            if (this.findBook(shelf,b.BookID).Author.compareTo(b.Author)!=0 ||
                    this.findBook(shelf,b.BookID).Name.compareTo(b.Name)!=0)
                this.addBooks(shelf,b);
            else{
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
        Member m=findMember(MemberRecord,MemberID);
        if (!m.issued.isEmpty()){
            for (Book b: m.issued){
                long time=(b.issued-System.currentTimeMillis())/1000;
                if (time>10){
                    m.due=Integer.parseInt(String.valueOf(time*3));
                }
            }
        }
    }
    public void calculateFineOfEachMember(TreeSet<Member> MemberList){
        for (Member m:MemberList){
            if (!m.issued.isEmpty()){
                for (Book b: m.issued){
                    long time=b.issued-System.currentTimeMillis()/1000;
                    if (time>10){
                        m.due=Integer.parseInt(String.valueOf(time*3));
                    }
                }
            }
        }
    }

    public void collectFine(Member M,int money){
        if (money<=0){
            System.out.println("error");
        }else if(money>M.due){
            System.out.println("money cannot be more than dues !");
        }else{
            M.due=M.due-money;
            System.out.println("paid successfully");
        }
    }

    public void collectBook(Member M,TreeSet<Book> shelf,String BookID){
        if (!M.issued.contains(this.findBook(shelf,BookID))){
            System.out.println("error specified book was not issued to you");
        }else{
            M.issued.remove(findBook(shelf,BookID));
            findBook(shelf,BookID).setCopies_available(findBook(shelf,BookID).getCopies_available()+1);
        }
    }
}