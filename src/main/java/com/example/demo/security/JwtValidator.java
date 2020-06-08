package com.example.demo.security;

import org.springframework.stereotype.Component;

import com.example.demo.Commons.Constants;
import com.example.demo.Models.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		
		try {
			Claims body = Jwts.parser().setSigningKey(Constants.YOUR_SECRET).parseClaimsJws(token).getBody();
			jwtUser = new JwtUser();
			jwtUser.setUsername(body.getSubject());
			jwtUser.setId(Long.parseLong((String)body.get(Constants.USER_ID)));
			jwtUser.setRole((String) body.get(Constants.ROLE));
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return jwtUser;
	}

}
