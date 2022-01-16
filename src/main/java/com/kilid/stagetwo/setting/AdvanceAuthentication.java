package com.kilid.stagetwo.setting;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class AdvanceAuthentication extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public AdvanceAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken userNamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,password);
        return this.authenticationManager.authenticate(userNamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(request.getRequestURI())
                .withClaim("roles",user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()))
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(request.getRequestURI())
                .sign(algorithm);
        Map<String , String> tokenList = new HashMap<>();
        tokenList.put("accessToken",accessToken);
        tokenList.put("refreshToken" , refreshToken);
        response.setHeader("accessToken",accessToken);
        response.setHeader("refreshToken" , refreshToken);
        response.addCookie(new Cookie("accessToken",accessToken));
        response.addCookie(new Cookie("refreshToken" , refreshToken));
        response.setContentType(APPLICATION_JSON_VALUE );  // "application/json"
        new ObjectMapper().writeValue(response.getOutputStream() , tokenList);
    }
}
