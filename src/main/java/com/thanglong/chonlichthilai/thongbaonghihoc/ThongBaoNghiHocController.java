package com.thanglong.chonlichthilai.thongbaonghihoc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.email.EmailDetails;
import com.thanglong.chonlichthilai.email.EmailService;
import com.thanglong.chonlichthilai.sinhvien.SinhVien;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ThongBaoNghiHocController {

    @Autowired
    private ThongBaoNghiHocService reportsService;

    @GetMapping("/thongbaonghihoc/{id}")
    public ResponseEntity<Resource> tbnhJasperReport24(@PathVariable("id") Long id) throws Exception {
    	String fileType = "DOC";

        ReportTypeEnum report = ReportTypeEnum.getReportTypeByCode(fileType);
        log.info("Enum: {}", report);
        byte[] bytes = reportsService.generateThongBaoNghiHocReport(fileType);

        ByteArrayResource resource = new ByteArrayResource(bytes);
        String fileName = "Employee24_JasperReport_" + LocalDateTime.now() + report.getExtension();

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
