package kz.project.jaryq.ui.namaz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.adapters.KitaptarAdapter;
import kz.project.jaryq.ui.kitaptar.models.KitapCategory;
import kz.project.jaryq.ui.namaz.activities.MakalaDetailsActivity;
import kz.project.jaryq.ui.namaz.activities.NamazDetailActivity;
import kz.project.jaryq.ui.namaz.models.Makala;


public class MakalalarAdapter extends RecyclerView.Adapter<MakalalarAdapter.ViewHolder> {
    private Context context;
    private List<Makala> makalaList;

    public MakalalarAdapter(Context context, List<Makala> makalaList) {
        this.context = context;
        this.makalaList = makalaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_makala, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Makala makala = makalaList.get(position);

        holder.image.setImageResource(makala.getImageDraw());
        holder.title.setText(makala.getTitle());
        holder.content.setText(makala.getDesc());
    }

    @Override
    public int getItemCount() {
        return makalaList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);

            itemView.setOnClickListener(view -> {

                Intent intent = new Intent(context, MakalaDetailsActivity.class);
                intent.putExtra("makalaPos", getAdapterPosition());
                intent.putExtra("makala", makalaList.get(getAdapterPosition()));
                context.startActivity(intent);
            });
        }
    }
}
