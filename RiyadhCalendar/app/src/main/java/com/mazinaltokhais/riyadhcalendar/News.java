package com.mazinaltokhais.riyadhcalendar;

/**
 * Created by mazoo_000 on 08/04/2015.
 */
public class News {
    String Txt;
    String Url;
    private String content;
    String descraption;
    String pubDate;

    public String getDescraption() {
        return descraption;
    }

    public void setDescraption(String descraption) {
        this.descraption = descraption;
    }
    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public News(String txt, String url) {
        Txt = txt;
        Url = url;
        setContent("");
    }

    public News() {
    }

    public String getTxt() {
        return Txt;
    }

    public String getUrl() {
        return Url;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
