package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thanglong.chonlichthilai.hocphan.HocPhan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "chuong_trinh_hoc_hoc_phan")
public class ChuongTrinhHoc_HocPhan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "chuong_trinh_hoc_id")
    @JsonBackReference // Thêm annotation này
    private ChuongTrinhHoc chuongTrinhHoc;
    
    @ManyToOne
    @JoinColumn(name = "hoc_phan_id")
    private HocPhan hocPhan;
    // Thêm trường loaiHocPhan để xác định học phần thuộc loại nào
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private LoaiHocPhan loaiHocPhan;
    
    @ManyToOne
    @JoinColumn(name = "to_hop_id")
    private ToHopHocPhan toHop;
    
    @ManyToOne
    @JoinColumn(name = "cth_lhp_id")
    private ChuongTrinhHoc_LoaiHocPhan chuongTrinhHocLoaiHocPhan;

}