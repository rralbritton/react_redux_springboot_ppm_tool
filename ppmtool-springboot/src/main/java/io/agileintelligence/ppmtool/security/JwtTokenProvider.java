package io.agileintelligence.ppmtool.security;

import io.agileintelligence.ppmtool.models.security.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static io.agileintelligence.ppmtool.security.SecurityConstant.EXPIRATION_TIME;
import static io.agileintelligence.ppmtool.security.SecurityConstant.SECRET;

@Component
public class JwtTokenProvider {

    //Generate token: method that will generate token with username/password = valid
    public String generateToken(Authentication authentication){//make sure that the Authentication parameter comes from the spring core package
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
        String userId = Long.toString(user.getId());

        //Note: Set Claims can be a string or a map. This example uses a map
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        /*The claims is where role would go as well
        * ie. claims.put("role", user.getRole());*/

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)//Research this - many options
                .compact();
    }

    //Validate token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch(SignatureException ex){
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        } catch (ExpiredJwtException ex){
            System.out.println("Expired JWT Token");
        } catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }

    //Get userId from token
    public Long getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
}
