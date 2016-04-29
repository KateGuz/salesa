package com.salesa;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class CurrencyConverter {

    private static final String GET_URL = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?valcode=EUR&json";


    /*public static void main(String[] args) throws IOException {
        sendGET();
        System.out.println("GET DONE");
    }
*/
    public static double sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            JSONObject jsonObject = new JSONObject(response.substring(1, response.capacity() - 1));


            in.close();
            return jsonObject.getDouble("rate");
            // print result
          // System.out.println(jsonObject.get("rate"));
        } else {
            System.out.println("GET request not worked");
            return -1;
        }
    }
}
