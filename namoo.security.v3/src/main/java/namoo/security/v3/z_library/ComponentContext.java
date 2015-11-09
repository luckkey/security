
package namoo.security.v3.z_library;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author eykim
 * @since 2015. 11. 5.
 */
public class ComponentContext {

    public static <T> T lookup(ServletContext servletContext, Class<T> beanType) {
        WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        
        if (ctx == null) {
            // TODO 리팩토링 필요
            return null;
        }
        
        return ctx.getBean(beanType);
    }
}
