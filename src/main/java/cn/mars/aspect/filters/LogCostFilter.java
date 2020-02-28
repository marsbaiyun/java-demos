package cn.mars.aspect.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Description：
 * Created by Mars on 2020/2/25.
 */
public class LogCostFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LogCostFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("Execute cost= {}", System.currentTimeMillis()-start);
    }

    @Override
    public void destroy() {

    }
}
