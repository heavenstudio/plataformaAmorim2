package br.com.muranodesign.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JWTFilter implements Filter{
	
	private FilterConfig filterConfig;
	
	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {		
//		 HttpServletRequest httpRequest = (HttpServletRequest) request;
//		 
//		Enumeration<String> e = httpRequest.getAttributeNames();
//		
//		
//		do{
//			String actualElement = e.nextElement();
//			System.out.println(actualElement);
//			System.out.println(httpRequest.getAttribute(actualElement));
//		}while(e.hasMoreElements());
//		
//		 String header = httpRequest.getHeader("auth");
//		 System.out.println(header);
		
		filterChain.doFilter(request, response);
	}

	
	public FilterConfig getFilterConfig() {
        return filterConfig;
    }

	@Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
	
	
	
}
