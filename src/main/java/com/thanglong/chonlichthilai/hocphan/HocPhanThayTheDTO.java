package com.thanglong.chonlichthilai.hocphan;

import lombok.*;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HocPhanThayTheDTO {
    private String maHocPhanGoc;
    private String maHocPhanThayThe;
    private String tenHocPhanThayThe; // Lấy từ hocPhanThayThe.getTenHocPhan()
    private Integer soTinChi;
    private Integer khoaApDungTu;
    private Integer khoaApDungDen;
    

}
