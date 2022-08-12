package com.example.apibot.bot;


import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import com.example.apibot.services.Myservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LondonStockExchange {

    @Autowired
    Myservice myservice ;


    public LondonStockExchange() {
    }

    public void showApiData() throws IOException {
        fetchApiData();
    }

    public void fetchApiData() throws IOException {
        List<Stock_Bot> stockData = new ArrayList<Stock_Bot>();
        final String BASE_URL = "https://api.londonstockexchange.com/api/v1/components/refresh";
        final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
        String Referer = "https://www.londonstockexchange.com/";
//        int numPages = 10;
        for (int i = 0; i < 1; i++) {
            String jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":\"page=" + i + "&size=20&sort=datetime,desc\"}]}";
            Connection.Response response = Jsoup.connect(BASE_URL)
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.POST)
                    .header("content-type", "application/json")
                    .header("referer", Referer)
                    .followRedirects(true)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .requestBody(jsonBody)
                    .method(Connection.Method.POST)
                    .timeout(0)
                    .execute();
            JSONArray arr = new JSONArray(response.body());
            int PAGE_SIZE = arr.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").length();
            for (int x = 0; x < PAGE_SIZE; x++) {
                JSONObject responseObject = arr.getJSONObject(0).getJSONArray("content").getJSONObject(1).getJSONObject("value").getJSONArray("content").getJSONObject(x);
                int companyId = (int) responseObject.get("id");
                String companyName = (String) responseObject.get("companyname");
                String companyTitle = (String) responseObject.get("title");
                String dateTime = (String) responseObject.get("datetime");
                Stock_Bot londonObject = new Stock_Bot(companyId, companyName, companyTitle, dateTime);
                stockData.add(londonObject);

            }
            stockData.forEach(obj -> {
                savetoDb(obj);
            });

        }

    }

    private void savetoDb(Stock_Bot obj) {
        myservice.inserttoDb(obj);
    }
}
