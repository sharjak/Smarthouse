package demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

public class OAuth2ServerConfig {

//        private static final String APP_RESOURCE_ID = "demo";

        @Configuration
        @EnableResourceServer
        protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
/*
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) {
                resources.resourceId(APP_RESOURCE_ID).stateless(false);
            }*/

            @Override
            public void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests().anyRequest().authenticated();
            }

        }

    @Configuration
    @EnableAuthorizationServer
    @Order(2)
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;

        @Autowired
        private UserApprovalHandler userApprovalHandler;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients.inMemory().withClient("my-trusted-client")
                    .authorizedGrantTypes("password","authorization_code","refresh_token", "implicit")
                    .authorities("ROLE_CLIENT","ROLE_TRUSTED_CLIENT","ROLE_USER")
                    .scopes("read", "write", "trust")
                    .resourceIds("oauth2-resource")
                    .secret("secret")
                    .accessTokenValiditySeconds(6000)
                    .and()

                    .withClient("my-client-with-registered-redirect")
                    .authorizedGrantTypes("authorization_code")
                    .authorities("ROLE_CLIENT")
                    .scopes("read","trust", "write")
                    .resourceIds("oauth2-resource")
                    .redirectUris("http://anywhere?key=value")
                    .accessTokenValiditySeconds(6000)
                    .and()
                    .withClient("my-client-with-secret")
                    .authorizedGrantTypes("client_credentials","password")
                    .authorities("ROLE_CLIENT")
                    .scopes("read", "trust", "write")
                    .resourceIds("oauth2-resource")
                    .secret("secret")
                    .accessTokenValiditySeconds(6000);
        }

        @Bean
        public TokenStore tokenStore() {
            return new InMemoryTokenStore();
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
                    .authenticationManager(authenticationManager)
                    .tokenStore(tokenStore)
                    .userApprovalHandler(userApprovalHandler);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.realm("demo/client");
        }
    }

    protected static class Properties {

        @Autowired
        private ClientDetailsService clientDetailsService;

        @Autowired
        private TokenStore tokenStore;

        @Bean
        public ApprovalStore approvalStore() throws Exception {
            TokenApprovalStore store = new TokenApprovalStore();
            store.setTokenStore(tokenStore);
            return store;
        }
    }
}
