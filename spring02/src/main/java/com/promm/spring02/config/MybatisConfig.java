package com.promm.spring02.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 해당 패키지에서 mapper로 인식하라
@MapperScan(basePackages = {"com.promm.spring02.mapper"})
public class MybatisConfig {
    
}
