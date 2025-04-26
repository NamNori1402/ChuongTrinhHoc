package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import com.thanglong.chonlichthilai.hocphan.HocPhan;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "to_hop_nhom_mon_chi_tiet")
public class ToHopNhomMonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "to_hop_nhom_mon_id")
    private ToHopNhomMon toHopNhomMon;

    @ManyToOne
    @JoinColumn(name = "hoc_phan_id")
    private HocPhan hocPhan;
} 
