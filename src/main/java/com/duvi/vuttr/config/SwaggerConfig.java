package com.duvi.vuttr.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "VUTTR API Docs ! Intuitive",
                description = "Les outils sont utiles et il est important de s'en souvenir",
                version = "1.0.0",
                contact = @Contact(email = "dmsosa21@outlook.com", name = "Duvi", url="https://dmsosa.github.io"),
                license = @License(name = "Apache 2.0", url = "https://foo.bar")
        )
)
public class SwaggerConfig {
}
