package kz.project.jaryq.ui.namaz.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.namaz.models.Makala;

public class MakalaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int makalaPos = intent.getIntExtra("makalaPos", 0);
        Makala makala = (Makala) intent.getSerializableExtra("makala");

        if (makalaPos == 0) {
            makala1();

        } else if (makalaPos == 1) {
            makala2();
        }else if (makalaPos == 2) {
            makala3();
        }else if (makalaPos == 3) {
            makala4();

        }else if (makalaPos == 4) {
            makala5();
        }

        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(view -> finish());

    }

    public void makala1() {
        setContentView(R.layout.activity_makala_gusil_er);
    }

    public void makala2() {
        setContentView(R.layout.activity_makala_daret_er);

    }
    public void makala3() {
        setContentView(R.layout.activity_makala_daret_buzatin_er_aiel);

    }

    public void makala4() {
        setContentView(R.layout.activity_makala_namaz_paryz_er_aiel);
        TextView text = findViewById(R.id.textTv);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.setText(Html.fromHtml(getResources().getString(R.string.namaz_paryzdary), Html.FROM_HTML_MODE_COMPACT));
        } else {
            text.setText(Html.fromHtml(getResources().getString(R.string.namaz_paryzdary)));
        }
    }

    public void makala5() {
        setContentView(R.layout.activity_makala_namaz_turleri_er_aiel);
        TextView text = findViewById(R.id.textTv);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text.setText(Html.fromHtml(getResources().getString(R.string.namaz_turleri), Html.FROM_HTML_MODE_COMPACT));
        } else {
            text.setText(Html.fromHtml(getResources().getString(R.string.namaz_turleri)));
        }
    }

}