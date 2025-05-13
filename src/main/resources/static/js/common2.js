var DATA_IDX;
cfgHoiDong = [
	{
		"maLoaiHoiDong":"82.02.01.01","loaiHoiDong":"KLTN","tenHoiDong":"Khóa luận tốt nghiệp",
		"vaiTro": [
			{"maVaiTro":"101","tenVaiTro":"Chủ tịch"},
			{"maVaiTro":"102","tenVaiTro":"Phản biện"},
			{"maVaiTro":"103","tenVaiTro":"Ủy viên"},
			{"maVaiTro":"999","tenVaiTro":"Thư ký"},
		],
		"tieuChi": [
			{
				"maTieuChi":"101",
				"tenTieuChi":"Điểm về văn phong, kết cấu, hình thức trình bày của khoá luận và thuyết trình trước hội đồng",
				"thangDiem":"3",
				"kqDanhGia":""
							
			},{
				"maTieuChi":"102",
				"tenTieuChi":"Điểm về độ tin cậy, tính trung thực của dữ liệu trong khoá luận",
				"thangDiem":"1",
				"kqDanhGia":""
							
			},{
				"maTieuChi":"103",
				"tenTieuChi":"Điểm về tính sáng tạo và thực tiễn của khoá luận",
				"thangDiem":"1",
				"kqDanhGia":""
							
			},{
				"maTieuChi":"104",
				"tenTieuChi":"Điểm về tính khoa học của nội dung khoá luận",
				"thangDiem":"5",
				"kqDanhGia":""
			}
		]
	},{
			"maLoaiHoiDong":"82.02.01.02","loaiHoiDong":"CĐTN","tenHoiDong":"Chuyên đề tốt nghiệp",
			"vaiTro": [
				{"maVaiTro":"111","tenVaiTro":"Chủ tịch"},
				{"maVaiTro":"112","tenVaiTro":"Phản biện"},
				{"maVaiTro":"999","tenVaiTro":"Thư ký"},
			],
			"tieuChi": [
						{
							"maTieuChi":"101",
							"ndTieuChi":"Điểm về văn phong, kết cấu, hình thức trình bày của khoá luận và thuyết trình trước hội đồng",
							"thangDiem":"3",
							"kqDanhGia":""
										
						},{
							"maTieuChi":"102",
							"ndTieuChi":"Điểm về độ tin cậy, tính trung thực của dữ liệu trong khoá luận",
							"thangDiem":"1",
							"kqDanhGia":""
										
						},{
							"maTieuChi":"103",
							"ndTieuChi":"Điểm về tính sáng tạo và thực tiễn của khoá luận",
							"thangDiem":"1",
							"kqDanhGia":""
										
						},{
							"maTieuChi":"104",
							"ndTieuChi":"Điểm về tính khoa học của nội dung khoá luận",
							"thangDiem":"5",
							"kqDanhGia":""
						}
					]
	}
]

cfgTrangThai = [
	{"maTrangThai":10,"tenTrangThaiHienTai":"Chờ duyệt đề tài","tenTrangThaiTiepTheo":"Chờ duyệt đề tài"},
	{"maTrangThai":11,"tenTrangThaiHienTai":"Không duyệt đề tài","tenTrangThaiTiepTheo":"Không duyệt đề tài"},
	{"maTrangThai":12,"tenTrangThaiHienTai":"Chờ đăng ký","tenTrangThaiTiepTheo":"Duyệt để SV đ.ký"},
	{"maTrangThai":13,"tenTrangThaiHienTai":"Đã duyệt để SV t.hiện","tenTrangThaiTiepTheo":"Duyệt để SV t.hiện"},
	{"maTrangThai":14,"tenTrangThaiHienTai":"T.chối duyệt t.hiện","tenTrangThaiTiepTheo":"T.chối duyệt để SV t.hiện"},
	{"maTrangThai":15,"tenTrangThaiHienTai":"Đăng ký bảo vệ","tenTrangThaiTiepTheo":"Đăng ký bảo vệ"},
	{"maTrangThai":16,"tenTrangThaiHienTai":"Đã duyệt để bảo vệ","tenTrangThaiTiepTheo":"Cho phép bảo vệ"},
	{"maTrangThai":18,"tenTrangThaiHienTai":"Chờ lập hội đồng","tenTrangThaiTiepTheo":"Chờ lập hội đồng"},
	{"maTrangThai":19,"tenTrangThaiHienTai":"Chờ bảo vệ","tenTrangThaiTiepTheo":"Chờ bảo vệ"},
	{"maTrangThai":20,"tenTrangThaiHienTai":"Đã bảo vệ","tenTrangThaiTiepTheo":"Đã bảo vệ"},
]

