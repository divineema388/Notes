package com.mynotes.save;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mynotes.save.databinding.ActivityHelpBinding;

public class HelpActivity extends AppCompatActivity {

    private ActivityHelpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the toolbar as an ActionBar
        setSupportActionBar(binding.toolbar);

        // Enable the Up button (back arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_help); // Set title from strings
        }

        // Make the website link clickable (autoLink="web" handles most of it, but this adds a fallback)
        TextView websiteLink = binding.websiteLink;
        websiteLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.help_website_url)));
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle the Up button (back arrow) click
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}