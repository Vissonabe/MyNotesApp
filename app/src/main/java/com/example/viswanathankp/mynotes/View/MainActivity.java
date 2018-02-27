package com.example.viswanathankp.mynotes.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.viswanathankp.mynotes.Adapter.NotesListAdapter;
import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.Model.NoteItem;
import com.example.viswanathankp.mynotes.R;
import com.example.viswanathankp.mynotes.Utils.Utils;
import com.example.viswanathankp.mynotes.ViewModels.NotesListViewModel;

import org.parceler.Parcels;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.viswanathankp.mynotes.Utils.Constants.NOTE_PARECEL;
import static com.example.viswanathankp.mynotes.Utils.Constants.SORT_BY_DATE;
import static com.example.viswanathankp.mynotes.Utils.Constants.SORT_BY_TITLE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Retention(SOURCE)
    @StringDef({
            SORT_BY_TITLE,
            SORT_BY_DATE
    })
    public @interface SortState {}

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.textView)
    TextView helpText;

    private NotesListViewModel viewModel;
    private NotesListAdapter recyclerViewAdapter;
    private @SortState String sortingState = SORT_BY_DATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new NotesListAdapter(new ArrayList<Note>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel.getNotesList().observe(MainActivity.this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                setVisibility(notes);
                addItemBySort(notes);
            }
        });
    }

    private void addItemBySort(List<Note> notes){
        if(notes != null) {
            notes = sortingState.equals(SORT_BY_DATE) ? viewModel.getSortedListByDate(notes) : viewModel.getSortedListByTitle(notes);
            recyclerViewAdapter.addItems(notes);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_order_date:
                sortingState = SORT_BY_DATE;
                addItemBySort(recyclerViewAdapter.getItems());
                break;
            case R.id.action_order_title:
                sortingState = SORT_BY_TITLE;
                addItemBySort(recyclerViewAdapter.getItems());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setVisibility(List<Note> notes){
        helpText.setVisibility(notes != null && notes.size() > 0 ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.fab)
    public void navigateToAddNotesActivity(){
        Intent i = new Intent(this, AddNoteActivity.class);
        startActivity(i);
    }

    public void navigateToNoteDetailsActivity(NoteItem noteItem){
        Intent i = new Intent(this, NoteDetailsActivity.class);
        i.putExtra(NOTE_PARECEL, Parcels.wrap(noteItem));
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        Note note = (Note) v.getTag();
        navigateToNoteDetailsActivity(Utils.getNoteItem(note));
    }
}
