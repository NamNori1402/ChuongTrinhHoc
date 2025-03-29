package com.thanglong.chonlichthilai.chuongtrinhhoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("/api/v1/chuongtrinhhoc")
public class ChuongTrinhHocController {

    @Autowired
    private ChuongTrinhHocService service;
    @PostMapping
    public ChuongTrinhHoc save(@RequestBody ChuongTrinhHoc e) {
        return service.save(e);
    }
    @PostMapping("/{id}/monhoc")
    public ChuongTrinhHoc addMonHoc(@PathVariable Long id, @RequestBody MonHoc monHoc) {
        service.addMonHocToChuongTrinh(id, monHoc);
        return service.findById(id);
    }

    @GetMapping
    public List<ChuongTrinhHoc> findAll() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public ChuongTrinhHoc findById(@PathVariable Long id) {
        return service.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
    @DeleteMapping("/{chuongTrinhId}/monhoc/{monHocId}")
    public ResponseEntity<String> removeMonHoc(
        @PathVariable Long chuongTrinhId,
        @PathVariable Long monHocId
    ) {
        try {
            service.removeMonHocFromChuongTrinh(chuongTrinhId, monHocId);
            return ResponseEntity.ok("Đã xóa môn học thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Trả về lỗi cụ thể
        }
    }
}