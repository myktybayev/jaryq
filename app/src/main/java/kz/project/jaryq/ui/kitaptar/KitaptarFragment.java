package kz.project.jaryq.ui.kitaptar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.adapters.CategoryAdapter;
import kz.project.jaryq.ui.kitaptar.adapters.KitaptarAllAdapter;
import kz.project.jaryq.ui.kitaptar.models.Kitap;
import kz.project.jaryq.ui.kitaptar.models.KitapCategory;

public class KitaptarFragment extends Fragment {

    View view;
    RecyclerView recyclerViewCategory;
    CategoryAdapter categoryAdapter;
    KitaptarAllAdapter kitaptarAllAdapter;
    SearchView searchView;
    List<KitapCategory> kitapCategoryList;
    List<Kitap> kitapListAll = new ArrayList<>();
    List<Kitap> kitapListSearch = new ArrayList<>();
    TextView bookCount;
    ImageView iv_filter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_kitaptar, container, false);
        initViews();
        loadBooks();
        searchView();
        initFilter();

        return view;
    }

    public void initViews() {
        recyclerViewCategory = view.findViewById(R.id.recyclerViewCategory);
        searchView = view.findViewById(R.id.searchView);
        bookCount = view.findViewById(R.id.bookCount);
        iv_filter = view.findViewById(R.id.iv_filter);

        kitaptarAllAdapter = new KitaptarAllAdapter(getActivity(), kitapListAll);
    }

    public void initFilter() {
        iv_filter.setOnClickListener(view1 -> {
            showBottomSheetDialog();
        });
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity(), R.style.BottomSheetDialog);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_layout);


        View.OnClickListener tvCatClick = view -> {
            String categoryStr = ((TextView) view).getText().toString();

            bookCount.setVisibility(View.VISIBLE);
            bookCount.setTextColor(Color.BLACK);
            bookCount.setTextSize(18.0f);

            List<Kitap> list = new ArrayList<>();
            for (int i = 0; i < kitapCategoryList.size(); i++) {
                if (kitapCategoryList.get(i).getCategoryName().equals(categoryStr)) {

                    bookCount.setText(categoryStr + getString(R.string.books));
                    list = kitapCategoryList.get(i).getKitapList();

                    break;
                }
            }

            if (list.size() == 0)
                bookCount.setText(categoryStr + getString(R.string.booksNotFounds));

            KitaptarAllAdapter kitatarByCategory = new KitaptarAllAdapter(getActivity(), list);
            recyclerViewCategory.setAdapter(kitatarByCategory);
            bottomSheetDialog.dismiss();
        };

        for (int i : new int[]{R.id.tv_category1, R.id.tv_category2, R.id.tv_category3, R.id.tv_category4, R.id.tv_category5, R.id.tv_category6}) {
            bottomSheetDialog.findViewById(i).setOnClickListener(tvCatClick);
            TextView tv = bottomSheetDialog.findViewById(i);
            tv.getText();
        }

        bottomSheetDialog.findViewById(R.id.iv_close).setOnClickListener(v -> {

            recyclerViewCategory.setAdapter(categoryAdapter);
            bookCount.setVisibility(View.GONE);
            bottomSheetDialog.dismiss();
        });
        bottomSheetDialog.show();
    }

    public void searchView() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s);

                if (s.length() == 0) {
                    recyclerViewCategory.setAdapter(categoryAdapter);
                    bookCount.setVisibility(View.GONE);
                } else {
                    bookCount.setVisibility(View.VISIBLE);
                    bookCount.setText(kitapListAll.size() + getString(R.string.bookFound));
                    recyclerViewCategory.setAdapter(kitaptarAllAdapter);
                }

                return false;
            }
        });
    }

    public void filter(String text) {
        if (kitapListAll.size() > 0) kitapListAll.clear();

        if (text.isEmpty()) {
            kitapListAll.addAll(kitapListSearch);
        } else {
            text = text.toLowerCase();
            for (Kitap kitap : kitapListSearch) {

                if (kitap.getBookName().toLowerCase().contains(text) || kitap.getBookName().toUpperCase().contains(text) ||
                        kitap.getBookAuthor().toLowerCase().contains(text) || kitap.getBookAuthor().toUpperCase().contains(text)) {
                    kitapListAll.add(kitap);
                }
            }
        }

        kitaptarAllAdapter.notifyDataSetChanged();
    }

    public void loadBooks() {
        try {
            InputStream is = getActivity().getAssets().open("books.json");
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

        List<Kitap> kitapListCategoryOther = new ArrayList<>();
        List<Kitap> kitapListCategoryKuranAndSunnet = new ArrayList<>();
        List<Kitap> kitapListCategoryKorkemMinez = new ArrayList<>();
        List<Kitap> kitapListCategoryKulshilik = new ArrayList<>();
        List<Kitap> kitapListCategoryFamily = new ArrayList<>();
        List<Kitap> kitapListCategoryDuga = new ArrayList<>();

        try {
            JSONArray objArray = new JSONArray(json);

            for (int i = 0; i < objArray.length(); i++) {
                JSONObject jo_inside = objArray.getJSONObject(i);

                String imagePath = jo_inside.getString("imagePath");
                String bookName = jo_inside.getString("bookName");
                String bookAuthor = jo_inside.getString("bookAuthor");
                String bookDesc = jo_inside.getString("bookDesc");
                String bookLink = jo_inside.getString("bookLink");
                String category = jo_inside.getString("category");

                if(category.equals(getString(R.string.other))){
                    kitapListCategoryOther.add(new Kitap(imagePath,bookName,bookAuthor,bookDesc,bookLink,category));
                }else if(category.equals(getString(R.string.kuranAndSunnet))){
                    kitapListCategoryKuranAndSunnet.add(new Kitap(imagePath,bookName,bookAuthor,bookDesc,bookLink,category));
                }else if(category.equals(getString(R.string.korkemMinez))){
                    kitapListCategoryKorkemMinez.add(new Kitap(imagePath,bookName,bookAuthor,bookDesc,bookLink,category));
                }else if(category.equals(getString(R.string.kulshilik))){
                    kitapListCategoryKulshilik.add(new Kitap(imagePath,bookName,bookAuthor,bookDesc,bookLink,category));
                }else if(category.equals(getString(R.string.family))){
                    kitapListCategoryFamily.add(new Kitap(imagePath,bookName,bookAuthor,bookDesc,bookLink,category));
                } else if (category.equals(getString(R.string.duga))) {
                    kitapListCategoryDuga.add(new Kitap(imagePath, bookName, bookAuthor, bookDesc, bookLink, category));
                }
            }

            kitapListAll.addAll(kitapListCategoryOther);
            kitapListAll.addAll(kitapListCategoryKuranAndSunnet);
            kitapListAll.addAll(kitapListCategoryKorkemMinez);
            kitapListAll.addAll(kitapListCategoryKulshilik);
            kitapListAll.addAll(kitapListCategoryFamily);
            kitapListAll.addAll(kitapListCategoryDuga);

            kitapListSearch.addAll(kitapListAll);

            kitapCategoryList = new ArrayList<>();
            kitapCategoryList.add(new KitapCategory(getString(R.string.other), kitapListCategoryOther));
            kitapCategoryList.add(new KitapCategory(getString(R.string.kuranAndSunnet), kitapListCategoryKuranAndSunnet));
            kitapCategoryList.add(new KitapCategory(getString(R.string.korkemMinez), kitapListCategoryKorkemMinez));
            kitapCategoryList.add(new KitapCategory(getString(R.string.kulshilik), kitapListCategoryKulshilik));
            kitapCategoryList.add(new KitapCategory(getString(R.string.family), kitapListCategoryFamily));
            kitapCategoryList.add(new KitapCategory(getString(R.string.duga), kitapListCategoryDuga));

            categoryAdapter = new CategoryAdapter(getActivity(), kitapCategoryList);
            recyclerViewCategory.setAdapter(categoryAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    public void loadBooks() {

        kitapListCategoryOther.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/kitap.jpg",
                "Діни басқармаға – 30 жыл",
                "Қазақстан мұсылмандары діни басқармасы",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/30%20%D0%B6%D1%8B%D0%BB%D0%B4%D1%8B%D2%9B%20%D0%BA%D1%96%D1%82%D0%B0%D0%BF.pdf",
                "Әртүрлі"));

        kitapListCategoryOther.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/kitap.jpg",
                "Діни басқармаға – 30 жыл",
                "Қазақстан мұсылмандары діни басқармасы",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/30%20%D0%B6%D1%8B%D0%BB%D0%B4%D1%8B%D2%9B%20%D0%BA%D1%96%D1%82%D0%B0%D0%BF.pdf",
                "Әртүрлі"));

        kitapListCategoryOther.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/kitap.jpg",
                "Діни басқармаға – 30 жыл",
                "Қазақстан мұсылмандары діни басқармасы",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/30%20%D0%B6%D1%8B%D0%BB%D0%B4%D1%8B%D2%9B%20%D0%BA%D1%96%D1%82%D0%B0%D0%BF.pdf",
                "Әртүрлі"));

        kitapListCategoryOtbasy.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/post_image_4919.jpg",
                "Дін мен дәстүр II",
                "С.З.Ибадуллаев, Е.А.Ongarov",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/book_4919.pdf",
                "Отбасы"));

        kitapListCategoryOtbasy.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/post_image_4919.jpg",
                "Din",
                "Ongarov",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/book_4919.pdf",
                "Отбасы"));

        kitapListCategoryKorkem.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/kitap.jpg",
                "Діни басқармаға – 30 жыл",
                "Қазақстан мұсылмандары діни басқармасы",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/30%20%D0%B6%D1%8B%D0%BB%D0%B4%D1%8B%D2%9B%20%D0%BA%D1%96%D1%82%D0%B0%D0%BF.pdf",
                "Көркем мінез"));
        kitapListCategoryKorkem.add(new Kitap(
                "https://www.muftyat.kz/media/muftyat/kitap.jpg",
                "Діни басқармаға – 30 жыл",
                "Kazakhstan",
                "Desc",
                "https://www.muftyat.kz/static/libs/pdfjs/web/viewer.html?file=/media/muftyat/30%20%D0%B6%D1%8B%D0%BB%D0%B4%D1%8B%D2%9B%20%D0%BA%D1%96%D1%82%D0%B0%D0%BF.pdf",
                "Көркем мінез"));

        kitapListCategoryKorkem.add(new Kitap(
                "https://qogamlibrary.kz/wp-content/uploads/2022/10/22.jpg",
                "Адамзаттың асыл тәжі",
                "Дамира Өмірзаққызы Ибрагим",
                "Адамзаттың асыл тәжі\n" +
                        "Алланың сүйікті құлы әрі елшісі, адамзаттың асыл тәжі – Мұхаммед (саллаллаһу аләйһи уә сәлләм) екендігі даусыз. Бұл кітапта Мұхаммед пайғамбардың (саллаллаһу аләйһи уә сәлләм) дүниеге кел- генге дейінгі Арабстанның жалпы жағдайы, Пайғамбардың дүниеге келуі, балалық және жастық шағы, пайғамбарлықты қабылдап алуы, алғашқы мұсылмандардың басынан кешкендері және Мұхаммед пайғамбардың (саллаллаһу аләйһи уә сәлләм) Мәдинаға көшіп барғаннан кейінгі өмір жолы қызықты оқиғалар желісімен баяндалады. Кітап жалпы оқырман қауымға арналған. ",
                "https://qogamlibrary.kz/wp-content/uploads/2022/10/adamzattyn-asyl-tazhi.pdf",
                "Көркем мінез"));


        kitapListAll.addAll(kitapListCategoryOther);
        kitapListAll.addAll(kitapListCategoryOtbasy);
        kitapListAll.addAll(kitapListCategoryKorkem);
        kitapListSearch.addAll(kitapListAll);

        kitapCategoryList = new ArrayList<>();
        kitapCategoryList.add(new KitapCategory("Әртүрлі", kitapListCategoryOther));
        kitapCategoryList.add(new KitapCategory("Отбасы", kitapListCategoryOtbasy));
        kitapCategoryList.add(new KitapCategory("Көркем мінез", kitapListCategoryKorkem));

        categoryAdapter = new CategoryAdapter(getActivity(), kitapCategoryList);
        recyclerViewCategory.setAdapter(categoryAdapter);
    }
     */
}