package kz.project.jaryq.ui.kitaptar.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.models.Kitap;

public class KitapDetailActivity extends AppCompatActivity {
    ImageView bookImage, iv_back;
    TextView bookName;
    TextView bookAuthor;
    TextView bookDesc;
    Button btn_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitap_detail);

        iv_back = findViewById(R.id.iv_back);
        bookImage = findViewById(R.id.bookImage);
        bookName = findViewById(R.id.bookName);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookDesc = findViewById(R.id.bookDesc);
        btn_read = findViewById(R.id.btn_read);

        Kitap kitap = (Kitap) getIntent().getSerializableExtra("kitap");

        if (kitap != null) {

            Glide.with(this)
                    .load(kitap.getImagePath())
                    .centerCrop()
                    .placeholder(R.drawable.kitap_placeholder)
                    .into(bookImage);

            bookName.setText(kitap.getBookName());
            bookAuthor.setText(kitap.getBookAuthor());
            bookDesc.setText(kitap.getBookDesc());

            btn_read.setOnClickListener(view -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(kitap.getBookLink()));
                startActivity(i);
            });
        }

        iv_back.setOnClickListener(view -> {
            finish();
        });
    }
}