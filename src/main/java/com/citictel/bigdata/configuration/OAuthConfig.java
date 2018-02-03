package com.citictel.bigdata.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
public class OAuthConfig {

    @Bean
    public RestTemplate oAuthRestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setId("1");

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());

        return restTemplate;
    }
}
