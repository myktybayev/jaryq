package kz.project.jaryq.ui.kitaptar.adapters;

import static androidx.core.content.ContentProviderCompat.requireContext;

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
import kz.project.jaryq.ui.kitaptar.models.KitapCategory;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private List<KitapCategory> kitapCategories;

    public CategoryAdapter(Context context, List<KitapCategory> kitapCategories) {
        this.context = context;
        this.kitapCategories = kitapCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_list,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.categoryName.setText(kitapCategories.get(position).getCategoryName());

        KitaptarAdapter kitaptarAdapter = new KitaptarAdapter(context, kitapCategories.get(position).getKitapList());
        holder.recyclerViewKitaptar.setAdapter(kitaptarAdapter);
    }

    @Override
    public int getItemCount() {
        return kitapCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        RecyclerView recyclerViewKitaptar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            recyclerViewKitaptar = itemView.findViewById(R.id.recyclerViewKitaptar);

            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyclerViewKitaptar.setLayoutManager(layoutManager);
        }
    }
}
