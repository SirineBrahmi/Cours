package com.example.pfe;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DevoirAdapter extends RecyclerView.Adapter<DevoirAdapter.DevoirViewHolder> {
    private Context context;
    private List<Devoir> devoirList;

    public DevoirAdapter(Context context, List<Devoir> devoirList) {
        this.context = context;
        this.devoirList = devoirList;
    }

    @NonNull
    @Override
    public DevoirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_devoir, parent, false);
        return new DevoirViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DevoirViewHolder holder, int position) {
        Devoir devoir = devoirList.get(position);
        holder.textTitre.setText(devoir.getTitre());
        holder.textDateLimite.setText("Date limite: " + devoir.getDateLimite());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DevoirDetailsActivity.class);
            intent.putExtra("devoir", devoir);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return devoirList.size();
    }

    public static class DevoirViewHolder extends RecyclerView.ViewHolder {
        TextView textTitre, textDateLimite;

        public DevoirViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitre = itemView.findViewById(R.id.textViewTitreDevoir);
            textDateLimite = itemView.findViewById(R.id.textViewDateLimite);
        }
    }
}
