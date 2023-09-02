package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library Lib=new Library();
        Scanner sc=new Scanner(System.in);
        int c=1;
        while (true){
            System.out.println("wish to continue ? press 1");
            c= sc.nextInt();
            sc.nextLine();
            if (c!=1)
                break;
            int choice;
            System.out.print("------"+"\n"+"To : enter as Member = 1"+"\n"+"To : enter as librarian = 2"+"\n"+"To : exit = 3"+"\n"+"------"+"\n");
            choice=sc.nextInt();
            sc.nextLine();
            if (choice==2)
                Lib.enterAsLibrarian();
            else if (choice==1)
                Lib.enterAsMember(Lib.loginMember());
            else if (choice==3)
                break;
            else
                System.out.println("invalid choice re-enter");
        }
    }
}