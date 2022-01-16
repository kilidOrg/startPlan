package com.kilid.stagetwo.config;

import com.kilid.stagetwo.setting.AdvanceAuthentication;
import com.kilid.stagetwo.setting.AdvanceAuthorization;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("http configure is started {} ");
        AdvanceAuthentication advanceAuthentication = new AdvanceAuthentication(authenticationManagerBean());
        advanceAuthentication.setFilterProcessesUrl("/userApi/login"); /* override*/
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers(POST , "/userApi/login/**").permitAll();
        http.authorizeHttpRequests().antMatchers(POST, "/userApi/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeHttpRequests().antMatchers(POST, "/userApi/addUser/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeHttpRequests().antMatchers(GET,"/userApi/userList/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeHttpRequests().antMatchers(POST,"/userApi/addUserRole/**").hasAnyAuthority("ROLE_USER_ROLE");
//        http.authorizeHttpRequests().antMatchers(POST,"/userApi/addUserSubscription/**").hasAnyAuthority("ROLE_USER_SUBSCRIPTION");

        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(advanceAuthentication);
        http.addFilterBefore(new AdvanceAuthorization(), UsernamePasswordAuthenticationFilter.class);
        log.info("http configure is ok {} ");
    }


    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
