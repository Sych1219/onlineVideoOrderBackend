package net.xdclass.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.xdclass.demo.model.entity.User;

import java.util.Date;

public class JWTUtils {

    private static final long EXPIRE = 60000 * 60 * 24 * 7;
//    private static final long EXPIRE =1;


    private static final String SECRET = "xdclass.sun";

    private static final String PREFIX = "xdclass";

    private static final String SUBJECT = "xdclass";

    public static String getWebToken(User user){
       String token = Jwts.builder().setSubject(SUBJECT)
                .claim("id",user.getId())
                .claim("name",user.getName())
                .claim("head_img",user.getHeadImg())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();

       token = PREFIX + token;
       return token;
    }

    public static Claims checkJWT(String token){
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(PREFIX,""))
                    .getBody();
            return claims;
        }catch (Exception e){
            return null;
        }

    }

}
