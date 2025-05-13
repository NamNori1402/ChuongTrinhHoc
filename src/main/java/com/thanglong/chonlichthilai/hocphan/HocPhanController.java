package com.thanglong.chonlichthilai.hocphan;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1")
public class HocPhanController {
 
    // Annotation
    @Autowired private HocPhanService service;
    @Autowired private HocPhanThayTheService hocPhanThayTheService;
    @Autowired private HocPhanRepository hocPhanRepository;
    @Autowired private HocPhanThayTheRepository hocPhanThayTheRepository;
    // Save operation
    @CrossOrigin(origins = "*") 
    @PostMapping("/hocphan")
    public HocPhan save(@Valid @RequestBody HocPhan e)  {
        return service.save(e);
    }
    //thêm nhiều hp cùng lúc
    @PostMapping("/hocphan/batch")
    public ResponseEntity<?> saveAllHocPhan(@RequestBody List<HocPhan> list) {
        if (list == null || list.isEmpty()) {
            return ResponseEntity.badRequest().body("Danh sách học phần trống.");
        }

        // Lưu và nhận về các học phần đã lưu
        List<HocPhan> savedList = service.saveAll(list);

        // Xác định các học phần bị bỏ qua (do đã tồn tại)
        Set<String> savedMaSet = savedList.stream()
            .map(HocPhan::getMaHocPhan)
            .collect(Collectors.toSet());

        List<HocPhan> skippedList = list.stream()
            .filter(hp -> !savedMaSet.contains(hp.getMaHocPhan()))
            .collect(Collectors.toList());

        // Trả về cả 2 danh sách
        Map<String, Object> response = new HashMap<>();
        response.put("saved", savedList);
        response.put("skipped", skippedList);
        response.put("message", String.format("Đã lưu %d học phần, bỏ qua %d học phần bị trùng Mã học phần.",
                savedList.size(), skippedList.size()));

        return ResponseEntity.ok(response);
    }


 
    // Read operation
    @GetMapping("/hocphan")
    public List<HocPhan> findAllByOrderByMaHocPhanAsc()  {
        return service.findAllByOrderByMaHocPhanAsc();
    }
    @GetMapping("/hocphan/{id}")
    public HocPhan getKyId(@PathVariable("id") Long id)  {
        return service.findById(id);
    }
    @GetMapping("/hocphan/search")
    public HocPhan getKyByMaHocPhan(@RequestParam("maHocPhan") String maHocPhan)  {
        return service.findByMaHocPhan(maHocPhan);
    }
    // Update operation
    @PutMapping("/hocphan/{id}")
    public HocPhan updateUser(@RequestBody HocPhan e, @PathVariable("id") Long id) {
        return service.update(e, id);
    }
 
    // Delete operation
    @DeleteMapping("/hocphan/{id}")
    public String deleteKyById(@PathVariable("id") Long id)  {
    	service.deleteById(id);
        return "Deleted Successfully";
    }
    
    @GetMapping("/hocphan-thaythe")
    public ResponseEntity<List<HocPhanThayTheDTO>> getAll() {
        return ResponseEntity.ok(hocPhanThayTheService.getAllHocPhanThayThe());
    }


    //thêm nhiều hp thay thế
    @PostMapping("/hocphan-thaythe/batch")
    public ResponseEntity<?> addMultipleHocPhanThayThe(@RequestBody List<HocPhanThayTheDTO> dtos) {
        try {
            List<HocPhanThayThe> savedList = hocPhanThayTheService.addMultipleHocPhanThayThe(dtos);
            return ResponseEntity.ok(savedList);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
    @DeleteMapping("/hocphan-thaythe")
    public ResponseEntity<?> deleteHocPhanThayThe(
            @RequestParam String maHocPhanGoc,
            @RequestParam String maHocPhanThayThe) {
        try {
            System.out.println("Attempting to delete hoc phan thay the: " + maHocPhanGoc + " -> " + maHocPhanThayThe);
            hocPhanThayTheService.deleteHocPhanThayThe(maHocPhanGoc, maHocPhanThayThe);
            return ResponseEntity.ok("Đã xóa học phần thay thế");
        } catch (RuntimeException e) {
            System.err.println("Error deleting hoc phan thay the: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/hocphan-thaythe/{id}")
    public ResponseEntity<?> deleteHocPhanThayTheById(@PathVariable Long id) {
        hocPhanThayTheService.deleteById(id);
        return ResponseEntity.ok("Đã xóa học phần thay thế theo ID");
    }

    
    
    @GetMapping("/hocphan-with-thaythe")
    public ResponseEntity<List<HocPhanWithThayTheDTO>> getHocPhanWithThayThe() {
        return ResponseEntity.ok(service.getAllHocPhanWithThayThe());
    }
    
    @GetMapping("/hocphan-with-thaythe/search")
    public ResponseEntity<List<HocPhanWithThayTheDTO>> searchHocPhanWithThayThe(
            @RequestParam(required = false) String keyword) {
        
        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        List<HocPhanWithThayTheDTO> result = service.searchHocPhanWithThayThe(keyword);
        
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}
