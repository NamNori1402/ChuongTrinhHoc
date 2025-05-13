package com.thanglong.chonlichthilai.bangdiem;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.tkb.TKB;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// Annotations 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
// Class 
public class BangDiem
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msv;
    private String maHocPhan;
    private float diemTongKet;
    private Integer soLanThi;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date time;
    private Integer soTinChi;
    
    @ManyToOne
    @JoinColumn(name = "hoc_phan_id")
    private HocPhan hocPhan;
}