package kz.project.jaryq.ui.uakit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.uakit.model.City;


public class UakitFragment extends Fragment {

    View view;
    ProgressBar progress_circular;
    Spinner spinner;
    List<City> cityTitles = new ArrayList<>();
    LinearLayout linearTime;
    boolean citySelected = false;
    TextView cityName, today, time1, time2, time3, time4, time5, time6;
    String aptaKunder[] = {" ", "Дүйсенбі", "Сейсенбі","Сәрсенбі","Бейсенбі","Жұма", "Сенбі", "Жексенбі"};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_uakit, container, false);

        initViews();
        loadCities();
        spinnetListener();


//        LoadApiCities task = new LoadApiCities();
//        task.execute();

        return view;
    }


    public void initViews() {
        progress_circular = view.findViewById(R.id.progress_circular);
        spinner = view.findViewById(R.id.spinner);
        linearTime = view.findViewById(R.id.linearTime);

        today = view.findViewById(R.id.today);
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.MONTH);
        int dayNumber = calendar.get(Calendar.DAY_OF_MONTH);

        Date date = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String hourMinute = df.format(date);

        today.setText(aptaKunder[dayOfWeek-1]+", "+hourMinute);


        cityName = view.findViewById(R.id.cityName);
        time1 = view.findViewById(R.id.time1);
        time2 = view.findViewById(R.id.time2);
        time3 = view.findViewById(R.id.time3);
        time4 = view.findViewById(R.id.time4);
        time5 = view.findViewById(R.id.time5);
        time6 = view.findViewById(R.id.time6);
    }

    public void spinnetListener() {
        spinner.setSelection(0, false);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                cityName.setText(cityTitles.get(position).getTitle());

                LoadCityTime loadCityTime = new LoadCityTime(cityTitles.get(position));
                loadCityTime.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void loadCities() {
        try {
            InputStream is = getActivity().getAssets().open("city.json");
            byte[] buffer = new byte[is.available()];

            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            createModelFromFile(json);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void createModelFromFile(String json) {
        try {
            JSONArray objArray = new JSONArray(json);

            for (int i = 0; i < objArray.length(); i++) {
                JSONObject jo_inside = objArray.getJSONObject(i);

                String title = jo_inside.getString("title");
                String lng = jo_inside.getString("lng");
                String lat = jo_inside.getString("lat");
                String timezone = jo_inside.getString("timezone");
                String slug = jo_inside.getString("slug");

                cityTitles.add(new City(title, lng, lat, timezone, slug));
            }

            Collections.sort(cityTitles);
            ArrayAdapter<City> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, cityTitles);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTimes(City city) {
        String data = "";
        //lat и lng
        String apiUrl = "https://namaz.muftyat.kz/kk/api/times/2023/" + city.getLat() + "/" + city.getLng();

        progress_circular.setVisibility(View.VISIBLE);

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }

            Log.i("json_data", "" + data);
            progress_circular.setVisibility(View.GONE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public class LoadCityTime extends AsyncTask<Void, Void, Void> {
        String data = "";
        String apiUrl;

        public LoadCityTime(City city) {
            apiUrl = "https://namaz.muftyat.kz/kk/api/times/2023/" + city.getLat() + "/" + city.getLng();
            progress_circular.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress_circular.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.i("json_data", "apiUrl: " + apiUrl);

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }


                JSONObject jo_inside = new JSONObject(data);
                JSONArray results = jo_inside.getJSONArray("result");


                for (int i = 0; i < results.length(); i++) {
                    JSONObject dayliJson = results.getJSONObject(i);
                    String date = dayliJson.getString("date");

                    if (date.equals("28-11-2023")) {
                        String fajr = dayliJson.getString("Fajr");
                        String sunrise = dayliJson.getString("Sunrise");
                        String dhuhr = dayliJson.getString("Dhuhr");
                        String asr = dayliJson.getString("Asr");
                        String maghrib = dayliJson.getString("Maghrib");
                        String isha = dayliJson.getString("Isha");

                        time1.setText(fajr);
                        time2.setText(sunrise);
                        time3.setText(dhuhr);
                        time4.setText(asr);
                        time5.setText(maghrib);
                        time6.setText(isha);

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progress_circular.setVisibility(View.GONE);

        }
    }

}