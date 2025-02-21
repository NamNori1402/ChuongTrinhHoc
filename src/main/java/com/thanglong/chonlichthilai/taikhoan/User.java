package com.thanglong.chonlichthilai.taikhoan;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String userCode;
    private String givenName;
    private String familyName;
    private String picture;
    private String email;
    private String mobile;
    private Boolean admin;
    private Boolean secretary;
    private Boolean lecturer;
    private Boolean active = true;
    private String academic;
    private String title;
    private String unitId;
    private String note;
}