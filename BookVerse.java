import java.util.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

class Book {
    int id;
    String title;
    String author;
    int quantity;
    int issuedCount;
    boolean isIssued;
    String issuedTo;
    Date issuedDate;

    Book(int bookId, String bookTitle, String bookAuthor, int bookQuantity) {
        this.id = bookId;
        this.title = bookTitle;
        this.author = bookAuthor;
        this.quantity = bookQuantity;
        this.issuedCount = 0;
        this.isIssued = false;
    }

    boolean isAvailable() {
        return (quantity - issuedCount) > 0;
    }
}

class User {
    String username;
    String password;
    boolean isAdmin;

    User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<String, User> users = new HashMap<>();
    private User currentUser = null;
    final int FINE_PER_DAY = 10;
    final int DEADLINE_DAYS = 7;

    public void registerUser(String username, String password, boolean isAdmin) {
        users.put(username, new User(username, password, isAdmin));
    }

    public boolean loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.password.equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logoutUser() {
        currentUser = null;
    }

    private boolean isAdmin() {
        return currentUser != null && currentUser.isAdmin;
    }

    private int findBookIndexById(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    public void addBook(int id, String title, String author, int quantity) {
        if (currentUser == null) {
            System.out.println("Please log in first.");
            return;
        }
        if (!isAdmin()) {
            System.out.println("Only admins can add books.");
            return;
        }
        books.add(new Book(id, title, author, quantity));
    }

    public void searchBookById(int id) {
        int index = findBookIndexById(id);
        if (index != -1) {
            displayBookDetails(books.get(index));
        } else {
            System.out.println("Book not found.");
        }
    }

    public void searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.title.equals(title)) {
                displayBookDetails(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void issueBook(int id, String registrationNumber, String studentName) {
        int index = findBookIndexById(id);
        if (index != -1 && books.get(index).isAvailable()) {
            Book book = books.get(index);
            book.isIssued = true;
            book.issuedTo = registrationNumber;
            book.issuedDate = new Date();
            book.issuedCount++;
            System.out.println("Book issued to " + studentName + ". Deadline to return: " + calculateDeadline());
        } else {
            System.out.println("Book is either not found or not available.");
        }
    }

    public void returnBook(int id) {
        int index = findBookIndexById(id);
        if (index != -1 && books.get(index).isIssued) {
            Book book = books.get(index);
            long daysLate = calculateDaysLate(book.issuedDate);
            if (daysLate > 0) {
                System.out.println("Late return. Fine to be paid: Rs. " + daysLate * FINE_PER_DAY);
            } else {
                System.out.println("Book returned on time. No fine.");
            }
            book.isIssued = false;
            book.issuedTo = null;
            book.issuedDate = null;
            book.issuedCount--;
        } else {
            System.out.println("Invalid return attempt.");
        }
    }

    public void listAllBooks() {
        Collections.sort(books, Comparator.comparing(book -> book.title));
        for (Book book : books) {
            displayBookDetails(book);
        }
    }

    public void deleteBook(int id) {
        int index = findBookIndexById(id);
        if (index != -1) {
            books.remove(index);
            System.out.println("Book deleted.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public void updateBookInfo(int id, String newTitle, String newAuthor) {
        int index = findBookIndexById(id);
        if (index != -1) {
            Book book = books.get(index);
            book.title = newTitle;
            book.author = newAuthor;
            System.out.println("Book information updated.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.id == bookId) {
                return book;
            }
        }
        return null;
    }

    private String calculateDeadline() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, DEADLINE_DAYS);
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    private long calculateDaysLate(Date issuedDate) {
        long diffInMillies = Math.abs(new Date().getTime() - issuedDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) - DEADLINE_DAYS;
        return diff > 0 ? diff : 0;
    }

    public void displayBookDetails(Book book) {
        int availableQuantity = book.quantity - book.issuedCount;
        System.out.println("ID: " + book.id + ", Title: " + book.title + ", Author: " + book.author + ", Available Quantity: " + availableQuantity + ", Issued Count: " + book.issuedCount);
    }
}

public class BookVerse {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        int choice, id, quantity;
        String title, author, student, username, password, registrationNumber;

        // Pre-register an admin user
        lib.registerUser("admin", "admin123", true);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Add Book");
            System.out.println("5. Search Book by ID");
            System.out.println("6. Search Book by Title");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. List All Books");
            System.out.println("10. Delete Book");
            System.out.println("11. Update Book Information");
            System.out.println("12. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter username: ");
                    username = sc.nextLine();
                    System.out.print("Enter password: ");
                    password = sc.nextLine();
                    System.out.print("Is admin (true/false): ");
                    boolean isAdmin = sc.nextBoolean();
                    lib.registerUser(username, password, isAdmin);
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter username: ");
                    username = sc.nextLine();
                    System.out.print("Enter password: ");
                    password = sc.nextLine();
                    if (lib.loginUser(username, password)) {
                        System.out.println("Login successful.");
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 3:
                    lib.logoutUser();
                    System.out.println("Logged out.");
                    break;
                case 4:
                    System.out.print("Enter book ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter book title: ");
                    title = sc.nextLine();
                    System.out.print("Enter book author: ");
                    author = sc.nextLine();
                    System.out.print("Enter book quantity: ");
                    quantity = sc.nextInt();
                    lib.addBook(id, title, author, quantity);
                    break;
                case 5:
                    System.out.print("Enter book ID: ");
                    id = sc.nextInt();
                    lib.searchBookById(id);
                    break;
                case 6:
                    sc.nextLine();
                    System.out.print("Enter book title: ");
                    title = sc.nextLine();
                    lib.searchBookByTitle(title);
                    break;
                case 7:
                    System.out.print("Enter book ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter student registration number: ");
                    registrationNumber = sc.nextLine();
                    System.out.print("Enter student name: ");
                    student = sc.nextLine();
                    lib.issueBook(id, registrationNumber, student);
                    break;
                case 8:
                    System.out.print("Enter book ID: ");
                    id = sc.nextInt();
                    lib.returnBook(id);
                    break;
                case 9:
                    lib.listAllBooks();
                    break;
                case 10:
                    System.out.print("Enter book ID: ");
                    id = sc.nextInt();
                    lib.deleteBook(id);
                    break;
                case 11:
                    System.out.print("Enter book ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new title: ");
                    title = sc.nextLine();
                    System.out.print("Enter new author: ");
                    author = sc.nextLine();
                    lib.updateBookInfo(id, title, author);
                    break;
                case 12:
                    System.out.println("Exiting system.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
