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

	@GetMapping(value="/getAuthentication", produces="application/json")
	public String getAuthentication(OAuth2AuthenticationToken authenticaiton) {
		return  authenticaiton.toString();
	}

	@GetMapping(value = "/getEmails", produces = "application/json")
	public String getEmails(OAuth2AuthenticationToken authentication) {
		OAuth2AuthorizedClient client = authorizedClientService.loadAuthorizedClient(
				authentication.getAuthorizedClientRegistrationId(),
				authentication.getPrincipal().getName());

		String getEmailUrl = "https://api.github.com/user/emails";

		// send HTTP request with Bearer token to get user emails
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken().getTokenValue());
		HttpEntity entity = new HttpEntity("", headers);
		ResponseEntity response = restTemplate.exchange(getEmailUrl, HttpMethod.GET, entity, String.class);
		return response.getBody().toString();
	}
}
