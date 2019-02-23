package com.bookmark.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by 12425 on 2018/8/8.
 */
@Configuration
public class MessageConvertConfiguration {
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        return new StringHttpMessageConverter();

    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        return new MappingJackson2HttpMessageConverter();
    }

    }
