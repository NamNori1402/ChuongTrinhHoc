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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.thanglong.chonlichthilai.bangdiem.BangDiem;
import com.thanglong.chonlichthilai.bangdiem.BangDiemService;
import com.thanglong.chonlichthilai.dangky.DangKy;
import com.thanglong.chonlichthilai.dangky.DangKyService;
import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;
import com.thanglong.chonlichthilai.hocphan.HocPhan;
import com.thanglong.chonlichthilai.hocphan.HocPhanService;
import com.thanglong.chonlichthilai.sinhvien.SinhVien;
import com.thanglong.chonlichthilai.sinhvien.SinhVienService;
import com.thanglong.chonlichthilai.tkb.TKB;
import com.thanglong.chonlichthilai.tkb.TKBService;
import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTiet;
import com.thanglong.chonlichthilai.tkb.chitiet.TkbChiTietService;
import com.thanglong.chonlichthilai.utils.Message;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
@RequestMapping("/api/v1")
public class RestUploadController {

	private final Logger logger = LoggerFactory.getLogger(RestUploadController.class);
	@Autowired
	private TKBService service;
	@Autowired
	private TkbChiTietService serviceChiTiet;
	@Autowired
	private BangDiemService serviceBangDiem;
	@Autowired
	private SinhVienService serviceSinhVien;
	@Autowired
	private DangKyService serviceDangKy;
	@Autowired
	private HocPhanService serviceHocPhan;
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "F://temp//";

//	// 3.1.1 Single file upload
//	@PostMapping("/u/diemSinhVien")
//	// If not @RestController, uncomment this
//	// @ResponseBody
//	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile,ServletRequest request) {
//		logger.debug("Single file upload!");
//		if (uploadfile.isEmpty()) {
//			return new ResponseEntity("please select a file!", HttpStatus.OK);
//		}
//		try {
//			saveUploadedFiles(Arrays.asList(uploadfile), request);
//		} catch (IOException e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity("Successfully uploaded - " + uploadfile.getOriginalFilename(), new HttpHeaders(),
//				HttpStatus.OK);
//
//	}
	@CrossOrigin(origins = "http://localhost:3000") //

	@PostMapping("/u/diemsinhvien")
	public ResponseEntity<?> uploadFileMulti2(@RequestParam("extraField2") String extraField,
			@RequestParam("files2") MultipartFile[] uploadfiles, ServletRequest request) {

		logger.debug("Multiple file upload!");

		// Get file name
		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		String str = "";
		try {
			str = saveUploadedFiles(Arrays.asList(uploadfiles), request, "diem");
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Message msg = new Message();
		msg.setCode(0);
		msg.setContent("Đã upload " + uploadedFileName + "; " + str);
		return new ResponseEntity(msg, HttpStatus.OK);

	}

	@PostMapping("/u/dangkyhoc")
	public ResponseEntity<?> uploadFileMulti3(@RequestParam("extraField3") String extraField,
			@RequestParam("files3") MultipartFile[] uploadfiles, ServletRequest request) {

		logger.debug("Multiple file upload!");

		// Get file name
		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		String str = "";
		try {
			str = saveUploadedFiles(Arrays.asList(uploadfiles), request, "dangkyhoc");
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Message msg = new Message();
		msg.setCode(0);
		msg.setContent("Đã upload " + uploadedFileName + "; " + str);
		return new ResponseEntity(msg, HttpStatus.OK);

	}

	// 3.1.2 Multiple file upload
	@CrossOrigin(origins = "http://localhost:3000") //

	@PostMapping("/upload/multi")
	public ResponseEntity<?> uploadFileMulti(@RequestParam("extraField") String extraField,
			@RequestParam("files") MultipartFile[] uploadfiles, ServletRequest request) {

		logger.debug("Multiple file upload!");

		// Get file name
		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			return new ResponseEntity("please select a file!", HttpStatus.OK);
		}
		String str = "";
		try {
			str = saveUploadedFiles(Arrays.asList(uploadfiles), request, "tkb");
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Message msg = new Message();
		msg.setCode(0);
		msg.setContent("Đã upload " + uploadedFileName + "; " + str);
		return new ResponseEntity(msg, HttpStatus.OK);

	}

	// 3.1.3 maps html form to a Model
	@PostMapping("/api/upload/multi/model")
	public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadModel model, HttpServletRequest request) {

		logger.debug("Multiple file upload! With UploadModel");

		try {

			saveUploadedFiles(Arrays.asList(model.getFiles()), request, "");

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity("Successfully uploaded!", HttpStatus.OK);

	}

//	byte[] bytes = file.getBytes();
//	Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//	Files.write(path, bytes);
	// save file
	private String saveUploadedFiles(List<MultipartFile> files, ServletRequest request, String up) throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");

		String str = "";

		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				System.out.println("Bỏ qua file rỗng: " + file.getOriginalFilename());
				continue;
			}
			byte[] bytes = file.getBytes();
			String maLopHocPhanNew = null;
			try (InputStream inputStream = new ByteArrayInputStream(bytes);
					Workbook workbook = getWorkbook(inputStream, file.getOriginalFilename())) {

				if (workbook.getNumberOfSheets() == 0) {
					System.out.println("File không có sheet nào hợp lệ: " + file.getOriginalFilename());
					continue;
				}
				Sheet sheet = (Sheet) workbook.getSheetAt(0);
				if (up.equals("tkb")) {
					str = uploadTKB(sheet, phienKetNoi.getMaKy(), phienKetNoi.getUserName());
				} else if (up.equals("diem")) {
					str = uploadDiem(sheet);
				} else if (up.equals("dangkyhoc")) {
					str = uploadDangKyHoc(sheet, phienKetNoi.getMaKy());
				}
			} catch (Exception e) {
				str = "Lỗi xử lý file: " + file.getOriginalFilename() + "; " + str + ", " + maLopHocPhanNew;
				e.printStackTrace();
				System.out.println(TKB.builder());
			}
		}
		return str;
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
		if (cell == null)
			return "";
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

