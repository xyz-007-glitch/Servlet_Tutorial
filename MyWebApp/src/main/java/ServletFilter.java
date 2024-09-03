
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class ServletFilter
 */
@WebFilter("/*")
public class ServletFilter extends HttpFilter implements Filter {



	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		
		ServletContext sCtx = getServletContext();
		Integer hitCount = (Integer) sCtx.getAttribute("hitCounter");
		System.out.println("Hi i am shantanu salvi");
		if(hitCount == null) {
			hitCount = 0;
		}
		sCtx.setAttribute("hitCounter", ++hitCount);

// Print the counter. 
		System.out.println("Visits count is :" + hitCount);

// Pass request back down the filter chain 
		chain.doFilter(request, response);
	}
	
	

}
