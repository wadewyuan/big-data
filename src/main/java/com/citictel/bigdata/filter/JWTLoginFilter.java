package com.citictel.bigdata.filter;

import com.citictel.bigdata.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        int expireIn = 60 * 60 * 24 * 1000;

        String token = Jwts.builder()
                .setSubject(((com.citictel.bigdata.domain.User) auth.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + expireIn))
                .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
                .compact();

        Map<String, String> body = new HashMap<>();
        body.put("access_token", token);
        body.put("token_type", "bearer");
        body.put("scope", "read write");
        body.put("expires_in", String.valueOf(expireIn / 1000));

        String json = new ObjectMapper().writeValueAsString(body);

        res.addHeader("Authorization", "Bearer " + token);
        res.getWriter().write(json);
    }

}