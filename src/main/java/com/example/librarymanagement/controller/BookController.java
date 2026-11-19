
package com.example.librarymanagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.librarymanagement.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    List<Book> books = new ArrayList<>();

    // Add a new book
    @PostMapping
    public String addBook(@RequestBody Book book) {
        books.add(book);
        return "Book added successfully!";
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return books;
    }

    // Get book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {

        for (Book b : books) {
            if (b.getId().equals(id)) {
                return ResponseEntity.ok(b);
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Update book
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
            @PathVariable Integer id,
            @RequestBody Book updatedBook) {

        for (Book b : books) {

            if (b.getId().equals(id)) {

                b.setTitle(updatedBook.getTitle());
                b.setAuthor(updatedBook.getAuthor());
                b.setCategory(updatedBook.getCategory());
                b.setAvailableCopies(updatedBook.getAvailableCopies());

                return ResponseEntity.ok("Book updated successfully!");
            }
        }

        return ResponseEntity.notFound().build();
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {

        for (Book b : books) {

            if (b.getId().equals(id)) {
                books.remove(b);
                return ResponseEntity.ok("Book deleted successfully!");
            }
        }

        return ResponseEntity.notFound().build();
    }
}
