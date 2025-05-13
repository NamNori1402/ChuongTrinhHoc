package com.thanglong.chonlichthilai.hocphan;
import java.util.List;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HocPhanWithThayTheDTO {
	private Long id;
    private String maHocPhan;
    private String maHocPhanHienTai;
    private String tenHocPhan;
    private Integer soTinChi;
    private String tienQuyet;
    private String nganhPhuTrach;
    private String maGiangVien;
    private String lienLac;
    
    private List<HocPhanThayTheDTO> danhSachThayThe;
}
