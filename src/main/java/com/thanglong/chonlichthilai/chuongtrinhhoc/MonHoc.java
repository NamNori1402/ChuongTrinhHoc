package com.thanglong.chonlichthilai.chuongtrinhhoc;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String maHocPhan;
    private String tenMonHoc;
    private Integer soTinChi;
    private String dieuKienTienQuyet;
    private Integer soGio;
    private Integer soCa;
    private Long heSo;
    private String giangVienPhuTrach;
    private String maGiangVien;
    private String monThayThe;
}
