package kz.project.jaryq.ui.namaz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;
import java.util.List;

import kz.project.jaryq.R;


public class NamazFragment extends Fragment {

    View view;
    ProgressBar progress_circular;
    MakalalarAdapter makalalarAdapter;
    NamazOkypUireneikAdapter namazOkypUireneikAdapter;
    List<String> list;
    List<String> list2;
    RecyclerView recyclerViewMakala, recyclerViewUireneik;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_namaz, container, false);

        initViews();

        return view;
    }

    public void initViews() {
        recyclerViewMakala = view.findViewById(R.id.recyclerViewMakala);
        recyclerViewUireneik = view.findViewById(R.id.recyclerViewUireneik);

        list = new ArrayList<>();
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");
        list.add(" ");


        makalalarAdapter = new MakalalarAdapter(getActivity(), list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewMakala.setLayoutManager(layoutManager);
        recyclerViewMakala.setAdapter(makalalarAdapter);


        list2 = new ArrayList<>();
        list2.add(" ");
        list2.add(" ");
        list2.add(" ");
        list2.add(" ");
        list2.add(" ");
        namazOkypUireneikAdapter = new NamazOkypUireneikAdapter(getActivity(), list2);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUireneik.setLayoutManager(layoutManager2);
        recyclerViewUireneik.setAdapter(namazOkypUireneikAdapter);

    }
}