function getTrangThai(trangThai){
	for(i = 0; i < cfgTrangThai.length; i++){
		if (cfgTrangThai[i].maTrangThai==trangThai){
			return cfgTrangThai[i].tenTrangThaiHienTai;
		}
	}
}
cfgHocKy 	= [
	{maHocKy: "K1", tenHocKy:"Học kỳ 1"},
	{maHocKy: "K2", tenHocKy:"Học kỳ 2"},
	{maHocKy: "K3", tenHocKy:"Học kỳ 3"}
	]
;
cfgHocHamHocVi 	= [ "GS", "PGS", "PGS.TS", "TS","ThS","CN" ];
cfgKhoa = [
	{
		"maKhoa":"82.02",
		"tenKhoa": "Công nghệ thông tin", 
		"tenKhoaVietTat": "CNTT", 
		"loaiDeTai":[
			{"tenLoaiDeTai": "Khóa luận tốt nghiệp","maLoaiDeTai": "82.02.01.01"},
			{"tenLoaiDeTai": "Chuyên đề tốt nghiệp","maLoaiDeTai": "82.02.01.02"},									
			],
		"chuyenNganh" : [
			{
				"maChuyenNganh" : "82.02.01",
				"tenChuyenNganh" : "TT-Công nghệ thông tin",
				"tenChuyenNganhVietTat" : "TT",
			},{
				"maChuyenNganh" : "82.02.02",
				"tenChuyenNganh" : "TA-Trí tuệ nhân tạo",
				"tenChuyenNganhVietTat" : "TA",
			},{
				"maChuyenNganh" : "82.02.03",
				"tenChuyenNganh" : "TI-Khoa học máy tính",
				"tenChuyenNganhVietTat" : "TI",
			},{
				"maChuyenNganh" : "82.02.04",
				"tenChuyenNganh" : "TE-Hệ thống thông tin",
				"tenChuyenNganhVietTat" : "TE",
			},{
				"maChuyenNganh" : "82.02.05",
				"tenChuyenNganh" : "TC-Mạng m.tính và t. thông",
				"tenChuyenNganhVietTat" : "TC",
			}			
		],
		vaiTro : [
			{"maVaiTro":10,"tenVaiTro":"Toàn bộ"},
			{"maVaiTro":11,"tenVaiTro":"Lập trình"},
			{"maVaiTro":12,"tenVaiTro":"Nghiệp vụ"},
			{"maVaiTro":13,"tenVaiTro":"Thiết kế"},
			{"maVaiTro":14,"tenVaiTro":"Kiểm thử"},
			{"maVaiTro":15,"tenVaiTro":"Triển khai"},
		]
	},{
		"maKhoa":"82.03",
		"tenKhoa": "Kinh tế", 
		"tenKhoaVietTat": "KT", 
		"loaiDeTai":[
			{"tenLoaiDeTai": "Luận văn tốt nghiệp","maLoaiDeTai": "82.03.01.01"},
			{"tenLoaiDeTai": "Chuyên đề tốt nghiệp","maLoaiDeTai": "82.03.01.02"},									
			],
		"chuyenNganh" : [
			{
				"maChuyenNganh" : "82.03.01",
				"tenChuyenNganh" : "Kinh tế đối ngoại",
				"tenChuyenNganhVietTat" : "TT",
			},{
				"maChuyenNganh" : "82.03.02",
				"tenChuyenNganh" : "Kinh tế trí thức",
				"tenChuyenNganhVietTat" : "TA"
			}		
		],
		vaiTro : [
			{"maVaiTro":20,"tenVaiTro":"Toàn bộ"},
			{"maVaiTro":21,"tenVaiTro":"Lập trình"},
			{"maVaiTro":22,"tenVaiTro":"Nghiệp vụ"},
			{"maVaiTro":23,"tenVaiTro":"Thiết kế"},
			{"maVaiTro":24,"tenVaiTro":"Kiểm thử"},
			{"maVaiTro":25,"tenVaiTro":"Triển khai"},
		]
	},
]


