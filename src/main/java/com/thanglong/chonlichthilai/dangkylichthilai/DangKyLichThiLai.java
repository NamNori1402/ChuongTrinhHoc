package com.thanglong.chonlichthilai.dangkylichthilai;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import lombok.NoArgsConstructor;


// Annotations 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// Class 
public class DangKyLichThiLai
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msv;
    private String tkbId;
    private String maKy;
}