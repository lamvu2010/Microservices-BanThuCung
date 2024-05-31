package com.example.apigateway.Security.Config.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "TzRpsApTWcNu68Qqv77B8v7stxT8VKwn6aVxYX1JNURSmbYfNFiiiKPZl3sicadNquBtoDrfpju2zphLWBfLkMqkEjNpt80PtakEAOhiMZoYoWuZ31BgWuBOewD0lIlZTKPcwTIcSUjRLoskFcEW5+FlVFPcQ9KG4Zo2ASUZHICFm3+58ktGBHVVjjK2Miq1NTb6b7mWgUbmxfz6LS7S8wEID8zE0ltTrUK8Cy6tL1HyXZercHBpHOXmDrMajwYkz1Egk9vwpE8oF3MmmI3YUX3az61G7BQp1noDdT8zAZLxNBtEl9ZjZEBHnO8KJQ9hok2Q+IGjYO3JS2DPo8AyVA==";

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token);
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
