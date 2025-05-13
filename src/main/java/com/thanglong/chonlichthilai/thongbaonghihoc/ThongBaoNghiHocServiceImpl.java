package com.thanglong.chonlichthilai.thongbaonghihoc;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

@Service
@Slf4j
public class ThongBaoNghiHocServiceImpl implements ThongBaoNghiHocService {

    @Autowired
    private ThongBaoNghiHocRepository thongBaoNghiHocRepository;

    @Autowired
    private ThongBaoNghiHocMapper tbnhMapper;

    @Override
    @Transactional
    public byte[] generateThongBaoNghiHocReport(String fileType) throws Exception {
        String template = "reports/thongbaonghihoc.jrxml";

        // Lấy dữ liệu từ database
        List<ThongBaoNghiHoc> thongBaoList = thongBaoNghiHocRepository.findAll();
        if (thongBaoList == null || thongBaoList.isEmpty()) {
            throw new RuntimeException("Không có dữ liệu để tạo báo cáo!");
        }

        List<ThongBaoNghiHocDto> dataSource = tbnhMapper.toDtoList(thongBaoList);

        // 1. Thiết lập tham số cho báo cáo
        Map<String, Object> parameters = new HashMap<>();
        try (FileInputStream logoStream = new FileInputStream(ResourceUtils.getFile("classpath:reports/logo.jpg"))) {
            parameters.put("companyName", "THANG LONG UNIVERSITY");
            parameters.put("address", "Trường Đại học Thăng Long, Hà Nội, Việt Nam");
            parameters.put("header", "Thông Báo Nghỉ Học Report");
            parameters.put("logo", logoStream);
            parameters.put("createdBy", "Hệ thống thông báo nghỉ học");

            // 2. Chuẩn bị DataSource
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(dataSource);

            // 3. Compile template JasperReport
            String path = ResourceUtils.getFile("classpath:" + template).getAbsolutePath();
            JasperReport jasperReport = JasperCompileManager.compileReport(path);

            // 4. Điền dữ liệu vào báo cáo
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanCollectionDataSource);

            // 5. Xuất báo cáo theo loại file
            ReportTypeEnum reportType = ReportTypeEnum.getReportTypeByCode(fileType);
            if (reportType == null) {
                throw new IllegalArgumentException("Loại báo cáo không hợp lệ: " + fileType);
            }
            return exportJasperReportBytes(jasperPrint, reportType);
        }
    }

    @Override
    public byte[] exportJasperReportBytes(JasperPrint jasperPrint, ReportTypeEnum reportType) throws JRException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        switch (reportType) {
            case DOC -> {
                JRRtfExporter docExporter = new JRRtfExporter();
                docExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                docExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                docExporter.exportReport();
            }
            default -> JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }
        return outputStream.toByteArray();
    }
}