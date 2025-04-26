// File: ChuongTrinhHoc_LoaiHocPhan.java
package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chuong_trinh_hoc_loai_hoc_phan")
public class ChuongTrinhHoc_LoaiHocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chuong_trinh_hoc_id")
    @JsonBackReference
    private ChuongTrinhHoc chuongTrinhHoc;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private LoaiHocPhan loaiHocPhan; // Sử dụng enum LoaiHocPhan sẵn có

    private Integer soTinChiToiThieu; // dùng Integer để cho phép null
    
    @Column(name = "chu_thich", columnDefinition = "TEXT")
    private String chuThich;
    
    @OneToMany(mappedBy = "chuongTrinhHocLoaiHocPhan", cascade = CascadeType.ALL)
    private List<ToHopHocPhan> toHops;

    @OneToMany(mappedBy = "chuongTrinhHocLoaiHocPhan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChuongTrinhHoc_HocPhan> hocPhans;
    
    @Column(name = "ma_bat_dau_duoc_tinh")
    private String maBatDauDuocTinh; // VD: "CF,CS,IS"
}