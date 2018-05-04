package com.is.db;

import com.alibaba.druid.support.http.StatViewServlet;  

import javax.servlet.annotation.WebInitParam;  
import javax.servlet.annotation.WebServlet;  
  
@SuppressWarnings("serial")  
@WebServlet(urlPatterns="/druid/*",  
        initParams={  
                /** 白名单，如果不配置或value为空，则允许所有 */  
                @WebInitParam(name="allow",value="127.0.0.1,192.0.0.1"),  
                /** 黑名单，与白名单存在相同IP时，优先于白名单 */  
                @WebInitParam(name="deny",value="192.0.0.1"),  
                /** 用户名 */  
                @WebInitParam(name="loginUsername",value="admin"),  
                /** 密码 */  
                @WebInitParam(name="loginPassword",value="admin"),  
                /** 禁用HTML页面上的“Reset All”功能   */  
                @WebInitParam(name="resetEnable",value="false")  
        })  
public class DruidStatViewServlet extends StatViewServlet {  
}  