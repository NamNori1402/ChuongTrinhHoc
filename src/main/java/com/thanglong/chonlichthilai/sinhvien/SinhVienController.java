package com.thanglong.chonlichthilai.sinhvien;
import com.thanglong.chonlichthilai.sinhvien.HocPhanConThieuDTO;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.chuongtrinhhoc.entity.ChuongTrinhHoc;
import com.thanglong.chonlichthilai.chuongtrinhhoc.repository.ChuongTrinhHocRepository;

import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1")
public class SinhVienController {
 
    // Annotation
    @Autowired private SinhVienService service;
 
    // Save operation
    @CrossOrigin(origins = "*") 
    @PostMapping("/sinhvien")
    public SinhVien save(@Valid @RequestBody SinhVien e)  {
        return service.save(e);
    }
    // Read operation
    @GetMapping("/sinhvien")
    public List<SinhVien> findAll()  {
        return service.findAll();
    }


    @GetMapping("/sinhvien/{maSinhVien}")
    public SinhVien getByMaSinhVien(@PathVariable("maSinhVien") String maSinhVien) {
        return service.findByMaSinhVien(maSinhVien);
    }
    // Update operation
    @PutMapping("/sinhvien/{id}")
    public SinhVien updateUser(@RequestBody SinhVien e, @PathVariable("id") Long id) {
        return service.update(e, id);
    }
 
    // Delete operation
    @DeleteMapping("/sinhvien/{id}")
    public String deleteById(@PathVariable("id") Long id)  {
    	service.deleteById(id);
        return "Deleted Successfully";
    }
    
    @Autowired
    private ChuongTrinhHocRepository chuongTrinhHocRepository;

    @GetMapping("/sinhvien/{maSinhVien}/chuongtrinhhoc")
    public ResponseEntity<?> getChuongTrinhHocBySinhVien(@PathVariable String maSinhVien) {
        Optional<ChuongTrinhHoc> ctOptional = service.findChuongTrinhHocCuaSinhVien(maSinhVien);
        if (ctOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chương trình học phù hợp");
        }
        return ResponseEntity.ok(ctOptional.get());
    }
    
    @GetMapping("/sinhvien/{maSinhVien}/hocphan-tichluy")
    public ResponseEntity<?> getHocPhanDaQua(@PathVariable String maSinhVien) {
        Set<String> ds = service.getHocPhanDaQua(maSinhVien);
        return ResponseEntity.ok(ds);
    }

    @GetMapping("/sinhvien/{maSinhVien}/hocphan-danghoc")
    public ResponseEntity<?> getHocPhanDangHoc(@PathVariable String maSinhVien) {
        Set<String> ds = service.getHocPhanDangHoc(maSinhVien);
        return ResponseEntity.ok(ds);
    }
    
    @GetMapping("/sinhvien/{maSinhVien}/hocphan-conthieu")
    public ResponseEntity<?> getHocPhanConThieu(@PathVariable String maSinhVien) {
        try {
            List<LoaiHocPhanConThieuDTO> list = service.getHocPhanConThieu(maSinhVien);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("❌ Không tìm thấy hoặc lỗi xử lý: " + e.getMessage());
        }
    }
    @GetMapping("/sinhvien/{maSinhVien}/hocphan-thilai-hoclai")
    public ResponseEntity<?> getHocPhanThiLaiHocLai(@PathVariable String maSinhVien) {
        try {
            List<HocPhanConThieuDTO> list = service.getHocPhanThiLaiHocLai(maSinhVien);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("❌ Lỗi: " + e.getMessage());
        }
    }

}
