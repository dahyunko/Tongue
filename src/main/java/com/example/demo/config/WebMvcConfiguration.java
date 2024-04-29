package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableAspectJAutoProxy
@MapperScan(basePackages = { "com.example.**.mapper" })
public class WebMvcConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*")
////			.allowedOrigins("http://localhost:8080", "http://localhost:8081")
//                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
//                        HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
//                        HttpMethod.PATCH.name())
//                .maxAge(1800); // 1800초 동안 preflight 결과를 캐시에 저장
//    }
}
