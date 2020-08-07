package cod.currency.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;

/**
 * JMA - 8/6/2020 20:00
 * DUMMY to configure Java Mail Sender - I used properties instead of bean configuration
 **/
//@Configuration
public class MailSenderConfiguration {

    private static final String PORT = "";
    private static final String JAVA_MAIL_FILE = "";
    private static final String HOST = "";
    private static final String PROTOCOL = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private ApplicationContext applicationContext;
    private Environment environment;

    @Bean
    public JavaMailSender mailSender() throws IOException {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Basic mail sender configuration, based on emailconfig.properties
        mailSender.setHost(this.environment.getProperty(HOST));
        mailSender.setPort(this.environment.getProperty(PORT) == null ? 587 : Integer.parseInt(this.environment.getProperty(PORT)));
        mailSender.setProtocol(this.environment.getProperty(PROTOCOL));
        mailSender.setUsername(this.environment.getProperty(USERNAME));
        mailSender.setPassword(this.environment.getProperty(PASSWORD));

        // JavaMail-specific mail sender configuration, based on javamail.properties
        final Properties javaMailProperties = new Properties();
        javaMailProperties.load(this.applicationContext.getResource(JAVA_MAIL_FILE).getInputStream());
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;

    }
}
