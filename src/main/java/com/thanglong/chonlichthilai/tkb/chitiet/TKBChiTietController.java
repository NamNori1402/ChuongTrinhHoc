package com.thanglong.chonlichthilai.tkb.chitiet;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class TKBChiTietController {
 
    // Annotation
    @Autowired private TkbChiTietService service;
 
    // Save operation
    @PostMapping("/tkbchitiet")
    public TkbChiTiet save(@Valid @RequestBody TkbChiTiet e)  {
        return service.save(e);
    }
 
    // Read operation
    @GetMapping("/tkbchitiet")
    public List<TkbChiTiet> findAll()  {
        return service.findAll();
    }
    @GetMapping("/tkbchitiet/{id}")
    public TkbChiTiet getId(@PathVariable("id") Long Id)  {
        return service.findById(Id);
    }
    // Update operation
    @PutMapping("/tkbchitiet/{id}")
    public TkbChiTiet update(@RequestBody TkbChiTiet e, @PathVariable("id") Long Id) {
        return service.update(e, Id);
    }
 
    // Delete operation
    @DeleteMapping("/tkbchitiet/{id}")
    public String deleteById(@PathVariable("id") Long Id)  {
    	service.deleteById(Id);
        return "Deleted Successfully";
    }
}
