package org.knownspace.gamemaker.server.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoggingInitListener implements ServletContextListener {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LoggingInitListener.class);

	public void contextInitialized(ServletContextEvent event) {
		org.apache.log4j.PropertyConfigurator.configureAndWatch("classpath:log4j.properties", 5 * 60 * 1000);
		LOG.info("LoggingInitListener finished initializing log4j");
	}
 
	public void contextDestroyed(ServletContextEvent event) {}

}
