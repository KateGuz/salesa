package com.salesa.service.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.salesa.entity.CurrencyRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyRateCache {

    private static final String RATE_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private List<CurrencyRate> rates = new ArrayList<>();

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    private synchronized void invalidate() {
        log.info("Start invalidating of currency rate cache");
        rates.clear();
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
                rates = mapper.readValue(jsonString.toString(), TypeFactory.defaultInstance().constructCollectionType(List.class, CurrencyRate.class));

            } else {
                log.error("Get rate request not worked");
            }
        } catch (Exception e) {
            log.error("Error during obtaining currency rate", e);
            throw new RuntimeException(e);
        }
        log.info("Currency rate cache is invalidated, {} rates have been put to cache", rates.size());
    }

    public synchronized List<CurrencyRate> getRates() {
        return new ArrayList<>(rates);
    }


}
