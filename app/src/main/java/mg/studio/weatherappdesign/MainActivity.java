/* mg.studio.weatherappdesign;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        new DownloadUpdate().execute();
    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "http://t.weather.sojson.com/api/weather/city/101040100";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature

                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String temperature) {
            //Update the temperature displayed
            String data;
            data=buffer.substring(buffer.indexOf("data\":")+7,buffer.indexOf("message")-3);
            ((TextView) findViewById(R.id.temperature_of_the_day)).setText(temperature);
        }
    }
}*/

package mg.studio.weatherappdesign;

        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.app.AlertDialog;
        import android.app.AlertDialog.Builder;
        import android.content.DialogInterface;
        import android.content.DialogInterface.OnClickListener;
        import android.content.DialogInterface.OnMultiChoiceClickListener;


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.ProtocolException;
        import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void btnClick(View view) {
        new DownloadUpdate().execute();
        AlertDialog.Builder builder=new Builder(this);
        builder.setIcon(R.drawable.button_shape_pressed);
        builder.setTitle("Refresh Finish");
        builder.setPositiveButton("OK", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog b=builder.create();
        b.show();

    }


    private class DownloadUpdate extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {
            String stringUrl = "http://t.weather.sojson.com/api/weather/city/101040100?tdsourcetag=s_pcqq_aiomsg";
            HttpURLConnection urlConnection = null;
            BufferedReader reader;

            try {
                URL url = new URL(stringUrl);

                // Create the request to get the information from the server, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Mainly needed for debugging
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                //The temperature
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String buffer) {
            //Update the temperature displayed
            String temperature, date, city;

            temperature = buffer.substring(buffer.indexOf("wendu") + 8, buffer.indexOf("ganmao") - 3);
            ((TextView) findViewById(R.id.temperature_of_the_day)).setText(temperature);

            date = buffer.substring(buffer.indexOf("time") + 7, buffer.indexOf("cityInfo") - 11);
            ((TextView) findViewById(R.id.tv_date)).setText(date);

            //city = buffer.substring(buffer.indexOf("cityInfo") + 19, buffer.indexOf("cityId") - 3);
            //((TextView) findViewById(R.id.tv_location)).setText(city);
           city=new String("chongqing");
            ((TextView) findViewById(R.id.tv_location)).setText(city);
            //((ImageView)findViewById(R.id.rainy_small)).setImageResource(R.drawable.windy_small);
            String week1, week2, week3, week4,week0,type0,type1,type2,type3,type4,low1,low2,low3,low4,high1,high2,high3,high4;

            {
                int i;
                int j = 0;
                int q=0;
                int m=0,n=0,p=0;
                for (i = 1; i < 4; i++) {
                    j = buffer.indexOf("week", j + 6);
                    q= buffer.indexOf("type", q + 6);
                    m=buffer.indexOf("high",  m+ 6);
                    n=buffer.indexOf("low", n+ 5);
                    p=buffer.indexOf("sunset", p+ 8);
                }
                week1 = buffer.substring(j + 7, j + 10);
                type1=buffer.substring(q+ 7, q+ 8);
                high1=buffer.substring(m + 9,n-3);
                low1=buffer.substring(n+8,p-3);

                ((TextView) findViewById(R.id.high1)).setText(high1);
                ((TextView) findViewById(R.id.low1)).setText(low1);

                if(week1.equals("星期六"))
                    week1=new String("saturday");
                if(week1.equals("星期日"))
                    week1=new String("sunday");
                if(week1.equals("星期一"))
                    week1=new String("monday");
                if(week1.equals("星期二"))
                    week1=new String("tuesday");
                if(week1.equals("星期三"))
                    week1=new String("wednesday");
                if(week1.equals("星期四"))
                    week1=new String("thursday");
                if(week1.equals("星期五"))
                    week1=new String("friday");
                ((TextView) findViewById(R.id.nextday)).setText(week1);
                if(type1.equals("晴"))
                    ((ImageView)findViewById(R.id.weather1)).setImageResource(R.drawable.sunny_small);
                if(type1.equals("阴"))
                    ((ImageView)findViewById(R.id.weather1)).setImageResource(R.drawable.partly_sunny_small);
                if(type1.equals("小"))
                    ((ImageView)findViewById(R.id.weather1)).setImageResource(R.drawable.rainy_small);
                if(type1.equals("大"))
                    ((ImageView)findViewById(R.id.weather1)).setImageResource(R.drawable.rainy_up);
            }

            {
                int i;
                int j = 0;
                int q=0;
                int m=0,n=0,p=0;
                for (i = 1; i < 5; i++) {
                    j = buffer.indexOf("week", j + 6);
                    q= buffer.indexOf("type", q + 6);
                    m=buffer.indexOf("high",  m+ 6);
                    n=buffer.indexOf("low", n+ 5);
                    p=buffer.indexOf("sunset", p+ 8);
                }
                week2 = buffer.substring(j + 7, j + 10);
                type2=buffer.substring(q+ 7, q+ 8);
                high2=buffer.substring(m + 9,n-3);
                low2=buffer.substring(n+8,p-3);

                ((TextView) findViewById(R.id.high2)).setText(high2);
                ((TextView) findViewById(R.id.low2)).setText(low2);
                if(week2.equals("星期六"))
                    week2=new String("saturday");
                if(week2.equals("星期日"))
                    week2=new String("sunday");
                if(week2.equals("星期一"))
                    week2=new String("monday");
                if(week2.equals("星期二"))
                    week2=new String("tuesday");
                if(week2.equals("星期三"))
                    week2=new String("wednesday");
                if(week2.equals("星期四"))
                    week2=new String("thursday");
                if(week2.equals("星期五"))
                    week2=new String("friday");
                ((TextView) findViewById(R.id.n_nextday)).setText(week2);
                if(type2.equals("晴"))
                    ((ImageView)findViewById(R.id.weather2)).setImageResource(R.drawable.sunny_small);
                if(type2.equals("阴"))
                    ((ImageView)findViewById(R.id.weather2)).setImageResource(R.drawable.partly_sunny_small);
                if(type2.equals("小"))
                    ((ImageView)findViewById(R.id.weather2)).setImageResource(R.drawable.rainy_small);
                if(type2.equals("大"))
                    ((ImageView)findViewById(R.id.weather2)).setImageResource(R.drawable.rainy_up);
            }
            {
                int i;
                int j = 0;
                int q=0;
                int m=0,n=0,p=0;
                for (i = 1; i < 6; i++) {
                    j = buffer.indexOf("week", j + 6);
                    q= buffer.indexOf("type", q+ 6);
                    m=buffer.indexOf("high",  m+ 6);
                    n=buffer.indexOf("low", n+ 5);
                    p=buffer.indexOf("sunset", p+ 8);

                }
                week3 = buffer.substring(j + 7, j + 10);
                type3=buffer.substring(q+ 7, q+ 8);
                high3=buffer.substring(m + 9,n-3);
                low3=buffer.substring(n+8,p-3);

                ((TextView) findViewById(R.id.high3)).setText(high3);
                ((TextView) findViewById(R.id.low3)).setText(low3);

                if(week3.equals("星期六"))
                    week3=new String("saturday");
                if(week3.equals("星期日"))
                    week3=new String("sunday");
                if(week3.equals("星期一"))
                    week3=new String("monday");
                if(week3.equals("星期二"))
                    week3=new String("tuesday");
                if(week3.equals("星期三"))
                    week3=new String("wednesday");
                if(week3.equals("星期四"))
                    week3=new String("thursday");
                if(week3.equals("星期五"))
                    week3=new String("friday");

                ((TextView) findViewById(R.id.nn_nextday)).setText(week3);
                if(type3.equals("晴"))
                    ((ImageView)findViewById(R.id.weather3)).setImageResource(R.drawable.sunny_small);
                if(type3.equals("阴"))
                    ((ImageView)findViewById(R.id.weather3)).setImageResource(R.drawable.partly_sunny_small);
                if(type3.equals("小"))
                    ((ImageView)findViewById(R.id.weather3)).setImageResource(R.drawable.rainy_small);
                if(type3.equals("大"))
                    ((ImageView)findViewById(R.id.weather3)).setImageResource(R.drawable.rainy_up);
            }
            {
                int i;
                int j = 0;
                int q=0;
                int m=0,n=0,p=0;
                for (i = 1; i < 7; i++) {
                    j = buffer.indexOf("week", j + 6);
                    q= buffer.indexOf("type", q + 6);
                    m=buffer.indexOf("high",  m+ 6);
                    n=buffer.indexOf("low", n+ 5);
                    p=buffer.indexOf("sunset", p+ 8);

                }
                week4 = buffer.substring(j + 7, j + 10);
                type4=buffer.substring(q+ 7, q+ 8);
                high4=buffer.substring(m + 9,n-3);
                low4=buffer.substring(n+8,p-3);

                ((TextView) findViewById(R.id.high4)).setText(high4);
                ((TextView) findViewById(R.id.low4)).setText(low4);
                if(week4.equals("星期六"))
                    week4=new String("saturday");
                if(week4.equals("星期日"))
                    week4=new String("sunday");
                if(week4.equals("星期一"))
                    week4=new String("monday");
                if(week4.equals("星期二"))
                    week4=new String("tuesday");
                if(week4.equals("星期三"))
                    week4=new String("wednesday");
                if(week4.equals("星期四"))
                    week4=new String("thursday");
                if(week4.equals("星期五"))
                    week4=new String("friday");
                ((TextView) findViewById(R.id.nnn_nextday)).setText(week4);

                if(type4.equals("晴"))
                    ((ImageView)findViewById(R.id.weather4)).setImageResource(R.drawable.sunny_small);
                if(type4.equals("阴"))
                    ((ImageView)findViewById(R.id.weather4)).setImageResource(R.drawable.partly_sunny_small);
                if(type4.equals("小"))
                    ((ImageView)findViewById(R.id.weather4)).setImageResource(R.drawable.rainy_small);
                if(type4.equals("大"))
                    ((ImageView)findViewById(R.id.weather4)).setImageResource(R.drawable.rainy_up);

            }
            {
                int i;
                int j = 0;
                int q=0;
                for (i = 1; i < 3; i++) {
                    j = buffer.indexOf("week", j + 6);
                    q= buffer.indexOf("type", q + 6);
                }
               week0 = buffer.substring(j + 7, j + 10);
                type0=buffer.substring(q+ 7, q+ 8);
                if(week0.equals("星期六"))
                    week0=new String("saturday");
                if(week0.equals("星期日"))
                    week0=new String("sunday");
                if(week0.equals("星期一"))
                    week0=new String("monday");
                if(week0.equals("星期二"))
                    week0=new String("tuesday");
                if(week0.equals("星期三"))
                    week0=new String("wednesday");
                if(week0.equals("星期四"))
                    week0=new String("thursday");
                if(week0.equals("星期五"))
                    week0=new String("friday");
                ((TextView) findViewById(R.id.today)).setText(week0);
                if(type0.equals("晴"))
                    ((ImageView)findViewById(R.id.img_weather_condition)).setImageResource(R.drawable.sunny_small);
                if(type0.equals("阴"))
                    ((ImageView)findViewById(R.id.img_weather_condition)).setImageResource(R.drawable.partly_sunny_small);
                if(type0.equals("小"))
                    ((ImageView)findViewById(R.id.img_weather_condition)).setImageResource(R.drawable.rainy_small);
                if(type0.equals("大"))
                    ((ImageView)findViewById(R.id.img_weather_condition)).setImageResource(R.drawable.rainy_up);


            }


        }
    }
}
