package com.thanglong.chonlichthilai.tkb.chitiet;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thanglong.chonlichthilai.tkb.TKB;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TkbChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private Integer tt;  // ❌ Removed @Column(unique = true)

    private String loaiHocPhan;
    private String maNguoiNhap;
    private Integer thu;
    private Integer batDau;
    private Integer ketThuc;
    private String phong;
    private String ghiChuCa;
    private Date time;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "tkb_id", nullable = false)
    private TKB tkb;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModify;

    @PrePersist
    protected void onCreate() {
        lastModify = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModify = new Date();
    }
}