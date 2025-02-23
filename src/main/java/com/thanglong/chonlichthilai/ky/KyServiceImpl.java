package com.thanglong.chonlichthilai.ky;

//Importing required classes
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//Annotation
@Service

//Class
public class KyServiceImpl implements KyService {

 @Autowired
 private KyRepository kyRepository;
 // Save operation
 @Override
 public Ky saveKy(Ky ky){
     return kyRepository.save(ky);
 }
 // Read operation
 @Override public List<Ky> fetchKyList(){
     return (List<Ky>) kyRepository.findAll();
 }
 // Read operation
 @Override public Ky findKyById(Long id){
     return kyRepository.findById(id).orElse(null);
 }
 
 @Override public Ky findKyByMaKy(String maKy){
     return kyRepository.findByMaKy(maKy).orElse(null);
 }
 // Update operation
 @Override
 public Ky updateKy(Ky ky, Long Id) {
     Ky k= kyRepository.findById(Id).get();
     k = ky;
     return kyRepository.save(k);
 }
 // Delete operation
 @Override
 public void deleteKyById(Long Id){
	 kyRepository.deleteById(Id);
 }
}

