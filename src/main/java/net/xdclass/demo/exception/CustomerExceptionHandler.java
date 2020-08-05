package net.xdclass.demo.exception;

import net.xdclass.demo.utils.JsonData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomerExceptionHandler  {

    private final static Logger logger = LoggerFactory.getLogger(XDExcetpion.class);

    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    private JsonData handler(Exception e){

        logger.error("[异常] {}", e);
        if(e instanceof XDExcetpion){
            XDExcetpion xdExcetpion = (XDExcetpion)e;
            return JsonData.buildError(xdExcetpion.getCode(),xdExcetpion.getMsg());
        }else {
            return JsonData.buildError("全局异常 未知错误");
        }

    }
}
