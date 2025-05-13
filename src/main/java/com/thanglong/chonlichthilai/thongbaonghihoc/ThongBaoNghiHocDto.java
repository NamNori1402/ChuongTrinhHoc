package com.thanglong.chonlichthilai.thongbaonghihoc;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * DTO (Data Transfer Object) cho ThongBaoNghiHoc.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThongBaoNghiHocDto {

    private Long id;
    private String maGiangVien;
    private String maTKBChiTiet;
    private String hocBu;
    private String lyDo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date ngayGuiThongBao;
}
