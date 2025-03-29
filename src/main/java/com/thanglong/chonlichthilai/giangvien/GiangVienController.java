package com.thanglong.chonlichthilai.giangvien;

import java.util.List;

import org.json.JSONObject;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1") //ặt đường dẫn gốc cho tất cả các API trong controller này là /api/v1.
public class GiangVienController {
 
    // Annotation
    @Autowired private GiangVienService service;
    //service: Để sử dụng các phương thức CRUD đã được định nghĩa trong GiangVienServiceImpl
	@Autowired private EmailService emailService; //dùng để gửi email
	
    @PostMapping("/giangvien")
    public GiangVien save(@Valid @RequestBody GiangVien e)  {
        return service.save(e);
    }
	
    // Read operation
    @GetMapping("/giangvien")
    public List<GiangVien> findAll()  {
        return service.findAll();
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
