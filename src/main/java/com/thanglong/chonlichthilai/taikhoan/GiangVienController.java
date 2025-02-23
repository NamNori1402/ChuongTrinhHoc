package com.thanglong.chonlichthilai.taikhoan;

import java.util.List;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.taikhoan.GiangVien;
import com.thanglong.chonlichthilai.taikhoan.GiangVienService;
import com.thanglong.chonlichthilai.util.Message;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class GiangVienController {
 
    // Annotation
    @Autowired private GiangVienService service;
	@Autowired private EmailService emailService;
	
    @PostMapping("/giangvien")
    public GiangVien save(@Valid @RequestBody GiangVien e)  {
        return service.save(e);
    }
	
    // Read operation
    @GetMapping("/giangvien")
    public List<GiangVien> fetchUserList()  {
        return service.fetchList();
    }
    @PutMapping("/giangvien/{id}")
    public GiangVien update(@RequestBody GiangVien taiKhoan, @PathVariable("id") Long Id) {
        return service.update(taiKhoan, Id);
    }
    // Delete operation
    @DeleteMapping("/giangvien/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
