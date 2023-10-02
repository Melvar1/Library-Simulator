public class Library { // Library Class
    Book[] books; //declared an array called books in the Book Class
    int bookCapacity; 
    double lateFeePerDay;
    double totalLateFeesCollected;
    int bookCount;
    Person[] customers;

    public Library(int capacity, double lateFee) { //constructor with 2 parameters
        books = new Book[capacity]; //initializing books array to size of capacity
        bookCapacity = capacity;
        lateFeePerDay = lateFee;
        totalLateFeesCollected = 0.0; // set late fees collected to 0
        bookCount = 0;
        customers = new Person[10];
    }

    public class Book { // Class called Book
        String title;
        String author;
        String ISBN;
        boolean checkedOut;
        int daysCheckedOut;

        public Book(String title, String author, String ISBN) { //Book Class constructor with 3 parameters
            this.title = title; 
            this.author = author;
            this.ISBN = ISBN;
            checkedOut = false;
            daysCheckedOut = 0;
        }

        // Getters and setters for Book class
        public String getTitle() { //gets book title
            return title;
        }

        public String getAuthor() { //gets author's name
            return author;
        }

        public String getISBN() { //gets ISBN
            return ISBN;
        }

        public boolean isCheckedOut() { //sets isCheckedOut to true or false
            return checkedOut;
        }

        public int getDaysCheckedOut() { //gets how many days book has been checked out
            return daysCheckedOut;
        }

        public void checkOut() { // checkOut method that checks our a book
            checkedOut = true; // sets checkedOut to true
            daysCheckedOut = 0; 
        }

        public void checkIn() { // checks in books
            checkedOut = false;
            daysCheckedOut = 0;
        }
    }
    public class Person { // Person class
        private String name; //name of person

        public Person(String name) { // Constructor of person
            this.name = name;
        }

        public String getName() { //returns name of person
            return name;
        }
    }
    public void addBook(String title, String author, String ISBN) { //addBook method that takes 3 parameters 
        if (bookCount < bookCapacity) { //if book count is less than book capacity, then add new book
            Book newBook = new Book(title, author, ISBN); // creates a new book of the Book class
            books[bookCount] = newBook; // assigns a reference to a newly created Book object (newBook) to a specific element in the books array.
            bookCount++; // increments book count by 1
        } else {
            System.out.println("The library is at full capacity."); // if book capacity is full user is notified
        }
    }

    public boolean checkoutBook(String ISBN, Person customer) { //Library method that takes ISBN as parameter and checks out book
        for (int i = 0; i < bookCount; i++) { //for loop that iterate through books array
            if (books[i].getISBN().equals(ISBN) && !books[i].isCheckedOut()) { //checks if current book is not checked out
                books[i].checkOut(); //checks out if coniditon is true
                System.out.println(customer.getName() + " has checked out the book: " + books[i].getTitle());
                return true; //returns true if book was successfully checked out
            }
        }
        return false; //returns false if book was not successfully checked out
    }

    // Checks in book 
    public boolean checkinBook(String ISBN, Person customer) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getISBN().equals(ISBN) && books[i].isCheckedOut()) { // if book being checked in is equal to book that was checkout out then check in book
                books[i].checkIn();
                System.out.println(customer.getName() + " has returned the book: " + books[i].getTitle() + "\n");
                return true;
            }
        }
        return false; // else do not check in book
    }

    // method that allows you to set how many days book has been checked out
    public void setDaysCheckedOut(String ISBN, int days) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getISBN().equals(ISBN)) {
                books[i].daysCheckedOut = days;
            }
        }
    }
    

    //method that lists books that are not checked out
    public void listAvailableBooks() {
        for (int i = 0; i < bookCount; i++) {
            if (!books[i].isCheckedOut()) {
                System.out.println("Title: " + books[i].getTitle());
                System.out.println("Author: " + books[i].getAuthor());
                System.out.println("ISBN: " + books[i].getISBN());
                System.out.println("Status: Available");
                System.out.println();
            }
        }
    }

    // lists books checked out
    public void listCheckedOutBooks() {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isCheckedOut()) {
                System.out.println("Title: " + books[i].getTitle());
                System.out.println("Author: " + books[i].getAuthor());
                System.out.println("ISBN: " + books[i].getISBN());
                System.out.println("Status: Checked Out");
                System.out.println();
            }
        }
    }

    // calculates late fee    
    public double getTotalLateFeesCollected() {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isCheckedOut()) {
                totalLateFeesCollected += (books[i].getDaysCheckedOut() * lateFeePerDay); // multiplies numbers of days book has been checked out by late fee per day
                
            }
        }
        return totalLateFeesCollected;
    }


    // prints library capacity, late fee per day, and total late fees collected
    public void printLibraryInfo() {
        System.out.println("Library Capacity: " + bookCapacity);
        System.out.println("Late Fee per Day: $" + lateFeePerDay);
        System.out.printf("Total Late Fees Collected: $%.2f%n", totalLateFeesCollected);
    }
    

    public static void main(String[] args) {
        Library library = new Library(10, 0.50); // Creating a library with a capacity of 10 and a late fee of $0.50 per day
    
        Person customer1 = library.new Person("Kimberly Smith");
    
        library.addBook("Atomic Habits", "James Clear", "ISBN123");
        library.addBook("The Midnight Library", "Matt Haig", "ISBN456");
    
        library.checkoutBook("ISBN123", customer1);
        library.checkoutBook("ISBN456", customer1);
    
        library.checkinBook("ISBN123", customer1);
    
        library.setDaysCheckedOut("ISBN123", 7); // Set "ISBN123" to be checked out for 7 days
        library.setDaysCheckedOut("ISBN456", 3); // Set "ISBN456" to be checked out for 3 days
    
        System.out.println("Here is a list of book(s) available in our tiny library: ");
        library.listAvailableBooks();
    
        System.out.println("\nHere is a list of book(s) checked out in our tiny library: ");
        library.listCheckedOutBooks();
    
        library.getTotalLateFeesCollected();
    
        library.printLibraryInfo();
    
    
    }
}
