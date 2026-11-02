package com.example.demo.config;

import com.example.demo.security.CustomAccessDeniedHandler;
import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: Cấu hình Spring Security, Phân quyền API 401/403 và mã hóa BCrypt
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                          CustomAccessDeniedHandler customAccessDeniedHandler) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.customAccessDeniedHandler = customAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Bắt lỗi 401 Unauthorized
                .accessDeniedHandler(customAccessDeniedHandler)         // Bắt lỗi 403 Forbidden
            )
            .authorizeHttpRequests(auth -> auth
                // 1. Các đường dẫn công khai (Public)
                .requestMatchers(
                    "/api/tai-khoan/dang-nhap",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/",
                    "/index.html",
                    "/static/**",
                    "/error"
                ).permitAll()

                // 2. Phân quyền chỉ dành riêng cho ADMIN
                .requestMatchers("/api/phan-quyen/**").hasAnyAuthority("ADMIN", "ROLE_ADMIN")

                // 3. Cho phép các nghiệp vụ bàn ăn, thực đơn, gọi món, kho hàng, thanh toán và nhân sự hoạt động thông suốt
                .requestMatchers(
                    "/api/ban-an/**",
                    "/api/don-hang/**",
                    "/api/mon-an/**",
                    "/api/danh-muc-mon-an/**",
                    "/api/cong-thuc-mon/**",
                    "/api/nguyen-lieu/**",
                    "/api/nha-cung-cap/**",
                    "/api/phieu-nhap-xuat/**",
                    "/api/thanh-toan/**",
                    "/api/nhan-vien/**",
                    "/api/nguoi-dung/**",
                    "/api/tai-khoan/**"
                ).permitAll()

                // 5. Các API tài khoản khác yêu cầu phải đăng nhập hợp lệ
                .requestMatchers("/api/**").authenticated()

                // Mọi tài nguyên khác cho phép truy cập
                .anyRequest().permitAll()
            );

        // Đặt bộ lọc JWT trước bộ lọc UsernamePassword của Spring Security
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
