package com.thanglong.chonlichthilai.utils.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.tkb.TKB;
import com.thanglong.chonlichthilai.tkb.TKBService;
import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTiet;
import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTietService;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class RestUploadController {

	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
    @Autowired private TKBService service;
    @Autowired private TkbChiTietService serviceChiTiet;
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "F://temp//";

	// 3.1.1 Single file upload
	@PostMapping("/api/upload")
	// If not @RestController, uncomment this
	// @ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile,ServletRequest request) {

		logger.debug("Single file upload!");

		if (uploadfile.isEmpty()) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		try {
			saveUploadedFiles(Arrays.asList(uploadfile), request);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}

	// 3.1.2 Multiple file upload
	@CrossOrigin(origins = "http://localhost:3000") //
	@PostMapping("/upload")
	public ResponseEntity<?> uploadFileMulti(@RequestParam("extraField") String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles, ServletRequest request) {

		logger.debug("Multiple file upload!");

		// Get file name
		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}

		try {

			saveUploadedFiles(Arrays.asList(uploadfiles), request);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded - " + uploadedFileName, HttpStatus.OK);

	}

	// 3.1.3 maps html form to a Model
	@PostMapping("/api/upload/multi/model")
	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model, HttpServletRequest request) {

		logger.debug("Multiple file upload! With UploadModel");

		try {

			saveUploadedFiles(Arrays.asList(model.getFiles()), request);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

//	byte[] bytes = file.getBytes();
//	Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//	Files.write(path, bytes);
	// save file
	 private void saveUploadedFiles(List<MultipartFile> files, ServletRequest request) throws IOException {
            HttpServletRequest req = (HttpServletRequest) request;
            PhienKetNoi phienKetNoi = (PhienKetNoi)req.getSession().getAttribute("phienKetNoi");
	        for (MultipartFile file : files) {
	            if (file.isEmpty()) {
	                System.out.println("Bỏ qua file rỗng: " + file.getOriginalFilename());
	                continue;
	            }
	            byte[] bytes = file.getBytes();
	            try (InputStream inputStream = new ByteArrayInputStream(bytes);
	                 Workbook workbook = getWorkbook(inputStream, file.getOriginalFilename())) {

	                if (workbook.getNumberOfSheets() == 0) {
	                    System.out.println("File không có sheet nào hợp lệ: " + file.getOriginalFilename());
	                    continue;
	                }

	                Sheet sheet = (Sheet)workbook.getSheetAt(0);
	                List<TkbChiTiet> tkbChiTietList = new ArrayList<>();
	                String maLopHocPhanLast = null;
	                String maLopHocPhanNew  = null;
	                TKB tkbLast = null;
	                TKB tkb = null;
	                for (Row row : (Iterable<Row>) sheet) {
	                    if (row.getRowNum() == 0) continue; // Bỏ qua dòng tiêu đề
	                    maLopHocPhanNew = getStringValueFromCell(row.getCell(1));
	                    if (maLopHocPhanNew == null || maLopHocPhanNew.length()  < 5) {
	                    	break;
	                    }
	                    if (maLopHocPhanNew != maLopHocPhanLast) {
                    		tkb = new TKB();
 	                    	tkb.setTtTkbTruong(getIntValueFromCell(row.getCell(0)));
 		                    tkb.setMaLopHocPhan(maLopHocPhanNew);
 		                    String arr1 [] = tkb.getMaLopHocPhan().split("_");
 		                    if (arr1.length < 2) {
 		                    	String a= "";
 		                    }
 		                    tkb.setMaHocPhan(arr1[1]);
 		                    tkb.setTenLop(arr1[2]);
 		                    String maGV = getStringValueFromCell(row.getCell(3));
 		                    if (maGV==null || maGV.length() < 3) {
 		                    	maGV = "BoMon";
 		                    }
 		                    tkb.setTrangThai(0);
 		                    tkb.setMaGiangVien(maGV);
 		                    tkb.setMaKy(phienKetNoi.getMaKy());
 		                    tkb.setMaNguoiNhap(phienKetNoi.getUserName());
 		                    tkb.setMaKy(phienKetNoi.getMaKy());
                    		TKB tkbHienTai = service.findByTtTkbTruong(Long.parseLong(tkb.getTtTkbTruong()+""));
                    		if (tkbHienTai!=null) {
                    			tkb = service.update(tkb,tkbHienTai.getId());
 		                    } else {
 		                    	tkb = service.save(tkb);
 		                    }
                    		tkbLast = tkb;
                    		maLopHocPhanLast  = tkb.getMaLopHocPhan();
	                    }
	                    TkbChiTiet tkbChiTiet = new TkbChiTiet();
	                    tkbChiTiet.setLoaiHocPhan(getStringValueFromCell(row.getCell(2)));
	                    tkbChiTiet.setLoaiHocPhan(getStringValueFromCell(row.getCell(2)));
	                    String thu = getThu(getStringValueFromCell(row.getCell(4)));
	                    tkbChiTiet.setThu(thu);
	                    String tiet = getStringValueFromCell(row.getCell(5));
	                    String arr [] = tiet.split("-");
	                    tkbChiTiet.setBatDau(Integer.parseInt(arr[0].trim()+""));
	                    tkbChiTiet.setKetThuc(Integer.parseInt(arr[1].trim()+""));
	                    tkbChiTiet.setPhong(getStringValueFromCell(row.getCell(6)));
	                    tkbChiTiet.setMaNguoiNhap(phienKetNoi.getUserName());
	                    tkbChiTiet.setTt(getIntValueFromCell(row.getCell(0)));
	                    tkbChiTiet.setGhiChuCa(getStringValueFromCell(row.getCell(2)));
	                    tkbChiTiet.setTkb(tkbLast);
	                    serviceChiTiet.save(tkbChiTiet);
//	                    tkbList.add(tkb);
	                }
//	                System.out.println("Đọc thành công " + tkbList.size() + " dòng từ file: " + file.getOriginalFilename());

	            } catch (Exception e) {
	                System.err.println("Lỗi xử lý file: " + file.getOriginalFilename());
	                e.printStackTrace();
	                System.out.println(TKB.builder());
	            }
	        }

//	        System.out.println("Tổng số dòng đọc được từ tất cả file: " + allTkbList.size());
	    }

	    /**
	     * Xác định loại file Excel (.xls hoặc .xlsx) và mở Workbook tương ứng.
	     */
	    private Workbook getWorkbook(InputStream inputStream, String fileName) throws IOException {
	        if (fileName.endsWith(".xlsx")) {
	            return new XSSFWorkbook(inputStream); // Excel mới (.xlsx)
	        } else if (fileName.endsWith(".xls")) {
	            return new HSSFWorkbook(inputStream); // Excel cũ (.xls)
	        } else {
	            throw new IllegalArgumentException("File không đúng định dạng Excel: " + fileName);
	        }
	    }

	    /**
	     * Lấy giá trị ô dưới dạng chuỗi, xử lý cả số & chữ.
	     */
	    private String getStringValueFromCell(Cell cell) {
	        if (cell == null) return "";
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue().trim();
	            case NUMERIC:
	                return String.valueOf((int) cell.getNumericCellValue());
	            default:
	                return "";
	        }
	    }

	    /**
	     * Lấy giá trị số nguyên từ ô, nếu không phải số thì trả về 0.
	     */
	    private int getIntValueFromCell(Cell cell) {
	        if (cell != null && cell.getCellType() == CellType.NUMERIC) {
	            return (int) cell.getNumericCellValue();
	        }
	        return 0;
	    }
	    private String getThu(String str) {
	    	if (str.indexOf("Hai") > 0) {
	    		return 2 +"";	    		
	    	} else if (str.indexOf("Ba") > 0) {
	    		return 3 +"";	
	    	} else if (str.indexOf("Tư") > 0) {
	    		return 4 +"";	
	    	} else if (str.indexOf("Năm") > 0) {
	    		return 5 +"";	
	    	} else if (str.indexOf("Sáu") > 0) {
	    		return 6 +"";	
	    	} else if (str.indexOf("Bảy") > 0) {
	    		return 7 +"";	
	    	} else if (str.indexOf("Chủ Nhật") > 0) {
	    		return 8 +"";
	    	}
	        return 0 +"";
	    } 
}