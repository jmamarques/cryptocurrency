package cod.currency.service;

import cod.currency.model.Coin;
import cod.currency.model.CryptoCurrency;
import cod.currency.repository.CoinRepository;
import cod.currency.repository.CryptoCurrencyRepository;
import cod.currency.util.DateUtil;
import cod.currency.util.utilities.BaseMapSet;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    @Autowired
    public CurrencyService(CryptoCurrencyRepository cryptoCurrencyRepository,
                           CoinRepository coinRepository,
                           RestTemplate restTemplate) {
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.coinRepository = coinRepository;
        this.restTemplate = restTemplate;
    }

    /**
     * This schedule run every hour to assure at least one record is saved.
     **/
    @Scheduled(fixedDelay = HOUR_IN_MILLISECOND)
    public void saveFirstValueOfDay() {
        // get all active coins
        coinRepository.findByActive(true).forEach(coin -> {
            // SYSDATE
            LocalDate now = LocalDate.now();
            // last element inserted
            Optional<CryptoCurrency> lastElement = cryptoCurrencyRepository.findAllByCoin(coin, PageRequest.of(1, 1)).stream().findFirst();
            // the value does not exist or the date is different from today
            if (!lastElement.isPresent() || DateUtil.convertToLocalDateViaInstant(lastElement.get().getTimestamp()).compareTo(now) != 0) {
                // add new element to unique record
                CryptoCurrency element = getCurrency(coin);
                if (element != null) {
                    element.setCoin(coin);
                    cryptoCurrencyRepository.save(element);
                } else {
                    // log error
                    log.error("We could not get properly the values from api for {} with id equals to {}", coin.getName(), coin.getName());
                }
            }

        });

    }

    /**
     * Auxiliary method to get currency to the current API
     *
     * @param coin - Current coin
     * @return Currency for that coin
     */
    private CryptoCurrency getCurrency(Coin coin) {
        return restTemplate.getForObject("https://www.bitstamp.net/api/v2/ticker/{exchange}/", CryptoCurrency.class, coin.getCurrency());
    }

    /**
     * Periodic schedule - Evaluation coins based in criteria previously defined in Parameter Table
     * Trigger email when we have special conditions
     */
    @Scheduled(fixedDelay = MINUTE_IN_MILLISECOND)
    public void periodicCoinAnalysis() {
        // add logic to my analysis
    }

    public HashMap<String, Object> hourReport(LocalDate date) {
        HashMap<String, Object> res = new HashMap<>();
        res.put("cryptoCurrencies", this.getLastCryptoCurrenciesByEndpoint());
        res.put("currentDate", date);
        res.put("cryptoCurrenciesTable", this.getAllCryptoCurrencies(10));
        res.put("cryptoCurrenciesChart", this.getAllCryptoCurrencies(24));
        return res;
    }

    // to delete
    public HashMap<String, Object> reportCoin() {
        return hourReport(LocalDate.now());
    }

    /**
     * @return the last entry of cryptoCurrencies with coin active
     */
    public List<CryptoCurrency> getLastCryptoCurrencies() {
        return coinRepository.findByActive(true)
                .stream()
                .map(coin -> cryptoCurrencyRepository.findAllByCoin(coin, PageRequest.of(1, 1)).stream().findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /**
     * @return Request cryptoCurrencies with coin active
     */
    public List<CryptoCurrency> getLastCryptoCurrenciesByEndpoint() {
        return coinRepository.findByActive(true)
                .stream()
                .map(coin -> {
                    CryptoCurrency currency = this.getCurrency(coin);
                    currency.setCoin(coin);
                    return currency;
                })
                .collect(Collectors.toList());
    }

    /**
     * Cryptocurrencies of active coins
     *
     * @param size - size of elements to return
     * @return Map with coins and all linked cryptocurrencies with size elements in maximum
     */
    public BaseMapSet<Coin, CryptoCurrency> getAllCryptoCurrencies(int size) {
        BaseMapSet<Coin, CryptoCurrency> map = new BaseMapSet<>();
        coinRepository.findByActive(true)
                .forEach(coin ->
                        map.put(coin, cryptoCurrencyRepository.findAllByCoin(coin, PageRequest.of(1, size))
                                .stream()
                                .collect(Collectors.toList())));
        return map;
    }
}
