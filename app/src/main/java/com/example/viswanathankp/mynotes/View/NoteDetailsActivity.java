package com.example.viswanathankp.mynotes.View;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.Model.NoteItem;
import com.example.viswanathankp.mynotes.R;
import com.example.viswanathankp.mynotes.Utils.ImageSaver;
import com.example.viswanathankp.mynotes.Utils.Utils;
import com.example.viswanathankp.mynotes.ViewModels.AddNoteViewModel;
import com.example.viswanathankp.mynotes.ViewModels.NoteDetailViewModel;

import org.parceler.Parcels;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.viswanathankp.mynotes.Utils.Constants.NOTE_PARECEL;

public class NoteDetailsActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText titleTxt;

    @BindView(R.id.editText2)
    EditText contentTxt;

    @BindView(R.id.img)
    ImageView imageView;

    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;

    NoteDetailViewModel viewModel;
    NoteItem user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel.class);
        user = Parcels.unwrap(getIntent().getParcelableExtra(NOTE_PARECEL));
        updateView(user);
    }

    private void updateView(NoteItem noteItem){
        titleTxt.setText(noteItem.title);
        contentTxt.setText(noteItem.content);
        if(!TextUtils.isEmpty(noteItem.imagePath)){
            Uri savedImageURI = Uri.parse(noteItem.imagePath);
            if(savedImageURI != null) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageURI(savedImageURI);
            }
        }
    }

    private void deleteNote(NoteItem note){
        if(note != null) {
            viewModel.deleteNote(note.id);
            finish();
        }
    }

    @OnClick(R.id.fab)
    public void updateNote() {
        user.title = titleTxt.getText().toString();
        user.content = contentTxt.getText().toString();
        viewModel.updateNote(user);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_edit:
                titleTxt.setEnabled(true);
                contentTxt.setEnabled(true);
                floatingActionButton.setVisibility(View.VISIBLE);
                break;
            case R.id.action_delete:
                deleteNote(user);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
