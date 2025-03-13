package com.thanglong.chonlichthilai.thongbaonghihoc;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Interface định nghĩa các phương thức để tạo và xuất báo cáo thông báo nghỉ học.
 */
public interface ThongBaoNghiHocService {

    /**
     * Tạo báo cáo thông báo nghỉ học dưới dạng byte array.
     * @param fileType Loại file cần xuất (PDF, CSV, XLSX, HTML, XML, DOC).
     * @return Mảng byte chứa nội dung báo cáo.
     * @throws Exception Nếu có lỗi xảy ra trong quá trình xử lý.
     */
    byte[] generateThongBaoNghiHocReport(String fileType) throws Exception;

    /**
     * Xuất báo cáo Jasper thành định dạng tương ứng.
     * @param jasperPrint Đối tượng JasperPrint đã được điền dữ liệu.
     * @param reportType Loại báo cáo cần xuất (PDF, CSV, XLSX, HTML, XML, DOC).
     * @return Mảng byte chứa báo cáo dưới định dạng tương ứng.
     * @throws JRException Nếu có lỗi trong quá trình xuất báo cáo.
     */
    byte[] exportJasperReportBytes(JasperPrint jasperPrint, ReportTypeEnum reportType) throws JRException;
}
