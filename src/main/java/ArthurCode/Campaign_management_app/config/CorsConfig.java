package ArthurCode.Campaign_management_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("https://campaign-management-app-production.up.railway.app");
        config.addAllowedHeader("*");
        config.setAllowedMethods(Arrays.asList("OPTIONS", "GET", "POST", "PATCH", "DELETE"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
