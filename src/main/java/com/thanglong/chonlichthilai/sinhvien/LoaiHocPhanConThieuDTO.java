package com.thanglong.chonlichthilai.sinhvien;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoaiHocPhanConThieuDTO {
    private String tenLoaiHocPhan;

    @Singular("hocPhanConThieu")
    private List<HocPhanConThieuDTO> hocPhansConThieu;

}
