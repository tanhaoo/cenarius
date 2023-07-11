package com.th.cola.adapter.config;

import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * @author Aaron
 */
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = SpringDocConfiguration.SECURITY_NAME,
        scheme = "Bearer", in = SecuritySchemeIn.HEADER, paramName = HttpHeaders.AUTHORIZATION)
@OpenAPIDefinition(
        info = @Info(
                title = "Cenarius-Cola",
                version = "v1",
                description = "API of Cenarius.",
                contact = @Contact(name = "Aaron", email = "514787498@qq.com")
        ),
        servers = {
                @Server(url = "http://localhost:8080/", description = "Local Server"),
//                @Server(url = "https://lab-api.raqn.io/dcp-store/mkp/", description = "MKP Project Dev Server.")
        }
)
public class SpringDocConfiguration {
    public static final String SECURITY_NAME = "JWT_ACCESS_TOKEN";
}
