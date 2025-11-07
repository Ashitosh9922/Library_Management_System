package library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final String URL = "jdbc:mysql://localhost:3306/library_db";
    private final String USER = "root"; 
    private final String PASS = "Ash@1234";     

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void addBook(String title, String author, int year) {
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public List<Book> viewBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getBoolean("available")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching books: " + e.getMessage());
        }
        return books;
    }

    public void borrowBook(int id) {
        String checkSql = "SELECT available FROM books WHERE id = ?";
        String updateSql = "UPDATE books SET available = false WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            checkStmt.setInt(1, id);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("available")) {
                updateStmt.setInt(1, id);
                updateStmt.executeUpdate();
                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book is not available or does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    public void returnBook(int id) {
        String sql = "UPDATE books SET available = true WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}
