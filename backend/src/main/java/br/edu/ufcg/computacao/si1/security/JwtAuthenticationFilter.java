package br.edu.ufcg.computacao.si1.security;

import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest servletRequest = (HttpServletRequest) request;
        String authorization = servletRequest.getHeader("Authorization");
        if (authorization != null) {
            AuthenticatedUser user = new AuthenticatedUser(authorization);
            SecurityContextHolder.getContext().setAuthentication(user);
        }
        chain.doFilter(request, response);

    }

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}