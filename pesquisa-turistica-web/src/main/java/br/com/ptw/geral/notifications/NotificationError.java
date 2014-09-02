package br.com.ptw.geral.notifications;

import org.apache.log4j.Logger;

public class NotificationError {

	private static final Logger logger = Logger.getLogger(NotificationError.class);

	public static void notify(String msg, Throwable e) {
		logger.error(msg, e);
		new EmailErrorSender(msg, e);
	}
}
