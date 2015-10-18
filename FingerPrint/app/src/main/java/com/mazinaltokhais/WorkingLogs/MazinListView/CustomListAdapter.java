package com.mazinaltokhais.WorkingLogs.MazinListView;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mazinaltokhais.WorkingLogs.FPlogsResult;
import com.mazinaltokhais.WorkingLogs.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
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

public class CustomListAdapter extends ArrayAdapter<FPlogsResult> {

    Context context;
    int layoutResourceId;
    List<FPlogsResult> data = null;
    //String data[] = null;

    public CustomListAdapter(Context context, int layoutResourceId, List<FPlogsResult> data) {
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
            holder.txtIn = (TextView)row.findViewById(R.id.InTV);
            holder.txtOut = (TextView)row.findViewById(R.id.OutTV);
            holder.txtday = (TextView)row.findViewById(R.id.dayTxt);

            //holder.WView = (WebView) row.findViewById(R.id.webView);

            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }


        String day = data.get(position).getDay();
        String month = data.get(position).getMonth();
        String year = data.get(position).getYear();
        String txtday="" ;
        Calendar calendar = new GregorianCalendar(Integer.parseInt(year),Integer.parseInt(month)-1,Integer.parseInt(day)); // Note that Month value is 0-based. e.g., 0 for January.
        int reslut = calendar.get(Calendar.DAY_OF_WEEK);
        switch (reslut) {
            case Calendar.SUNDAY:
                txtday = "Sun";
                break;
            case Calendar.MONDAY:
                txtday = "Mon";
                break;
            case Calendar.TUESDAY:
                txtday = "Tue";
                break;
            case Calendar.WEDNESDAY:
                txtday = "Wen";
                break;
            case Calendar.THURSDAY:
                txtday = "Thu";
                break;
            case Calendar.FRIDAY:
                txtday = "Fri";
                break;
            case Calendar.SATURDAY:
                txtday = "Sat";
                break;
        }

        String weather = Showdate(day,month,year);
        holder.txtTitle.setText( weather);
        holder.txtday.setText(txtday);
        holder.txtIn.setText(data.get(position).getTimeIN());
        holder.txtOut.setText(data.get(position).getTimeOut());
        //weather.setContent(weather.getTxt() +weather.getContent()());
       // holder.WView.loadDataWithBaseURL("", weather.getContent(), "text/html", "UTF-8", "");
       // holder.imgIcon.setImageResource(weather.icon);

        return row;
    }

    static class WeatherHolder
    {
        WebView WView;
        //ImageView imgIcon;
        TextView txtTitle;
        TextView txtIn;
        TextView txtOut;
        TextView txtday;
    }
    public String Showdate(String h, String m,String s)
    {
        //String curTime = day + "/" + month + "/" + year;
        String curTime = h + "/" + m + "/" + s;
        return curTime;
    }
}
