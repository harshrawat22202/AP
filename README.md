# AP Assignment-1(Used 2 accounts my personal github account and one made with college mail ID)
## About code
This code uses HashMap<String,Book> and HashMap<String,Member> to store Books and Member respectively.
Book , Library , Librarian and Member class is made in this code.
## Library 
Library is used to call methods in librarian and meber class and store data about books and members.
Each Library can only have one member.
## Librairan
Libraian is used to add books,members,delete books,delete members for calculation of fine , issue book, colleact books , collect fine and to show available books.
Therefore this is the most important class in this code .
## Member
Member class's methods are generally used to call methods inside librairan.
## Use as Librarian
When Main.java is run
first user is asked to do this if 1 is not pressed
program will terminate
```
wish to continue ? press 1
```
then this menu appears .
```
------
To : enter as Member = 1
To : enter as librarian = 2
To : exit = 3
------
```
To perform any operation first we have to add 
members and books **after entering as librarian** .
```
----------------------------
To : addBook = 1
To : addMember = 2
To : calculate fine of each Member = 3
To : calculate individual members fine = 4
To : List books = 5
To : List Members = 6
To : Delete Book = 7
To : Remove Member = 8
----------------------------
```
Note = whenever fine is calculated there is a rounding error in calculation as System.currenttimemillis() returns long data time
and when we calculate fine ((curent_time-issue_time)/1000)*3 it returns a long data type and not float type
We can add books or members first
if we add book we have to write name of book , name of author and number of copies 
**specifically in this order line by line** .
```
enter name author and copies
Name of Book
Name of Author
3
Book added
```
If we add members we have to add Name,Phone number(**should be unique**) and age
**line by line in this order** .
```
enter name , phone number & age
Member Name
123
25
```
calculate fine of each member calculates dues of each member
of library it does not print anything but the dues of each
member can be seen from List Members.

Same with calculate individual members fine, but you have 
to enter member ID (which is phone number).

If one is trying to remove a book it will not be removed
if it is not returned by the member who issued it and if a member has borrowed a
book or has to pay fine it will not be removed until fine is not paid.

## Use as Member

We can enter as Member once Member name and phone number is added
by librarian.

if any other option which is not mentioned is enter it will show 
invalid option .
```
------
To : enter as Member = 1
To : enter as librarian = 2
To : exit = 3
------
1
enter name and phone no.
Member Name
123
wish to continue ? press 1
1
----------------------------
To : View profile = 1
To : View issued books = 2
To : Issue book = 3
To : Return Book = 4
To : Pay Fine = 5
To : Show available books = 6
To : See all books = 7
----------------------------
```

One can issue at most 2 books , but if fine is due 
first fine is to be paid else it no further book will
be issued.

Whenever it asked 
```
wish to continue ? press 1
```
press 1 to continue else the while loop will terminate.
