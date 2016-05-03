package com.salesa.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.salesa.entity.CurrencyRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String RATE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public double getRate(String baseCurrency, String targetCurrency) {
        double rate = 0;
        try {
            URL url = new URL(RATE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            log.info("Get rate Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer jsonString = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    jsonString.append(inputLine);
                }
                in.close();

                ObjectMapper mapper = new ObjectMapper();
                List<CurrencyRate> rates = mapper.readValue(jsonString.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, CurrencyRate.class));
                System.out.println(rates);

                return calculateRate(baseCurrency, targetCurrency, rates);

            } else {
                log.error("Get rate request not worked");
                return -1;
            }
        } catch (Exception e) {
            log.error("Error during obtaining currency rate", e);
            throw new RuntimeException(e);
        }
    }

    public double calculateRate(String baseCurrency, String targetCurrency, List<CurrencyRate> list) {
        double rate = 1;
        if (baseCurrency.equals("UAH")) {
            for (CurrencyRate currencyRate : list) {
                if (currencyRate.getCurrency().equals(targetCurrency)) {
                    rate = 1 / currencyRate.getBuy();
                }
            }
        } else if (targetCurrency.equals("UAH")) {
            for (CurrencyRate currencyRate : list) {
                if (currencyRate.getCurrency().equals(baseCurrency)) {
                    rate = currencyRate.getSale();
                }
            }
        } else {
            double rateTarget = 1;
            double rateBase = 1;
            for (CurrencyRate currencyRate : list) {
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

    public static void main(String[] args) {
        CurrencyConverter currencyConverter = new CurrencyConverter();
        System.out.println(currencyConverter.getRate("USD", "UAH"));
    }
}
