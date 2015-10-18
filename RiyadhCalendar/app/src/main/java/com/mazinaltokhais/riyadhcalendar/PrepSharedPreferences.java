package com.mazinaltokhais.riyadhcalendar;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mazoo_000 on 16/04/2015.
 */
public class PrepSharedPreferences {
    SharedPreferences pref;
    SharedPreferences.Editor editor ;

    public PrepSharedPreferences(Context cont)
    {
        pref = cont.getSharedPreferences("MyPref", 0);
        editor = pref.edit();
    }
    public void putNewsValue(List<News> Lnews)
    {
        String txt;
        String url;
        String descraption;
        for (int i =0 ; i <Lnews.size(); i++)
        {
            txt = "news"+String.valueOf(i);
            url = "url"+String.valueOf(i);
            descraption = "url"+String.valueOf(i);
            editor.putString(txt, Lnews.get(i).getTxt());  // Saving string
            editor.putString(url, Lnews.get(i).getUrl());
            editor.putString(descraption, Lnews.get(i).getContent());
            // Save the changes in SharedPreferences
            editor.commit(); // commit changes
        }

    }
    public List<News> getNewsvalues()
    {
        String txt;
        String url;
        String descraption;
        List<News> Lnews = new ArrayList<>();

        for (int i =0 ; i < 10 ; i++){
            News temp = new News();
            txt = "news"+String.valueOf(i);
            url = "url"+String.valueOf(i);
            descraption = "url"+String.valueOf(i);

            temp.setTxt(pref.getString(txt, null));
            temp.setUrl(pref.getString(url, null));
            temp.setContent((pref.getString(descraption, null)));
            if (pref.getString(txt, null)!= null)
            Lnews.add(temp);
        }

        return Lnews;
    }

    public void putEventsValue(List<News> Lnews)
    {
        String txt;
        String url;
        for (int i =0 ; i <Lnews.size(); i++)
        {
            txt = "event"+String.valueOf(i);
            url = "url"+String.valueOf(i);
            editor.putString(txt, Lnews.get(i).getTxt());  // Saving string
            editor.putString(url, Lnews.get(i).getUrl());
            // Save the changes in SharedPreferences
            editor.commit(); // commit changes
        }

    }
    public List<News> getEventsvalues()
    {
        String txt;
        String url;
        List<News> Lnews = new ArrayList<>();

        for (int i =0 ; i < 15 ; i++){
            News temp = new News();
            txt = "event"+String.valueOf(i);
            url = "url"+String.valueOf(i);
            temp.setTxt(pref.getString(txt, null));
            temp.setUrl(pref.getString(url, null));
            if (pref.getString(txt, null)!= null)
            Lnews.add(temp);
        }

        return Lnews;
    }
}
