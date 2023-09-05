package ituniversal.videocourseserver.config;

import ituniversal.videocourseserver.entity.enums.RoleName;
import ituniversal.videocourseserver.security.JwtErrors;
import ituniversal.videocourseserver.security.JwtTokenFilter;
import ituniversal.videocourseserver.service.AuthService;
import ituniversal.videocourseserver.utils.PermitionsUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthService authService;
    private final JwtErrors jwtErrors;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public HttpFirewall allowUrlEncodeHttp() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtErrors)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .antMatchers(
                        "/api/v1/attachment/**",
                        PermitionsUrl.CODE.getUrl(),
                        PermitionsUrl.COURSE.getUrl(),
                        PermitionsUrl.FAQ.getUrl(),
                        PermitionsUrl.SEND_MESSAGE.getUrl(),
                        PermitionsUrl.CONTACT.getUrl(),
                        PermitionsUrl.AUTH.getUrl(),
                        PermitionsUrl.MODULE.getUrl()
                )
                .permitAll()
                .antMatchers("/api/v1/**")
                .authenticated();

        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    public String openUrl() {
        List<String> urls = new ArrayList<>();
        for (PermitionsUrl item : PermitionsUrl.values()) {
            if (item.getAllUrlOpen().equals(RoleName.ADMIN)) {
                urls.add(item.getUrl());
            }
        }
        Object[] objects = urls.toArray();
        String apis = Arrays.toString(objects).substring(1, objects.length - 1);
        System.out.println(apis);
        return Arrays.toString(apis.split(", "));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.httpFirewall(allowUrlEncodeHttp());
    }
}
