package com.thanglong.chonlichthilai.chuongtrinhhoc;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChuongTrinhHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChuyenNganh chuyenNganh;

    @Enumerated(EnumType.STRING)
    private KhoaHoc khoa;

    @Enumerated(EnumType.STRING)
    private LoaiHocPhan loaiHocPhan;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chuongTrinhHoc_id")
    private List<MonHoc> monHocList;
}

enum ChuyenNganh {
    CONG_NGHE_THONG_TIN, KE_TOAN, KHOA_HOC_MAY_TINH, TRI_TUE_NHAN_TAO, QUAN_TRI_KINH_DOANH;
}

enum KhoaHoc {
    K30, K31, K32, K33, K34, K35, K36, K37, K38, K39;
}

enum LoaiHocPhan {
    HOC_PHAN_DAI_CUONG, HOC_PHAN_CO_SO_NGANH, HOC_PHAN_BAT_BUOC_NGANH, HOC_PHAN_LUA_CHON_NGANH, HOC_PHAN_TU_DO;
}