function getQueryString() {
  var key = false, res = {}, itm = null;
  // get the query string without the ?
  var qs = location.search.substring(1);
  // check for the key as an argument
  if (arguments.length > 0 && arguments[0].length > 1)
    key = arguments[0];
  // make a regex pattern to grab key/value
  var pattern = /([^&=]+)=([^&]*)/g;
  // loop the items in the query string, either
  // find a match to the argument, or build an object
  // with key/value pairs
  while (itm = pattern.exec(qs)) {
    if (key !== false && decodeURIComponent(itm[1]) === key)
      return decodeURIComponent(itm[2]);
    else if (key === false)
      res[decodeURIComponent(itm[1])] = decodeURIComponent(itm[2]);
  }

  return key === false ? res : null;
}

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}
function eraseCookie(name) {   
    document.cookie = name +'=; Path=/; Expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}

function frmAddGiangVien() {
	action = "add";
	html = '';
	html = '<table>';
	html = html + '<tr><td>Mã giảng viên</td><td><input type="text" id="username" value="CTI061"/></td><tr>'
	html = html + '<tr><td>Tên giảng viên</td><td><input type="text" id="tenGiangVien" value="Nguyễn Xuân Thanh"/></td><tr>'
	html = html + '<tr><td>Học hàm/học vị</td><td>';
	html = html + '<Select id="hocHamHocVi"/>'
	for (i = 0; i < cfgHocHamHocVi.length; i++){
		if (cfgHocHamHocVi[i]=='ThS'){
			html = html + '<option value="'+cfgHocHamHocVi[i]+'" Selected>'+cfgHocHamHocVi[i]+'</option>'
		} else {
			html = html + '<option value="'+cfgHocHamHocVi[i]+'">'+cfgHocHamHocVi[i]+'</option>'
		}
	}
	html = html + '</Select>'
	html = html + '</td><tr>'
	html = html + '<tr><td>Chức danh</td><td><input type="text" id="chucDanh" value="Giảng viên"/></td><tr>'
	html = html + '<tr><td>Chuyên ngành</td>'
	html = html + '<td>';
	html = html + '<select id="maChuyenNganh">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		for(j=0; j < cfgKhoa[i].chuyenNganh.length; j++){
			html = html + '<option value="'+cfgKhoa[i].chuyenNganh[j].maChuyenNganh+'">+ '+cfgKhoa[i].chuyenNganh[j].tenChuyenNganh+'</option>'
		}
	}
	html = html + '</select>'
	html = html + '</td><tr>'
	html = html + '<tr><td>Đơn vị</td><td><input type="text" id="donVi" value="Trường Đại học Thăng Long"/></td><tr>'
	html = html + '<tr><td>Điện thoại</td><td><input type="text" id="mobile" value="0904222294"/></td><tr>'
	html = html + '<tr><td>Email</td><td><input type="email" id="email" value="thanhnx@thanglong.edu.vn"/></td><tr>'
	html = html + '<tr><td>Ghi chú</td><td><input type="text" id="ghiChu"/></td><tr>'
	html = html + '</table>'
	html = html + '<input hidden type="checkbox" id="khoa"/>'
	html = html + '<input hidden type="checkbox" id="taoDeTai" checked/></td><tr>'
	html = html + '<input hidden type="checkbox" id="duyetDeTai"/></td><tr>'
	html = html + '<input hidden type="checkbox" id="thanhLapHoiDong"/></td><tr>'
	$('#modal-title').html("Thêm Giảng viên");
	$('#frm-content').html(html);
	$('#frm').modal('show');
}
function frmEditGiangVien(this_) {
	record=getRecordFromID(DATA,this_.value);
	html = '';
	html = '<table>';
	html = html + '<tr><td>ID</td><td><input type="text" id="id" value="'+record.id+'" readonly/></td><tr>'
	html = html + '<tr><td>Mã giảng viên</td><td><input type="text" id="username" value="'+record.username+'" /></td><tr>'
	html = html + '<tr><td>Tên giảng viên</td><td><input type="text" id="tenGiangVien" value="'+record.tenGiangVien+'" /></td><tr>'
	html = html + '<tr><td>Học hàm/học vị</td><td>';
	html = html + '<Select id="hocHamHocVi"/>'
	for (i = 0; i < cfgHocHamHocVi.length; i++){
		if (cfgHocHamHocVi[i]=='ThS'){
			html = html + '<option value="'+cfgHocHamHocVi[i]+'" Selected>'+cfgHocHamHocVi[i]+'</option>'
		} else {
			html = html + '<option value="'+cfgHocHamHocVi[i]+'">'+cfgHocHamHocVi[i]+'</option>'
		}
	}
	html = html + '</Select>'
	html = html + '</td><tr>'
	html = html + '<tr><td>Chức danh</td><td><input type="text" id="chucDanh" value="'+record.chucDanh+'" /></td><tr>'

	html = html + '<tr><td>Chuyên ngành</td>'
	html = html + '<td>';
	html = html + '<select id="maChuyenNganh">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		for(j=0; j < cfgKhoa[i].chuyenNganh.length; j++){
			if (cfgKhoa[i].chuyenNganh[j].maChuyenNganh == record.maChuyenNganh){
				html = html + '<option value="'+cfgKhoa[i].chuyenNganh[j].maChuyenNganh+'" selected>+ '+cfgKhoa[i].chuyenNganh[j].tenChuyenNganh+'</option>'				
			} else {
				html = html + '<option value="'+cfgKhoa[i].chuyenNganh[j].maChuyenNganh+'">+ '+cfgKhoa[i].chuyenNganh[j].tenChuyenNganh+'</option>'
			}
		}
	}
	html = html + '</select>'
	html = html + '</td><tr>'	
	
	html = html + '<tr><td>Đơn vị</td><td><input type="text" id="donVi" value="'+record.donVi+'" /></td><tr>'
	html = html + '<tr><td>Điện thoại</td><td><input type="text" id="mobile" value="'+record.mobile+'" /></td><tr>'
	html = html + '<tr><td>Email</td><td><input type="text" id="email" value="'+record.email+'" /></td><tr>'

	html = html + '<tr><td>Ghi chú</td><td><input type="text" id="ghiChu" value="'+record.ghiChu+'" /></td><tr>'
	if (record.khoa=='1'){
		html = html + '<tr><td>Khóa</td><td><input type="checkbox" id="khoa" checked/></td><tr>'		
	} else {
		html = html + '<tr><td>Khóa</td><td><input type="checkbox" id="khoa" /></td><tr>'
	}
	if (record.taoDeTai=='1'){
		html = html + '<tr><td>Tạo đề tài</td><td><input type="checkbox" id="taoDeTai" checked/></td><tr>'		
	} else {
		html = html + '<tr><td>Tạo đề tài</td><td><input type="checkbox" id="taoDeTai" /></td><tr>'
	}
	if (record.duyetDeTai=='1'){
		html = html + '<tr><td>Duyệt đề tài</td><td><input type="checkbox" id="duyetDeTai" checked/></td><tr>'		
	} else {
		html = html + '<tr><td>Duyệt đề tài</td><td><input type="checkbox" id="duyetDeTai" /></td><tr>'
	}
	if (record.thanhLapHoiDong=='1'){
		html = html + '<tr><td>Thành lập hội đồng</td><td><input type="checkbox" id="thanhLapHoiDong" checked/></td><tr>'		
	} else {
		html = html + '<tr><td>Thành lập hội đồng</td><td><input type="checkbox" id="thanhLapHoiDong" /></td><tr>'
	}
	html = html + '</table>'
	$('#modal-title').html("Sửa Giảng viên");
	$('#frm-content').html(html);
	$('#frm').modal('show');
	action = "edit";
}
function frmAddSinhVien() {
	action = "add";
	html = '';
	html = '<table>';
	html = html + '<tr><td>Mã sinh viên</td><td><input type="text" id="username" value="A02103" onchange="setEmail();"/></td><tr>'
	html = html + '<tr><td>Tên sinh viên</td><td><input type="text" id="tenSinhVien" value="ThanhNX"/></td><tr>'
	html = html + '<tr><td>Khoa</td><td><input type="khoa" id="khoa" value="CNTT"/></td><tr>'
	html = html + '<tr><td>Lớp</td><td><input type="lop" id="lop" value="TT34h1"/></td><tr>'
	html = html + '<tr><td>Chuyên ngành</td>'
	html = html + '<td>';
	html = html + '<select id="maChuyenNganh">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		for(j=0; j < cfgKhoa[i].chuyenNganh.length; j++){
			html = html + '<option value="'+cfgKhoa[i].chuyenNganh[j].maChuyenNganh+'">&nbsp&nbsp+ '+cfgKhoa[i].chuyenNganh[j].tenChuyenNganh+'</option>'
		}
	}
	html = html + '</select>'
	html = html + '</td><tr>'
	html = html + '<tr><td>Điện thoại</td><td><input type="text" id="mobile" value="0904222294"/></td><tr>'
	html = html + '<tr><td>Email</td><td><input type="text" id="email" value="thanhnx@thanglong.edu.vn"/></td><tr>'
	html = html + '<tr><td>Trạng thái</td><td><input hidden type="checkbox" id="trangThai"/>Đang hoạt động</td><tr>'
	html = html + '<tr><td>Ghi chú</td><td><input type="text" id="ghiChu" value="Ghi chu"/></td><tr>'
	html = html + '</table>'
	$('#modal-title').html("Thêm Sinh viên");
	$('#frm-content').html(html);
	$('#frm').modal('show');
}
function setEmail(){
	$('#email').val($('#username').val()+'@thanglong.edu.vn')
}
function frmEditSinhVien(ts) {
	idx=ts.value;
	html = '';
	html = '<table>';
	html = html + '<tr><td>ID</td><td><input type="text" id="id" value="'+record.id+'" readonly/></td><tr>'
		html = html + '<tr><td>Mã sinh viên</td><td><input type="text" id="username" value="'+record.username+'" /></td><tr>'
		html = html + '<tr><td>Tên sinh viên</td><td><input type="text" id="tenSinhVien" value="'+record.tenSinhVien+'" /></td><tr>'
		html = html + '<tr><td>Khoa</td><td><input type="khoa" id="khoa" value="'+record.khoa+'" /></td><tr>'
		html = html + '<tr><td>Lớp</td><td><input type="lop" id="lop" value="'+record.lop+'" /></td><tr>'
		html = html + '<tr><td>Chuyên ngành</td>'
		html = html + '<td>';
		html = html + '<select id="maChuyenNganh">';
		for(i=0; i < cfgKhoa.length; i++){
			html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
			for(j=0; j < cfgKhoa[i].chuyenNganh.length; j++){
				if (cfgKhoa[i].chuyenNganh[j].maChuyenNganh==record.maChuyenNganh){
					html = html + '<option value="'+cfgKhoa[i].chuyenNganh[j].maChuyenNganh+'" selected>&nbsp&nbsp+ '+cfgKhoa[i].chuyenNganh[j].tenChuyenNganh+'</option>'
				} else {
					html = html + '<option value="'+cfgKhoa[i].chuyenNganh[j].maChuyenNganh+'">&nbsp&nbsp+ '+cfgKhoa[i].chuyenNganh[j].tenChuyenNganh+'</option>'
				}
			}
		}
		html = html + '</select>'
		html = html + '</td><tr>'
		html = html + '<tr><td>Điện thoại</td><td><input type="text" id="mobile" value="'+record.mobile+'" /></td><tr>'
		html = html + '<tr><td>Email</td><td><input type="text" id="email"  value="'+record.email+'" /></td><tr>'
		if (record.trangThai=='1'){
			html = html + '<tr><td>Khóa</td><td><input type="checkbox" id="trangThai" checked/></td><tr>'		
		} else {
			html = html + '<tr><td>Khóa</td><td><input type="checkbox" id="trangThai" /></td><tr>'
		}
		html = html + '<tr><td>Ghi chú</td><td><input type="text" id="ghiChu" value="'+record.ghiChu+'" /></td><tr>'

	html = html + '</table>'
	$('#modal-title').html("Sửa Sinh viên");
	$('#frm-content').html(html);
	$('#frm').modal('show');
	action = "edit";
}

