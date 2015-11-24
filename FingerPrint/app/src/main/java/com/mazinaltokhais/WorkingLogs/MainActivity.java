package com.mazinaltokhais.WorkingLogs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {
int year;
    int month;
    int day;
    int hours ;
    int minutes;
    int seconds;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         db = new DatabaseHandler(this);

        Thread myThread = null;

        Runnable runnable = new CountDownRunner();
        myThread= new Thread(runnable);
        myThread.start();

        final TextView _tvcheckin= (TextView)findViewById(R.id.tvcheckin);
        final TextView _TvlateTot= (TextView)findViewById(R.id.TvlateTot);
        final TextView _TvlateVal= (TextView)findViewById(R.id.TvlateVal);

        final TextView _tvCheckOut= (TextView)findViewById(R.id.tvCheckOut);
        final TextView _tvOvertime= (TextView)findViewById(R.id.TvOverTime);
        final TextView _tvOverTimeTot= (TextView)findViewById(R.id.tvOverTimeTot);


        Button _btnCheckIn = (Button) findViewById(R.id.btnCheckIn);
        Button _btnCheckOut = (Button) findViewById(R.id.btnCheckOut);
        Button _btnViewMonth = (Button) findViewById(R.id.btnViewMonth);
        _tvcheckin.setVisibility(View.GONE);
        _tvOvertime.setVisibility(View.GONE);
        _TvlateTot.setVisibility(View.GONE);
        _TvlateVal.setVisibility(View.GONE);
        _tvCheckOut.setVisibility(View.GONE);
        _tvOverTimeTot.setVisibility(View.GONE);
        setDate();
        List<FPlogs> CheckinLogsByDay = (List<FPlogs>) db.getFPlogsByDateAndtype(String.valueOf(day), String.valueOf(month), "0");

        List<FPlogs> CheckOutLogsByDay = (List<FPlogs>) db.getFPlogsByDateAndtype(String.valueOf(day),String.valueOf(month), "1");


        if (CheckinLogsByDay.size() != 0) {
            int i = CheckinLogsByDay.size();
            FPlogs cn  = CheckinLogsByDay.get(i - 1);
            String log = getString(R.string.CheckInTime);
            String Val =cn.getTime();
            _tvcheckin.setText(Val);
            ////////////_TvChckinValue.setText(Val);

            _tvcheckin.setVisibility(View.VISIBLE);

            _TvlateTot.setVisibility(View.VISIBLE);
            _TvlateVal.setVisibility(View.VISIBLE);
            _tvOvertime.setVisibility(View.VISIBLE);
           _tvOverTimeTot.setVisibility(View.VISIBLE);

        }
        if (CheckOutLogsByDay.size() != 0) {
            int i = CheckOutLogsByDay.size();
            FPlogs cn  = CheckOutLogsByDay.get(i - 1);
            String log =  cn.getTime() ;
            _tvCheckOut.setText(log);

            _tvCheckOut.setVisibility(View.VISIBLE);


        }
     /*   for (FPlogs cn : contacts) {
            String log = "Id: " + cn.getId() + " ,Name: " + cn.getDate() + " ,Phone: " + cn.getTime() +",type: " + cn.getType();
            // Writing Contacts to log
            Log.d("Name: ", log);
            Toast.makeText(MainActivity.this, log, Toast.LENGTH_SHORT).show();
        }*/

        _btnCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _tvcheckin.setText( ShowTime(day, month, year));
                // Inserting Contacts

                db.addFPLogs(new FPlogs(String.valueOf(day),String.valueOf( month),String.valueOf( year), ShowTime(day, month, year), 0));
                _tvcheckin.setVisibility(View.VISIBLE);
            }
        });


        _btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               _tvCheckOut.setText(ShowTime(day, month, year));
                db.addFPLogs(new FPlogs(String.valueOf(day),String.valueOf( month),String.valueOf(year), ShowTime(day, month, year), 1));

                _tvCheckOut.setVisibility(View.VISIBLE);

            }
        });


        _btnViewMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<FPlogs> contacts = db.getAllFPlogs();
                    Intent intent=new Intent(MainActivity.this,ShawMonth2Activity.class);
                    startActivity(intent);
            }
        });

    }

    public String ShowTime(int h, int m,int s)
    {
        //String curTime = day + "/" + month + "/" + year;
        String curTime = hours + ":" + minutes + ":" + seconds;
        return curTime;
    }
    public String Showdate(int h, int m,int s)
    {
        //String curTime = day + "/" + month + "/" + year;
        String curTime = day + "/" + month + "/" + year;
        return curTime;
    }
    public void setDate()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.get(Calendar.YEAR);
        year = calendar.get(Calendar.YEAR);// dt.getYear();
        month = calendar.get(Calendar.MONTH)+1;//
        // dt.getMonth();
        day = calendar.get(Calendar.DAY_OF_MONTH);//dt.getDay();

    }
    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try{
                    TextView txtCurrentTime= (TextView)findViewById(R.id.txCurrentTime);
                    Date dt = new Date();

                    setDate();
                     hours = dt.getHours();
                     minutes = dt.getMinutes();
                     seconds = dt.getSeconds();

                    String curTime = day + "/" + month + "/" + year;
                    //String curTime = hours + ":" + minutes + ":" + seconds;
                    txtCurrentTime.setText(curTime);
                }catch (Exception e) {}
            }
        });
    }


    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
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
            Intent i = new Intent(this, SettingsActivity.class);
            startActivityForResult(i, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
