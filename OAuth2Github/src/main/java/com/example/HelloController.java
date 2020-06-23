package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
class HelloController {
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;

	@GetMapping(value="/")
	public String sayHello(@AuthenticationPrincipal OAuth2User principal) {
		return "Hello, " + principal.getAttribute("name");
	}

	@GetMapping(value="/getUserInfo", produces="application/json")
	public ResponseEntity<String> getUserInfo(OAuth2AuthenticationToken authentication){
		OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
				authentication.getAuthorizedClientRegistrationId(),
				authentication.getPrincipal().getName());

		// get UserInfoEndpoint from ClientRegistration
		// For Github, It is defined in CommonOAuth2Provider class. The value should be https://api.github.com/user
		String userInfoEndpointUri = client.getClientRegistration()
				.getProviderDetails().getUserInfoEndpoint().getUri();

		// send request to UserInfoEndpoint to get user info
		if(!StringUtils.isEmpty(userInfoEndpointUri)) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
			HttpEntity entity = new HttpEntity("", headers);
			ResponseEntity response  = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, String.class);
			return  ResponseEntity.ok().body(response.getBody().toString());
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
