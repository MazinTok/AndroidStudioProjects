package com.mazinaltokhais.riyadhcalendar;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mazoo_000 on 12/04/2015.
 */
/*
public  class CustomListAdapter extends BaseAdapter {
    List<String> codeLearnChapterList;
    TextView tv;

    public CustomListAdapter(MainActivity mainActivity, int simple_list_item_1, int newslistitem, List<String> list) {
        codeLearnChapterList = list;
       /// tv = ;

    }

    @Override
    public int getCount() {
        return codeLearnChapterList.size();
    }

    @Override
    public Object getItem(int position) {
        return codeLearnChapterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override

        public View getView(int position, View convertView, ViewGroup parent) {
        //View v = super.getView(position, convertView, parent);
       // TextView tv1 = (TextView)convertView.findViewById(R.id.newslistitem);
        //tv1.setText(codeLearnChapterList.get(position));


        ViewHolder holder;
        if (convertView == null) {
            convertView = new LayoutInflater(R.layout.news_row_list, null) {
                @Override
                public LayoutInflater cloneInContext(Context newContext) {
                    return null;
                }
            };
            holder = new ViewHolder();
            holder.news = (TextView)convertView.findViewById(R.id.newslistitem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.news.setText(codeLearnChapterList.get(position));

       return convertView;
    }

    static class ViewHolder {
        TextView news;
    }


}
*/
/*
public class CustomListAdapter extends ArrayAdapter<String> {
    Activity context;
    List<String> items;

    public CustomListAdapter(Activity aContext, List<String> items) {
        super(aContext, R.layout.news_row_list, items);
        context = aContext;
        this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getLayoutInflater();
            row = inflater.inflate(R.layout.news_row_list, null);

        }
        TextView text = (TextView) row.findViewById(R.id.newslistitem);

        text.setText(items.get(position));

        return row;
    }
}

*/
/*
public class CustomListAdapter extends SimpleAdapter {
    private int[] colors = new int[]{0x30FF0000, 0x300000FF};

    public CustomListAdapter(Context context, List<HashMap<String, String>> items, int resource, String[] from, int[] to) {
        super(context, items, resource, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);
        return view;
    }
}*/

public class CustomListAdapter extends ArrayAdapter<News> {

    Context context;
    int layoutResourceId;
    List<News> data = null;
    //String data[] = null;

    public CustomListAdapter(Context context, int layoutResourceId, List<News> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new WeatherHolder();
          //  holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.newslistitem);
            holder.WView = (WebView) row.findViewById(R.id.webView);

            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }

        News weather = (News) data.get(position);
        holder.txtTitle.setText(weather.getTxt());
        //weather.setContent(weather.getTxt() +weather.getContent()());
        holder.WView.loadDataWithBaseURL("", weather.getContent(), "text/html", "UTF-8", "");
       // holder.imgIcon.setImageResource(weather.icon);

        return row;
    }

    static class WeatherHolder
    {
        WebView WView;
        //ImageView imgIcon;
        TextView txtTitle;
    }
}
