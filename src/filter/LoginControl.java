package filter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginControl implements Filter {
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
        
        if (httpSession != null && httpSession.getAttribute("currentSessionUser") != null) {
            chain.doFilter(request, response);
        } else {
            filterConfig.getServletContext().log("Erisim izni yok: " + LocalDateTime.now()
                                                                            .format(DateTimeFormatter
                                                                            .ofLocalizedDateTime(FormatStyle.SHORT)));
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("login.jsp");
        }
    }

    @Override
    public void destroy() {
    }

}
