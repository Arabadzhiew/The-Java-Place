package com.arabadzhiev.site.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DefaultEmailService implements EmailService{
	
	@Autowired private JavaMailSender mailSender;
	
	@Async
	@Override
	public void sendRecoveryEmail(String email, String recoveryKey) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setContent("<!DOCTYPE html>\n"
					+ "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n"
					+ "	<head>\n"
					+ "		<meta charset=\"utf-8\">\n"
					+ "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
					+ "		<meta name=\"x-apple-disable-message-reformating\">\n"
					+ "		<title></title>\n"
					+ "		<!--[if mso]>\n"
					+ "			<noscript>\n"
					+ "				<xml>\n"
					+ "					<o:OfficeDocumentSettings>\n"
					+ "						<o:PixelsPerInch>96</o:PixelsPerInch>\n"
					+ "					</o:OfficeDocumentSettings>\n"
					+ "				</xml>\n"
					+ "			</noscript>\n"
					+ "		<![endif]-->\n"
					+ "		<style type=\"text/css\">\n"
					+ "			table, td, div, h1, p{\n"
					+ "				font-family: Arial, sans-serif;\n"
					+ "			}\n"
					+ "		</style>\n"
					+ "	</head>\n"
					+ "	<body style=\"margin:0;padding:0;\">\n"
					+ "		<table role=\"presentation\" style=\"width: 100%; border-collapse: collapse; border-spacing: 0;\" align=\"center\">\n"
					+ "			<tr>\n"
					+ "				<td style=\"padding:0;\" align=\"center\">\n"
					+ "					<table role=\"presentation\" style=\"width: 602px; border:1px solid #cccccc; border-collapse: collapse; border-spacing: 0;\">\n"
					+ "						<tr>\n"
					+ "							<td style=\"padding:40px 0 20px 0; background:#ebebeb;\" align=\"center\">\n"
					+ "								<img width=\"300\" style=\"height: auto;display: block;\" src=\"https://localhost:8443/tjp/resources/images/tjpLogo.png\" alg=\"Logo\">\n"
					+ "								<h3 style=\"margin: 20px 0 0 0;font-family: Arial, sans-serif;\">The place for everything java related</h3>\n"
					+ "							</td>\n"
					+ "						</tr>\n"
					+ "						<tr>\n"
					+ "							<td style=\"padding:20px 30px 70px 30px; background:#ffffff;\">\n"
					+ "								<h2 style=\"padding: 0 0 20px 0; font-family: Arial, sans-serif;\">Password recovery</h2>\n"
					+ "								<p style=\"margin: 0 0 25px 0; line-height: 25px; color: #153643\">\n"
					+ "									Hey, what happened? You lost your password? Where were you looking at... Fine, I will help you out. Just make sure it doesnt happen again, okay? Click on the link bellow to change your password:\n"
					+ "								</p>\n"
					+ "								<a style=\"margin: 0 0 25px 0;color:#ee4c50;text-decoration:underline;\" href=\"https://localhost:8443/tjp/recovery/"+recoveryKey+"\">\n"
					+ "									Password reset\n"
					+ "								</a>\n"
					+ "							</td>\n"
					+ "						</tr>\n"
					+ "						<tr>\n"
					+ "							<td style=\"padding:30px; background:#ee4c50;\">\n"
					+ "								<p style=\"margin:0; font-size:14px; line-height: 16px; font-family:Arial, sans-serif; color:#ffffff;\">\n"
					+ "									&#169; Petar Arabadzhiev, Plovdiv Bulgaria 2021<br/>\n"
					+ "									<a style=\"color:#ffffff;\" href=\"https://github.com/Arabadzhiew\">GitHub</a>\n"
					+ "								</p>\n"
					+ "							</td>\n"
					+ "						</tr>\n"
					+ "					</table>\n"
					+ "				</td>\n"
					+ "			</tr>\n"
					+ "			<tr></tr>\n"
					+ "			<tr></tr>\n"
					+ "		</table>\n"
					+ "	</body>\n"
					+ "</html>", "text/html");
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			helper.setTo(email);
			helper.setSubject("The Java Place - Password recovery");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(message);
	}

}
