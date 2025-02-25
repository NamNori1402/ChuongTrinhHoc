package com.thanglong.chonlichthilai.sinhvien;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController

// Class
public class SinhVienController {
 
    // Annotation
    @Autowired private SinhVienService sinhvienService;

//    // Save operation
    @CrossOrigin(origins = "*") 
    @PostMapping("/sinhvien")
    public SinhVien saveSinhVien(@Valid @RequestBody SinhVien sinhvien)  {
        return sinhvienService.saveSinhVien(sinhvien);
    }
 
    // Read operation
    @GetMapping("/sinhvien")
    public List<SinhVien> fetchSinhVienList()  {
        return sinhvienService.fetchSinhVienList();
    }
    @GetMapping("/sinhvien/{id}")
    public SinhVien getSinhVienId(@PathVariable("id") Long sinhvienId)  {
        return sinhvienService.findSinhVienById(sinhvienId);
    }
    @GetMapping("/sinhvien/search")
    public SinhVien getSinhVienByMaSinhVien(@RequestParam("maSinhVien") String maSinhVien)  {
        return sinhvienService.findSinhVienByMaSinhVien(maSinhVien);
    }
    // Update operation
 // cập nhật thông tin SinhVien theo id
    @PutMapping("/sinhvien/{id}")
    public SinhVien updateUser(@RequestBody SinhVien sinhvien, @PathVariable("id") Long sinhvienId) {
        return sinhvienService.updateSinhVien(sinhvien, sinhvienId);
    }
 
 // Cập nhật thông tin SinhVien theo maSinhVien
    @PutMapping("/updateByMaSinhVien/{maSinhVien}")
    public SinhVien updateSinhVienByMaSinhVien(@RequestBody SinhVien sinhVien, @PathVariable("maSinhVien") String maSinhVien) {
        return sinhvienService.updateSinhVienByMaSinhVien(sinhVien, maSinhVien);
    }
    // Delete operation
    @DeleteMapping("/sinhvien/{id}")
    public String deleteSinhVienById(@PathVariable("id") Long sinhvienId)  {
        sinhvienService.deleteSinhVienById(sinhvienId);
        return "Deleted Successfully";
    }
    @DeleteMapping("/deleteByMaSinhVien/{maSinhVien}")
    public String deleteSinhVienByMaSinhVien(@PathVariable("maSinhVien") String maSinhVien) {
        sinhvienService.deleteSinhVienByMaSinhVien(maSinhVien);
        return "Deleted Successfully";
    }
}
