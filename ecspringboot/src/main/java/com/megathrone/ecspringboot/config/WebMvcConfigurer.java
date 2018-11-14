package com.megathrone.ecspringboot.config;

import com.megathrone.ecspringboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
  @Bean
  public LoginInterceptor getLoginIntercepter() {
    return new LoginInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**");
  }
}
