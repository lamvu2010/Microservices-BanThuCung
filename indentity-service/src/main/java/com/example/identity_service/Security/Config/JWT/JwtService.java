package com.example.identity_service.Security.Config.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET_KEY = "TzRpsApTWcNu68Qqv77B8v7stxT8VKwn6aVxYX1JNURSmbYfNFiiiKPZl3sicadNquBtoDrfpju2zphLWBfLkMqkEjNpt80PtakEAOhiMZoYoWuZ31BgWuBOewD0lIlZTKPcwTIcSUjRLoskFcEW5+FlVFPcQ9KG4Zo2ASUZHICFm3+58ktGBHVVjjK2Miq1NTb6b7mWgUbmxfz6LS7S8wEID8zE0ltTrUK8Cy6tL1HyXZercHBpHOXmDrMajwYkz1Egk9vwpE8oF3MmmI3YUX3az61G7BQp1noDdT8zAZLxNBtEl9ZjZEBHnO8KJQ9hok2Q+IGjYO3JS2DPo8AyVA==";

    public boolean isTokenValid(String token,String username){
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String createToken(Map<String,Object> claims, String subject,String role){
        claims.put("role",role);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*10))
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
