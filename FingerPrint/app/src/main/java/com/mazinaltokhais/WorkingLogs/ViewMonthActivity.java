package com.mazinaltokhais.WorkingLogs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;


public class ViewMonthActivity extends ActionBarActivity {

    CalendarView calendar;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_month);
        //initializes the calendarview
    db = new DatabaseHandler(this);
        initializeCalendar();
    }
    public void initializeCalendar() {
        calendar = (CalendarView) findViewById(R.id.calendarView);
        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(1);
        //The background color for the selected week.

       // calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.material_blue_grey_800));
        //sets the color for the dates of an unfocused month.
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.material_deep_teal_200));
       calendar.setSelectedWeekBackgroundColor(Color.WHITE);
        //sets the color for the separator line between weeks.

       // calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.material_blue_grey_950));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.

       // calendar.setSelectedDateVerticalBar(R.color.material_blue_grey_900);

        //sets the listener to be notified upon selected date change.
/*
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            //show the selected date as a toast

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                String log3 = "";
                // Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

                //  Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<FPlogs> contacts = db.getFPlogsByDateAndtype(day + "/" + month + "/" + year, "0");

                //  for (FPlogs cn : contacts) {
                if (contacts.size() != 0) {
                    FPlogs cn = contacts.get(contacts.size() - 1);
                    String log = "IN: \n" + cn.getTime() + "\n";
                    // Writing Contacts to log

                    Log.d("Name: ", log);
                    // Toast.makeText(getApplicationContext(), log, Toast.LENGTH_SHORT).show();
                    // }
                    log3 += log;
                }
                List<FPlogs> contacts2 = db.getFPlogsByDateAndtype(day + "/" + month + "/" + year, "1");

                if (contacts2.size() != 0) {
                    //  for (FPlogs cn : contacts) {
                    FPlogs cn2 = contacts2.get(contacts2.size() - 1);
                    String log2 = "Out: \n" + cn2.getTime();
                    // Writing Contacts to log

                    Log.d("Name: ", log2);
                    // Toast.makeText(getApplicationContext(), log2, Toast.LENGTH_SHORT).show();
                    log3 += log2;
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
            }

        });
        */
    }

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_month, menu);
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
}
