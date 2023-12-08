package kz.project.jaryq.ui.namaz.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.namaz.models.Namaz;

public class NamazDetailActivity extends AppCompatActivity {

    TextView title, desc, content;
    YouTubePlayerView youTubePlayerView;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_namaz_detail);
        iv_back = findViewById(R.id.iv_back);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        content = findViewById(R.id.content);
        youTubePlayerView = findViewById(R.id.youtube_player_view);

        Intent intent = getIntent();
        Namaz namaz = (Namaz) intent.getSerializableExtra("nmz");


        title.setText(namaz.getTitle());
        desc.setText(namaz.getDesc());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            content.setText(Html.fromHtml(namaz.getContent(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            content.setText(Html.fromHtml(namaz.getContent()));
        }

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.loadVideo(namaz.getVideoId(), 0);
            }
        });


        iv_back.setOnClickListener(view -> finish());

    }
}