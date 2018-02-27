package com.example.viswanathankp.mynotes.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.R;

import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by viswanathan.kp on 26/02/18.
 */

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.RecyclerViewHolder> {

    private List<Note> noteList;
    private View.OnClickListener clickListener;

    public NotesListAdapter(List<Note> noteList, View.OnClickListener clickListener) {
        this.noteList = noteList;
        this.clickListener = clickListener;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        Note noteModel = noteList.get(position);
        holder.titleTextView.setText(noteModel.getTitle());
        holder.contentTextView.setText(noteModel.getContent());
        String df = SimpleDateFormat.getDateTimeInstance().format(noteModel.getCreatedDate());
        holder.dateTextView.setText(df);
        holder.itemView.setTag(noteModel);
        holder.itemView.setOnClickListener(clickListener);
    }

    public List<Note> getItems(){
        return noteList;
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void addItems(List<Note> noteList) {
        if(noteList != null) {
            this.noteList = noteList;
            notifyDataSetChanged();
        }
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title_txt) TextView titleTextView;
        @BindView(R.id.content_txt) TextView contentTextView;
        @BindView(R.id.date_txt) TextView dateTextView;

        RecyclerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
