package com.thanglong.chonlichthilai.covanhoctap.cvht;

import java.util.List;
// Importing required classes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.dangkylichthilai.DangKyLichThiLai;
import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.entity.ThiLaiResp;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
 
// Annotation
@RestController
@RequestMapping("/api/v1")
public class CoVanHocTapController {
 
    // Annotation
    @Autowired private CoVanHocTapService service;
 
    @PostMapping("/covanhoctap")
    public List<CoVanHocTap> getCvhtByMaKy(@RequestBody CoVanHocTap e)  {
        return service.findByMaKy(e.getMaKy());
    }
}