package com.mynotes.save;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mynotes.save.databinding.ActivityAddEditNoteBinding;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.mynotes.save.EXTRA_ID";
    public static final String EXTRA_TITLE = "com.mynotes.save.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.mynotes.save.EXTRA_DESCRIPTION";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private ActivityAddEditNoteBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editTextTitle = binding.editTextTitle;
        editTextDescription = binding.editTextDescription;

        // Set up the toolbar as an ActionBar
        setSupportActionBar(binding.toolbar);
        // Enable the Up button (back arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close); // Custom back icon
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save_note) {
            saveNote();
            return true;
        } else if (id == android.R.id.home) { // Handle the Up button (back arrow) click
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}