	private Integer getThu(String str) {
		if (str.indexOf("Hai") > 0) {
			return 2;
		} else if (str.indexOf("Ba") >= 0) {
			return 3;
		} else if (str.indexOf("Tư") >= 0) {
			return 4;
		} else if (str.indexOf("Năm") >= 0) {
			return 5;
		} else if (str.indexOf("Sáu") >= 0) {
			return 6;
		} else if (str.indexOf("Bảy") >= 0) {
			return 7;
		} else if (str.indexOf("Nhật") > 0) {
			return 8;
		}
		return 0;
	}

	private String uploadDiem(Sheet sheet) {
		String str = "";
		List<BangDiem> bangDiemList = new ArrayList<>();
		List<SinhVien> sinhVienList = new ArrayList<>();
		List<HocPhan> hocPhanList = new ArrayList<>();
		Set<String> msvSet = new HashSet<>();
		HashMap<String, String> hm = new HashMap<String, String>();

		for (Row row : (Iterable<Row>) sheet) {
			if (row.getRowNum() == 0)
				continue; // Bỏ qua dòng tiêu đề

			String msv = getStringValueFromCell(row.getCell(0));
			msv = msv.trim();
			if (msv == null || msv.length() < 5)
				break;

			msvSet.add(msv); // Thu thập danh sách mã sinh viên để xử lý hàng loạt

			// Kiểm tra xem sinh viên đã tồn tại chưa
			SinhVien existingSinhVien = serviceSinhVien.findFirstByMaSinhVien(msv);
			if (existingSinhVien == null) {
				SinhVien sv = new SinhVien();
				sv.setMaSinhVien(msv);
				sv.setEmail1(msv + "@thanglong.edu.vn");
				sinhVienList.add(sv);
			}
			String maHocPhan = getStringValueFromCell(row.getCell(1));
			HocPhan existingHocPhan = null;
			if (!hm.containsKey(maHocPhan)) {
				existingHocPhan = serviceHocPhan.findByMaHocPhan(maHocPhan);
				if (existingHocPhan == null) {
					HocPhan hp = new HocPhan();
					hp.setMaHocPhan(maHocPhan);
					hp.setTenHocPhan(getStringValueFromCell(row.getCell(5)));
					hp.setSoTinChi(Integer.parseInt(getStringValueFromCell(row.getCell(8))));
					hm.put(maHocPhan, "");
					hocPhanList.add(hp);
				}
			}
			// Tạo mới bảng điểm
			BangDiem bangDiem = new BangDiem();
			bangDiem.setMsv(msv);
			bangDiem.setMaHocPhan(getStringValueFromCell(row.getCell(1)));
			if (msv.equals("A49570")) {
				float f = Float.parseFloat(getStringValueFromCell(row.getCell(2)));
			}
			String s1 = row.getCell(2).toString();
			s1 = s1.trim();
			s1 = s1.replaceAll(",", ".");
			bangDiem.setDiemTongKet(Float.parseFloat(s1));
			bangDiem.setSoLanThi(Integer.parseInt(getStringValueFromCell(row.getCell(4))));
			bangDiem.setTime(new Date());
			bangDiem.setSoTinChi(Integer.parseInt(getStringValueFromCell(row.getCell(8))));
			bangDiemList.add(bangDiem);
		}

		// Xóa điểm cũ theo danh sách mã sinh viên (nếu có)
		if (!msvSet.isEmpty()) {
			serviceBangDiem.deleteAllByMsv(msvSet);
		}

		// Lưu danh sách sinh viên mới
		if (!sinhVienList.isEmpty()) {
			String m = "";
			try {
				serviceSinhVien.saveAll(sinhVienList);
			} catch (Exception e) {
				for (SinhVien sv : sinhVienList) {
					m = sv.getMaSinhVien();
					try {
						serviceSinhVien.save(sv);
					} catch (Exception e1) {
						str = "Có lỗi khi Insert sinh viên: " + sv + "; " + e.getMessage();
						System.out.println(m);
						e.printStackTrace();
					}
				}
			}

		}

		// Lưu danh sách điểm mới
		if (!bangDiemList.isEmpty()) {
			serviceBangDiem.saveAll(bangDiemList);
		}
		// Danh sach hoc phan moi
		if (!hocPhanList.isEmpty()) {
			String m = "";
			try {
				serviceHocPhan.saveAll(hocPhanList);
			} catch (Exception e) {
				for (HocPhan hp : hocPhanList) {
					m = hp.getMaHocPhan();
					try {
						serviceHocPhan.save(hp);
					} catch (Exception e1) {
						str = "Có lỗi khi Insert hoc phan: " + m + "; " + e.getMessage();
						System.out.println(m);
						e.printStackTrace();
					}
				}
			}
		}
		str = "Bảng điểm: -> Xóa sinh viên: " + msvSet.size() + "; Insert: " + bangDiemList.size()
				+ " sinh viên; Thêm sinh viên: " + sinhVienList.size();
		return str;
	}

