package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input");
        Library Lib=new Library();
        Scanner sc=new Scanner(System.in);
        int c=1;
        while (true){
            int choice;
            System.out.println("wish to continue ? press 1");
            c= sc.nextInt();
            sc.nextLine();
            System.out.println("enter choice");
            choice=sc.nextInt();
            sc.nextLine();
            if (c!=1)
                break;
            if (choice==2)
                Lib.enterAsLibrarian();
            else if (choice==1)
                Lib.enterAsMember(Lib.loginMember());
            else
                System.out.println("invalid choice re-enter");
        }
    }
}