package com.resthome.config;
import com.resthome.annotation.SerializedField;
import com.resthome.util.AESUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * Created by 一缕仙缘 on 2017-07-06.
 * @Description : 交互数据加密配置
 */
@Order(1)
//@ControllerAdvice(basePackages = "com.resthome.controller")
public class MyControllerAdvice implements ResponseBodyAdvice {


    private static Logger LOGGER = LoggerFactory.getLogger(MyControllerAdvice.class);

    //是否加密
    private boolean encode = true;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //这里可以根据自己的需求
        return true;
    }

    /**
     * @Description : 若加了SerializedField注释，则进行加密
     * @param o
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //重新初始化为默认值
        encode = true;

        //判断返回的对象是单个对象，还是list，活着是map
        if (o == null) {
            return null;
        }
        if (!(methodParameter.getMethod().isAnnotationPresent(SerializedField.class))) {
            return o;
        }

        //获取注解配置的包含和去除字段
        SerializedField serializedField = methodParameter.getMethodAnnotation(SerializedField.class);
        //是否加密
        encode = serializedField.encode();
        //如果不需要加密  直接返回 不处理
        if (!encode) {
            return o;
        }
        try {

            LOGGER.info("======encode======");
            if (o != null) {
                if (o instanceof JSONObject) {
                    return AESUtils.aesEncrypt(((JSONObject) o).toString(), AESUtils.KEY);
                }
                if (o instanceof JSONArray) {
                    return AESUtils.aesEncrypt(((JSONArray) o).toString(), AESUtils.KEY);
                }
            }
        } catch (Exception e) {
            LOGGER.error("======aesEncrypt failed======the message:" + e.getMessage());
        }
      return null;
    }
}