	private String uploadTKB(Sheet sheet, String maKy, String maNguoiNhap) {
		List<TkbChiTiet> tkbChiTietList = new ArrayList<>();
		String maLopHocPhanLast = null;
		String maLopHocPhanNew = null;
		TKB tkbLast = null;
		TKB tkb = null;
		String str = "";
		int tkbUpdate = 0;
		int tkbInsert = 0;
		int tkbCTUpdate = 0;
		int tkbCTInsert = 0;
		int svUpdate = 0;
		int svInsert = 0;
		int diemDelete = 0;
		int diemInsert = 0;
		for (Row row : (Iterable<Row>) sheet) {
			try {
				if (row.getRowNum() <= 1)
					continue; // Bỏ qua dòng tiêu đề
				maLopHocPhanNew = getStringValueFromCell(row.getCell(1));
				if (maLopHocPhanNew == null || maLopHocPhanNew.length() < 5) {
					break;
				}
				if (maLopHocPhanNew != maLopHocPhanLast) {
					tkb = new TKB();
					try {
						tkb.setTtTkbTruong(getIntValueFromCell(row.getCell(0)));
					} catch (Exception e) {
						str = "Không xác định được STT ở dòng số " + row.getRowNum();
						break;
					}
					try {
						tkb.setMaLopHocPhan(maLopHocPhanNew);
						String arr1[] = tkb.getMaLopHocPhan().split("_");
						if (arr1.length < 2) {
							String a = "";
						}
						tkb.setMaHocPhan(arr1[1]);
						tkb.setTenLop(arr1[2]);
					} catch (Exception e) {
						str = "Không phân tích được Mã LHP " + maLopHocPhanNew + " định được STT ở dòng số "
								+ row.getRowNum();
						break;
					}
					try {
						String sucChua = getStringValueFromCell(row.getCell(5));
						tkb.setSucChua(Integer.parseInt(sucChua));
					} catch (Exception e) {
						tkb.setSucChua(0);
					}

					try {
						tkb.setSldk(getIntValueFromCell(row.getCell(6)));
					} catch (Exception e) {
						str = "Không phân tích được Số lượng đăng ký " + maLopHocPhanNew + " định được STT ở dòng số "
								+ row.getRowNum();
						break;
					}
					try {
						String maGV = getStringValueFromCell(row.getCell(7));
						if (maGV == null || maGV.length() < 3) {
							maGV = "BoMon";
						}
						tkb.setMaGiangVien(maGV);
					} catch (Exception e) {
						str = "Không phân tích được Mã Giảng viên " + maLopHocPhanNew + " định được STT ở dòng số "
								+ row.getRowNum();
						break;
					}
					tkb.setTrangThai(0);
					tkb.setMaKy(maKy);
					tkb.setMaNguoiNhap(maNguoiNhap);
					tkb.setTime(new Date());

					TKB tkbHienTai = service.findByTtTkbTruongAndMaKy(Long.parseLong(tkb.getTtTkbTruong() + ""), maKy);
					if (tkbHienTai != null) {
						tkb = service.update(tkb, tkbHienTai.getId());
						tkbUpdate++;
					} else {
						tkb = service.save(tkb);
						tkbInsert++;
					}
					tkbLast = tkb;
					maLopHocPhanLast = tkb.getMaLopHocPhan();
				}
				TkbChiTiet tkbChiTiet = new TkbChiTiet();
				try {
					tkbChiTiet.setLoaiHocPhan(getStringValueFromCell(row.getCell(4)));
				} catch (Exception e) {
					str = "Không phân tích Loại học phần (LT/BT) " + maLopHocPhanNew + " định được STT ở dòng số "
							+ row.getRowNum();
					break;
				}
				try {
					Integer thu = getThu(getStringValueFromCell(row.getCell(8)));
					tkbChiTiet.setThu(thu);
				} catch (Exception e) {
					str = "Không phân tích thứ học trong tuần " + maLopHocPhanNew + " định được STT ở dòng số "
							+ row.getRowNum();
					break;
				}
				String tiet = getStringValueFromCell(row.getCell(9)).trim();
				String arr[];
				try {
					arr = tiet.split("-");
					tkbChiTiet.setBatDau(Integer.parseInt(arr[0].trim() + ""));
					tkbChiTiet.setKetThuc(Integer.parseInt(arr[1].trim() + ""));
				} catch (Exception e) {
					tkbChiTiet.setBatDau(0);
					tkbChiTiet.setKetThuc(0);
				}
				tkbChiTiet.setTt(getIntValueFromCell(row.getCell(0)));
				tkbChiTiet.setGhiChuCa(getStringValueFromCell(row.getCell(4)));
				tkbChiTiet.setPhong(getStringValueFromCell(row.getCell(10)));
				tkbChiTiet.setMaNguoiNhap(maNguoiNhap);
				tkbChiTiet.setTkb(tkbLast);
				tkbChiTiet.setTime(new Date());
				serviceChiTiet.save(tkbChiTiet);
				tkbCTInsert++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String strTemp = tkbCTInsert == 0 ? "" : ", Số ca học là : " + tkbCTInsert;
		str = "Cập nhật: " + tkbUpdate + "; Insert: " + tkbInsert + strTemp + ", " + str;
		return str;
	}

	private String uploadDangKyHoc(Sheet sheet, String maKy) {
		List<DangKy> dangKyList = new ArrayList<>();
		Set<String> msvSet = new HashSet<>();
		HashMap<String, String> hm = new HashMap<String, String>();
		List<DangKy> dangKyDelete = serviceDangKy.deleteAllByMaKy(maKy);
		String str = "Số bán ghi xóa: " + dangKyDelete.size();
		for (Row row : (Iterable<Row>) sheet) {
			if (row.getRowNum() == 0)
				continue;
			String msv = getStringValueFromCell(row.getCell(0));
			msv = msv.trim().replaceAll(" ", "").toUpperCase();
			String lop = getStringValueFromCell(row.getCell(3)).trim().toUpperCase();
			if (msv != null && msv.length() > 5 && (lop.indexOf("TT")==0 || lop.indexOf("TA")==0 || lop.indexOf("TI")==0 || lop.indexOf("TC")==0 ||lop.indexOf("TE")==0)) {
				String maHocPhan = getStringValueFromCell(row.getCell(5));
				String maLopHocPhan = getStringValueFromCell(row.getCell(6));
				String ngayDangKy = getStringValueFromCell(row.getCell(18));
		        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a", new Locale("vi"));
		        Date ngayDangKyT = null;
		        try {
		            ngayDangKyT = formatter.parse(ngayDangKy);	        	
		        } catch (Exception e) {
		        	System.out.println(msv);
		        	e.printStackTrace();
		        }
				String nguoiDangKy = getStringValueFromCell(row.getCell(19)).toUpperCase();
				String tinhTrangHocPhi = getStringValueFromCell(row.getCell(22));
				DangKy dangKy = new DangKy();
				dangKy.setMsv(msv);
				dangKy.setMaHocPhan(maHocPhan);
				dangKy.setMaLopHocPhan(maLopHocPhan);
				dangKy.setNguoiDangKy(nguoiDangKy);
				dangKy.setNgayDangKy(ngayDangKyT);
				dangKy.setTinhTrangHocPhi(tinhTrangHocPhi);
				dangKy.setMaKy(maKy);
				dangKyList.add(dangKy);	
			} else {
				System.out.println(msv+" > "+lop);
			}
			
		}
			// Lưu danh sách sinh viên mới
		String m = "";
		if (!dangKyList.isEmpty()) {
			try {
				serviceDangKy.saveAll(dangKyList);
			} catch (Exception e) {
				for (DangKy entity : dangKyList) {
					m = entity.getMsv();
					try {
						serviceDangKy.save(entity);
					} catch (Exception e1) {
						str = "Có lỗi khi Insert sinh viên: " + m + "; " + e.getMessage();
						System.out.println(m);
						e.printStackTrace();
					}
				}
			}

		}
		str = "Đăng ký học: -> Xóa: " + dangKyDelete.size() + "; Insert: " + dangKyList.size();
		return str;
	}

}