var CFG_KY_MAC_DINH='2526K2';
var TAB_GIANG_VIEN = '';
var TAB_HOC_PHAN = '';
var CFG_NAM_HOC_MAC_DINH='';
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

