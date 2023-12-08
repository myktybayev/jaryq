package kz.project.jaryq.ui.namaz;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.project.jaryq.R;
import kz.project.jaryq.ui.namaz.activities.NamazDetailActivity;
import kz.project.jaryq.ui.namaz.models.Namaz;


public class NamazOkypUireneikAdapter extends RecyclerView.Adapter<NamazOkypUireneikAdapter.ViewHolder> {
    private Context context;
    private List<Namaz> namazList;

    public NamazOkypUireneikAdapter(Context context, List<Namaz> namazList) {
        this.context = context;
        this.namazList = namazList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_namaz,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Namaz namaz = namazList.get(position);
        holder.image.setImageResource(namaz.getImageDraw());
        holder.title.setText(namaz.getTitle());
        holder.content.setText(namaz.getDesc());

    }

    @Override
    public int getItemCount() {
        return namazList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NamazDetailActivity.class);
                    intent.putExtra("nmz", namazList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
