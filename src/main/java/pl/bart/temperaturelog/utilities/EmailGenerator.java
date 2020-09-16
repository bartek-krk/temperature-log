package pl.bart.temperaturelog.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.models.Station;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EmailGenerator {

    @Value("classpath:static/emails/credentials.txt")
    Resource resource;

    JavaMailSender javaMailSender;

    public EmailGenerator(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendCredentials(Station station) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(station.geteMail());
        mimeMessageHelper.setSubject("Your Temperature Log credentials");

        String message = "";

        try {
            File file = resource.getFile();
            message = new String(Files.readAllBytes(file.toPath()));
        }
        catch (IOException e) {e.printStackTrace();}

        message = message.replace("$id", station.getId().toString());
        message = message.replace("$key", station.getApiKey());

        System.out.println(message);

        mimeMessageHelper.setText("test", true);
        //javaMailSender.send(mimeMessage); //disabled for testing
    }

}
