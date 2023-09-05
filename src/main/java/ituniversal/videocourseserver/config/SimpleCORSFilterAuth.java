package ituniversal.videocourseserver.config;

import io.jsonwebtoken.Jwts;
import ituniversal.videocourseserver.payload.ApiResponse;
import ituniversal.videocourseserver.repository.AuthRepository;
import ituniversal.videocourseserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class SimpleCORSFilterAuth extends CorsFilter {

    @Value("${app.jwtSecretKey}")
    private String key;

    private final AuthService authService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Methods,Access-Control-Allow-Origin,x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
        String token = request.getHeader("Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
//            permitions(token);
        } else {
            try {
                if (!resp.isCommitted()) {
                    chain.doFilter(req, resp);
//                    permitions(token);
                }
            } catch (IOException | ServletException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
            }
        }
    }

    public ApiResponse<?> permitions(String token) {
        String userId = getUserIdTokenningIchidan(token);
        UserDetails user = authService.getUserById(UUID.fromString(userId));
        Object role = user.getAuthorities().toArray()[0];
        if ("ADMIN".equals(role) || role.equals("DIRECTOR")) {
            return new ApiResponse<>("admin kirish ruxsat berildi", true);
        } else if (role.equals("USER")) {
            return new ApiResponse<>("user kirish ruxsat berildi", true);
        }
        return null;
    }

    public String getUserIdTokenningIchidan(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}