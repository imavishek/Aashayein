package org.avishek.aashayein.eventListener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionListener implements HttpSessionListener {

	private static final Logger logger = LogManager.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		logger.info("Session Created");
		event.getSession().setMaxInactiveInterval(3600);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		logger.info("Session Destroyed");
	}
}
