package com.thanglong.chonlichthilai.covanhoctap.cvht;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CoVanHocTapRepository extends JpaRepository<CoVanHocTap, Long> {
	public List<CoVanHocTap> findByMaKy(String maKy);
    
}