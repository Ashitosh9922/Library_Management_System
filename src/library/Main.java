package library;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Delete Book");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Title: "); String title = sc.nextLine();
                    System.out.print("Author: "); String author = sc.nextLine();
                    System.out.print("Year: "); int year = sc.nextInt();
                    library.addBook(title, author, year);
                }
                case 2 -> {
                    List<Book> books = library.viewBooks();
                    if (books.isEmpty()) System.out.println("No books available.");
                    else books.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("Enter Book ID to borrow: "); int id = sc.nextInt();
                    library.borrowBook(id);
                }
                case 4 -> {
                    System.out.print("Enter Book ID to return: "); int id = sc.nextInt();
                    library.returnBook(id);
                }
                case 5 -> {
                    System.out.print("Enter Book ID to delete: "); int id = sc.nextInt();
                    library.deleteBook(id);
                }
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
