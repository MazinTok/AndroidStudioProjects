package com.mazinaltokhais.WorkingLogs;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.mazinaltokhais.WorkingLogs.MazinListView.CustomListAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ShawMonth2Activity extends ActionBarActivity {

    DatabaseHandler db;
    List<FPlogsResult> NewsResList;
    ListView listview;
    TextView MonthTxtView;
    ImageButton PrevButton;
    ImageButton NextButton;
    int AddNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shaw_month2);

        listview = (ListView) findViewById(R.id.listView);
        MonthTxtView = (TextView) findViewById(R.id.textView4);
        NewsResList = new ArrayList<FPlogsResult>();
        AddNumber = 0;

        initializeView(AddNumber);

        PrevButton = (ImageButton) findViewById(R.id.PrevImageB);
        NextButton = (ImageButton) findViewById(R.id.NextImageB);

        PrevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNumber--;
                initializeView(AddNumber);
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNumber++;
                initializeView(AddNumber);
            }
        });

    }

    public void initializeView(int addNumber) {
        db = new DatabaseHandler(this);
        String log3 = "";
        Calendar calendar  = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1 + addNumber;


        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        if (month == 0)
        {
            month = 12;
        }
        else if (month/12 > 1)
        {
            year++;

        }else if(month/12 < 0) {
            year--;
        }

        month = month % 12;

        // String monthstr = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        MonthTxtView.setText(getMonthName(month) + "/" + String.valueOf(year));

//calendar.getn
        for (int i = 1 ; i <30 ; i++)
        {
            List<FPlogs> CheckinLogsByDay = (List<FPlogs>) db.getFPlogsByDateAndtype(String.valueOf(i), String.valueOf(month), "0");

            List<FPlogs> CheckOutLogsByDay = (List<FPlogs>) db.getFPlogsByDateAndtype(String.valueOf(i),String.valueOf(month), "1");

            FPlogsResult _Fplogs = new FPlogsResult() ;

            _Fplogs.setMonth(String.valueOf(month));
            _Fplogs.setDay(String.valueOf(i));
            _Fplogs.setYear(String.valueOf(year));
            _Fplogs.setTimeOut(" ");
            _Fplogs.setTimeIN(" ");


            if (CheckinLogsByDay.size() != 0) {
                int j = CheckinLogsByDay.size();
                FPlogs cn  = CheckinLogsByDay.get(j - 1);
                String log =  cn.getTime() ;
                //_tvcheckin.setText(log);
               // NewsResList.add(log);
                _Fplogs.setTimeIN(log);
            }

            if (CheckOutLogsByDay.size() != 0) {
                int j = CheckOutLogsByDay.size();
                FPlogs cn  = CheckOutLogsByDay.get(j - 1);
                String log =  cn.getTime() ;
               // _tvCheckOut.setText(log);
               // NewsResList.add(log);
                _Fplogs.setTimeOut(log);
            }
           NewsResList.add(_Fplogs);

        }

  /*      // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");
        List<FPlogs> contacts = db.getFPlogsByMonthAndtype(String.valueOf(month), "0");

          for (FPlogs cn : contacts) {
        //if (contacts.size() != 0) {
            //FPlogs cn = contacts.get(contacts.size() - 1);
            String log = "IN: \n" + cn.getTime() + "\n";
            Log.d("Name: ", log);
            log3 += log;
            NewsResList.add(log);

          }
        List<FPlogs> contacts2 = db.getFPlogsByMonthAndtype(String.valueOf(month), "1");

        for (FPlogs cn2 : contacts2) {
        //if (contacts2.size() != 0) {
            //  for (FPlogs cn : contacts) {
           // FPlogs cn2 = contacts2.get(contacts2.size() - 1);
            String log2 = "Out: \n" + cn2.getTime();
            // Writing Contacts to log

            Log.d("Name: ", log2);
            // Toast.makeText(getApplicationContext(), log2, Toast.LENGTH_SHORT).show();
            log3 += log2;
            NewsResList.add(log2);
        }
        // }
        if (log3 != "") {


            // Toast.makeText(getApplicationContext(), log3, Toast.LENGTH_SHORT).show();

            Toast toast = Toast.makeText(getApplicationContext(), log3, Toast.LENGTH_SHORT);
            LinearLayout toastLayout = (LinearLayout) toast.getView();
            TextView toastTV = (TextView) toastLayout.getChildAt(0);
            toastTV.setTextSize(70);
            toast.show();
        } else
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        //NewsResList = shredData.getNewsvalues();
*/
        CustomListAdapter adapter = new CustomListAdapter(this, R.layout.item_list, NewsResList);
        listview.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shaw_month2, menu);
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
    public static String getMonthName(int month){
        switch(month){
            case 1:
                return "Jan";

            case 2:
                return "Feb";

            case 3:
                return "Mar";

            case 4:
                return "Apr";

            case 5:
                return "May";

            case 6:
                return "Jun";

            case 7:
                return "Jul";

            case 8:
                return "Aug";

            case 9:
                return "Sep";

            case 10:
                return "Oct";

            case 11:
                return "Nov";

            case 12:
                return "Dec";
        }

        return "";
    }
}
