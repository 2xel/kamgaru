package kr.kamgaru.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class EncodingFilter implements Filter {
	
	private String encoding;
 
    public EncodingFilter() {
    }
    
    public void init(FilterConfig fConfig) throws ServletException {

    	this.encoding = fConfig.getInitParameter("encoding"); //web.xml
    	System.out.println("Filter Init : "+ this.encoding);
	}

	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if(request.getCharacterEncoding()==null){
			request.setCharacterEncoding(this.encoding);
		}
		chain.doFilter(request, response);
		
	}

	
}
