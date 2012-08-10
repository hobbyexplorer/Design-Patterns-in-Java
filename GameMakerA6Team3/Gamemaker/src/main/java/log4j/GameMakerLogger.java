package log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * Logger class for game maker
 * 
 * Responsibility: to display the error, fatal, log, warn and debug information
 * to the logger
 */
public class GameMakerLogger
{
	static final Logger logger = Logger.getLogger(GameMakerLogger.class);
	private String logProperties;

	/*
	 * constructor for logger
	 */
	public GameMakerLogger(String logProperties)
	{
		this.logProperties = logProperties;
	}

	/*
	 * 
	 * to configure log
	 */
	public void configureLog()
	{
		PropertyConfigurator.configure(getClass().getClassLoader().getResource(logProperties));
	}

	/*
	 * add debug information
	 */
	public void logDebug(String message)
	{
		configureLog();
		logger.debug(message);
	}

	/*
	 * add log information
	 */
	public void logInfo(String message)
	{
		configureLog();
		logger.info(message);
	}

	/*
	 * add warning information
	 */
	public void logWarn(String message)
	{
		configureLog();
		logger.warn(message);
	}

	/*
	 * adds error log information
	 */
	public void logError(String message)
	{
		configureLog();
		logger.error(message);
	}

	/*
	 * add fatal information to logs
	 */
	public void logFatal(String message)
	{
		configureLog();
		logger.fatal(message);
	}

}
