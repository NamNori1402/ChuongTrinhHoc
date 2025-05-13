package com.thanglong.chonlichthilai.hocphan;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hoc_phan_thay_the", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"hoc_phan_goc_id", "hoc_phan_thay_the_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HocPhanThayThe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hoc_phan_goc_id")  // Tham chiếu đến id (không cần referencedColumnName vì mặc định là khóa chính)
    @JsonBackReference
    private HocPhan hocPhanGoc;

    @ManyToOne
    @JoinColumn(name = "hoc_phan_thay_the_id")  // Tham chiếu đến id
    private HocPhan hocPhanThayThe;
    
 // Mới thêm: áp dụng từ khóa nào
    private Integer khoaApDungTu; 
    private Integer khoaApDungDen; // VD: 34
}