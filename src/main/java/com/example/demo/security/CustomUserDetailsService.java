package com.example.demo.security;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: Load thông tin người dùng và quyền hạn từ Database cho Spring Security
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TaiKhoanRepository taiKhoanRepository;

    public CustomUserDetailsService(TaiKhoanRepository taiKhoanRepository) {
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan tk = taiKhoanRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy tài khoản: " + username));

        String role = (tk.getPhanQuyen() != null && tk.getPhanQuyen().getTenPq() != null)
                ? tk.getPhanQuyen().getTenPq()
                : "USER";

        boolean enabled = (tk.getTrangThai() == null || tk.getTrangThai() == 1);

        java.util.List<org.springframework.security.core.GrantedAuthority> authorities = new java.util.ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        if (!role.startsWith("ROLE_")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new User(
                tk.getUsername(),
                tk.getPassword(),
                enabled,
                true,
                true,
                true,
                authorities
        );
    }
}
