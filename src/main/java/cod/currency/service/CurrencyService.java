package cod.currency.service;

import cod.currency.model.Coin;
import cod.currency.model.CryptoCurrency;
import cod.currency.model.CryptoCurrencyReport;
import cod.currency.repository.CoinRepository;
import cod.currency.repository.CryptoCurrencyReportRepository;
import cod.currency.repository.CryptoCurrencyRepository;
import cod.currency.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;

import static cod.currency.util.enums.CoinEnum.BTCEUR;

/**
 * JMA - 8/7/2020 21:57
 **/
@Service
@Log4j2
public class CurrencyService {

    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private static final int SECOND_IN_MILLISECOND = 1000;
    private final CoinRepository coinRepository;
    private final RestTemplate restTemplate;
    private static final int MINUTE_IN_MILLISECOND = SECOND_IN_MILLISECOND * 60;
    private static final int HOUR_IN_MILLISECOND = MINUTE_IN_MILLISECOND * 60;
    private static final int DAY_IN_MILLISECOND = HOUR_IN_MILLISECOND * 24;
    private final CryptoCurrencyReportRepository cryptoCurrencyReportRepository;


    @Autowired
    public CurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository,
                           CryptoCurrencyReportRepository cryptoCurrencyReportRepository,
                           CoinRepository coinRepository,
                           RestTemplate restTemplate) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.cryptoCurrencyReportRepository = cryptoCurrencyReportRepository;
        this.coinRepository = coinRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * Populate unique value to weekly and monthly report. This schedule run every hour to assure at least one record is saved.
     **/
    @Scheduled(fixedDelay = HOUR_IN_MILLISECOND)
    public void saveUniqueValue() {
        // get all active coins
        coinRepository.findByActive(true).forEach(coin -> {
            // SYSDATE
            LocalDate now = LocalDate.now();
            // last element inserted
            Optional<CryptoCurrencyReport> lastElement = cryptoCurrencyReportRepository.findAllByCoin(coin, PageRequest.of(1, 1)).stream().findFirst();
            // the value does not exist or the date is different from today
            if (!lastElement.isPresent() || DateUtil.convertToLocalDateViaInstant(lastElement.get().getTimestamp()).compareTo(now) != 0) {
                // add new element to unique record
                CryptoCurrencyReport element = restTemplate.getForObject("https://www.bitstamp.net/api/v2/ticker/{exchange}/", CryptoCurrencyReport.class, coin.getCurrency());
                if (element != null) {
                    element.setCoin(coin);
                    cryptoCurrencyReportRepository.save(element);
                } else {
                    // log error
                    log.error("We could not get properly the values from api for {} with id equals to {}", coin.getName(), coin.getName());
                }
            }

        });

    }

    @Scheduled(fixedDelay = 60000)
    public void t() {
        Coin btceur = coinRepository.findByCurrency(BTCEUR.getValue());
        LocalDate localDate = LocalDate.now();
        hourReport(localDate, btceur);
    }

    @Scheduled(fixedDelay = 60000)
    public void test() {
        Coin btceur = coinRepository.findByCurrency(BTCEUR.getValue());

        CryptoCurrency forObject = restTemplate.getForObject("https://www.bitstamp.net/api/v2/ticker/{exchange}/", CryptoCurrency.class, btceur.getCurrency());

        forObject.setCoin(btceur);
        CryptoCurrency save = cryptoCurrencyRepository.save(forObject);

        log.info(save.toString());
    }

    public HashMap<String, Object> hourReport(LocalDate date, Coin coin) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("avg", cryptoCurrencyRepository.avgByCoinAndDate(coin, date));
        // 1 day
//        res.put("daily", cryptoCurrencyRepository.findAllByCoin(coin, PageRequest.of(0,1)).stream().findFirst().orElseThrow());
        res.put("table", cryptoCurrencyRepository.findAllByCoin(coin, PageRequest.of(0, 10)));
        return res;
    }

    public HashMap<String, Object> reportCoin() {
        Coin btceur = coinRepository.findByCurrency(BTCEUR.getValue());
        return hourReport(LocalDate.now(), btceur);
    }
}
