package au.com.sudeera.inventory.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * The Class RequestLogger.
 */
@Configuration
public class RequestLogger extends OncePerRequestFilter {

	/**
	 * Log each request and respponse with full Request URI, content payload and
	 * duration of the request in ms.
	 *
	 * @param request            the request
	 * @param response            the response
	 * @param filterChain            chain of filters
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		long startTime = System.currentTimeMillis();
		StringBuffer reqInfo = new StringBuffer().append("[").append(startTime % 10000) // request
																						// ID
				.append("] ").append(request.getMethod()).append(" ").append(request.getRequestURL());

		String queryString = request.getQueryString();
		if (queryString != null) {
			reqInfo.append("?").append(queryString);
		}

		if (request.getAuthType() != null) {
			reqInfo.append(", authType=").append(request.getAuthType());
		}
		if (request.getUserPrincipal() != null) {
			reqInfo.append(", principalName=").append(request.getUserPrincipal().getName());
		}

		this.logger.info("=> " + reqInfo);

		// ========= Log request and response payload ("body") ========
		// We CANNOT simply read the request payload here, because then the
		// InputStream would be consumed and cannot be read again by the actual
		// processing/server.
		// String reqBody =
		// DoogiesUtil._stream2String(request.getInputStream()); // THIS WOULD
		// NOT WORK!
		// So we need to apply some stronger magic here :-)
		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

		filterChain.doFilter(wrappedRequest, wrappedResponse); // ======== This
																// performs the
																// actual
																// request!
		long duration = System.currentTimeMillis() - startTime;

		this.logger.info("<= " + reqInfo + ": returned status=" + response.getStatus() + " in " + duration + "ms");

		wrappedResponse.copyBodyToResponse(); // IMPORTANT: copy content of
												// response back into original
												// response

	}

}
