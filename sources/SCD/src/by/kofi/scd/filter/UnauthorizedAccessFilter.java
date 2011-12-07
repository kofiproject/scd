package by.kofi.scd.filter;

import by.kofi.scd.dto.UserContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author harchevnikov_m
 *         Date: 13/11/11
 *         Time: 19:04
 */
public class UnauthorizedAccessFilter implements javax.servlet.Filter {
    private static final Set<Pattern> SKIP_VALIDATION_URL_PATTERNS = new HashSet<Pattern>() {{
        add(Pattern.compile(".*/javax\\.faces\\.resource/.*"));//JSF resources url pattern
        add(Pattern.compile(".*/faces/rfRes/.*"));//JSF resources url pattern

        add(Pattern.compile(".*/index.jsp"));
        add(Pattern.compile(".*/faces/login/login\\.xhtml.*"));
        add(Pattern.compile(".*/faces/login/registration\\.xhtml.*"));
    }};

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        String requestURI = request.getRequestURI();

        if (skipValidation(requestURI)) {
            chain.doFilter(req, resp);
        } else {

            HttpSession session = request.getSession();
            UserContext userContext = (UserContext) session.getAttribute("userContext");

            if (userContext == null) {
                HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect("/");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    private boolean skipValidation(String url) {
        for (Pattern pattern : SKIP_VALIDATION_URL_PATTERNS) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }

        return false;
    }

}
