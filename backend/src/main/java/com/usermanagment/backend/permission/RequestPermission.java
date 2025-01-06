package com.usermanagment.backend.permission;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;

public class RequestPermission {
    public static void authorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authorizeHttp) {
        //Users
        authorizeHttp.requestMatchers(HttpMethod.OPTIONS, "/user/**").permitAll();
        authorizeHttp.requestMatchers(HttpMethod.POST, "/user/login").permitAll();
        authorizeHttp.requestMatchers(HttpMethod.GET, "/user/all").hasAuthority(UserPermission.CanReadUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.GET, "/user/{id}").hasAuthority(UserPermission.CanReadUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.POST, "/user/create").hasAuthority(UserPermission.CanCreateUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.GET, "/user/edit/{id}").hasAuthority(UserPermission.CanUpdateUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.PUT, "/user/edit/{id}").hasAuthority(UserPermission.CanUpdateUsers.getName());
        authorizeHttp.requestMatchers(HttpMethod.DELETE, "/user/{id}").hasAuthority(UserPermission.CanDeleteUsers.getName());

        //Orders
        authorizeHttp.requestMatchers(HttpMethod.GET, "/order").hasAuthority(UserPermission.CanTrackOrder.getName());
        authorizeHttp.requestMatchers(HttpMethod.POST, "/order/create").hasAuthority(UserPermission.CanPlaceOrder.getName());
    }
}
