package br.com.ptw.geral.notifications;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

import br.com.ptw.util.PropriedadesSistema;

public class EmailSender extends HtmlEmail {

	static Logger logger = Logger.getLogger(EmailSender.class);

	public EmailSender() throws EmailException {
		this.configurar();
	}

	@SuppressWarnings("deprecation")
	private void configurar() throws EmailException {
		this.setHostName(PropriedadesSistema.SMTP_SERVER);
		this.setAuthentication(PropriedadesSistema.USERNAME, PropriedadesSistema.PASSWORD);
		this.setSmtpPort(NumberUtils.createInteger(PropriedadesSistema.SMTP_PORT));
		this.setSslSmtpPort(PropriedadesSistema.SMTP_PORT);
		this.setTLS(true);
		// this.setSSL(true);
		this.setFrom(PropriedadesSistema.USERNAME, PropriedadesSistema.NOME_CLIENTE); // remetente
		this.setCharset("ISO-8859-1");
	}

	public void sendEasyEmail(String assunto, String msg, String... emails) throws EmailException {

		this.setSubject(assunto); // assunto do e-mail
		this.setHtmlMsg(msg); // conteudo do e-mail
		this.addTo(emails);

		this.enviar();
	}

	public void enviar() {

		new Thread() {
			@Override
			public void run() {
				try {
					EmailSender.this.send();
				} catch (EmailException e) {
					logger.error("Erro ao enviar email", e);
				}
			}
		}.start();
	}

	/*	*//**
	 * envia email com arquivo anexo
	 * 
	 * @throws EmailException
	 */
	/*
	 * public void enviaEmailComAnexo() throws EmailException{
	 * 
	 * // cria o anexo 1. EmailAttachment anexo1 = new EmailAttachment();
	 * anexo1.setPath("teste/teste.txt"); //caminho do arquivo
	 * (RAIZ_PROJETO/teste/teste.txt)
	 * anexo1.setDisposition(EmailAttachment.ATTACHMENT);
	 * anexo1.setDescription("Exemplo de arquivo anexo");
	 * anexo1.setName("teste.txt");
	 * 
	 * // cria o anexo 2. EmailAttachment anexo2 = new EmailAttachment();
	 * anexo2.setPath("teste/teste2.jsp"); //caminho do arquivo
	 * (RAIZ_PROJETO/teste/teste2.jsp)
	 * anexo2.setDisposition(EmailAttachment.ATTACHMENT);
	 * anexo2.setDescription("Exemplo de arquivo anexo");
	 * anexo2.setName("teste2.jsp");
	 * 
	 * // configura o email MultiPartEmail email = new MultiPartEmail();
	 * email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do
	 * e-mail email.addTo("teste@gmail.com", "Guilherme"); //destinat�rio
	 * email.setFrom("teste@gmail.com", "Eu"); // remetente
	 * email.setSubject("Teste -> Email com anexos"); // assunto do e-mail
	 * email.setMsg("Teste de Email utilizando commons-email"); //conteudo do
	 * e-mail email.setAuthentication("teste", "xxxxx"); email.setSmtpPort(465);
	 * 
	 * // adiciona arquivo(s) anexo(s) email.attach(anexo1);
	 * email.attach(anexo2); // envia o email email.send(); }
	 * 
	 * public static void enviaEmailHtml(String assunto, String msg, Map<String,
	 * String> destinos) throws EmailException, MalformedURLException {
	 * HtmlEmail email = new HtmlEmail();
	 * 
	 * // adiciona uma imagem ao corpo da mensagem e retorna seu id //URL url =
	 * new URL("http://www.apache.org/images/asf_logo_wide.gif"); //String cid =
	 * email.embed(url, "Apache logo"); // configura a mensagem para o formato
	 * HTML //email.setHtmlMsg("<html>Logo do Apache - <img ></html>");
	 * 
	 * // configure uma mensagem alternativa caso o servidor n�o suporte HTML
	 * //email.setTextMsg("Seu servidor de e-mail n�o suporta mensagem HTML");
	 * 
	 * for (Map.Entry<String, String> item : destinos.entrySet()) {
	 * email.addTo(item.getKey(), item.getValue()); //destinat�rioa }
	 * 
	 * // envia email }
	 */
}
