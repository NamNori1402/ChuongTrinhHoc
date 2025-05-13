package com.thanglong.chonlichthilai.dangnhap;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(2)
public class RequestResponseLoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		System.out.println("Logging Request " + req.getMethod() + ", " + req.getRequestURI());

		String uri = req.getRequestURI();
//		if (uri.indexOf("api") >=0) {
//			PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
//			if (phienKetNoi == null) {
//				phienKhongHopLe(res,"-9");
//				return;
//			}
//		}
//		String api [] = {"/ky","/tkb"};
		String api [] = {};
		for(int i = 0; i < api.length; i++) {
			if (uri.startsWith(api[i]) && !uri.endsWith(".html")) {
				PhienKetNoi phienKetNoi = (PhienKetNoi) req.getSession().getAttribute("phienKetNoi");
				if (phienKetNoi == null) {
					phienKhongHopLe(res,"-9");
					return;
				} else if (req.getMethod().equals("POST") || req.getMethod().equals("DELETE")|| req.getMethod().equals("PUT")) {
					if(
							phienKetNoi.getDsQuyen().equals("quanTri") ||
							phienKetNoi.getDsQuyen().equals("thuKy") ||
							phienKetNoi.getDsQuyen().equals("giangVien")

					 ) {
						System.out.println("Phien truy cap hop le");
					} else {
						phienKhongHopLe(res,"-8");
						return;
					}
				}
			}
		}	
		chain.doFilter(request, response);
		System.out.println("Logging Response : " + res.getContentType());
	}

	void phienKhongHopLe(HttpServletResponse res, String code) throws IOException {
		System.out.println("Không tìm thấy đối tượng phienKetNoi trong session, chuyển hướng đến login.html");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(code);
		res.getWriter().flush();
		return;
	}
	// other methods
}
