package com.safi.dairy.base.config;

import com.safi.dairy.base.intercepter.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by safi on 17/4/8.
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.safi"})
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
//        resolver.setSuffix(".ftl");
        resolver.setSuffix(".html");
        return resolver;

    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
        result.setTemplateLoaderPaths("/WEB-INF/views/");
        RemoteTemplateLoader remoteTemplateLoader = new RemoteTemplateLoader("http://safi.static.ngrok.cc/dairy-vue");
        List<String> includePaths = new ArrayList<>();
        includePaths.add("index.html");
        remoteTemplateLoader.setIncludePaths(includePaths);
        result.setPreTemplateLoaders(remoteTemplateLoader);
        result.setDefaultEncoding("utf-8");
        return result;

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor());
    }
}
