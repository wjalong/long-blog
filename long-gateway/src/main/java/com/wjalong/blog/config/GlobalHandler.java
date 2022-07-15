package com.wjalong.blog.config;

import com.wjalong.common.ResultMsg;
import com.wjalong.common.exception.BizException;
import com.wjalong.util.web.ResponseCodeE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.Locale;

@RestControllerAdvice
public class GlobalHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalHandler.class);

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler(Throwable.class)
    @ResponseBody //表示拦截json
    public ResultMsg<String> exceptionHandler(Exception e){
        String logMsg = "";
        ResultMsg<String> ret = null;

        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            logMsg = bizException.getMessage();
            ret =  ResultMsg.fail(bizException.getError_code(), getMessage(bizException.getError_code()));
        }else if (e instanceof MethodArgumentNotValidException){
            logMsg = e.getMessage();
            ret = ResultMsg.fail("201", getMessage("comm.argument.not.valid"));
        }
        else {
            ret =  ResultMsg.fail(ResponseCodeE.SYS_500.getCode(),ResponseCodeE.SYS_500.getMsg());
        }
        if (e instanceof BizException && "auth.token_has_exipred".equals(((BizException) e).getError_code())){
            log.error(logMsg);
        }
        else {
            log.error(logMsg,e);
        }
        return ret;

    }


    public String getMessage(String key, String... args) {
        return this.messageSource.getMessage(key, args, Locale.getDefault());
    }
}
