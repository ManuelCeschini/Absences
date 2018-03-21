package com.example.liquidsoftware.absences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Manuel on 21.03.2018.
 */

public class Absence {
    private String id;
    private String title;
    public String datum;

    public void setPositionId(String id) {

        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getOpenLibraryId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDatum() {
        return datum;
    }

    public static Absence fromJSON(JSONObject o){
        Absence abc = new Absence();
        try {
            if (o.has("cover_edition_key"))  {
                abc.id = o.getString("cover_edition_key");
            } else if(o.has("edition_key")) {
                final JSONArray ids = o.getJSONArray("edition_key");
                abc.id = ids.getString(0);
            }
            abc.title = o.has("title_suggest") ? o.getString("title_suggest") : "";
            abc.author = findeAutor(o);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return book;
    }
    private static String findeAutor(JSONObject o) {
        String result = "";
        try {
            final JSONArray aJSONA = o.getJSONArray("author_name");
            for (int i = 0; i < aJSONA.length(); i++) {
                result = result + aJSONA.getString(i) + "; ";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Book> fromJSON(JSONArray jsonArray) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                Book b = fromJSON(o);
                books.add(b);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}
}
