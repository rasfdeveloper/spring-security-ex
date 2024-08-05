package br.com.rf.spring_security_ex.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class Controller {

    @GetMapping("/public/{param}")
    public String publicMethod(@PathVariable String param) {
        return String.format("<h1>Did you said %s?</h1>", param);
    }
    
    @GetMapping("/private")
    public String privateRoute(@AuthenticationPrincipal OidcUser principal) {
        return String.format("""
            <h1> Only authorized person </h1>
            <h3> Principal: %s </h3>
            <h3> Email: %s </h3>
            <h3> Authorities: %s </h3>
            <h3> JWT: %s </h3>
            """
            , principal, principal.getEmail(), principal.getAuthorities(), principal.getIdToken().getTokenValue() );
    }

    @GetMapping("/cookie")
    public String cookie(@AuthenticationPrincipal OidcUser principal) {
        return String.format("""
            <h1> Oauth2 </h1>
            <h3> Principal: %s </h3>
            <h3> Email: %s </h3>
            <h3> Authorities: %s </h3>
            <h3> JWT: %s </h3>
            """
            , principal, principal.getEmail(), principal.getAuthorities(), principal.getIdToken().getTokenValue());
    }

    @GetMapping("jwt")
    public String jwt(@AuthenticationPrincipal Jwt jwt) {
        return String.format("""
                 Principal: %s \n
                 email: %s \n
                 JWT: %s \n
                """, jwt.getClaims(), jwt.getClaim("email"), jwt.getTokenValue());
    }
    
    
    

}
