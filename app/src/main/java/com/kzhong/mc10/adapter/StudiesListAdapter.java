package com.kzhong.mc10.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kzhong.mc10.R;
import com.kzhong.mc10.model.Studies;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class StudiesListAdapter extends RecyclerView.Adapter<StudiesListAdapter.StudiesListViewHolder> {

    private ArrayList<Studies> studiesList;

    public StudiesListAdapter(ArrayList<Studies> studiesList) {
        this.studiesList = studiesList;
    }

    @Override @NonNull
    public StudiesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new StudiesListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudiesListViewHolder holder, int position) {
        holder.tvDisplayName.setText(studiesList.get(position).getDisplayName());
        holder.tvTitle.setText(studiesList.get(position).getTitle());
        holder.tvTimeStamp.setText(convertTimeStamp(studiesList.get(position).getTimeStamp()));
    }

    @Override
    public int getItemCount() {
        return studiesList.size();
    }

    class StudiesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDisplayName, tvTitle, tvTimeStamp;

        StudiesListViewHolder(View itemView) {
            super(itemView);
            tvDisplayName = itemView.findViewById(R.id.tvDisplayName);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {}
    }

    private String convertTimeStamp(String timestamp) {

        Timestamp time = new Timestamp(Long.valueOf(timestamp));
        Date date = new Date(time.getTime());

        DateFormat dateFormat = new SimpleDateFormat("EEEE MMMM dd, yyyy hh:mm a", Locale.getDefault());

        return dateFormat.format(date);
    }
}
