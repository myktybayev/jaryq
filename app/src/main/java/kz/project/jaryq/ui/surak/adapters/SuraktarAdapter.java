package kz.project.jaryq.ui.surak.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.activity.KitapDetailActivity;
import kz.project.jaryq.ui.kitaptar.models.Kitap;
import kz.project.jaryq.ui.namaz.models.Makala;
import kz.project.jaryq.ui.surak.activities.SurakDetailActivity;
import kz.project.jaryq.ui.surak.models.Surak;


public class SuraktarAdapter extends RecyclerView.Adapter<SuraktarAdapter.ViewHolder> {
    private Context context;
    private List<Surak> surakList;

    public SuraktarAdapter(Context context, List<Surak> surakList) {
        this.context = context;
        this.surakList = surakList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surak,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Surak surak = surakList.get(position);

        Glide.with(context)
                .load(surakList.get(position).getImagePath())
                .centerCrop()
                .placeholder(R.drawable.q_placeholder)
                .into(holder.image);

        holder.qTitle.setText(surak.getTitle());
    }

    @Override
    public int getItemCount() {
        return surakList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView qTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            qTitle = itemView.findViewById(R.id.qTitle);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, SurakDetailActivity.class);
                intent.putExtra("surak", surakList.get(getAdapterPosition()));
                context.startActivity(intent);
            });
        }
    }
}
