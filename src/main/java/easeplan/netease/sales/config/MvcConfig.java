package easeplan.netease.sales.config;

import easeplan.netease.sales.interceptor.IdentityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huangzw
 * @version 1.0
 * @since <pre>2018/3/9</pre>
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Autowired
    IdentityInterceptor identityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(identityInterceptor);
    }
}
