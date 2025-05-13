package com.thanglong.chonlichthilai.danhmuc.hinhthucthi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class HinhThucThi
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tenHinhThucThi;
}