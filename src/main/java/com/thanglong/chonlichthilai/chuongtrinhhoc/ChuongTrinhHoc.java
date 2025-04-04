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

	@Enumerated(EnumType.ORDINAL)
    @Column(length = 50) // Thêm annotation @Column để chỉ định độ dài
    private ChuyenNganh chuyenNganh;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private KhoaHoc khoa;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private LoaiHocPhan loaiHocPhan;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "chuongTrinhHoc_id")
    private List<MonHoc> monHocList;
}

enum ChuyenNganh {
    CONG_NGHE_THONG_TIN, KHOA_HOC_MAY_TINH, TRI_TUE_NHAN_TAO, HE_THONG_THONG_TIN, MANG_MAY_TINH,
    KE_TOAN, TAI_CHINH_NGAN_HANG, LOGISTIC, QUAN_TRI_KINH_DOANH, QUAN_TRI_KHACH_SAN, MARKETING, KINH_TE_QUOC_TE, LUAT_KINH_TE, THUONG_MAI_DIEN_TU, DICH_VU_DU_LICH_LU_HANH,
    NGON_NGU_ANH, NGON_NGU_TRUNG, NGON_NGU_NHAT, NGON_NGU_HAN,
    VIET_NAM_HOC, CONG_TAC_XA_HOI, TRUYEN_THONG_DA_PHUONG_TIEN, THIET_KE_DO_HOA,
    DIEU_DUONG, DINH_DUONG,
    THANH_NHAC
    ;
}

enum KhoaHoc {
    K30, K31, K32, K33, K34, K35, K36, K37, K38, K39, K40, K41;
}

enum LoaiHocPhan {
    HOC_PHAN_DAI_CUONG, HOC_PHAN_CO_SO_NGANH, HOC_PHAN_BAT_BUOC_NGANH, HOC_PHAN_LUA_CHON_NGANH, HOC_PHAN_TU_DO, KLTN_CĐTN_THUC_TAP;
}
