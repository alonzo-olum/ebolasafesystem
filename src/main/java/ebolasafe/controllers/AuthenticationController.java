package ebolasafe.controllers;
import java.security.Principal;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestMapping("/rest/authentication")
@RestController
public class AuthenticationController {
    ObjectMapper mapper = new ObjectMapper();
        
    
    @RequestMapping(method=RequestMethod.GET)
    public Principal authenticate(Principal user) throws JsonProcessingException {
       
    	   return user;
       
    }

    @ExceptionHandler(value={SecurityException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleSecurityException() {
    }
}