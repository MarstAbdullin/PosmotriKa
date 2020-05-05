package posmotriKa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CustomDayFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String agent = httpRequest.getHeader("X-Request-Start");
        Long unixTime = Long.getLong(agent);
        Date date = new java.util.Date(unixTime*1000L);
        Integer hour = date.getHours();
        if (hour<=21 && hour>=6) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
