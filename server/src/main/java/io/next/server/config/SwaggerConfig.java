package io.next.server.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;

import org.springdoc.core.providers.ObjectMapperProvider;
import org.springdoc.core.properties.SpringDocConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.configuration.SpringDocConfiguration;
import org.springdoc.core.configuration.SpringDocUIConfiguration;

@Configuration
@Profile("dev")
public class SwaggerConfig {
  @Bean
  SpringDocConfiguration springDocConfiguration() {
    return new SpringDocConfiguration();
  }

  @Bean
  SpringDocConfigProperties springDocConfigProperties() {
    return new SpringDocConfigProperties();
  }

  @Bean
  ObjectMapperProvider objectMapperProvider(SpringDocConfigProperties springDocConfigProperties) {
    return new ObjectMapperProvider(springDocConfigProperties);
  }

  @Bean
  SpringDocUIConfiguration SpringDocUIConfiguration(
      Optional<SwaggerUiConfigProperties> optionalSwaggerUiConfigProperties) {
    return new SpringDocUIConfiguration(optionalSwaggerUiConfigProperties);
  }
}