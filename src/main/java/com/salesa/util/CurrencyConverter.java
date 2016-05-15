package com.salesa.util;

import java.math.BigDecimal;
import java.util.List;

import com.salesa.entity.Advert;
import com.salesa.entity.CurrencyRate;
import com.salesa.service.cache.CurrencyRateCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CurrencyRateCache currencyRateCache;

    public double calculateRate(String baseCurrency, String targetCurrency) {
        if (baseCurrency.equals(targetCurrency)) {
            return 1;
        }

        List<CurrencyRate> rates = currencyRateCache.getRates();
        double rate = 1;
        if ("UAH".equals(baseCurrency)) {
            for (CurrencyRate currencyRate : rates) {
                if (currencyRate.getCurrency().equals(targetCurrency)) {
                    rate = 1 / currencyRate.getBuy();
                }
            }
        } else if ("UAH".equals(targetCurrency)) {
            for (CurrencyRate currencyRate : rates) {
                if (currencyRate.getCurrency().equals(baseCurrency)) {
                    rate = currencyRate.getSale();
                }
            }
        } else {
            double rateTarget = 1;
            double rateBase = 1;
            for (CurrencyRate currencyRate : rates) {
                if (currencyRate.getCurrency().equals(targetCurrency)) {
                    rateTarget = currencyRate.getSale();
                } else if (currencyRate.getCurrency().equals(baseCurrency)) {
                    rateBase = currencyRate.getSale();
                }
            }
            rate = rateBase / rateTarget;
        }
        return rate;
    }

    public void updatePriceAndCurrency(List<Advert> adverts, String currency) {
        log.info("Updating price information for adverts. Adverts count : {}, currency : {}",
                adverts.size(), currency);
        for (Advert advert : adverts) {
            updatePriceAndCurrency(advert, currency);
        }
    }

    public void updatePriceAndCurrency(Advert advert, String currency) {
        String baseCurrency = advert.getCurrency();
        advert.setCurrency(currency);
        double oldPrice = advert.getPrice();
        double newPrice = oldPrice * calculateRate(baseCurrency, currency);
        BigDecimal price = new BigDecimal(newPrice);
        price = price.setScale(2, BigDecimal.ROUND_CEILING);
        advert.setPrice(price.doubleValue());
    }


}
