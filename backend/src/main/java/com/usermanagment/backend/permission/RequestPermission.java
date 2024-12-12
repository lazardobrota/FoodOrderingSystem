package com.usermanagment.backend.permission;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class RequestPermission {
    public static void authorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeHttp) {
        authorizeHttp.requestMatchers(HttpMethod.POST, "/login").permitAll();
        authorizeHttp.requestMatchers(HttpMethod.GET).hasAuthority(Permission.CanReadUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.GET, "/{id}").hasAuthority(Permission.CanReadUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.POST, "/create").hasAuthority(Permission.CanCreateUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.GET, "/edit/{id}").hasAuthority(Permission.CanUpdateUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.PUT, "/edit/{id}").hasAuthority(Permission.CanUpdateUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.DELETE, "/{id}").hasAuthority(Permission.CanDeleteUsers.getName());
    }
}
