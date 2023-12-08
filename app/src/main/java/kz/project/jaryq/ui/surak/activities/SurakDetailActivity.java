package kz.project.jaryq.ui.surak.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.models.Kitap;
import kz.project.jaryq.ui.surak.models.Surak;

public class SurakDetailActivity extends AppCompatActivity {
    ImageView iv_back, surak_image;
    TextView title;
    TextView site_link;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surak_detail);

        iv_back = findViewById(R.id.iv_back);
        surak_image = findViewById(R.id.surak_image);
        title = findViewById(R.id.title);
        site_link = findViewById(R.id.site_link);
        content = findViewById(R.id.content);


        Surak surak = (Surak) getIntent().getSerializableExtra("surak");

        if (surak != null) {

            Glide.with(this)
                    .load(surak.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.kitap_placeholder)
                    .into(surak_image);

            title.setText(surak.getTitle());
            site_link.setText(surak.getSite_link());
            content.setText(surak.getContent());

            site_link.setOnClickListener(view -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(surak.getSite_link()));
                startActivity(i);
            });
        }

        iv_back.setOnClickListener(view -> {
            finish();
        });
    }
}