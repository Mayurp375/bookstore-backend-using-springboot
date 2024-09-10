package bookstore.bookstore.controller;
import bookstore.bookstore.entity.Medicine;
import bookstore.bookstore.entity.dto.MedicineDto;
import bookstore.bookstore.service.MedicineService;
import bookstore.bookstore.util.constant.AppConstant;
import bookstore.bookstore.util.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/medicine")
@CrossOrigin("http://localhost:4200/")
public class MedicineController {
    private final MedicineService medicineService;
    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    //http://localhost:8080/api/books/add
//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<Medicine> addBook(@RequestBody MedicineDto medicineDto) {
        return new ResponseEntity<>(medicineService.addMedicine(medicineDto), HttpStatus.CREATED);
    }
    //http://localhost:8080/api/books/{id}
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getBook(@PathVariable Long id) {
        return new ResponseEntity<>(medicineService.getMedicineById(id), HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{medicineId}")
    public ResponseEntity<Void> removeMedicine(@PathVariable Long medicineId) {
        Optional<Medicine> medicine = medicineService.findById(medicineId);

        if (medicine.isPresent()) {
            medicineService.removeMedicine(medicineId);
            return ResponseEntity.noContent().build();  // 204 No Content
        } else {
            return ResponseEntity.notFound().build();  // 404 Not Found
        }
    }

    //http://localhost:8080/api/medicine/allMedicine
    @GetMapping("allMedicine")
    public ResponseEntity<?> getAllMedicines(){
        Map map = new HashMap<>();
        map.put(AppConstant.CODE,HttpStatus.OK.value());
        map.put(AppConstant.STATUS,AppConstant.SUCCESS);
        map.put(AppConstant.DATA,medicineService.getAll());
        return ResponseEntity.ok(map);
    }

    // ... Similarly, endpoints for update, delete, and fetch all books ...
}
