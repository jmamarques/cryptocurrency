package cod.currency.bootstrap;

import cod.currency.model.CryptoCurrency;
import cod.currency.repository.CryptoCurrencyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.mail.internet.MimeMessage;

/**
 * JMA - 8/5/2020 21:25
 **/
@Component
@Log4j2
public class StartUp implements CommandLineRunner {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ConfigurableApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        validateJasypt();

        //TODO delete dummy
        String exchange = "btceur";
        CryptoCurrency forObject = restTemplate.getForObject("https://www.bitstamp.net/api/v2/ticker/{exchange}/", CryptoCurrency.class, exchange);
        assert forObject != null;
        CryptoCurrency save = cryptoCurrencyRepository.save(forObject);
        log.info(save.toString());

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
        message.setFrom("joaomalado1@hotmail.com");
        message.setTo("jm.a.marques.93@gmail.com");
        message.setSubject("This is the message subject");
        message.setText("This is the message body");
        this.mailSender.send(mimeMessage);
    }

    /**
     * Check if you put -Djasypt.encryptor.password via command line or property file
     */
    private void validateJasypt() {
        String jasyptPassword = env.getProperty("jasypt.encryptor.password");
        boolean isJasyptMandatory = Boolean.parseBoolean(env.getProperty("jasypt.mandatory"));
        if (StringUtils.isEmpty(jasyptPassword)) {
            if (isJasyptMandatory) {
                log.error("-------------------------------------------------------------------");
                log.error("Jasypt is mandatory!");
                log.error("-------------------------------------------------------------------");
                hintForJasypt();
                log.error("-------------------------------------------------------------------");
                log.error("------------------------Server Shut Down---------------------------");
                log.error("-------------------------------------------------------------------");
                ctx.close();
            } else {
                log.warn("Jasypt isn't fill! If you are using this library, it's mandatory.");
                hintForJasypt();
            }
        } else {
            log.info("Jasypt: " + jasyptPassword);
        }
    }

    private void hintForJasypt() {
        log.info("Choose one of these options to fill Jasypt:");
        log.info("Add to VM options: -Djasypt.encryptor.password=secretkey");
        log.info("Add to configuration file: jasypt.encryptor.password=secretkey");
        log.info("More information: https://github.com/ulisesbocchio/jasypt-spring-boot");
    }
}
