package kz.project.jaryq.ui.surak;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.adapters.CategoryAdapter;
import kz.project.jaryq.ui.kitaptar.adapters.KitaptarAllAdapter;
import kz.project.jaryq.ui.kitaptar.models.Kitap;
import kz.project.jaryq.ui.kitaptar.models.KitapCategory;
import kz.project.jaryq.ui.surak.adapters.SuraktarAdapter;
import kz.project.jaryq.ui.surak.models.Surak;

public class SurakFragment extends Fragment {

    View view;
    TextView tv_surak0, tv_surak1, tv_surak2, tv_surak3, tv_surak4, tv_surak5, tv_surak6, tv_surak7;

    SuraktarAdapter suraktarAdapter;
    RecyclerView recyclerViewSuraktar;
    List<Surak> surakArrayList = new ArrayList<>();

    List<Surak> surakListAll = new ArrayList<>();
    List<Surak> surakListAkida = new ArrayList<>();
    List<Surak> surakListGibadat = new ArrayList<>();
    List<Surak> surakListHalalHaram = new ArrayList<>();
    List<Surak> surakListZeket = new ArrayList<>();
    List<Surak> surakListOtbasy = new ArrayList<>();
    List<Surak> surakListKogam = new ArrayList<>();
    List<Surak> surakListDuga = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_suraktar, container, false);
        initViews();
        loadSuraktar();

        return view;
    }

    public void initViews() {
        tv_surak0 = view.findViewById(R.id.tv_surak0);
        tv_surak1 = view.findViewById(R.id.tv_surak1);
        tv_surak2 = view.findViewById(R.id.tv_surak2);
        tv_surak3 = view.findViewById(R.id.tv_surak3);
        tv_surak4 = view.findViewById(R.id.tv_surak4);
        tv_surak5 = view.findViewById(R.id.tv_surak5);
        tv_surak6 = view.findViewById(R.id.tv_surak6);
        tv_surak7 = view.findViewById(R.id.tv_surak7);

        @SuppressLint("UseCompatLoadingForDrawables") View.OnClickListener tvClick = view -> {
            int id = view.getId();
            byDefault();

            if (id == R.id.tv_surak0) {
                tv_surak0.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));
                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListAll);

            } else if (id == R.id.tv_surak1) {
                tv_surak1.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));
                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListAkida);

            } else if (id == R.id.tv_surak2) {
                tv_surak2.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));
                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListGibadat);
            } else if (id == R.id.tv_surak3) {
                tv_surak3.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));
                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListHalalHaram);
            } else if (id == R.id.tv_surak4) {
                tv_surak4.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));

                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListZeket);
            } else if (id == R.id.tv_surak5) {
                tv_surak5.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));

                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListOtbasy);
            } else if (id == R.id.tv_surak6) {
                tv_surak6.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));

                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListKogam);
            } else if (id == R.id.tv_surak7) {
                tv_surak7.setBackground(getActivity().getDrawable(R.drawable.text_view_bottom));
                suraktarAdapter = new SuraktarAdapter(getActivity(), surakListDuga);
            }


            recyclerViewSuraktar.setAdapter(suraktarAdapter);
        };

        tv_surak0.setOnClickListener(tvClick);
        tv_surak1.setOnClickListener(tvClick);
        tv_surak2.setOnClickListener(tvClick);
        tv_surak3.setOnClickListener(tvClick);
        tv_surak4.setOnClickListener(tvClick);
        tv_surak5.setOnClickListener(tvClick);
        tv_surak6.setOnClickListener(tvClick);
        tv_surak7.setOnClickListener(tvClick);


        surakArrayList.add(new Surak("", "", "", "", ""));
        surakArrayList.add(new Surak("", "", "", "", ""));
        surakArrayList.add(new Surak("", "", "", "", ""));
        surakArrayList.add(new Surak("", "", "", "", ""));
        surakArrayList.add(new Surak("", "", "", "", ""));
        surakArrayList.add(new Surak("", "", "", "", ""));
        surakArrayList.add(new Surak("", "", "", "", ""));

        recyclerViewSuraktar = view.findViewById(R.id.recyclerViewSuraktar);
    }

    public void byDefault() {
        tv_surak0.setBackground(null);
        tv_surak1.setBackground(null);
        tv_surak2.setBackground(null);
        tv_surak3.setBackground(null);
        tv_surak4.setBackground(null);
        tv_surak5.setBackground(null);
        tv_surak6.setBackground(null);
        tv_surak7.setBackground(null);
    }

    public void loadSuraktar() {
        try {
            InputStream is = getActivity().getAssets().open("surak_jauap.json");
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
                String imagePath = jo_inside.getString("imagePath");
                String content = jo_inside.getString("content");
                String site_link = jo_inside.getString("site_link");
                String category = jo_inside.getString("category");

                surakListAll.add(new Surak(title, imagePath, content, site_link, category));

                if (category.equals("Ақида")) {
                    surakListAkida.add(new Surak(title, imagePath, content, site_link, category));
                } else if (category.equals("Ғибадат")) {
                    surakListGibadat.add(new Surak(title, imagePath, content, site_link, category));
                } else if (category.equals("Халал мен Харам")) {
                    surakListHalalHaram.add(new Surak(title, imagePath, content, site_link, category));
                } else if (category.equals("Зекет пен Қажылық")) {
                    surakListZeket.add(new Surak(title, imagePath, content, site_link, category));
                } else if (category.equals("Отбасы")) {
                    surakListOtbasy.add(new Surak(title, imagePath, content, site_link, category));
                } else if (category.equals("Қоғам")) {
                    surakListKogam.add(new Surak(title, imagePath, content, site_link, category));
                } else if (category.equals("Дұға")) {
                    surakListDuga.add(new Surak(title, imagePath, content, site_link, category));
                }
            }

            suraktarAdapter = new SuraktarAdapter(getActivity(), surakListAll);
            recyclerViewSuraktar.setAdapter(suraktarAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}