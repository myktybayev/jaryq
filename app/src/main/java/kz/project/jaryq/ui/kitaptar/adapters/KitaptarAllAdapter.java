package kz.project.jaryq.ui.kitaptar.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.kitaptar.activity.KitapDetailActivity;
import kz.project.jaryq.ui.kitaptar.models.Kitap;


public class KitaptarAllAdapter extends RecyclerView.Adapter<KitaptarAllAdapter.ViewHolder> {
    private Context context;
    private List<Kitap> kitapList;

    public KitaptarAllAdapter(Context context, List<Kitap> kitapList) {
        this.context = context;
        this.kitapList = kitapList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_view_kitap,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context)
                .load(kitapList.get(position).getImagePath())
                .centerCrop()
                .placeholder(R.drawable.kitap_placeholder)
                .into(holder.image);

        holder.title.setText(kitapList.get(position).getBookName());
        holder.author.setText(kitapList.get(position).getBookAuthor());

    }

    @Override
    public int getItemCount() {
        return kitapList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
        TextView author;
        Button btn_read;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            btn_read = itemView.findViewById(R.id.btn_read);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, KitapDetailActivity.class);
                intent.putExtra("kitap", kitapList.get(getAdapterPosition()));
                context.startActivity(intent);
            });

            btn_read.setOnClickListener(view -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(kitapList.get(getAdapterPosition()).getBookLink()));
                context.startActivity(i);
            });
        }
    }
}
