package com.megathrone.ecspringboot.config;

import com.megathrone.ecspringboot.interceptor.LoginInterceptor;
import com.megathrone.ecspringboot.interceptor.OtherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
  @Bean
  public LoginInterceptor getLoginIntercepter() {
    return new LoginInterceptor();
  }

  @Bean
  public OtherInterceptor getOtherIntercepter() {
    return new OtherInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(getLoginIntercepter()).addPathPatterns("/**");
  }
}
