package bookstore.bookstore.controller;
import bookstore.bookstore.entity.Book;
import bookstore.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/books")
@CrossOrigin("http://localhost:4200/")
public class BookController {

    @Autowired
    private BookService bookService;
//http://localhost:8080/api/books/add
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }
    //http://localhost:8080/api/books/{id}
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable Long bookId) {
        Optional<Book> book = bookService.findById(bookId);

        if (book.isPresent()) {
            bookService.removeBook(bookId);
            return ResponseEntity.noContent().build();  // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }

    // ... Similarly, endpoints for update, delete, and fetch all books ...
}
