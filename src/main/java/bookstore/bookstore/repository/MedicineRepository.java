package bookstore.bookstore.repository;

import bookstore.bookstore.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    // If you have specific queries related to books, you can define them here.
}
