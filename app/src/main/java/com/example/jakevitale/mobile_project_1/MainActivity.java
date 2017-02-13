package com.example.jakevitale.mobile_project_1;

import android.content.Intent;
import android.icu.util.RangeValueIterator;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToMeals(View view) throws IOException {

        EditText user = (EditText) findViewById(R.id.username);
        String u = user.getText().toString();
        EditText pswd = (EditText) findViewById(R.id.password);
        String p = pswd.getText().toString();

        String realuser = getString(R.string.username);
        String realpwd = getString(R.string.password);

        if (u.equals(realuser) && p.equals(realpwd))
        {
            Intent intent = new Intent(this, MealsActivity.class);
            startActivity(intent);
        }

        else
        {
            TextView tv = (TextView)findViewById(R.id.warning);
            tv.setText(R.string.login_fail);
        }
    }

    /**
     * Called when the user clicks the Send button
     */
    public void sendMessage(View view) throws IOException {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = scrapeMenu("2017-02-06").get(0).get(0).get(0);
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
    }


}
