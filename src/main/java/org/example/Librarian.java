package org.example;

import java.util.Arrays;
import java.util.TreeSet;

public class Librarian {
    String Name;
    String ID;
    public Librarian(String Name){
        this.Name=Name;
        this.ID=Integer.toString(hashCode());
    }

    public void addBooks(TreeSet<Book> shelf,Book ...book){
        shelf.addAll(Arrays.asList(book));
    }

    public boolean findBook(TreeSet<Book> shelf,Book book){
        return shelf.contains(book);
    }

    public boolean findBook(TreeSet<Book> shelf,String ID){
        for (Book i:shelf){
            if (i.BookID.compareTo(ID)==0){
                return true;
            }
        }
        return false;
    }

    public void deleteBook(TreeSet<Book> shelf,String BookID){
        for (Book i:shelf){
            if (i.BookID.compareTo(BookID)==0){
                shelf.remove(i);
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
}
