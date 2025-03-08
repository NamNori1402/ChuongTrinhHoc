package com.thanglong.chonlichthilai.sinhvien;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
