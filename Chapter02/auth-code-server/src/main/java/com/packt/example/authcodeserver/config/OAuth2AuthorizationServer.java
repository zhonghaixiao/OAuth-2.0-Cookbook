package com.packt.example.authcodeserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServer extends
        AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        //@formatter:off
        clients.inMemory()
                .withClient("clientapp")
                .secret("123456")
                .redirectUris("http://localhost:9000/callback")
                .authorizedGrantTypes("authorization_code", "refresh_token")
                .accessTokenValiditySeconds(120)
                .scopes("read_profile", "read_contacts")
                .and()
                .withClient("clientappimplicit")
                .secret("123456")
                .redirectUris("http://localhost:9000/callback")
                .authorizedGrantTypes("implicit", "password", "refresh_token")
                .accessTokenValiditySeconds(120)
                .scopes("read_profile", "read_contacts")
                .and()
                .withClient("clientadmin")
                .secret("123456")
                .authorizedGrantTypes("client_credentials")
                .scopes("admin");
        //@formatter:on
    }

}
