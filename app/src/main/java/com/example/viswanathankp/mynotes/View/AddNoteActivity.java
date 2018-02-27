package com.example.viswanathankp.mynotes.View;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.viswanathankp.mynotes.DB.Note;
import com.example.viswanathankp.mynotes.R;
import com.example.viswanathankp.mynotes.ViewModels.AddNoteViewModel;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddNoteActivity extends AppCompatActivity {

    private final int requestCode = 20;

    private AddNoteViewModel viewModel;

    @BindView(R.id.editText)
    EditText titleTxt;

    @BindView(R.id.editText2)
    EditText contentTxt;

    @BindView(R.id.img)
    ImageView imageView;

    private String imageFilePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(AddNoteViewModel.class);
    }

    private void addNote(){
        if (titleTxt.getText() == null || contentTxt.getText() == null)
            Toast.makeText(this, "Missing fields", Toast.LENGTH_SHORT).show();
        else {
            viewModel.addNote(new Note(
                    titleTxt.getText().toString(),
                    contentTxt.getText().toString(),
                    Calendar.getInstance().getTime(), imageFilePath
            ));
            finish();
        }
    }

    private void captureImageIntent(){
        Intent photoCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(photoCaptureIntent, requestCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_save:
                addNote();
                break;
            case R.id.action_attach:
                captureImageIntent();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            Bitmap bitmap = (Bitmap)data.getExtras().get("data");
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageBitmap(bitmap);
            imageFilePath = viewModel.saveImageToFile(bitmap);
        }
    }
}
