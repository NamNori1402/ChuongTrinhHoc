package com.thanglong.chonlichthilai.hocphan;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1")
public class HocPhanController {
 
    // Annotation
    @Autowired private HocPhanService service;
 
    // Save operation
    @CrossOrigin(origins = "*") 
    @PostMapping("/hocphan")
    public HocPhan save(@Valid @RequestBody HocPhan e)  {
        return service.save(e);
    }
 
    // Read operation
    @GetMapping("/hocphan")
    public List<HocPhan> findAll()  {
        return service.findAll();
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
}
