package bookstore.bookstore.service.interfaces;

import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.dto.MedicineDto;

import java.util.List;
import java.util.Optional;

public interface MedicineService {
    Medicine addMedicine(MedicineDto medicineDto);
    Medicine getMedicineById(Long id);
    void removeMedicine(Long bookId);
    Optional<Medicine> findById(Long bookId);
    List<Medicine> getAll();
}