function frmAddDeTai() {
	action = "add";
	html = '';
	html = '<table width=100%>';
	html = html + '<tr><td width="40%">Mã đề tài</td><td><input type="text" id="maDeTai" value="84.02.82"/></td><tr>'
	html = html + '<tr><td>Tên đề tài</td><td><input type="tenDeTai" id="tenDeTai" value="Xây dựng web bán đồng hồ"/></td><tr>'
	html = html + '<tr><td>Chuyên ngành</td><td>'
	html = html + '<select id="maChuyenNganh">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		try {
			var obj = cfgKhoa[i].chuyenNganh;
			for(k=0; k < obj.length; k++){
				html = html + '<option value="'+obj[k].maChuyenNganh+'">+ '+obj[k].tenChuyenNganh+'</option>'
			}	
		} catch (e){
			
		}
	}
	html = html + '</select>'
	html = html + '</td></tr>'

	html = html + '<tr><td>Loại đề tài</td>'
	html = html + '<td>';
	html = html + '<select id="maLoaiDeTai">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		try {
			var obj = cfgKhoa[i].loaiDeTai;
			for(k=0; k < obj.length; k++){
				html = html + '<option value="'+obj[k].maLoaiDeTai+'">+ '+obj[k].tenLoaiDeTai+'</option>'
			}	
		} catch (e){
			
		}
	}
	html = html + '</select>'
	html = html + '</td><tr>'	
	html = html + '<tr><td>Học kỳ</td><td>';
	html = html + '<select id="maNhomKy" value=""/>'
	for(i = 0; i < cfgHocKy.length; i++){
		html = html + '<option value="'+cfgHocKy[i].maHocKy+'">'+cfgHocKy[i].tenHocKy+'</option>';
		//for(j = 0; j < cfgHocKy[i].nhomKy.length; j++){
		//	html = html + '<option value="'+cfgHocKy[i].nhomKy[j].maNhomKy+'">&nbsp;&nbsp;+ '+cfgHocKy[i].nhomKy[j].tenNhomKy+'</option>';
		//}
	}
	html = html + '</td><tr>'
	html = html + '<tr><td>Năm</td><td><input type="number" min="2019" max="2024" id="nam" value="2024" />'+'</td><tr>'
	html = html + '<tr><td>Số người thực hiện</td><td><input type="number" min=1 id="soLuong" value="1" onchange="soLuongVaiTro(this);"/></td><tr>'
	html = html + '<tr><td>Mô tả đề tài</td><td><textarea type="text" id="moTaDeTai" value="Mô tả" heigth=30></textarea></td><tr>'
	html = html + '<tr><td>Ghi chú</td><td><textarea type="text" id="ghiChu" value="Mô tả" heigth=30></textarea></td><tr>'

	html = html + '</table>'
	html = html + '<input id="trangThai" value="10" hidden>'
	$('#modal-title').html("Thêm đề tài");
	$('#frm-content').html(html);
	$('#frm').modal('show');
	sleep(500).then(() => {
		soLuongVaiTro('s')
	});
}

