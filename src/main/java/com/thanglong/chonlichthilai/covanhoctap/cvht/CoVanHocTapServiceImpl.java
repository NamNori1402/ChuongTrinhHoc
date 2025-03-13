package com.thanglong.chonlichthilai.covanhoctap.cvht;

import java.util.Date;
//Importing required classes
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

//Annotation
@Service

//Class
public class CoVanHocTapServiceImpl implements CoVanHocTapService {

	@Autowired
	private CoVanHocTapRepository repository;
	// Save operation

	@Override
	public List<CoVanHocTap> findByMaKy(String maKy) {
	    return repository.findByMaKy(maKy);
	}

}
