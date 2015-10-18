package com.mazinaltokhais.riyadhcalendar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    ListView listview;
    TextView Newstxt;
    List<News> NewsResList;
    ProgressBar Pbar2;
    ImageView imageViewNews;
    PrepSharedPreferences shredData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            listview = (ListView) findViewById(R.id.listView);
      //  Newstxt = (TextView) rootView.findViewById(R.id.Newstxt);
        Pbar2 = (ProgressBar) findViewById(R.id.progressBar2);
        //imageViewNews = (ImageView) findViewById(R.id.imageViewNews);
        shredData = new PrepSharedPreferences(this.getApplicationContext());

        NewsResList = shredData.getNewsvalues();
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.fucklistrow, NewsResList);
        listview.setAdapter(adapter);

        // setting list adapter

        new LongOperation().execute("s");

/*        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent;
                intent = new Intent(getApplicationContext(),ServiceWebViewActivity.class);
                String NewsUrl = (String) NewsResList.get(position).getUrl();

                intent.putExtra("titel",getString( R.string.event) );
                NewsUrl = NewsUrl.replace("SPSite Url=", "");
                NewsUrl = "http://www.mohe.gov.sa/ar/Mobile/Det.aspx?URL=" + NewsUrl.substring(NewsUrl.lastIndexOf("sa") + 2);
                intent.putExtra("URL", NewsUrl);
                startActivity(intent);


            }
        });
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LongOperation extends AsyncTask<String, Void, List<News>> {
        @Override
        protected List<News> doInBackground(String... params) {

            HTMLRemoverParser ne = new HTMLRemoverParser();

            return   ne.HTMLRemoverParser("http://www.eyeofriyadh.com/ar/rss/events.php?lang=ar&cat=riyadh");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (listview == null )
                Pbar2.setVisibility(ProgressBar.VISIBLE);

        }

        protected void onPostExecute(List<News> result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            Pbar2.setVisibility(ProgressBar.GONE);

            if (result != null) {
                NewsResList = result;
                shredData.putNewsValue(NewsResList);
                //CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, R.layout.fucklistrow, PrepareListViewssListData(result));
                CustomListAdapter adapter = new CustomListAdapter(MainActivity.this, R.layout.fucklistrow, result);
                listview.setAdapter(adapter);

            }
            else
            {
                Toast.makeText(MainActivity.this, "?? ???? ??????? ?????????",
                        Toast.LENGTH_SHORT).show();

            }

        }
    }
    public String[] PrepareListViewssListData(List<News> result ) {

        if (result != null ) {
            String[] values = new String[result.size()];

            List<String> list = new ArrayList<>();
            for (int i = 0; i < result.size(); ++i) {
                list.add(result.get(i).getTxt());
                values[i] = result.get(i).getTxt();
            }
            return values;
        }
        else
        {
            return null;
        }
    }

}
