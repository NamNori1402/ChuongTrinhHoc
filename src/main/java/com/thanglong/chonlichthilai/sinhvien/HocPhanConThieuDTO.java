package com.thanglong.chonlichthilai.sinhvien;

import java.util.List;

import com.thanglong.chonlichthilai.hocphan.HocPhanThayTheDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HocPhanConThieuDTO {
    private String loai;    // "Tổ hợp", "Học phần"
    private String noiDung; // Tên học phần hoặc mô tả tổ hợp chưa đạt
    private String ghiChu;  // Ghi chú thêm nếu cần (ví dụ: "Thiếu 3 tín chỉ", "Cần học 2 môn nữa")
    
    private Long id;
    private String maHocPhan;
    private String maHocPhanHienTai;
    private String tenHocPhan;
    private String tienQuyet;
    private String nganhPhuTrach;
    private String lienLac;
    private String maGiangVien;
    private String moRong;
    private Integer soTinChi;
    private Integer  soCa;
    
    private List<HocPhanThayTheDTO> danhSachThayThe;
}
