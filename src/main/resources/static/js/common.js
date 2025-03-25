var CFG_KY_MAC_DINH='2526K2';
var TAB_GIANG_VIEN = '';
var TAB_HOC_PHAN = '';
var CFG_NAM_HOC_MAC_DINH='';
THP=JSON.parse(localStorage.getItem('TAB_HOC_PHAN'));
TGV=JSON.parse(localStorage.getItem('TAB_GIANG_VIEN'));
CA_THI=JSON.parse(localStorage.getItem('TAB_CA_THI'));
PHONG_HOC=JSON.parse(localStorage.getItem('TAB_PHONG_HOC'));
HINH_THUC_THI=JSON.parse(localStorage.getItem('TAB_HINH_THUC_THI'));
TKY=JSON.parse(localStorage.getItem('TAB_KY'));
//-------------------------------------------------------------------------------------------------------------------------
var TAB_KY=JSON.parse(localStorage.getItem('TAB_KY'));
function getKyMacDinh() {
	for(i = 0; i < TAB_KY.length; i++){
		if(TAB_KY[i].macDinh == 1){
			return TAB_KY[i];
		}
	}
}
function getTenKy(maKy) {
	for(i = 0; i < TAB_KY.length; i++){
		if(TAB_KY[i].maKy == maKy){
			return TAB_KY[i];
		}
	}
}
//-------------------------------------------------------------------------------------------------------------------------
var URL = '/api/v1'
tk=JSON.parse(localStorage.getItem('taiKhoan'));
//-------------------------------------------------------------------------------------------------------------------------
function formatDate(date, daysToAdd = 0) {
    let d = new Date(date);
    d.setDate(d.getDate() + daysToAdd);
    let day = ("0" + d.getDate()).slice(-2);
    let month = ("0" + (d.getMonth() + 1)).slice(-2);
    return `${d.getFullYear()}-${month}-${day}`;
}
//-------------------------------------------------------------------------------------------------------------------------
var TAB_HOC_PHAN=JSON.parse(localStorage.getItem('TAB_HOC_PHAN'));
function getHocPhan(maHocPhan) {
	for(i = 0; i < TAB_HOC_PHAN.length; i++){
		if(TAB_HOC_PHAN[i].maHocPhan == maHocPhan){
			return TAB_HOC_PHAN[i];
		}
	}
	var hocPhan = {
       maHocPhan: maHocPhan,
       tenHocPhan: 'Chưa xác định'
   };
	return hocPhan
}
//-
//-------------------------------------------------------------------------------------------------------------------------
var TAB_GIANG_VIEN=JSON.parse(localStorage.getItem('TAB_GIANG_VIEN'));
function getGiangVien(maGiangVien) {
	for(i = 0; i < TAB_GIANG_VIEN.length; i++){
		if(TAB_GIANG_VIEN[i].maGiangVien == maGiangVien){
			return TAB_GIANG_VIEN[i];
		}
	}
}
//-------------------------------------------------------------------------------------------------------------------------
var TAB_CA_THI=JSON.parse(localStorage.getItem('TAB_CA_THI'));
function getCaThi(caThi) {
	for(i = 0; i < TAB_CA_THI.length; i++){
		if(TAB_CA_THI[i].id == caThi){
			return TAB_CA_THI[i];
		}
	}
}
function checkResponse(resp) {
	if (resp == -9){
		alert("Phiên truy cập không hợp lệ, vui lòng đăng nhập lại, xin cám ơn!")
		window.location.replace("login.html");
		return;
	}	
}
//---------------------------------------------------------------------------------------------
function hienTkb(user,doiTuong){
	let userName = '';
	if (tk.userName == user) {
		userName = user;
	} else {
		userName = $('#maGiangVien').val()===undefined?$('#maSinhVien').val():$('#maGiangVien').val();
	}
	data = {userName : userName, doiTuong: doiTuong}
	$.ajax({
		url 		: URL + '/tkb/caNhan',
		type		: 'POST',
		data		: JSON.stringify(data),	
		contentType : 'application/json; charset=UTF-8',
		success : function(resp) {
			checkResponse(resp);
			console.log(resp)
			DATA = resp;
			let txt = '<table border=1>';
			txt += '<tr>'
			txt += '<td align="center">Thứ</td>'
			for(j=1; j < 14; j++){
				txt += '<td align="center">'+j+'</td>'
			}
			txt += '</tr>'
			const myMap = new Map();
			const myCho = new Map();
			let tongSoTin = 0;
			for (let i = 2; i < 9; i++) {
			    txt += '<tr><td align="center">' + i;
			    let j = 1;			    
			    while (j < 14) {
			        let check = 0;
			        let rcord = '';
			        let bgcolor = '';

			        for (let k = 0; k < DATA.length; k++) {
			            if (DATA[k].thu == i && j >= DATA[k].batDau && j <= DATA[k].ketThuc) {
			                check = (DATA[k].ketThuc - DATA[k].batDau) + 1;
			                hp = DATA[k];
			                break;
			            }
			        }
			        if (check > 0) {
			            if (!myMap.has(hp.maLopHocPhan)) {
							while(true){
								bgcolor = getRandomColor();								
								if(myCho.has(bgcolor)) {
									continue;
								} else {
									break;
								}
							}
			                myMap.set(hp.maLopHocPhan, bgcolor);
							let hocPhanTemp1 = getHocPhan(hp.maHocPhan)
							tongSoTin += hocPhanTemp1.soTinChi;
							myCho.set(bgcolor,"");
			            } else {
			                bgcolor = myMap.get(hp.maLopHocPhan);
			            }
						let lopHocPhanTem = hp.maLopHocPhan;
						let kyID = lopHocPhanTem.substring(0,3);
						let hocPhanID = lopHocPhanTem.substring(3,8);
						let lopID = lopHocPhanTem.substring(8,10);
						hpTen = getHocPhan(hp.maHocPhan);
						hp.maKy = (hp.maKy).split("2425").join("");
			            txt += `<td colspan="${check}" align="center" width="7%" title="${lopHocPhanTem} - ${hpTen.tenHocPhan} (${hpTen.soTinChi} tín chỉ)"
						style="
						background-color: ${bgcolor.bg}; 
						color: ${bgcolor.cl};
						border-color: ${bgcolor.bg}
						">${kyID}.${hocPhanID}.${lopID}.${hp.phong}.${hp.maKy}</td>`;
			            j += check; // Nhảy đến tiết tiếp theo
			        } else {
			            txt += '<td width="7%">&nbsp;</td>';
			            j++; // Duyệt tiếp
			        }
			    }
			    txt += '</tr>';
			}
			txt += '</table>';
			$('#dTable').html(txt);			
			$('#tkbTitle').html('Thời khóa biểu: <font color="red">' + myMap.size + ' học phần (' + tongSoTin + ' tín chỉ)</font>  ');
		}
	});
}
//---------------------------------------------------------------------------------------------
const colors = [ 
	{"bg":"#CC99FF","cl":"#D9D9D9"},
	{"bg":"#FFD154","cl":"#002795"},
	{"bg":"#6D24CF","cl":"#FFE06F"},
	{"bg":"#68D69D","cl":"#401D83"},
	{"bg":"#FF9240","cl":"#FFFFA9"},
	{"bg":"#0063EC","cl":"#FFFFFF"},
	{"bg":"#D9D9D9","cl":"#C20000"},
	{"bg":"#FF83A9","cl":"#FFFFFF"},
	{"bg":"#3D3D3D","cl":"#FFD154"},
	{"bg":"#D4B5FF","cl":"#002795"},
	{"bg":"#96D6FF","cl":"#FFFFFF"},
	{"bg":"#FFC0B9","cl":"#FFFFC5"}
	]
