package bot;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;

public class LondonStockExchange {
    public void showApiData() throws IOException {
        fetchApiData();
    }
    public void fetchApiData() throws IOException {
        final String BASE_URL = "https://api.londonstockexchange.com/api/v1/components/refresh";
        final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
        String Referer = "https://www.londonstockexchange.com/";
        int numPages = 10;
        for (int i = 0; i <numPages; i++) {
            if (i == 0) {
                String url = "https://www.londonstockexchange.com/news?tab=news-explorer";
            } else {
                String url = "https://www.londonstockexchange.com/news?tab=news-explorer&page=" + i;
            }
            String jsonBody = "{\"path\":\"news\",\"parameters\":\"tab%3Dnews-explorer%26tabId%3D58734a12-d97c-40cb-8047-df76e660f23f\",\"components\":[{\"componentId\":\"block_content%3A431d02ac-09b8-40c9-aba6-04a72a4f2e49\",\"parameters\":\"page="+i+"&size=20&sort=datetime,desc\"}]}";
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
            int statusCode = response.statusCode();
            System.out.println("status code is :" + statusCode);
            System.out.println(response.body());
        }
    }
}
