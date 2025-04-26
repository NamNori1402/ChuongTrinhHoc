package com.thanglong.chonlichthilai.utils;

import com.thanglong.chonlichthilai.dangnhap.PhienKetNoi;

import jakarta.servlet.http.HttpServletRequest;

public class AuthorizationUtils {

    // Kiểm tra xem đã đăng nhập hay chưa
    public static boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("phienKetNoi") != null;
    }

    // Lấy đối tượng PhienKetNoi từ session (nếu có)
    public static PhienKetNoi getCurrentUser(HttpServletRequest request) {
        Object sessionObj = request.getSession().getAttribute("phienKetNoi");
        if (sessionObj instanceof PhienKetNoi phien) {
            return phien;
        }
        return null;
    }

    // Kiểm tra quyền cụ thể
    public static boolean hasRole(HttpServletRequest request, String role) {
        PhienKetNoi phien = getCurrentUser(request);
        return phien != null && role.equals(phien.getDsQuyen());
    }

    public static boolean isSinhVien(HttpServletRequest request) {
        return hasRole(request, "sinhVien");
    }

    public static boolean isGiangVien(HttpServletRequest request) {
        return hasRole(request, "giangVien");
    }

    public static boolean isThuKy(HttpServletRequest request) {
        return hasRole(request, "thuKy");
    }

    public static boolean isQuanTri(HttpServletRequest request) {
        return hasRole(request, "quanTri");
    }

    // Người có quyền admin gồm cả quanTri và thuKy
    public static boolean isAdmin(HttpServletRequest request) {
        return isQuanTri(request) || isThuKy(request);
    }
}
