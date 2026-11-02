package com.example.stopwatch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LapAdapter extends RecyclerView.Adapter<LapAdapter.LapViewHolder> {

    private final List<LapItem> lapList;

    public static class LapItem {
        public String lapNumber;
        public String lapTime;

        public LapItem(String lapNumber, String lapTime) {
            this.lapNumber = lapNumber;
            this.lapTime = lapTime;
        }
    }

    public LapAdapter(List<LapItem> lapList) {
        this.lapList = lapList;
    }

    @NonNull
    @Override
    public LapViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lap, parent, false);
        return new LapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LapViewHolder holder, int position) {
        LapItem item = lapList.get(position);
        holder.tvLapNumber.setText(item.lapNumber);
        holder.tvLapTime.setText(item.lapTime);
    }

    @Override
    public int getItemCount() {
        return lapList.size();
    }

    static class LapViewHolder extends RecyclerView.ViewHolder {
        TextView tvLapNumber;
        TextView tvLapTime;

        public LapViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLapNumber = itemView.findViewById(R.id.tvLapNumber);
            tvLapTime = itemView.findViewById(R.id.tvLapTime);
        }
    }
}