function frmEditDeTai(this_) {
	record=getRecordFromID(DATA,this_.value);
	action='edit'
	html = '';
	html = '<table width=100%>';
	html = html + '<tr><td width="40%">ID</td><td><input type="text" id="id" value="'+record.id+'" readonly/></td><tr>'
	html = html + '<tr><td>Mã đề tài</td><td><input type="text" id="maDeTai" value="'+record.maDeTai+'" /></td><tr>'
	html = html + '<tr><td>Tên đề tài</td><td><input type="tenDeTai" id="tenDeTai" value="'+record.tenDeTai+'" /></td><tr>'
	html = html + '<tr><td>Chuyên ngành</td><td>'
	html = html + '<select id="maChuyenNganh">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		try {
			var obj = cfgKhoa[i].chuyenNganh;
			for(k=0; k < obj.length; k++){
				if (obj[k].maChuyenNganh == record.maChuyenNganh){
					html = html + '<option selected value="'+obj[k].maChuyenNganh+'">+ '+obj[k].tenChuyenNganh+'</option>'
				} else {
					html = html + '<option value="'+obj[k].maChuyenNganh+'">+ '+obj[k].tenChuyenNganh+'</option>'
				}				
			}	
		} catch (e){
			
		}
	}
	html = html + '</select>'
	html = html + '</td></tr>'
	html = html + '<tr><td>Loại đề tài</td>'
	html = html + '<td>';
	html = html + '<select id="maLoaiDeTai">';
	for(i=0; i < cfgKhoa.length; i++){
		html = html + '<option value="" disable>Khoa '+cfgKhoa[i].tenKhoa+'</option>'
		try {
			var obj = cfgKhoa[i].loaiDeTai;
			console.log(obj)
			for(k=0; k < obj.length; k++){
				if (record.maLoaiDeTai == obj[k].maLoaiDeTai){
					html = html + '<option value="'+obj[k].maLoaiDeTai+'" selected>+ '+obj[k].tenLoaiDeTai+'</option>'
				} else {
					html = html + '<option value="'+obj[k].maLoaiDeTai+'">+ '+obj[k].tenLoaiDeTai+'</option>'
				}
			}
		} catch (e){
			
		}
	}
	html = html + '</select>'
	html = html + '</td></tr>'
	html = html + '<tr><td>Học kỳ</td><td>';
	html = html + '<select id="maNhomKy" value=""/>'
	for(i = 0; i < cfgHocKy.length; i++){
		if (record.maNhomKy == cfgHocKy[i].maHocKy){
			html = html + '<option value="'+cfgHocKy[i].maHocKy+'" selected>'+cfgHocKy[i].tenHocKy+'</option>';
		} else {
			html = html + '<option value="'+cfgHocKy[i].maHocKy+'">'+cfgHocKy[i].tenHocKy+'</option>';
		}
		//for(j = 0; j < cfgHocKy[i].nhomKy.length; j++){
		//	if (record.maNhomKy == cfgHocKy[i].nhomKy[j].maNhomKy){
		//		html = html + '<option value="'+cfgHocKy[i].nhomKy[j].maNhomKy+'" selected>&nbsp;&nbsp;+ '+cfgHocKy[i].nhomKy[j].tenNhomKy+'</option>';	
		//	} else {
		//		html = html + '<option value="'+cfgHocKy[i].nhomKy[j].maNhomKy+'">&nbsp;&nbsp;+ '+cfgHocKy[i].nhomKy[j].tenNhomKy+'</option>';	
		//	}
		//}
	}
	html = html + '</td><tr>'
	html = html + '<tr><td>Năm</td><td><input type="number" min="1996" max="2024" id="nam" value="'+record.nam+'" />'+'</td><tr>'
	html = html + '<tr><td>Người đăng</td><td><input hidden type="text" id="maNguoiDang" value="'+record.maNguoiDang+'" />'+record.maNguoiDang+'</td><tr>'
	html = html + '<tr><td>Số người thực hiện</td><td><input type="number" min=1 id="soLuong" value="'+record.dsThucHien.length+'" onchange="soLuongVaiTro(this);"/></td><tr>'
	html = html + '<tr><td>Mô tả đề tài</td><td><textarea type="text" id="moTaDeTai" value="Mô tả" heigth=30>'+record.moTaDeTai+'</textarea></td><tr>'
	html = html + '<tr><td>Ghi chú</td><td><textarea type="text" id="ghiChu" value="Mô tả" heigth=30>'+record.ghiChu+'</textarea></td><tr>'

	html = html + '<tr><td>Trạng thái</td>';
	html = html + '<td>';
	html = html + '<Select id="trangThai" onchange="xuLyTrangThai(this)">'
	for(i = 0; i < cfgTrangThai.length; i++){
		replace='';
		if (record.trangThai==cfgTrangThai[i].maTrangThai){
			html = html + '<option value="'+cfgTrangThai[i].maTrangThai+'"  selected >'+cfgTrangThai[i].maTrangThai+' > '+cfgTrangThai[i].tenTrangThaiTiepTheo+'</option>';
		} else{
			html = html + '<option value="'+cfgTrangThai[i].maTrangThai+'" >'+cfgTrangThai[i].maTrangThai+' > '+cfgTrangThai[i].tenTrangThaiTiepTheo+'</option>';
		}
	}
	html = html + '</Select>'
	html = html + '</td>';
	html = html + '</tr>';
	var cfgGiangVien = JSON.parse(localStorage.getItem('cfgGiangVien'));
	if (record.trangThai>=18){
		html = html + '<tr><td colspan=2 align=center><hr/></td></tr>';
		html = html + '<tr><td colspan=2 align=center>Chọn thành viên hội đồng</td></tr>';
		for(i = 0; i < cfgHoiDong.length;i++){
			if (cfgHoiDong[i].maLoaiHoiDong == record.maLoaiDeTai){
				for(j = 0; j < cfgHoiDong[i].vaiTro.length; j++){
					html = html + '<tr><td>'+(j+1)+'. '+cfgHoiDong[i].vaiTro[j].tenVaiTro+'</td><td>'
					html = html + '<Select id="maHoiDong'+j+'">';
					for(k=0; k < cfgGiangVien.length; k++ ){
						if (typeof record.dsHoiDong[j]!=='undefined' && record.dsHoiDong[j].maNguoi==cfgGiangVien[k].username){
							html = html + '<option selected value="'+cfgHoiDong[i].vaiTro[j].maVaiTro+'___'+cfgGiangVien[k].username+'">'+cfgGiangVien[k].hocHamHocVi+'.'+cfgGiangVien[k].tenGiangVien+'</option>'
						} else {
							html = html + '<option value="'+cfgHoiDong[i].vaiTro[j].maVaiTro+'___'+cfgGiangVien[k].username+'">'+cfgGiangVien[k].hocHamHocVi+'.'+cfgGiangVien[k].tenGiangVien+'</option>'
						}
					}				
					html = html + '</Select>'
					html = html +'</td></tr>'
				}
			}
		}		
		html = html + '</tr>';
	}
	html = html + '</table>'
	$('#modal-title').html("Sửa đề tài");
	$('#frm-content').html(html);
	$('#frm').modal('show');
	sleep(500).then(() => {
		DATA_IDX = record;
		soLuongVaiTro(record)
	});
}

