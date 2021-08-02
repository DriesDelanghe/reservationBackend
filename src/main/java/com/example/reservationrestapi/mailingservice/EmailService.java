package com.example.reservationrestapi.mailingservice;

import com.example.reservationrestapi.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Locale;

@Service
public class EmailService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JavaMailSender mailSender;

    @Qualifier("emailTemplateEngine")
    @Autowired
    private TemplateEngine stringTemplateEngine;

    private static final String PNG_MIME = "image/png";
    private static final String TJOK_LOGO_IMAGE = "mail/html/images/LogoTjok.png";

    public void sendHTMLMail(
            final Reservation reservation, String subject, final String htmlContent,
            final Locale locale)
            throws MessagingException {

        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message
                = new MimeMessageHelper(mimeMessage, true /* multipart */, "UTF-8");
        message.setSubject(subject);
        message.setFrom("info@tjok.be");
        message.setTo(reservation.getEmail().getEmail());

        // Prepare the evaluation context
        final Context ctx = new Context(locale);
        ctx.setVariable("subscriptionDate", new Date());
        ctx.setVariable("people", reservation.getPersonList());
        ctx.setVariable("openingDates", reservation.getOpeningDateList());

        // Create the HTML body using Thymeleaf
        final String output = stringTemplateEngine.process(htmlContent, ctx);
        message.setText(output, true /* isHtml */);

        // Add the inline images, referenced from the HTML code as "cid:image-name"
        message.addInline("tjok-logo", new ClassPathResource(TJOK_LOGO_IMAGE), PNG_MIME);


        // Send mail
        mailSender.send(mimeMessage);
    }

}
