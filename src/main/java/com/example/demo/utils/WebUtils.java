package com.example.demo.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    /**
     * 將字串渲染到客戶端
     *
     * @param response 渲染對象
     * @param string 要渲染的字串
     */
    public static String renderString(HttpServletResponse response,String string){
        try{
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(string);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return  null;
    }

}
