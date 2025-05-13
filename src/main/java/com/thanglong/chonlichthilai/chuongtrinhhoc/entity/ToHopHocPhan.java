package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "to_hop_hoc_phan")
public class ToHopHocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenToHop;

    private Integer soTinChiToiThieu;
    private Integer soLuongMonToiThieu;
    @Builder.Default
    private Boolean batBuocHocTatCa = false;

    @ManyToOne
    @JoinColumn(name = "cth_lhp_id")
    @JsonBackReference 
    private ChuongTrinhHoc_LoaiHocPhan chuongTrinhHocLoaiHocPhan;
    
    @OneToMany(mappedBy = "toHopHocPhan", cascade = CascadeType.ALL)
    @JsonManagedReference 
    private List<ToHopHocPhanChiTiet> chiTietList;
    
    @OneToMany(mappedBy = "toHopHocPhan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToHopNhomMon> toHopNhomMons;
}
