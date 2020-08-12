package cod.currency.service;

import cod.currency.model.Coin;
import cod.currency.model.CryptoCurrency;
import cod.currency.repository.CoinRepository;
import cod.currency.repository.CryptoCurrencyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static cod.currency.util.enums.CoinEnum.BTCEUR;

/**
 * JMA - 8/7/2020 21:57
 **/
@Service
@Log4j2
public class CurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CoinRepository coinRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository, CoinRepository coinRepository, RestTemplate restTemplate) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.coinRepository = coinRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void t() {
        Coin btceur = coinRepository.findByCurrency(BTCEUR.getValue());
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2020, Calendar.AUGUST, 11);
        CryptoCurrency forObject = cryptoCurrencyRepository.avgByCoinAndDate(btceur, gregorianCalendar.getTime());


    }

    @Scheduled(fixedDelay = 60000, initialDelay = 1000)
    public void test() {
        Coin btceur = coinRepository.findByCurrency(BTCEUR.getValue());

        CryptoCurrency forObject = restTemplate.getForObject("https://www.bitstamp.net/api/v2/ticker/{exchange}/", CryptoCurrency.class, btceur.getCurrency());

        forObject.setCoin(btceur);
        CryptoCurrency save = cryptoCurrencyRepository.save(forObject);

        log.info(save.toString());
    }
}
