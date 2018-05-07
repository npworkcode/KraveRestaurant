package com.example.npmain.kraverestaurant;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private static final String KRAVE_GPS_COORDINATES = "geo:0,0?q=10.295543,-61.445620(Krave Restaurant)";
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";
    private static final String EMAIL_TEXT_PLAIN = "text/plain";
    private static final int FIRST_EMAIL_ADDRESS = 0;
    private static final String PHONE_NUMBER_INTENT = "tel:";
    private static TextView websiteView;
    private static TextView emailView;
    private static TextView phoneView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            setContentView(R.layout.activity_main);
        else
            setContentView(R.layout.activity_land);
        websiteView = findViewById(R.id.krave_website);
        emailView = findViewById(R.id.krave_email);
        phoneView = findViewById(R.id.krave_phone);
    }

    /* private method to handle the intent from many sources.
       Checks if the intent can be handled and then starts the activity
       @param Intent - the Intent to be handled
     */
    private void doIntent(Intent doIntent)
    {
        if (doIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(doIntent);
        }
    }

    /* method to load the Map activity
    @Param View - that initiated the creation of the Map Intent
     */
    public void loadMap(View view)
    {
        Uri kraveMapUri = Uri.parse(KRAVE_GPS_COORDINATES);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, kraveMapUri);
        doIntent(mapIntent);
    }

    /* method to load the Internet browser activity
    @Param View - that initiated the creation of the Map Intent
     */
    public void loadBrowser(View view)
    {
        String url = websiteView.getText().toString();
        if (!url.startsWith(HTTP) && (!url.startsWith(HTTPS))) url = HTTP + url;
        Uri kraveWebsiteUri = Uri.parse(url);
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, kraveWebsiteUri);
        doIntent(websiteIntent);
    }

    /* method to load the Email activity
    @Param View - that initiated the creation of the Email Intent
     */
    public void loadEmail(View view)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType(EMAIL_TEXT_PLAIN);
        String[] toAddress = {emailView.getText().toString()};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, toAddress);
        doIntent(emailIntent);
    }

    /* method to load the phone activity
    @Param View - that initiated the creation of the Phone Intent
     */
    public void loadPhone(View view)
    {
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        String phoneNumber = phoneView.getText().toString();
        Uri kravePhoneUri = Uri.parse(PHONE_NUMBER_INTENT + phoneNumber);
        phoneIntent.setData(kravePhoneUri);
        doIntent(phoneIntent);
    }

    /* method to load the Calendar activity
    @Param View - that initiated the creation of the Calendar Intent
    */
    public void loadCalendar(View view)
    {
        Intent calendarIntent = new Intent(Intent.ACTION_INSERT).setData(CalendarContract.Events.CONTENT_URI);
        doIntent(calendarIntent);
    }
}
