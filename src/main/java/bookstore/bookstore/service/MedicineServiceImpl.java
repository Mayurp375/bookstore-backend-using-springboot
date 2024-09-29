package bookstore.bookstore.service;


import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.dto.MedicineDto;
import bookstore.bookstore.repository.MedicineRepository;
import bookstore.bookstore.service.interfaces.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public Medicine addMedicine(MedicineDto medicineDto) {
        return medicineRepository.save(new Medicine(medicineDto));
    }

    public Medicine getMedicineById(Long id) {
        return medicineRepository.findById(id).orElse(null);
    }

    public void removeMedicine(Long bookId) {
        medicineRepository.deleteById(bookId);
    }

    public Optional<Medicine> findById(Long bookId) {
        return medicineRepository.findById(bookId);
    }

    public List<Medicine> getAll() {
        return medicineRepository.findAll();
    }


    // ... methods like updateBook, deleteBook, getAllBooks etc. ...
}
