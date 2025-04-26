package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.thanglong.chonlichthilai.hocphan.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "to_hop_hoc_phan_chi_tiet")
public class ToHopHocPhanChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "to_hop_id")
    @JsonBackReference // phía bị động
    private ToHopHocPhan toHopHocPhan;


    @ManyToOne
    @JoinColumn(name = "hoc_phan_id")
    private HocPhan hocPhan;
}
