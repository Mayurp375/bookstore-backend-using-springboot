package bookstore.bookstore.service;


import bookstore.bookstore.entity.Book;
import bookstore.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void removeBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public Optional<Book> findById(Long bookId) {
        return bookRepository.findById(bookId);
    }


    // ... methods like updateBook, deleteBook, getAllBooks etc. ...
}
