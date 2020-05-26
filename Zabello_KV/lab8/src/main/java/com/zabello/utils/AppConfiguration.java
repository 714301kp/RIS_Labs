package com.zabello.utils;

import com.zabello.dao.UserHibernateDao;
import com.zabello.dao.UserXmlDao;
import com.zabello.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.zabello.*")
public class AppConfiguration {

    @Bean
    UserXmlDao userXmlDao(){
        return new UserXmlDao();
    }

    @Bean
    UserHibernateDao userHibernateDao(){
        return new UserHibernateDao();
    }

    @Bean
    UserServiceImpl userServiceImpl(){
        return new UserServiceImpl();
    }
}
