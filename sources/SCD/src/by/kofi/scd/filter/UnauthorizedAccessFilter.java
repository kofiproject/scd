package by.kofi.scd.filter;

import by.kofi.scd.common.FacesUtil;
import by.kofi.scd.dto.UserContext;

import javax.faces.context.FacesContext;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author harchevnikov_m
 *         Date: 13/11/11
 *         Time: 19:04
 */
public class UnauthorizedAccessFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        return;

/*
        HttpServletRequest request = (HttpServletRequest) req;
//        String requestURI = request.getRequestURI();

        HttpSession session = request.getSession();
        UserContext userContext = (UserContext) session.getAttribute("userContext");
        if (userContext == null) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect(request.getServletPath());
        } else {
            chain.doFilter(req, resp);
        }
*/
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
