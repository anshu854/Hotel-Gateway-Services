package com.lcwd.gateway.Controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.gateway.model.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient aClient,
			@AuthenticationPrincipal OidcUser user,
			Model model
			
			){
		
		logger.info("User Email id is {}",user.getEmail());
		//creating auth response object
		AuthResponse authResponse = new AuthResponse();
		//setting email to authresponse
		authResponse.setUserId(user.getEmail());
		//setting token to authresponse
		authResponse.setAccessToken(aClient.getAccessToken().getTokenValue());
		//setting referesh token to authresponse
		authResponse.setRefereshToken(aClient.getRefreshToken().getTokenValue());
		//setting expireat to authresponse
		authResponse.setExpireAt(aClient.getAccessToken().getExpiresAt().getEpochSecond());
		
		List<String> authority = user.getAuthorities().stream().map(GrantedAuthority -> {
			return GrantedAuthority.getAuthority();
		}).collect(Collectors.toList());
		authResponse.setAuthorities(authority);
		
		return new ResponseEntity<>(authResponse,HttpStatus.OK);
		
	}
	
}
