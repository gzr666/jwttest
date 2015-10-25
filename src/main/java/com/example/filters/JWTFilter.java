package com.example.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.example.exception.BookNotFoundException;
import com.example.exception.GlobalExceptionHandlerController;
import com.example.exception.NoTokenException;


public class JWTFilter extends GenericFilterBean {

	
	
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		
		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	        response.setStatus(HttpServletResponse.SC_OK);
	        return;
	    } 
		
		final String authHeader = request.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            
			response.setHeader("autherror", "false");
			
			
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" +  request.getServerPort() + "/api/notoken");
			return;	
			
        }
		
		final String token = authHeader.substring(7);
		
		
		try {
            final Claims claims = Jwts.parser().setSigningKey("secretkey")
                .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        }
        catch (final SignatureException e) {
        	response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" +  request.getServerPort() + "/api/wrongtoken");
			return;	
            
        }
		catch(final MalformedJwtException e)
		{
			response.sendRedirect(request.getScheme() + "://" + request.getServerName() + ":" +  request.getServerPort() + "/api/wrongtoken");
			return;	
		}
		
		
		
			chain.doFilter(req, res);
		
		
		
	}

}
