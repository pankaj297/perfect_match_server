
package com.shaadi.shaadi.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping(
        //         "/**")
        registry.addMapping("/**")
                .allowedOrigins(
                    "https://laganmelava.netlify.app",
                        "http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}












// package com.shaadi.shaadi.Config;

// import lombok.RequiredArgsConstructor;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @RequiredArgsConstructor
// public class WebConfig implements WebMvcConfigurer {

//     private final AppProperties appProperties;

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         String[] origins = appProperties.getCors().getAllowedOrigins().toArray(String[]::new);
//         registry.addMapping("/**")
//                 .allowedOrigins(origins)
//                 .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                 .allowedHeaders("*")
//                 .allowCredentials(true);
//     }
// }