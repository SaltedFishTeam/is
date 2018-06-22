package com.is;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.is.websocket.SocketServer;

@ServletComponentScan
@SpringBootApplication
public class Application
{
//introduce
    public static void main(String[] args) throws Exception
    {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        System.out.println("初始化");
        SocketServer.setApplicationContext(context);
    }
   
    @Bean 
    public MultipartConfigElement multipartConfigElement(){ 
	    MultipartConfigFactory config = new MultipartConfigFactory(); 
	    config.setMaxFileSize("80MB"); 
	    config.setMaxRequestSize("100MB"); 
	    return config.createMultipartConfig(); 
    } 
}
