package com.example.e_cclesia;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class VotersRecViewAdapter extends RecyclerView.Adapter<VotersRecViewAdapter.ViewHolder>{
    private ArrayList<Voter> votersList = new ArrayList<>();

    public VotersRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voters_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(votersList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return votersList.size();
    }
    public void setVotersList(ArrayList<Voter> votersList){
        this.votersList = votersList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = Objects.requireNonNull(itemView).findViewById(R.id.VoterName);
        }
    }

}