function soLuongVaiTro(th1){
	th = DATA_IDX
	sl = $('#soLuong').val();
	html = '<table width=100%>';
	for(i = 0; i < sl; i++){
		html = html + '<tr><td width="40%">Vai trò thành viên '+(i+1)+'</td><td>'
		html = html + '<Select id="vaiTro'+i+'">'
		for(j=0; j < cfgKhoa.length; j++){
			html = html + '<option value="">'+cfgKhoa[j].tenKhoa+'</option>'
			for(k=0; k < cfgKhoa[j].vaiTro.length; k++){
				if (action=='edit'){
					dsVT = '';
					try{
						maVT = th.dsThucHien[i].maVaiTro;
					} catch(e){}
					console.log(action+', '+ maVT + '->' + cfgKhoa[j].vaiTro[k].maVaiTro)
					if (maVT == cfgKhoa[j].vaiTro[k].maVaiTro){
						html = html + '<option value="'+cfgKhoa[j].vaiTro[k].maVaiTro+'" selected>&nbsp;&nbsp;+ '+cfgKhoa[j].vaiTro[k].tenVaiTro+'</option>'						
					} else {
						html = html + '<option value="'+cfgKhoa[j].vaiTro[k].maVaiTro+'" >&nbsp;&nbsp;+ '+cfgKhoa[j].vaiTro[k].tenVaiTro+'</option>'						
					}
				} else {
					html = html + '<option value="'+cfgKhoa[j].vaiTro[k].maVaiTro+'">&nbsp;&nbsp;+ '+cfgKhoa[j].vaiTro[k].tenVaiTro+'</option>'					
				}
			}
		}
		maNguoi = '';
		try{
			maNguoi = th.dsThucHien[i].maNguoi;
		} catch(e){}
		if (action=='edit'){
			html = html + '</td><td><input id="maNguoi'+i+'" type="text" size=8px value="'+maNguoi+'"/></td>'
		}
		html = html + '</tr>'
		html = html + '</Select>'			
	}
	html = html + '</table>'	

	$('#frm-content-detail').html(html);
}
function xuLyTrangThai(_this){
	if (_this.value == 18){
		
	}
}
function getVaiTro(maVaiTro){
	for(j=0; j < cfgKhoa.length; j++){
		for(k=0; k < cfgKhoa[j].vaiTro.length; k++){
			if (maVaiTro == cfgKhoa[j].vaiTro[k].maVaiTro){
				return cfgKhoa[j].vaiTro[k].tenVaiTro;
			}
		}
	}
	return 'Không xác định'
}
function sleep (time) {
  return new Promise((resolve) => setTimeout(resolve, time));
}
function getRecordFromID(o,id){
	for(i=0; i< o.length; i++){
		if (o[i].id==id){
			return o[i];
		}
	}
}
function getMaDanhSachVaiTroFromMa(ma){
	for (z = 0; z < cfgHoiDong.length; z++){
		for(j=0; j < cfgHoiDong[z].vaiTro.length; j++){
			if (cfgHoiDong[z].vaiTro[j].maVaiTro==ma){
				return cfgHoiDong[z].vaiTro[j].tenVaiTro;
			}	
		}
	}
	return '';
}
function chamDiem(this_){
	action='chamDiem'
	record=getRecordFromID(DATA,this_.value);
	html1 = '<table>'
	html1 = html1 + '<tr><td>Người chấm</td><td>'
	thuKy = '';
	for(i = 0; i < record.dsHoiDong.length; i++){
		if (record.dsHoiDong[i].maVaiTro == '999'){
			thuKy = record.dsHoiDong[i].maNguoi;
		}
	}
	if (thuKy == tk.username) {
		html1 = html1 + '<Select id="maNguoi1">';
	} else {
		html1 = html1 + '<Select id="maNguoi1" disabled>';
	}
	for(i = 0; i < record.dsHoiDong.length; i++){
		x=getMaDanhSachVaiTroFromMa(record.dsHoiDong[i].maVaiTro);
		if (tk.username == record.dsHoiDong[i].maNguoi){
			html1 = html1 + '<option value="'+record.dsHoiDong[i].maNguoi+'" selected>'+record.dsHoiDong[i].maNguoi+' - '+x+'</option>'
		} else {
			html1 = html1 + '<option value="'+record.dsHoiDong[i].maNguoi+'">'+record.dsHoiDong[i].maNguoi+' - '+x+'</option>'
		}
	}
	html1 = html1 + '</td></tr></table>'
	html1 = html1 + '</select>'
	//console.log(record)
	html = '<table border=1>'	
	html = html + '<tr><td rowspan=2>STT</td><td rowspan=2>Tiêu chí</td><td rowspan=2>Thang điểm</td><td colspan='+record.dsThucHien.length+'>Kết quả đánh giá</td></tr>'
	html = html + '<tr>'
	for(i = 0; i < record.dsThucHien.length; i++){
		html = html + '<td>'+record.dsThucHien[i].maNguoi+'</td>';
	}
	html = html + '</tr>'
		for (i = 0; i < cfgHoiDong.length; i++){
		if (cfgHoiDong[i].maLoaiHoiDong==record.maLoaiDeTai){
			for(j=0; j < cfgHoiDong[i].tieuChi.length; j++){
				html = html + '<tr><td style="text-align: center">'+(j+1)+'</td>';
				html = html + '<td>'+cfgHoiDong[i].tieuChi[j].tenTieuChi+'</td>';
				html = html + '<td style="text-align: center">'+cfgHoiDong[i].tieuChi[j].thangDiem+'</td>';
				for(p = 0; p < record.dsThucHien.length; p++){
					diem = 0;
					try {
						diem = cfgHoiDong[i].tieuChi[j].diem;
					} catch (e){}
					html = html + '<td style="text-align: center">'
					html = html + '<input val="ssss" value="'+cfgHoiDong[i].tieuChi[j].thangDiem+'" style="width:30px" '
					html = html + ' type="text" id='+record.dsThucHien[p].maNguoi+'__'+cfgHoiDong[i].tieuChi[j].maTieuChi+' value="'+diem+'"'
					html = html + ' onchange="tinhDiem(this,record)"></td>';
				}
				html = html + '</tr>'
			}
		}
	}
	html = html + '<tr><td colspan=2 style="text-align: right">Tổng cộng</td><td style="text-align: center">10</td>';
	for(p = 0; p < record.dsThucHien.length; p++){
		html = html + '<td style="text-align: center">'
		html = html + '<span id="'+record.dsThucHien[p].maNguoi+'">10</span>';
		html = html + '</td>'
	}
	html = html + '</td></tr>'
	html = html + '</table>'
	$('#modal-title').html("Chấm điểm");
	//console.log(record)
	$('#frm-content').html(html1);
	$('#frm').modal('show');
	$('#frm-content-detail').html(html);
}
function tinhDiem(this_, record_){
	arr = (this_.id).split('__');
	tong = 0;
	for (i = 0; i < cfgHoiDong.length; i++){
		if (cfgHoiDong[i].maLoaiHoiDong==record.maLoaiDeTai){
			for(j=0; j < cfgHoiDong[i].tieuChi.length; j++){
				id=arr[0]+'__' + cfgHoiDong[i].tieuChi[j].maTieuChi;
				v = $('#'+id).val();
				v = v.split(",").join(".")
				if (v > cfgHoiDong[i].tieuChi[j].thangDiem) {
					alert('('+cfgHoiDong[i].tieuChi[j].tenTieuChi +') vượt quá thang điểm');
					return;
				}
				tong = tong + v*1;
			}
		}
	}
	$('#'+arr[0]).html(tong);
}

