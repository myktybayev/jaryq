package kz.project.jaryq.ui.namaz;

import android.content.res.TypedArray;
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
import kz.project.jaryq.ui.namaz.models.Makala;
import kz.project.jaryq.ui.namaz.models.Namaz;


public class NamazFragment extends Fragment {

    View view;
    ProgressBar progress_circular;
    MakalalarAdapter makalalarAdapter;
    NamazOkypUireneikAdapter namazOkypUireneikAdapter;
    List<Makala> makalalar;
    List<Namaz> namazList;
    RecyclerView recyclerViewMakala, recyclerViewUireneik;
    String [] namazTimes, namazDesc, namazContent, namazVideoId;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_namaz, container, false);

        initViews();

        return view;
    }

    public void initViews() {
        recyclerViewMakala = view.findViewById(R.id.recyclerViewMakala);
        recyclerViewUireneik = view.findViewById(R.id.recyclerViewUireneik);

        makalalar = new ArrayList<>();
        makalalar.add(new Makala(R.drawable.gusil, "Ғұсыл", "Ғұсыл дегеніміз не және оны не үшін алады?"));
        makalalar.add(new Makala(R.drawable.daret, "Дәрет", "Ерлерге арналған дәрет алу үлгісі"));
        makalalar.add(new Makala(R.drawable.daret_byzu, "Дәретті бұзатын нәрселер", "Жел шығу. Хадисте желдің шығып-шықпағаны жайлы күмәнданған адамға желдің дыбысы яки исі сезілмейінше..."));
        makalalar.add(new Makala(R.drawable.namaz_paryz, "Намаз парыздары", "Аллаға құлшылық қылу. Бес парыздың біреуі"));
        makalalar.add(new Makala(R.drawable.namaz_turleri, "Намаздың түрлері", "Бұлар бес уақыт намаз, жұма және жаназа намаздары."));


        makalalarAdapter = new MakalalarAdapter(getActivity(), makalalar);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewMakala.setLayoutManager(layoutManager);
        recyclerViewMakala.setAdapter(makalalarAdapter);

        TypedArray imgs = getActivity().getResources().obtainTypedArray(R.array.namaz_image);
        namazTimes = getActivity().getResources().getStringArray(R.array.namaz_times);
        namazDesc = getActivity().getResources().getStringArray(R.array.namaz_desc);
        namazContent = getActivity().getResources().getStringArray(R.array.namaz_content_er);
        namazVideoId = getActivity().getResources().getStringArray(R.array.namaz_video_id);
        namazList = new ArrayList<>();

        for (int i = 0; i < namazTimes.length; i++) {
            int imgR = imgs.getResourceId(i, 0);
            namazList.add(new Namaz(imgR, namazTimes[i], namazDesc[i], namazContent[i],namazVideoId[i]));
        }

        namazOkypUireneikAdapter = new NamazOkypUireneikAdapter(getActivity(), namazList);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewUireneik.setLayoutManager(layoutManager2);
        recyclerViewUireneik.setAdapter(namazOkypUireneikAdapter);

    }
}