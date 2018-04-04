package com.example.liquidsoftware.absences;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class AbsencesClient {

    private static final String API_BASE_URL = "http://absences.bplaced.com/";
    private AsyncHttpClient client;

    public AbsencesClient() {
        client = new AsyncHttpClient();
    }

    public String getAPIUrl(int id) {
        String url = API_BASE_URL;
        url += "Absences_Webservice/";
        url += "?absenz=";
        url += id;
        return url;
    }

    public void getAbsence(JsonHttpResponseHandler handler, int id) {
         client.get(getAPIUrl(id), handler);
    }
}
