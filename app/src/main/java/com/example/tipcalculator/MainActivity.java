package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Here I create a counter I will use to see when the user leaves the second activity.
    public int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Here I add one to the counter to show the app has launched
        counter++;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onRestart() {
        // Here I use onRestart to create a simple toast whenever counter is even which it will be after the second activity is opened. It is made odd here again so until the second activity is opened the toast will not show.
        super.onRestart();
        if(counter % 2 == 0){
        Context context = getApplicationContext();
        CharSequence text = "WebLookup has finished";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        counter++;
        }
    }
    public void buttonPress(View v){
        // Here I simply take each of the EditText boxes and retrieve the info, converting to the correct format. I leave tipPercent unconverted as it can be left blank.
        EditText tipPercent = findViewById(R.id.tipPercentage);
        EditText checkTot = findViewById(R.id.checkAmount);
        double checkTotal = Double.parseDouble(checkTot.getText().toString());
        EditText numPpl = findViewById(R.id.numberPeople);
        int numPeople = Integer.parseInt(numPpl.getText().toString());
        // Here I set a default tip percent as a backup before checking if the field was left blank. If it was, I set the tip percent to 15 just in case and if not, I convert the inputted text like the rest.
        double tipPercentage = 15;
        if(tipPercent.getText().toString().equals("")){
            tipPercentage = 15;
        } else {
            tipPercentage = Double.parseDouble(tipPercent.getText().toString());
        }
        // Here I do some simple math calculations to get each value I need. I also set up the strings I will use by using String.format with %.2f to set the numbers to two decimal places and I add a $ to the front to make it currency.
        double tip = checkTotal * tipPercentage/100;
        String tipString = "$" + String.format("%.2f", tip);
        double total = checkTotal + tip;
        String totalString = "$" + String.format("%.2f", total);
        double totalPer = total / numPeople;
        String totalPerString = "$" + String.format("%.2f", totalPer);
        double tipPer = tip / numPeople;
        String tipPerString = "$" + String.format("%.2f", tipPer);

        // Here I simply set the text views created for the values to the newly found values.
        TextView totalBill = findViewById(R.id.totalBill);
        totalBill.setText(totalString);
        TextView totalTip = findViewById(R.id.totalTip);
        totalTip.setText(tipString);
        TextView totalPerPerson = findViewById(R.id.totalPerPerson);
        totalPerPerson.setText(totalPerString);
        TextView tipPerPerson = findViewById(R.id.tipPerPerson);
        tipPerPerson.setText(tipPerString);

    }
    public void openDial(View v){
        // Here I use a simply intent with an ACTION_CALL to auto-dial the number provided.
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:7818912000"));
        startActivity(intent);
    }
    public void openMaps(View v){
        // Here I use another relatively simple intent with coordinate data I got online and a zoom size I thought fit the requirements after some testing. I use the Maps package so it works properly.
        Uri gmmIntentUri = Uri.parse("geo:42.3854, -71.2219?z=16");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
    public void openWeb(View v){
        // Here I set my counter up, making it even, and open the second activity so when a user returns the toast checker will see counter is even.
            counter++;
                startActivity(new Intent(MainActivity.this, WebLookup.class));
            }
    }


