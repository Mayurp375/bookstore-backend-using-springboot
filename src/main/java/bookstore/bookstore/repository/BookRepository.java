package bookstore.bookstore.repository;

import bookstore.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // If you have specific queries related to books, you can define them here.
}
