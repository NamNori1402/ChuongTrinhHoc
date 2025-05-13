package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"chuyenNganh", "khoa"})
})
public class ChuongTrinhHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) // Thay đổi thành String
    private String chuyenNganh; // Lưu tên chuyên ngành thay vì enum

    @Column(nullable = false, length = 10)
    private String khoa; // Lưu dưới dạng String

    @OneToMany(mappedBy = "chuongTrinhHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<ChuongTrinhHoc_LoaiHocPhan> cacLoaiHocPhan = new ArrayList<>();

    @OneToMany(mappedBy = "chuongTrinhHoc", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @JsonManagedReference
    private List<ChuongTrinhHoc_HocPhan> hocPhans = new ArrayList<>();

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



