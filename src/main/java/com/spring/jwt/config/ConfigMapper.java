package com.spring.jwt.config;

import com.spring.jwt.dto.ShippingDto;
import com.spring.jwt.entity.ShippingDetail;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigMapper {
    @Bean
        public ModelMapper modelMapper(){
            return new ModelMapper();

        }


}

