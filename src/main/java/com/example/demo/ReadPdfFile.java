package com.example.demo;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import sun.rmi.runtime.Log;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;
import java.security.spec.PSSParameterSpec;
import java.util.ArrayList;
import java.util.HashMap;


public class ReadPdfFile {
    ArrayList<String> PDF_LIST = new ArrayList<>();
    ArrayList<String> HTML_LIST = new ArrayList<>();

    public void ReadPdfFile() throws IOException {

        final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
        final String API_URL = "https://bdif.amf-france.org/back/api/v1/informations?typesInformation=VAD&from=0&size=20";
        Connection.Response response = Jsoup.connect(API_URL)
                .userAgent(USER_AGENT)
                .method(Connection.Method.GET)
                .header("content-type", "application/json")
                .followRedirects(true)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .method(Connection.Method.GET)
                .timeout(0)
                .execute();
        JSONObject Response = new JSONObject(response.body());
        JSONObject Data = Response.getJSONObject("hits").getJSONObject("total");
        int Total_Records = Data.getInt("value");
        JSONArray Base_Response = Response.getJSONObject("hits").getJSONArray("hits");
        for (int i = 0; i < Base_Response.length(); i++) {
            JSONObject Base_Object = Base_Response.getJSONObject(i);
            JSONArray Base_Array = Base_Object.getJSONObject("_source").getJSONArray("documents");
            for (int inner = 0; inner < Base_Array.length(); inner++) {
                String nested = (String) Base_Array.getJSONObject(inner).get("path");
                if (nested.contains(".html")) {
                    HTML_LIST.add(nested);
                } else if (nested.contains(".pdf")) {
                    PDF_LIST.add(nested);
                }
            }

        }
    }
}
