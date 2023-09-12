package com.example.j1.j1.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private  static final String SECRET_Key="ydIElSPMlojRrPzjtyg0GEKEvHtyOlGf";
    public String extractUsername(String token) {

        return extractClaims(token,Claims::getSubject);
    }

    // extract singlke cliam
    public <T> T extractClaims(String token, Function<Claims,T> claimresolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimresolver.apply(claims);
    }
    public String generateToken(UserDetails userDetails)
    {
        return  generateToken(new HashMap<>(),userDetails);
    }
public  String generateToken(
        Map<String, Object> extraClaims, UserDetails userDetails)
{
    return  Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact();
}
    public String generateRefreshToken(UserDetails userDetails) {
        //return buildToken(new HashMap<>(), userDetails, refreshExpiration);
        return  null;
    }

public boolean isTokenValid(String token, UserDetails userDetails)
{
    final String username=extractUsername(token);
    return (username.equals(userDetails.getUsername()))&&!isTokenExpired(token);
}

    private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return  extractClaims(token, Claims::getExpiration);
    }

    // extrat all claims
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build().parseClaimsJws(token).getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode(SECRET_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}