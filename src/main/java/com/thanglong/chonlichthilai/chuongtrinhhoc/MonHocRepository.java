package com.thanglong.chonlichthilai.chuongtrinhhoc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MonHocRepository extends JpaRepository<MonHoc, Long> {
}
