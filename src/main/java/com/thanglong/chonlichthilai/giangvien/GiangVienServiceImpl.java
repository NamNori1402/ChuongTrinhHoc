package com.thanglong.chonlichthilai.giangvien; //khai báo package

// Importing required classes
import java.util.List; // List: Để làm việc với danh sách đối tượng GiangVien
import java.util.Objects; //Objects: Để kiểm tra các đối tượng không bị null
import org.springframework.beans.factory.annotation.Autowired; //@Autowired: Tự động tiêm (Inject) phụ thuộc
import org.springframework.stereotype.Service; //@Service: Đánh dấu class này là một Service trong Spring
 
// Annotation
@Service //@Service: Thông báo cho Spring biết rằng đây là một Service Layer (tầng dịch vụ).
 
// Class
public class GiangVienServiceImpl implements GiangVienService { //Triển khai interface GiangVienService để cung cấp các phương thức được định nghĩa trong đó.
 
    @Autowired
    private GiangVienRepository repository; //Dùng @Autowired để tiêm phụ thuộc của GiangVienRepository (có thể là interface kế thừa từ JpaRepository hoặc CrudRepository).
    // Save operation
    @Override 
    public GiangVien save(GiangVien e){ //Lưu đối tượng GiangVien vào cơ sở dữ liệu.        
        return repository.save(e);//Trả về đối tượng đã được lưu
    }
    // Read operation
    @Override public List<GiangVien> findAll(){ //Lấy toàn bộ danh sách GiangVien từ cơ sở dữ liệu.
        return (List<GiangVien>) repository.findAll(); //Ép kiểu về List<GiangVien>.
    }
    // Read operation
    @Override public GiangVien findById(Long id){ //Tìm GiangVien theo id.
        return repository.findById(id).orElse(null); //Nếu không tồn tại, trả về null.
    } 
    // Read operation
    @Override public GiangVien findByMaGiangVien(String maGiangVien){ 
        return repository.findByMaGiangVien(maGiangVien).orElse(null);
    }
    // Update operation
    @Override
    public GiangVien update(GiangVien e, Long Id) { 
    	GiangVien edb= repository.findById(Id).get(); //Lấy đối tượng GiangVien theo Id.
    	edb.setDienThoai(e.getDienThoai());
    	edb.setMaGiangVien(e.getMaGiangVien());
    	edb.setEmail1(e.getEmail1());
    	edb.setEmail2(e.getEmail2());
    	edb.setHoTenDem(e.getHoTenDem());
    	edb.setDonVi(e.getDonVi());
    	edb.setGhiChu(e.getGhiChu());
    	edb.setTen(e.getTen());
        return repository.save(edb);
    }
    // Delete operation
    @Override
    public void deleteById(Long Id){
    	repository.deleteById(Id);
    }
}