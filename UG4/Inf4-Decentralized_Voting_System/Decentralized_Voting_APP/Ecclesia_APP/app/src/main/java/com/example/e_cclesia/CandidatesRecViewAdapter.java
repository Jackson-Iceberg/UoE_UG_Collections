package com.example.e_cclesia;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;


public class CandidatesRecViewAdapter extends RecyclerView.Adapter<CandidatesRecViewAdapter.ViewHolder> {

    private ArrayList<Candidate> candidatesList = new ArrayList<>();

    public CandidatesRecViewAdapter() {
    }

    @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.candidates_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.txtName.setText(candidatesList.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return candidatesList.size();
        }


        public void setCandidatesList(ArrayList<Candidate> candidatesList) {
            this.candidatesList = candidatesList;
            notifyDataSetChanged();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView txtName;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtName = Objects.requireNonNull(itemView).findViewById(R.id.CandidateName);
            }
        }


    }

