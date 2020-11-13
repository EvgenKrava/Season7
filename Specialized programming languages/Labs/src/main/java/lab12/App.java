package lab12;

import twitter4j.JSONArray;
import twitter4j.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class App {
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    public static void main(String[] args) {
        System.out.println("Task 1");
        task1("http://google.com");
        System.out.println("Task 2");
        task2();
        System.out.println("Task 3");
        task3();
    }

    private static void task3() {
        //TODO
    }

    private static void task2() {
        LocalDate now = LocalDate.now();
        LocalDate start = LocalDate.of(now.getYear() - 2, now.getMonth(), 1);

        Map<Date, Currency> map = new HashMap<>();
        while (start.isBefore(now)) {
            Date date = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
            map.put(date, getEURByDate(date));
            start = start.plusMonths(1);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        map.forEach((date, currency) -> System.out.println(simpleDateFormat.format(date) + "    " + Double.parseDouble(currency.getSaleRate()) + "   " + Double.parseDouble(currency.getPurchaseRate())));
    }


    private static Currency getEURByDate(Date date) {
        Currency currency = new Currency();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            URL url = new URL("https://api.privatbank.ua/p24api/exchange_rates?json&date=" + simpleDateFormat.format(date));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("exchangeRate");
            int i = 0;
            while (true) {
                if (jsonArray.getJSONObject(i).toString().contains("EUR")) {
                    jsonObject = jsonArray.getJSONObject(i);
                    break;
                }
                ++i;
            }
            currency.setBaseCurrency(jsonObject.getString("baseCurrency"));
            currency.setCurrency(jsonObject.getString("currency"));
            currency.setSaleRateNB(jsonObject.getString("saleRateNB"));
            currency.setPurchaseRateNB(jsonObject.getString("purchaseRateNB"));
            currency.setSaleRate(jsonObject.getString("saleRate"));
            currency.setPurchaseRate(jsonObject.getString("purchaseRate"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currency;
    }

    private static void task1(String url) {
        try {
            URL url1 = new URL(url);
            InputStream inputStream;
            if (url.contains("https")) {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url1.openConnection();
                httpsURLConnection.setRequestMethod("GET");
                inputStream = httpsURLConnection.getInputStream();
            } else {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setRequestMethod("GET");
                inputStream = httpURLConnection.getInputStream();
            }
            try (FileOutputStream fos = new FileOutputStream("page1.html")) {
                int a;
                while ((a = inputStream.read()) != -1) {
                    fos.write(a);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
