package com.thanglong.chonlichthilai.chuongtrinhhoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChuongTrinhHocRepository extends JpaRepository<ChuongTrinhHoc, Long> {
}