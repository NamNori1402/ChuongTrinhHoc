package com.thanglong.chonlichthilai.lopmonhoc;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class LopMonHocController {
 
    // Annotation
    @Autowired private LopMonHocService lopmonhocService;
 
    // Save operation
    @PostMapping("/lopmonhoc")
    public LopMonHoc saveLopMonHoc(@Valid @RequestBody LopMonHoc lopmonhoc)  {
        return lopmonhocService.saveLopMonHoc(lopmonhoc);
    }
 
    // Read operation
    @GetMapping("/lopmonhoc")
    public List<LopMonHoc> fetchLopMonHocList()  {
        return lopmonhocService.fetchLopMonHocList();
    }
    @GetMapping("/lopmonhoc/{id}")
    public LopMonHoc getLopMonHocId(@PathVariable("id") Long lopmonhocId)  {
        return lopmonhocService.findLopMonHocById(lopmonhocId);
    }
    @GetMapping("/lopmonhoc/search")
    public LopMonHoc getLopMonHocByMaHocKy(@RequestParam("maHocKy") String maHocKy)  {
        return lopmonhocService.findLopMonHocByMaHocKy(maHocKy);
    }
    // Update operation
    @PutMapping("/lopmonhoc/{id}")
    public LopMonHoc updateUser(@RequestBody LopMonHoc lopmonhoc, @PathVariable("id") Long lopmonhocId) {
        return lopmonhocService.updateLopMonHoc(lopmonhoc, lopmonhocId);
    }
 
    // Delete operation
    @DeleteMapping("/lopmonhoc/{id}")
    public String deleteLopMonHocById(@PathVariable("id") Long lopmonhocId)  {
    	lopmonhocService.deleteLopMonHocById(lopmonhocId);
        return "Deleted Successfully";
    }
}
