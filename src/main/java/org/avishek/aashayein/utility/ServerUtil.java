/**
 * @ProjectName Aashayein
 * © @Author avishekdas
 * package org.avishek.aashayein.utility;
 * @FileName ServerUtil.java
 * @CreatedDate 05-Feb-2019
 * Modified by @author avishekdas last on 2019-02-05 00:15:39
 */

package org.avishek.aashayein.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerUtil {

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private HttpServletRequest request;

	private static final Logger logger = LogManager.getLogger(ServerUtil.class);

	public String getServerUrl() {

		String url = null;
		try {
			url = "http://" + InetAddress.getLocalHost().getHostAddress() + ":" + request.getServerPort()
					+ servletContext.getContextPath() + "/";
			logger.info("Servel Url:- " + url);
		} catch (UnknownHostException e) {

			logger.error(e.getMessage() + " [Exception " + e.getClass() + "]");
		}

		return url;
	}
}