function getRandomColor() {
  return colors[Math.floor(Math.random() * colors.length)];
}
function getRandomFont(str) {
  return fonts[Math.floor(Math.random() * colors.length)];
}
//
function khoiTao() {
	    TAB = ['TAB_GIANG_VIEN', 'TAB_HOC_PHAN', 'TAB_CA_HOC', 'TAB_CA_THI','TAB_PHONG_HOC','TAB_HINH_THUC_THI','TAB_KY'];
	    API = ['giangvien', 'hocphan', 'cahoc', 'cathi','phonghoc','hinhthucthi','ky'];

	    for (let i = 0; i < TAB.length; i++) {
	        let tabName = TAB[i];
	        let apiEndpoint = API[i];

	        $.ajax({
	            url: URL + '/' + apiEndpoint, // Gọi API tương ứng với index
	            type: 'GET',
	            dataType: 'json',
	            contentType: 'application/json; charset=UTF-8',
	            success: function (resp) {
	                let DATA = JSON.parse(JSON.stringify(resp), (key, value) => (value == null ? '' : value));
	                localStorage.setItem(tabName, JSON.stringify(DATA));
	            },
	            error: function (xhr, status, error) {
	                console.error('Lỗi khi gọi API:', apiEndpoint, error);
	            }
	        });
	    }
	}
//-------------------------------------------------------------------------------------------------------------------------
function includeHTML() {
  var z, i, elmnt, file, xhttp;
  /* Loop through a collection of all HTML elements: */
  z = document.getElementsByTagName("*");
  for (i = 0; i < z.length; i++) {
    elmnt = z[i];
    /*search for elements with a certain atrribute:*/
    file = elmnt.getAttribute("w3-include-html");
    if (file) {
      /* Make an HTTP request using the attribute value as the file name: */
      xhttp = new XMLHttpRequest();
      xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
          if (this.status == 200) {elmnt.innerHTML = this.responseText;}
          if (this.status == 404) {elmnt.innerHTML = "Page not found.";}
          /* Remove the attribute, and call this function once more: */
          elmnt.removeAttribute("w3-include-html");
          includeHTML();
        }
      }
	  
      xhttp.open("GET", file, true);
      xhttp.send();
      /* Exit the function: */
      return;
    }
  }
}
//-------------------------------------------------------------------------------------------------------------------------

var loading ='			<div class="progress" style="text-align:center;size:20pt">'
		loading =loading + '	<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">'
		loading =loading + '		<span class="sr-only">45% Complete</span>'
		loading =loading + '	</div>'
		loading =loading + '</div>'

