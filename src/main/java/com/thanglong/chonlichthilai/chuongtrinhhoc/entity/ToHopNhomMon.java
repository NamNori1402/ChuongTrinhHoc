package com.thanglong.chonlichthilai.chuongtrinhhoc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "to_hop_nhom_mon")
public class ToHopNhomMon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenNhom; // Ví dụ: Nhóm tiếng Nhật

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_hop_id")
    private ToHopHocPhan toHopHocPhan;


    @OneToMany(mappedBy = "toHopNhomMon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToHopNhomMonChiTiet> chiTietMonHoc = new ArrayList<>();

}
