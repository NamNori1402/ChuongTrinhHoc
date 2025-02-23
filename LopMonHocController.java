package com.thanglong.chonlichthilai.lopmohoc;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
 
// Annotation
@RestController
 
// Class
public class LMHController {
 
    // Annotation
    @Autowired private LMHService lmhService;
 
    // Save operation
    @PostMapping("/ky/add")
    public LMH saveKy(@Valid @RequestBody LMH lmh )  {
        return lmhService.saveLMH(lmh);
    }
 
    // Read operation
    @GetMapping("/lmh")
    public List<lmh> fetchLMHList()  {
        return lmhService.fetchLMHList();
    }
    @GetMapping("/lmh/{id}")
    public LMH getLMHId(@PathVariable("id") Long kyId)  {
        return kyService.findKyById(kyId);
    }
    @GetMapping("/ky/search")
    public Ky getKyByMaKy(@RequestParam("maKy") String maKy)  {
        return kyService.findKyByMaKy(maKy);
    }
    // Update operation
    @PutMapping("/ky/{id}")
    public Ky updateUser(@RequestBody Ky ky, @PathVariable("id") Long kyId) {
        return kyService.updateKy(ky, kyId);
    }
 
    // Delete operation
    @DeleteMapping("/ky/{id}")
    public String deleteKyById(@PathVariable("id") Long kyId)  {
        kyService.deleteKyById(kyId);
        return "Deleted Successfully";
    }
}
