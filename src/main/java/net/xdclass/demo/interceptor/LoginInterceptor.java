package net.xdclass.demo.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import net.xdclass.demo.utils.JWTUtils;
import net.xdclass.demo.utils.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            String accessToken = request.getHeader("token");
            if(accessToken == null){
                accessToken = request.getParameter("token");
            }
            if(StringUtils.isNotBlank(accessToken)){
                Claims claims = JWTUtils.checkJWT(accessToken);
                if(claims == null){
                    sendJsonMessage(response, JsonData.buildError("登陆过期, 重新登陆"));
                    return false;
                }else {
                    Integer id = (Integer) claims.get("id");
                    String name = (String) claims.get("name");

                    request.setAttribute("user_id", id);
                    request.setAttribute("user_name",name);
                    return true;
                }
            }
        }catch (Exception e){}

        sendJsonMessage(response, JsonData.buildError("登陆过期, 重新登陆"));
        return false;
    }

    private void sendJsonMessage(HttpServletResponse response, Object obj) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json; charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.print(objectMapper.writeValueAsString(obj));
            printWriter.checkError();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
