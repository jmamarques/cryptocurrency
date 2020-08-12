package cod.currency.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * JMA - 8/6/2020 19:47
 **/
@Service
@Log4j2
public class EmailService {

    @Scheduled(fixedDelay = 100000, initialDelay = 1000)
    public void testEmailSend() {
        log.info("I'm doing this, and I can't stop" + new Date());

    }


}
