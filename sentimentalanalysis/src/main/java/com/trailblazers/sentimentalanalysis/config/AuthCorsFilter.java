//package com.trailblazers.sentimentalanalysis.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////CORS CONFIGURATION
//@Configuration
//public class AuthCorsFilter{
//    private static final String GET = "GET";
//    private static final String POST = "POST";
//    private static final String PUT = "PUT";
//    private static final String DELETE = "DELETE";
//    private static final String OPTIONS = "OPTIONS";
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods(GET, POST, PUT, DELETE, OPTIONS)
//                        .allowedHeaders("*").allowedOrigins("https://starperformer-a047c.web.app/","https://starperformer.azurewebsites.net/")
//                        .allowCredentials(true)
//                        .maxAge(3600);
//            }
//        };
//    }
//}