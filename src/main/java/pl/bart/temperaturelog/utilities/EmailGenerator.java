package pl.bart.temperaturelog.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.bart.temperaturelog.models.Station;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class EmailGenerator {

    @Value("classpath:static/emails/credentials.html")
    Resource credentialsEmailResource;

    @Value("classpath:static/emails/max_api_calls_exceeded.html")
    Resource apiWarningEmailResource;

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
            File emailFile = credentialsEmailResource.getFile();
            message = new String(Files.readAllBytes(emailFile.toPath()));
        }
        catch (IOException e) {e.printStackTrace();}

        message = message.replace("$location", station.getLocation());
        message = message.replace("$id", station.getId().toString());
        message = message.replace("$key", station.getApiKey());

        mimeMessageHelper.setText(message, true);
        //javaMailSender.send(mimeMessage); //disabled for testing
    }

    public void sendMaxApiCallsWarning(Station station) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(station.geteMail());
        mimeMessageHelper.setSubject("Temperature Log - API calls exceeded");

        String message = "";

        try {
            File emailFile = apiWarningEmailResource.getFile();
            message = new String(Files.readAllBytes(emailFile.toPath()));
        }
        catch (IOException e) {e.printStackTrace();}

        mimeMessageHelper.setText(message, true);
        //javaMailSender.send(mimeMessage); //disabled for testing
    }

}
