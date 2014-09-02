package br.com.ptw.geral.notifications;

import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ThrowableInformation;

import br.com.ptw.util.DataUtil;
import br.com.ptw.util.PropriedadesSistema;

public class EmailErrorSender extends HtmlEmail {

	static Logger logger = Logger.getLogger(EmailErrorSender.class);

	public EmailErrorSender(String msgErro, Throwable e) {
		try {
			this.configurar();

			this.setSubject("ERRO - " + msgErro + " - " + PropriedadesSistema.NOME_CLIENTE);
			// this.setFrom("marcelocoperacional@mcogo.com",
			// PropriedadesSistema.NOME_CLIENTE);
			this.setHtmlMsg(montarHtmlErro(msgErro, e)); // conteudo do e-mail
			this.addTo(PropriedadesSistema.EMAIL_ERRORS);
		} catch (Exception error) {
			logger.error("", error);
		}

		this.enviar();
	}

	private String imprimirStackTraceHtml(Throwable t) {
		if (t == null) {
			return "";
		}

		ThrowableInformation throwableInformation = new ThrowableInformation(t);
		String[] v = throwableInformation.getThrowableStrRep();

		StringBuffer sb = new StringBuffer();
		sb.append("<b>StackTrace:</b><br/>");
		for (int i = 0; i < v.length; i++) {
			sb.append(v[i]).append("<br>");
		}

		return sb.toString();
	}

	private String montarHtmlErro(String msgErro, Throwable t) {
		StringBuilder sb = new StringBuilder();
		sb.append("Erro - <b>" + DataUtil.dataToString(new Date(), "dd/MM/yyyy HH:mm:ss")).append("</b><br><br>");
		sb.append("<b>" + msgErro + "</b><br><br>");

		sb.append(imprimirStackTraceHtml(t));

		return sb.toString();
	}

	@SuppressWarnings("deprecation")
	private void configurar() throws EmailException {
		this.setHostName(PropriedadesSistema.SMTP_SERVER);
		this.setAuthentication(PropriedadesSistema.USERNAME, PropriedadesSistema.PASSWORD);
		this.setSmtpPort(NumberUtils.createInteger(PropriedadesSistema.SMTP_PORT));
		this.setSslSmtpPort(PropriedadesSistema.SMTP_PORT);
		this.setTLS(true);
		this.setCharset("ISO-8859-1");
	}

	public void enviar() {

		new Thread() {
			@Override
			public void run() {
				try {
					EmailErrorSender.this.send();
				} catch (EmailException e) {
					logger.error("Erro ao enviar email", e);
				}
			}
		}.start();
	}

}
