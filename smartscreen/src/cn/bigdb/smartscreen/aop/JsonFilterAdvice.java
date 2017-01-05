package cn.bigdb.smartscreen.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import cn.bigdb.smartscreen.annotation.JsonFilter;
import cn.bigdb.smartscreen.annotation.JsonFilters;
import cn.bigdb.smartscreen.common.WebContext;

public class JsonFilterAdvice {

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {  
        MethodSignature msig = (MethodSignature) pjp.getSignature();  
        JsonFilter annotation =  msig.getMethod().getAnnotation(  
                JsonFilter.class);  
        JsonFilters annotations = msig.getMethod().getAnnotation(  
                JsonFilters.class);  
  
        if (annotation == null && annotations == null) {  
            return pjp.proceed();  
        }  
  
        ObjectMapper mapper = new ObjectMapper(); 
        //添加下面一句，在对象转换成json时，为null或者字符串为""或者数字为0时，均不会将该属性序列化到json串中
//        mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_DEFAULT);
        if (annotation != null) {  
            Class<?> mixin = annotation.mixin();  
            Class<?> target = annotation.target();  
              
            if (target != null) {  
                mapper.getSerializationConfig().addMixInAnnotations(target,  
                        mixin);  
            } else {  
                mapper.getSerializationConfig().addMixInAnnotations(  
                        msig.getMethod().getReturnType(), mixin);  
            }  
        }  
          
        if (annotations != null) {  
            JsonFilter[] filters= annotations.values();  
            for(JsonFilter filter :filters){  
                Class<?> mixin = filter.mixin();  
                Class<?> target = filter.target();  
                  
                if (target != null) {  
                    mapper.getSerializationConfig().addMixInAnnotations(target,  
                            mixin);  
                } else {  
                    mapper.getSerializationConfig().addMixInAnnotations(  
                            msig.getMethod().getReturnType(), mixin);  
                }  
            }  
              
        }  
          
        try { 
            mapper.writeValue(WebContext.getInstance().getResponse()  
                    .getOutputStream(), pjp.proceed());
        	//单元测试使用
//        	return mapper.writeValueAsString(pjp.proceed());
        } catch (Exception ex) {  
            throw new RuntimeException(ex);  
        }  
        return null;  
    }  
}
