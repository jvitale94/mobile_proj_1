package com.example.jakevitale.mobile_project_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class MealsActivity extends AppCompatActivity {

    private TextView finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);
    }

    public void showBreakfast(View view)
    {
        TextView tv = (TextView)findViewById(R.id.mealtext);
        finalResult = (TextView)findViewById(R.id.mealtext);
        finalResult.setMovementMethod(new ScrollingMovementMethod());
        tv.setText("breakfast");
        ValScraper meal = new ValScraper();
        String sleepTime = "1";
        meal.execute(sleepTime);
    }

    public void showLunch(View view)
    {
        TextView tv = (TextView)findViewById(R.id.mealtext);
        finalResult = (TextView)findViewById(R.id.mealtext);
        finalResult.setMovementMethod(new ScrollingMovementMethod());
        tv.setText("lunch");
        ValScraper meal = new ValScraper();
        String sleepTime = "1";
        meal.execute(sleepTime);
    }

    public void showDinner(View view)
    {
        TextView tv = (TextView)findViewById(R.id.mealtext);
        finalResult = (TextView)findViewById(R.id.mealtext);
        finalResult.setMovementMethod(new ScrollingMovementMethod());
        tv.setText("dinner");
        ValScraper meal = new ValScraper();
        String sleepTime = "1";
        meal.execute(sleepTime);
    }



    private class ValScraper extends AsyncTask<Object, Object, ArrayList<ArrayList<ArrayList<String>>>> {

        private String resp;
        private ArrayList<ArrayList<ArrayList<String>>> men;

        public ArrayList<ArrayList<ArrayList<String>>> scrapeMenu(String date) throws IOException {

            ArrayList<ArrayList<ArrayList<String>>> menues = new ArrayList<ArrayList<ArrayList<String>>>();
            ArrayList<ArrayList<String>> breakfast = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> lunch = new ArrayList<ArrayList<String>>();
            ArrayList<ArrayList<String>> dinner = new ArrayList<ArrayList<String>>();
            menues.add(breakfast);
            menues.add(lunch);
            menues.add(dinner);

            Document doc;
            doc = Jsoup.connect("https://www.amherst.edu/campuslife/housing-dining/dining/menu").get();

            Element day = doc.getElementById("dining-menu-"+date);

            Elements meals = day.getElementsByClass("dining-menu-meal");

            for(int i=0; i<meals.size(); i++){
                Elements courses = meals.get(i).getElementsByClass("dining-course-name");
                Elements content = meals.get(i).select("p");

                for(int j=0; j<courses.size(); j++){
                    if(i==0){
                        breakfast.add(new ArrayList<String>());
                        breakfast.get(j).add(courses.get(j).text());
                        String s = content.get(j).text();

                        int start=0;

                        for(int k = 0; k<s.length(); k++){
                            if(s.substring(k, k+1).equals(";")){
                                breakfast.get(j).add(s.substring(start,k));
                                start=k+1;
                            }
                        }
                    }
                    else if(i==1){
                        lunch.add(new ArrayList<String>());
                        lunch.get(j).add(courses.get(j).text());
                        String s = content.get(j).text();

                        int start=0;

                        for(int k = 0; k<s.length(); k++){
                            if(s.substring(k, k+1).equals(";")){
                                lunch.get(j).add(s.substring(start,k));
                                start=k+1;
                            }
                        }
                    }
                    else if(i==2){
                        dinner.add(new ArrayList<String>());
                        dinner.get(j).add(courses.get(j).text());
                        String s = content.get(j).text();

                        int start=0;

                        for(int k = 0; k<s.length(); k++){
                            if(s.substring(k, k+1).equals(";")){
                                dinner.get(j).add(s.substring(start,k));
                                start=k+1;
                            }
                        }
                    }
                }
            }

            return menues;

        }

        @Override
        protected ArrayList<ArrayList<ArrayList<String>>> doInBackground(Object... params) {
//            publishProgress("Sleeping..."); // Calls onProgressUpdate()

            try {
                resp = "Slept for 1 seconds";
                men = scrapeMenu("2017-02-12");
            }

            catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }

            return men;
        }

        @Override
        protected void onPostExecute(ArrayList<ArrayList<ArrayList<String>>> result) {
            int i = 0;
            if (finalResult.getText().toString().equals("lunch"))
            {
                i = 1;
            }
            else if (finalResult.getText().toString().equals("dinner"))
            {
                i = 2;
            }
            String f = "";
            for (int j = 0; j < result.get(i).size(); j++)
            {
                for (int k = 0; k<result.get(i).get(j).size(); k++)
                {
                    f = f + result.get(i).get(j).get(k) + "\n";
                }
            }
            finalResult.setText(f);
        }

        @Override
        protected void onPreExecute() {
        }


        @Override
        protected void onProgressUpdate(Object... text) {
            finalResult.setText("something");
        }
    }

}
