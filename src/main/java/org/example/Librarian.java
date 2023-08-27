package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.time.LocalTime;

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
        System.out.println(shelf);
    }

    public void seeMember(TreeSet<Member> T){
        System.out.println(T);
    }

    public void issueBook(TreeSet<Book> shelf,TreeSet<Member> M,String Ph){
        Scanner sc=new Scanner(System.in);
        if (!isMember(M,Ph)){
            System.out.println("Not a member");
            return;
        }
        Member m=findMember(M,Ph);
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
        b.setTotal_copies(b.getCopies_available()-1);
        b.setIssueTime(System.currentTimeMillis());
        m.issued.add(b);
    }
}
