package com.services.chambitas.utility;

import static com.services.chambitas.constant.EmailConstant.*;
import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.services.chambitas.domain.Offer;
import com.services.chambitas.domain.User;
import com.sun.mail.smtp.SMTPTransport;

@Service
public class EmailService {

	public void resetPassword(String password, String email) throws MessagingException {
		Message message = resetPasswordBody(password, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	private Message resetPasswordBody(String token, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject(EMAIL_SUBJECT);
		message.setContent("<!DOCTYPE html>\r\n"
		+ "<html lang=\"en\">\r\n"
		+ "<head>\r\n"
		+ "    <meta charset=\"UTF-8\">\r\n"
		+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
		+ "    <title>Recuperar cuenta | Hale Jobs</title>\r\n"
		+ "</head>\r\n"
		+ "<body>\r\n"
		+ "    <div style=\"background-color: rgba(255, 255, 255, 0.2); padding: 50px; text-align: center; margin: 0 auto; font-family: sans-serif;\">\r\n"
		+ "        <img src=\"https:hale.com.mx/assets/images/logo-b.png\" style=\"width: 180px;\" alt=\"\" />\r\n"
		+ "        <div><h1 >&iquest;Quiéres cambiar la contrase&ntilde;a?</h1></div>\r\n"
		+ "        <div class=\"more-text\">\r\n"
		+ "            <h2 style=\"margin: 0px; color: rgb(62, 62, 62); font-weight: 600;\"> &iquest;Ha pedido cambiar la contrase&ntilde;a?</h2>\r\n"
		+ "            <h3 style=\"margin: 0px 0px 30px 0px; color: rgb(65, 65, 65); font-weight: 300;\"> Presione el botón para cambiar la contrase&ntilde;a?</h3>\r\n"
		+ "        </div>\r\n"
		+ "        <div style=\"display: flex; justify-content: center; margin: 10px 0px;\">\r\n"
		+ "        <a style=\"align-items: center; cursor: pointer; display: flex; flex-direction: row; flex-shrink: 0; margin: 0 auto;  text-align: center;\" href=\"https:hale.com.mx/#/auth/reset-password/" + token + "/" + email +  "\">"
		+ "            <button class=\"button-22\" role=\"button\"  style=\"align-items: center; appearance: button; background-color: #FFD605; border-radius: 8px; border-style: none; box-shadow: rgba(255, 255, 255, 0.26) 0 1px 2px inset; box-sizing: border-box; color: #fff; cursor: pointer; display: flex; flex-direction: row; flex-shrink: 0; font-size: 100%;line-height: 1.15; margin: 0 auto; padding: 10px 21px; text-align: center; text-transform: none; transition: color .13s ease-in-out,background .13s ease-in-out,opacity .13s ease-in-out,box-shadow .13s ease-in-out;\">Cambiar contraseña</button>\r\n"
		+ "        </a>\n"
		+ "        </div>\r\n"
		+ "        <div style=\"color: rgb(119, 119, 119); font-weight: 500;\"><h3> Si no pidio el cambio de contrase&ntilde;a por favor ignore el correo</h3></div>\r\n"
		+ "        <div style=\"color: rgb(119, 119, 119); font-weight: 500;\"><p style=\"margin: 0px;\"> Has recibido este correo por ser usuario de los servicios de hale.com.mx </p><p style=\"margin: 0px;\"> Todos los derechos reservados - © Hale Jobs  </p></div>\r\n"
		+ "    </div>\r\n"
		+ "</body>\r\n"
		+ "</html>", "text/html");

		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
	
	// VISTO
	public void sendNotification( Offer offer, User user, String email) throws MessagingException {
		Message message = messageVisto(offer, user, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	// SELECCIONADO
	public void sendNotificationSeleccionado(String messageExtra, Offer offer, User user, String email) throws MessagingException {
		Message message = messageSeleccionado(offer, user, messageExtra, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
	}

	// RECHAZO
	public void sendNotificationRechazo(String messageExtra, Offer offer, User user, String email) throws MessagingException {
			Message message = messageRechazo(offer, user, messageExtra, email);
			SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
			smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
			smtpTransport.sendMessage(message, message.getAllRecipients());
			smtpTransport.close();
	}
	
	public void sendNotificationUser(String messageExtra, Offer offer, User user, String email) throws MessagingException {
		Message message = messageUser(offer, user, messageExtra, email);
		SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
		smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
		smtpTransport.sendMessage(message, message.getAllRecipients());
		smtpTransport.close();
   }

   public void sendNotificationCerrar(String messageExtra, Offer offer, User user, String email) throws MessagingException {
	Message message = messageOfertaCerrada(offer, user, email);
	SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
	smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
	smtpTransport.sendMessage(message, message.getAllRecipients());
	smtpTransport.close();
}


public void sendNotificationPostulacion(Offer offer, User user, String email) throws MessagingException {
	Message message = messagePostulacionRH(offer, user, email);
	SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
	smtpTransport.connect(GMAIL_SMTP_SERVER, USERNAME, PASSWORD);
	smtpTransport.sendMessage(message, message.getAllRecipients());
	smtpTransport.close();
}



	private Message messageVisto(Offer offer, User user, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setRecipients(CC, InternetAddress.parse(CC_EMAIL, false));
		message.setSubject("HALE JOBS: " + offer.getCompany().getName() + " ha visto tu postulación");
	
		message.setContent("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Mensaje -  Hale Jobs</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_-7626415423304311386email_table\" style=\"border-collapse:collapse\">\n"
				+ "        <tbody>\n"
				+ "          <tr>\n"
				+ "            <td id=\"m_-7626415423304311386email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">\n"
				+ "              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                <tbody>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\">\n"
				+ "                              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td style=\"width:100%;text-align:left;height:33px\"><img height=\"55\" src=\"https://hale.com.mx/assets/images/logo-b.png\" style=\"border:0\" class=\"CToWUd\" data-bit=\"iit\"></td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td>\n"
				+ "                              <table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td>\n"
				+ "                                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                        <tbody>\n"
				+ "                                          <tr>\n"
				+ "                                            <td>\n"
				+ "                                              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                <tbody>\n"
				+ "                                                  <tr>\n"
				+ "                                                    <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                                    <td>\n"
				+ "                                                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                        <tbody>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td>\n"
				+ "                                                              <p style=\"margin:5px 0 10px 0;color:#000000;font-size:16px\">!Hola " +  user.getNames() + "¡\n"
				+ "                                                              </p>\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\"> \n"
				+ "                                                                La " +  offer.getCompany().getName() + " \n"
				+ "                                                                ha visto tu postulación para la posición:\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                                 " +  offer.getTitle() + "\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                              </p>\n"
				+ "                                                            <span class=\"il\">\n"
				+ "                                                \n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                Contacto: " + user.getNumberPhone() + "\n"
				+ "                                                                <br>\n"
				+ "                                                                Email: " + user.getUsername() + "\n"
				+ "                                                              </p>\n"
				+ "                                                              \n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                Si recientemente cambiaste alguno de los datos anteriores, te sugerimos actualizarlos en\n"
				+ "                                                                <a href=\"https://hale.com.mx/#/profile/cv\" target=\"_blank\">\n"
				+ "                                                                  tu CV\n"
				+ "                                                                </a>\n"
				+ "                                                                .\n"
				+ "                                                              </p>\n"
				+ "\n"
				+ "\n"
				+ "                                                            </span>\n"
				+ "                                                            </p>\n"
				+ "                                                            </td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td><a href=\"https://hale.com.mx/#/search\"  style=\"color:#1b74e4;text-decoration:none;display:block;width:370px\" target=\"_blank\" >\n"
				+ "                                                                <table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                                  <tbody>\n"
				+ "                                                                    <tr>\n"
				+ "                                                                      <td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #FFD605;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#FFD605\">\n"
				+ "                                                                        <a style=\"color:#FFD605;text-decoration:none;display:block\" target=\"_blank\" >\n"
				+ "                                                                          <center>\n"
				+ "                                                                            <font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\"><span class=\"il\"> Continuar buscando ofertas </span></font>\n"
				+ "                                                                          </center>\n"
				+ "                                                                        </a>\n"
				+ "                                                                    </td>\n"
				+ "                                                                    </tr>\n"
				+ "                                                                  </tbody>\n"
				+ "                                                                </table>\n"
				+ "                                                              </a></td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                        </tbody>\n"
				+ "                                                      </table>\n"
				+ "                                                    </td>\n"
				+ "                                                  </tr>\n"
				+ "                                                </tbody>\n"
				+ "                                              </table>\n"
				+ "                                            </td>\n"
				+ "                                          </tr>\n"
				+ "                                        </tbody>\n"
				+ "                                      </table>\n"
				+ "                                    </td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td style=\"text-align:center\">\n"
				+ "                              <div style=\"padding-top:10px;display:flex\">\n"
				+ "                                <div style=\"margin:auto\"><img src=\"https://hale.com.mx/assets/images/logo-b.png\"  width=\"52\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></div><br>\n"
				+ "                              </div>\n"
				+ "                              <div style=\"height:10px\"></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">Has recibido este correo por ser usuario de los servicios de hale.com.mx <br></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">© Hale  Jobs. Mérida,Yucatan, CP 97209, #916 <br></div>\n"
				+ "\n"
				+ "                              <br>\n"
				+ "                              \n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\"> \n"
				+ "                                Este mensaje es enviado por Hale Jobs y contiene información que puede ser privilegiada y confidencial, no se deberá usar, copiar o comunicar de manera parcial ni total. Los datos recabados tienen la finalidad de brindarte asesoría respecto al uso de productos y servicios ya sean propios o de terceros y que consideremos pueden ser de tu interés.\n"
				+ "                                <br></div>\n"
				+ "\n"
				+ "\n"
				+ "                            </td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                </tbody>\n"
				+ "            </td>\n"
				+ "          </tr>\n"
				+ "        </tbody>\n"
				+ "      </table>\n"
				+ "</body>\n"
				+ "</html>",  "text/html");
		
		// message.setText("Hola " + firstName + ", \n \n El password para tu cuenta es:
		// " + token + "\n \n Ingresa a nuestro sitio: https://portal.macropay.mx/login"
		// + "\n \n Team SAP");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}
	
	private Message messageSeleccionado(Offer offer, User user, String messageContent, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setSubject("HALE JOBS:" + offer.getCompany().getName() + " te ha seleccionado para la vacante " + offer.getTitle());
	
		message.setContent("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Mensaje -  Hale Jobs</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_-7626415423304311386email_table\" style=\"border-collapse:collapse\">\n"
				+ "        <tbody>\n"
				+ "          <tr>\n"
				+ "            <td id=\"m_-7626415423304311386email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">\n"
				+ "              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                <tbody>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\">\n"
				+ "                              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td style=\"width:100%;text-align:left;height:33px\"><img height=\"55\" src=\"https://hale.com.mx/assets/images/logo-b.png\" style=\"border:0\" class=\"CToWUd\" data-bit=\"iit\"></td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td>\n"
				+ "                              <table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td>\n"
				+ "                                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                        <tbody>\n"
				+ "                                          <tr>\n"
				+ "                                            <td>\n"
				+ "                                              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                <tbody>\n"
				+ "                                                  <tr>\n"
				+ "                                                    <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                                    <td>\n"
				+ "                                                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                        <tbody>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td>\n"
				+ "                                                              <p style=\"margin:5px 0 10px 0;color:#000000;font-size:16px\">!Hola " + user.getNames() + "¡\n"
				+ "                                                              </p>\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\"> \n"
				+ "                                                                La " + offer.getCompany().getName() + "\n"
				+ "                                                                inicio el proceso de selección para la posición:\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                                " + offer.getTitle() + "\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                              </p>\n"
				+ "                                                            <span class=\"il\">\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                "  +  messageContent + "\n"
				+ "                                                              </p>\n"
				+ "\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                Contacto: " + user.getNumberPhone() + "\n"
				+ "                                                                <br>\n"
				+ "                                                                Email:" +  user.getUsername() + "\n"
				+ "                                                              </p>\n"
				+ "\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                Si recientemente cambiaste alguno de los datos anteriores, te sugerimos actualizarlos en\n"
				+ "                                                                <a href=\"https://hale.com.mx/#/profile/cv\" target=\"_blank\">\n"
				+ "                                                                  tu CV\n"
				+ "                                                                </a>\n"
				+ "                                                                .\n"
				+ "                                                              </p>\n"
				+ "\n"
				+ "\n"
				+ "                                                            </span>\n"
				+ "                                                            </p>\n"
				+ "                                                            </td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td><a href=\"https://hale.com.mx/#/search\"  style=\"color:#1b74e4;text-decoration:none;display:block;width:370px\" target=\"_blank\" >\n"
				+ "                                                                <table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                                  <tbody>\n"
				+ "                                                                    <tr>\n"
				+ "                                                                      <td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #FFD605;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#FFD605\">\n"
				+ "                                                                        <a style=\"color:#FFD605;text-decoration:none;display:block\" target=\"_blank\" >\n"
				+ "                                                                          <center>\n"
				+ "                                                                            <font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\"><span class=\"il\"> Continuar buscando ofertas </span></font>\n"
				+ "                                                                          </center>\n"
				+ "                                                                        </a>\n"
				+ "                                                                    </td>\n"
				+ "                                                                    </tr>\n"
				+ "                                                                  </tbody>\n"
				+ "                                                                </table>\n"
				+ "                                                              </a></td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                        </tbody>\n"
				+ "                                                      </table>\n"
				+ "                                                    </td>\n"
				+ "                                                  </tr>\n"
				+ "                                                </tbody>\n"
				+ "                                              </table>\n"
				+ "                                            </td>\n"
				+ "                                          </tr>\n"
				+ "                                        </tbody>\n"
				+ "                                      </table>\n"
				+ "                                    </td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td style=\"text-align:center\">\n"
				+ "                              <div style=\"padding-top:10px;display:flex\">\n"
				+ "                                <div style=\"margin:auto\"><img src=\"https://hale.com.mx/assets/images/logo-b.png\"  width=\"52\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></div><br>\n"
				+ "                              </div>\n"
				+ "                              <div style=\"height:10px\"></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">Has recibido este correo por ser usuario de los servicios de hale.com.mx <br></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">© Hale  Jobs. Mérida,Yucatan, CP 97209, #916 <br></div>\n"
				+ "\n"
				+ "                              <br>\n"
				+ "                              \n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\"> \n"
				+ "                                Este mensaje es enviado por Hale Jobs y contiene información que puede ser privilegiada y confidencial, no se deberá usar, copiar o comunicar de manera parcial ni total. Los datos recabados tienen la finalidad de brindarte asesoría respecto al uso de productos y servicios ya sean propios o de terceros y que consideremos pueden ser de tu interés.\n"
				+ "                                <br></div>\n"
				+ "\n"
				+ "\n"
				+ "                            </td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                </tbody>\n"
				+ "            </td>\n"
				+ "          </tr>\n"
				+ "        </tbody>\n"
				+ "      </table>\n"
				+ "</body>\n"
				+ "</html>",  "text/html");
		
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Message messageRechazo( Offer offer, User user,String messageContent, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setSubject("HALE JOBS: No has sido seleccionado");
	
		message.setContent("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Mensaje -  Hale Jobs</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_-7626415423304311386email_table\" style=\"border-collapse:collapse\">\n"
				+ "        <tbody>\n"
				+ "          <tr>\n"
				+ "            <td id=\"m_-7626415423304311386email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">\n"
				+ "              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                <tbody>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\">\n"
				+ "                              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td style=\"width:100%;text-align:left;height:33px\"><img height=\"55\" src=\"https://hale.com.mx/assets/images/logo-b.png\" style=\"border:0\" class=\"CToWUd\" data-bit=\"iit\"></td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td>\n"
				+ "                              <table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td>\n"
				+ "                                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                        <tbody>\n"
				+ "                                          <tr>\n"
				+ "                                            <td>\n"
				+ "                                              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                <tbody>\n"
				+ "                                                  <tr>\n"
				+ "                                                    <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                                    <td>\n"
				+ "                                                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                        <tbody>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td>\n"
				+ "                                                              <p style=\"margin:5px 0 10px 0;color:#000000;font-size:16px\">Hola " +  user.getNames() +  "\n"
				+ "                                                              </p>\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\"> \n"
				+ "                                                                " + messageContent +"\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                                Desearíamos tener mejores noticias. Te sugerimos seguir buscando y postularte a otros empleos.\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                              </p>\n"
				+ "                                                            <span class=\"il\">\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                Mucha suerte,\n"
				+ "                                                                <br>\n"
				+ "                                                                Hale Jobs.\n"
				+ "                                                              </p>\n"
				+ "\n"
				+ "                                                              \n"
				+ "                                                              \n"
				+ "\n"
				+ "                                                            </span>\n"
				+ "                                                            </p>\n"
				+ "                                                            </td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td><a href=\"https://hale.com.mx/#/search\"  style=\"color:#1b74e4;text-decoration:none;display:block;width:370px\" target=\"_blank\" >\n"
				+ "                                                                <table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                                  <tbody>\n"
				+ "                                                                    <tr>\n"
				+ "                                                                      <td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #FFD605;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#FFD605\">\n"
				+ "                                                                        <a style=\"color:#FFD605;text-decoration:none;display:block\" target=\"_blank\" >\n"
				+ "                                                                          <center>\n"
				+ "                                                                            <font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\"><span class=\"il\"> Continuar buscando ofertas </span></font>\n"
				+ "                                                                          </center>\n"
				+ "                                                                        </a>\n"
				+ "                                                                    </td>\n"
				+ "                                                                    </tr>\n"
				+ "                                                                  </tbody>\n"
				+ "                                                                </table>\n"
				+ "                                                              </a></td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                        </tbody>\n"
				+ "                                                      </table>\n"
				+ "                                                    </td>\n"
				+ "                                                  </tr>\n"
				+ "                                                </tbody>\n"
				+ "                                              </table>\n"
				+ "                                            </td>\n"
				+ "                                          </tr>\n"
				+ "                                        </tbody>\n"
				+ "                                      </table>\n"
				+ "                                    </td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td style=\"text-align:center\">\n"
				+ "                              <div style=\"padding-top:10px;display:flex\">\n"
				+ "                                <div style=\"margin:auto\"><img src=\"https://hale.com.mx/assets/images/logo-b.png\"  width=\"52\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></div><br>\n"
				+ "                              </div>\n"
				+ "                              <div style=\"height:10px\"></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">Has recibido este correo por ser usuario de los servicios de hale.com.mx <br></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">© Hale  Jobs. Mérida,Yucatan, CP 97209, #916 <br></div>\n"
				+ "\n"
				+ "                              <br>\n"
				+ "                              \n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\"> \n"
				+ "                                Este mensaje es enviado por Hale Jobs y contiene información que puede ser privilegiada y confidencial, no se deberá usar, copiar o comunicar de manera parcial ni total. Los datos recabados tienen la finalidad de brindarte asesoría respecto al uso de productos y servicios ya sean propios o de terceros y que consideremos pueden ser de tu interés.\n"
				+ "                                <br></div>\n"
				+ "\n"
				+ "\n"
				+ "                            </td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                </tbody>\n"
				+ "            </td>\n"
				+ "          </tr>\n"
				+ "        </tbody>\n"
				+ "      </table>\n"
				+ "</body>\n"
				+ "</html>",  "text/html");
		
		// message.setText("Hola " + firstName + ", \n \n El password para tu cuenta es:
		// " + token + "\n \n Ingresa a nuestro sitio: https://portal.macropay.mx/login"
		// + "\n \n Team SAP");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Message messageUser( Offer offer, User user,String messageForUser, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setSubject(offer.getUser().getNames() + " te ha enviado un mensaje" + " - HALE JOBS");
	
		message.setContent("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Mensaje -  Hale Jobs</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_-7626415423304311386email_table\" style=\"border-collapse:collapse\">\n"
				+ "        <tbody>\n"
				+ "          <tr>\n"
				+ "            <td id=\"m_-7626415423304311386email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">\n"
				+ "              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                <tbody>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\">\n"
				+ "                              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td style=\"width:100%;text-align:left;height:33px\"><img height=\"55\" src=\"https://hale.com.mx/assets/images/logo-b.png\" style=\"border:0\" class=\"CToWUd\" data-bit=\"iit\"></td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td>\n"
				+ "                              <table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td>\n"
				+ "                                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                        <tbody>\n"
				+ "                                          <tr>\n"
				+ "                                            <td>\n"
				+ "                                              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                <tbody>\n"
				+ "                                                  <tr>\n"
				+ "                                                    <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                                    <td>\n"
				+ "                                                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                        <tbody>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td>\n"
				+ "                                                              <p style=\"margin:5px 0 10px 0;color:#565a5c;font-size:18px\">Hola " + user.getNames() + "\n"
				+ "                                                              </p>\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#565a5c;font-size:18px\"> \n"
				+ "                                                                " +  offer.getUser().getSurnames() + "  de la empresa " +  offer.getCompany().getName() + "\n"
				+ "                                                                \n"
				+ "                                                                te ha enviado un mensaje: \n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                            <span class=\"il\">\n"
				+ "                                                                " +  messageForUser  +  "\n"
				+ "                                                            </span>\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                             Relacionado a la siguiente oferta: " + offer.getTitle() + ""
				+ "                                                            </p>\n"
				+ "                                                            </td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td><a href=\"https://hale.com.mx/#/view-job/" + offer.getId() + "\"  style=\"color:#1b74e4;text-decoration:none;display:block;width:370px\" target=\"_blank\" >\n"
				+ "                                                                <table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                                  <tbody>\n"
				+ "                                                                    <tr>\n"
				+ "                                                                      <td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #FFD605;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#FFD605\">\n"
				+ "                                                                        <a style=\"color:#FFD605;text-decoration:none;display:block\" target=\"_blank\" >\n"
				+ "                                                                          <center>\n"
				+ "                                                                            <font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\"><span class=\"il\">Ver oferta </span></font>\n"
				+ "                                                                          </center>\n"
				+ "                                                                        </a>\n"
				+ "                                                                    </td>\n"
				+ "                                                                    </tr>\n"
				+ "                                                                  </tbody>\n"
				+ "                                                                </table>\n"
				+ "                                                              </a></td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                        </tbody>\n"
				+ "                                                      </table>\n"
				+ "                                                    </td>\n"
				+ "                                                  </tr>\n"
				+ "                                                </tbody>\n"
				+ "                                              </table>\n"
				+ "                                            </td>\n"
				+ "                                          </tr>\n"
				+ "                                        </tbody>\n"
				+ "                                      </table>\n"
				+ "                                    </td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td style=\"text-align:center\">\n"
				+ "                              <div style=\"padding-top:10px;display:flex\">\n"
				+ "                                <div style=\"margin:auto\"><img src=\"https://hale.com.mx/assets/images/logo-b.png\"  width=\"52\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></div><br>\n"
				+ "                              </div>\n"
				+ "                              <div style=\"height:10px\"></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">Has recibido este correo por ser usuario de los servicios de hale.com.mx <br></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">© Hale  Jobs. Mérida,Yucatan, CP 97209, #916 <br></div>\n"
				+ "\n"
				+ "                              <br>\n"
				+ "                              \n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\"> \n"
				+ "                                Este mensaje es enviado por Hale Jobs y contiene información que puede ser privilegiada y confidencial, no se deberá usar, copiar o comunicar de manera parcial ni total. Los datos recabados tienen la finalidad de brindarte asesoría respecto al uso de productos y servicios ya sean propios o de terceros y que consideremos pueden ser de tu interés.\n"
				+ "                                <br></div>\n"
				+ "\n"
				+ "\n"
				+ "                            </td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                </tbody>\n"
				+ "            </td>\n"
				+ "          </tr>\n"
				+ "        </tbody>\n"
				+ "      </table>\n"
				+ "</body>\n"
				+ "</html>",  "text/html");
		
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	private Message messageOfertaCerrada(Offer offer, User user, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setSubject("HALE JOBS: La oferta "  + offer.getTitle()  + " ha dejado de recibir postulaciones");
	
		message.setContent("<!DOCTYPE html>\n"
				+ "<html lang=\"en\">\n"
				+ "<head>\n"
				+ "    <meta charset=\"UTF-8\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Mensaje -  Hale Jobs</title>\n"
				+ "</head>\n"
				+ "<body>\n"
				+ "    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_-7626415423304311386email_table\" style=\"border-collapse:collapse\">\n"
				+ "        <tbody>\n"
				+ "          <tr>\n"
				+ "            <td id=\"m_-7626415423304311386email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">\n"
				+ "              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                <tbody>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\">\n"
				+ "                              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td style=\"width:100%;text-align:left;height:33px\"><img height=\"55\" src=\"https://hale.com.mx/assets/images/logo-b.png\" style=\"border:0\" class=\"CToWUd\" data-bit=\"iit\"></td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td>\n"
				+ "                              <table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\">\n"
				+ "                                <tbody>\n"
				+ "                                  <tr>\n"
				+ "                                    <td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td>\n"
				+ "                                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                        <tbody>\n"
				+ "                                          <tr>\n"
				+ "                                            <td>\n"
				+ "                                              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                <tbody>\n"
				+ "                                                  <tr>\n"
				+ "                                                    <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                                                    <td>\n"
				+ "                                                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                        <tbody>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td>\n"
				+ "                                                              <p style=\"margin:5px 0 10px 0;color:#000000;font-size:16px\">Hola " +  user.getNames() + "\n"
				+ "                                                              </p>\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\"> \n"
				+ "                                                                La oferta " + offer.getTitle() + "\n"
				+ "                                                                ha sido cerrada.\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                                Esto significa que ya no está recibiendo currículos por el momento. Te invitamos a aplicar a más ofertas.\n"
				+ "                                                                <br>\n"
				+ "                                                                <br>\n"
				+ "                                                              </p>\n"
				+ "                                                            <span class=\"il\">\n"
				+ "                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\n"
				+ "                                                                Mucha suerte, Hale Jobs.\n"
				+ "                                                              </p>\n"
				+ "\n"
				+ "                                                              \n"
				+ "                                                              \n"
				+ "\n"
				+ "                                                            </span>\n"
				+ "                                                            </p>\n"
				+ "                                                            </td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td><a href=\"https://hale.com.mx/#/search\"  style=\"color:#1b74e4;text-decoration:none;display:block;width:370px\" target=\"_blank\" >\n"
				+ "                                                                <table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\n"
				+ "                                                                  <tbody>\n"
				+ "                                                                    <tr>\n"
				+ "                                                                      <td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #FFD605;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#FFD605\">\n"
				+ "                                                                        <a style=\"color:#FFD605;text-decoration:none;display:block\" target=\"_blank\" >\n"
				+ "                                                                          <center>\n"
				+ "                                                                            <font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\"><span class=\"il\"> Continuar buscando ofertas </span></font>\n"
				+ "                                                                          </center>\n"
				+ "                                                                        </a>\n"
				+ "                                                                    </td>\n"
				+ "                                                                    </tr>\n"
				+ "                                                                  </tbody>\n"
				+ "                                                                </table>\n"
				+ "                                                              </a></td>\n"
				+ "                                                          </tr>\n"
				+ "                                                          <tr>\n"
				+ "                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\n"
				+ "                                                          </tr>\n"
				+ "                                                        </tbody>\n"
				+ "                                                      </table>\n"
				+ "                                                    </td>\n"
				+ "                                                  </tr>\n"
				+ "                                                </tbody>\n"
				+ "                                              </table>\n"
				+ "                                            </td>\n"
				+ "                                          </tr>\n"
				+ "                                        </tbody>\n"
				+ "                                      </table>\n"
				+ "                                    </td>\n"
				+ "                                  </tr>\n"
				+ "                                  <tr>\n"
				+ "                                    <td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td>\n"
				+ "                                  </tr>\n"
				+ "                                </tbody>\n"
				+ "                              </table>\n"
				+ "                            </td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td>\n"
				+ "                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\">\n"
				+ "                        <tbody>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td style=\"text-align:center\">\n"
				+ "                              <div style=\"padding-top:10px;display:flex\">\n"
				+ "                                <div style=\"margin:auto\"><img src=\"https://hale.com.mx/assets/images/logo-b.png\"  width=\"52\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></div><br>\n"
				+ "                              </div>\n"
				+ "                              <div style=\"height:10px\"></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">Has recibido este correo por ser usuario de los servicios de hale.com.mx <br></div>\n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">© Hale  Jobs. Mérida,Yucatan, CP 97209, #916 <br></div>\n"
				+ "\n"
				+ "                              <br>\n"
				+ "                              \n"
				+ "                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\"> \n"
				+ "                                Este mensaje es enviado por Hale Jobs y contiene información que puede ser privilegiada y confidencial, no se deberá usar, copiar o comunicar de manera parcial ni total. Los datos recabados tienen la finalidad de brindarte asesoría respecto al uso de productos y servicios ya sean propios o de terceros y que consideremos pueden ser de tu interés.\n"
				+ "                                <br></div>\n"
				+ "\n"
				+ "\n"
				+ "                            </td>\n"
				+ "                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\n"
				+ "                            <td width=\"15px\" style=\"width:15px\"></td>\n"
				+ "                          </tr>\n"
				+ "                          <tr>\n"
				+ "                            <td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                          </tr>\n"
				+ "                        </tbody>\n"
				+ "                      </table>\n"
				+ "                    </td>\n"
				+ "                  </tr>\n"
				+ "                  <tr>\n"
				+ "                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\n"
				+ "                  </tr>\n"
				+ "                </tbody>\n"
				+ "            </td>\n"
				+ "          </tr>\n"
				+ "        </tbody>\n"
				+ "      </table>\n"
				+ "</body>\n"
				+ "</html>",  "text/html");
		
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}


	// Postulación
	private Message messagePostulacionRH(Offer offer, User user, String email) throws MessagingException {
		Message message = new MimeMessage(getEmailSession());
		message.setFrom(new InternetAddress(FROM_EMAIL));
		message.setRecipients(TO, InternetAddress.parse(email, false));
		message.setSubject("HALE JOBS: Tienes un nuevo candidato para la oferta: "  + offer.getTitle());
	
		message.setContent("<!DOCTYPE html>\r\n" + //
						"<html lang=\"en\">\r\n" + //
						"<head>\r\n" + //
						"    <meta charset=\"UTF-8\">\r\n" + //
						"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
						"    <title>Mensaje -  Hale Jobs</title>\r\n" + //
						"</head>\r\n" + //
						"<body>\r\n" + 
						"    <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" id=\"m_-7626415423304311386email_table\" style=\"border-collapse:collapse\">\r\n" + 
						"        <tbody>\r\n" + 
						"          <tr>\r\n" + 
						"            <td id=\"m_-7626415423304311386email_content\" style=\"font-family:Helvetica Neue,Helvetica,Lucida Grande,tahoma,verdana,arial,sans-serif;background:#ffffff\">\r\n" + 
						"              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\r\n" + 
						"                <tbody>\r\n" + 
						"                  <tr>\r\n" + 
						"                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\r\n" + 
						"                  </tr>\r\n" + 
						"                  <tr>\r\n" + 
						"                    <td height=\"1\" colspan=\"3\" style=\"line-height:1px\"></td>\r\n" + 
						"                  </tr>\r\n" + 
						"                  <tr>\r\n" + 
						"                    <td>\r\n" + 
						"                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;text-align:center;width:100%\">\r\n" + 
						"                        <tbody>\r\n" + 
						"                          <tr>\r\n" + 
						"                            <td width=\"15px\" style=\"width:15px\"></td>\r\n" + 
						"                            <td style=\"line-height:0px;max-width:600px;padding:0 0 15px 0\">\r\n" + 
						"                              <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\r\n" + 
						"                                <tbody>\r\n" + 
						"                                  <tr>\r\n" + 
						"                                    <td style=\"width:100%;text-align:left;height:33px\"><img height=\"55\" src=\"https:hale.com.mx/assets/images/logo-b.png\" style=\"border:0\" class=\"CToWUd\" data-bit=\"iit\"></td>\r\n" + 
						"                                  </tr>\r\n" + 
						"                                </tbody>\r\n" + 
						"                              </table>\r\n" + 
						"                            </td>\r\n" + 
						"                            <td width=\"15px\" style=\"width:15px\"></td>\r\n" + 
						"                          </tr>\r\n" + 
						"                        </tbody>\r\n" + 
						"                      </table>\r\n" + 
						"                    </td>\r\n" + 
						"                  </tr>\r\n" + 
						"                  <tr>\r\n" + 
						"                    <td>\r\n" + 
						"                      <table border=\"0\" width=\"430\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto\">\r\n" + 
						"                        <tbody>\r\n" + 
						"                          <tr>\r\n" + 
						"                            <td>\r\n" + 
						"                              <table border=\"0\" width=\"430px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:430px\">\r\n" + 
						"                                <tbody>\r\n" + 
						"                                  <tr>\r\n" + 
						"                                    <td width=\"15\" style=\"display:block;width:15px\">&nbsp;&nbsp;&nbsp;</td>\r\n" + 
						"                                  </tr>\r\n" + 
						"                                  <tr>\r\n" + 
						"                                    <td>\r\n" + 
						"                                      <table border=\"0\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\r\n" + 
						"                                        <tbody>\r\n" + 
						"                                          <tr>\r\n" + 
						"                                            <td>\r\n" + 
						"                                              <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\r\n" + 
						"                                                <tbody>\r\n" + 
						"                                                  <tr>\r\n" + 
						"                                                    <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\r\n" + 
						"                                                    <td>\r\n" + 
						"                                                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\r\n" + 
						"                                                        <tbody>\r\n" + 
						"                                                          <tr>\r\n" + 
						"                                                            <td>\r\n" + 
						"                                                              <p style=\"margin:5px 0 10px 0;color:#000000;font-size:16px\">Hola " + offer.getUser().getNames() + " \r\n" + 
						"                                                              </p>\r\n" + 
						"                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\"> \r\n" + 
						"                                                                \r\n" + 
						"                                                                "+  user.getSurnames() + " se ha postulado exitosamente a tu vacante '" + offer.getTitle() + "'\r\n" + 
						"                                                                <br>\r\n" + 
						"                                                                <br>\r\n" + 
						"                                                                Estos son sus datos de contacto:.\r\n" + 
						"                                                                <br>\r\n" + 
						"                                                                <br>\r\n" + 
						"                                                                Teléfono: " + user.getNumberPhone() + "<br> Email: " +  user.getUsername() + "\r\n" + 
						"                                                              </p>\r\n" + 
						"                                                            <span class=\"il\">\r\n" + 
						"                                                              <p style=\"margin:10px 0 10px 0;color:#000000;font-size:14px\">\r\n" + 
						"                                                                Mucha suerte, <br> Hale Jobs.\r\n" + 
						"                                                              </p>\r\n" + 
						"\r\n" + 
						"                                                              \r\n" + 
						"                                                              \r\n" + 
						"\r\n" + 
						"                                                            </span>\r\n" + 
						"                                                            </p>\r\n" + 
						"                                                            </td>\r\n" + 
						"                                                          </tr>\r\n" + 
						"                                                          <tr>\r\n" + 
						"                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\r\n" + 
						"                                                          </tr>\r\n" + 
						"                                                          <tr>\r\n" + 
						"                                                            <td><a href=\"https://hale.com.mx/#/dashboard/view-worker/" + user.getId() + "\"  style=\"color:#1b74e4;text-decoration:none;display:block;width:370px\" target=\"_blank\" >\r\n" + 
						"                                                                <table border=\"0\" width=\"390\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse\">\r\n" + 
						"                                                                  <tbody>\r\n" + 
						"                                                                    <tr>\r\n" + 
						"                                                                      <td style=\"border-collapse:collapse;border-radius:3px;text-align:center;display:block;border:solid 1px #FFD605;padding:10px 16px 14px 16px;margin:0 2px 0 auto;min-width:80px;background-color:#FFD605\">\r\n" + 
						"                                                                        <a style=\"color:#FFD605;text-decoration:none;display:block\" target=\"_blank\" >\r\n" + 
						"                                                                          <center>\r\n" + 
						"                                                                            <font size=\"3\"><span style=\"font-family:Helvetica Neue,Helvetica,Roboto,Arial,sans-serif;white-space:nowrap;font-weight:bold;vertical-align:middle;color:#fdfdfd;font-size:16px;line-height:16px\"><span class=\"il\"> Ver perfil del candidato </span></font>\r\n" + 
						"                                                                          </center>\r\n" + 
						"                                                                        </a>\r\n" + 
						"                                                                    </td>\r\n" + 
						"                                                                    </tr>\r\n" + 
						"                                                                  </tbody>\r\n" + 
						"                                                                </table>\r\n" + 
						"                                                              </a></td>\r\n" + 
						"                                                          </tr>\r\n" + 
						"                                                          <tr>\r\n" + 
						"                                                            <td height=\"20\" style=\"line-height:20px\">&nbsp;</td>\r\n" + 
						"                                                          </tr>\r\n" + 
						"                                                        </tbody>\r\n" + 
						"                                                      </table>\r\n" + 
						"                                                    </td>\r\n" + 
						"                                                  </tr>\r\n" + 
						"                                                </tbody>\r\n" + 
						"                                              </table>\r\n" + 
						"                                            </td>\r\n" + 
						"                                          </tr>\r\n" + 
						"                                        </tbody>\r\n" + 
						"                                      </table>\r\n" + 
						"                                    </td>\r\n" + 
						"                                  </tr>\r\n" + 
						"                                  <tr>\r\n" + 
						"                                    <td height=\"10\" style=\"line-height:10px\" colspan=\"1\">&nbsp;</td>\r\n" + 
						"                                  </tr>\r\n" + 
						"                                </tbody>\r\n" + 
						"                              </table>\r\n" + 
						"                            </td>\r\n" + 
						"                          </tr>\r\n" + 
						"                        </tbody>\r\n" + 
						"                      </table>\r\n" + 
						"                    </td>\r\n" + 
						"                  </tr>\r\n" + 
						"                  <tr>\r\n" + 
						"                    <td>\r\n" + 
						"                      <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-collapse:collapse;margin:0 auto 0 auto;width:100%;max-width:600px\">\r\n" + 
						"                        <tbody>\r\n" + 
						"                          <tr>\r\n" + 
						"                            <td height=\"4\" style=\"line-height:4px\" colspan=\"3\">&nbsp;</td>\r\n" + 
						"                          </tr>\r\n" + 
						"                          <tr>\r\n" + 
						"                            <td width=\"15px\" style=\"width:15px\"></td>\r\n" + 
						"                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\r\n" + 
						"                            <td style=\"text-align:center\">\r\n" + 
						"                              <div style=\"padding-top:10px;display:flex\">\r\n" + 
						"                                <div style=\"margin:auto\"><img src=\"https:hale.com.mx/assets/images/logo-b.png\"  width=\"52\" alt=\"\" class=\"CToWUd\" data-bit=\"iit\"></div><br>\r\n" + 
						"                              </div>\r\n" + 
						"                              <div style=\"height:10px\"></div>\r\n" + 
						"                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">Has recibido este correo por ser usuario de los servicios de hale.com.mx <br></div>\r\n" + 
						"                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\">© Hale  Jobs. Mérida,Yucatan, CP 97209, #916 <br></div>\r\n" + 
						"\r\n" + 
						"                              <br>\r\n" + 
						"                              \r\n" + 
						"                              <div style=\"color:#abadae;font-size:11px;margin:0 auto 5px auto\"> \r\n" + 
						"                                Este mensaje es enviado por Hale Jobs y contiene información que puede ser privilegiada y confidencial, no se deberá usar, copiar o comunicar de manera parcial ni total. Los datos recabados tienen la finalidad de brindarte asesoría respecto al uso de productos y servicios ya sean propios o de terceros y que consideremos pueden ser de tu interés.\r\n" + 
						"                                <br></div>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"                            </td>\r\n" + 
						"                            <td width=\"20\" style=\"display:block;width:20px\">&nbsp;&nbsp;&nbsp;</td>\r\n" + 
						"                            <td width=\"15px\" style=\"width:15px\"></td>\r\n" + 
						"                          </tr>\r\n" + 
						"                          <tr>\r\n" + 
						"                            <td height=\"32\" style=\"line-height:32px\" colspan=\"3\">&nbsp;</td>\r\n" + 
						"                          </tr>\r\n" + 
						"                        </tbody>\r\n" + 
						"                      </table>\r\n" + 
						"                    </td>\r\n" + 
						"                  </tr>\r\n" + 
						"                  <tr>\r\n" + 
						"                    <td height=\"20\" style=\"line-height:20px\" colspan=\"3\">&nbsp;</td>\r\n" + 
						"                  </tr>\r\n" + 
						"                </tbody>\r\n" + 
						"            </td>\r\n" + 
						"          </tr>\r\n" + 
						"        </tbody>\r\n" + 
						"      </table>\r\n" + 
						"</body>\r\n" + 
						"</html>",  "text/html");
		
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

	
	private Session getEmailSession() {
		Properties properties = System.getProperties();
		properties.put(SMTP_HOST, GMAIL_SMTP_SERVER);
		properties.put(SMTP_AUTH, true);
		properties.put(SMTP_PORT, DEFAULT_PORT);
		properties.put(SMTP_STARTTLS_ENABLE, true);
		properties.put(SMTP_STARTTLS_REQUIRED, true);
		return Session.getInstance(properties, null);
	}

}
