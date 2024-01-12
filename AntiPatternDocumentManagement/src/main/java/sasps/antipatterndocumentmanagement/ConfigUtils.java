package sasps.antipatterndocumentmanagement;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;



@Configuration
public class ConfigUtils {

    @Configuration
    public static class DaoAuthenticationProviderConfig {

        @Qualifier("utils")
        @Autowired
        public UserDetailsService userDetailsService;

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService);
            authProvider.setPasswordEncoder(new BCryptPasswordEncoder());
            return authProvider;
        }
    }

    @Component
    public static class JwtConfig {
        private final KeyPair keyPair = generateKeyPair();

        private KeyPair generateKeyPair() {
            try {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
                keyPairGenerator.initialize(2048); // Key size
                return keyPairGenerator.generateKeyPair();
            } catch (Exception e) {
                throw new RuntimeException("Error generating KeyPair", e);
            }
        }

        public String generateToken(String username){
            Map<String, Object> claims = new HashMap<>();
            return createToken(claims,username);
        }

        private String createToken(Map<String,Object> claims, String subject){
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                    .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                    .compact();
        }

        public Boolean validateToken(String token, UserDetails userDetails){
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

        private Boolean isTokenExpired(String token){
            return extractExpiration(token).before(new Date());
        }

        public String extractUsername(String token){
            return extractClaim(token, Claims::getSubject);
        }

        public Date extractExpiration(String token){
            return extractClaim(token, Claims::getExpiration);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token){
            return Jwts
                    .parserBuilder()
                    .setSigningKey(keyPair.getPublic())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
    }

    @Configuration
    @Import(DaoAuthenticationProviderConfig.class)
    public static class AppConfig {
        @Bean
        public AuthenticationManager authenticationManagerBean(DaoAuthenticationProvider authenticationProvider) {
            return new ProviderManager(List.of(authenticationProvider));
        }
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
