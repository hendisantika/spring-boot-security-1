/**
 * 
 */
package org.qifeng.sbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author jackho
 *
 */
public class AccessDeniedExceptionHandler implements AccessDeniedHandler {

	private static Logger LOG = LoggerFactory.getLogger(AccessDeniedExceptionHandler.class);
	
	private String errorPage ;
	
	/**
	 * @return the errorPage
	 */
	public String getErrorPage() {
		return errorPage;
	}



	/**
	 * @param errorPage the errorPage to set
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException arg2) throws IOException, ServletException {
		LOG.debug(String.format("### AccessDeniedHandler: URL [%s]", request.getServletPath()));
		LOG.debug("### Error page: ["+ errorPage +"] ");
		request.getRequestDispatcher(errorPage).forward(request, response);
		
		
	}
	
}
