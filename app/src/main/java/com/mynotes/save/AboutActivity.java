package com.mynotes.save;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mynotes.save.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity {

    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the toolbar as an ActionBar
        setSupportActionBar(binding.toolbar);

        // Enable the Up button (back arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_about); // Set title from strings
        }

        // Display app version
        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = "Version " + pInfo.versionName;
            binding.aboutVersion.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            binding.aboutVersion.setText("Version N/A");
        }

        // The usefulness and credits content will be loaded directly from strings.xml
        // No explicit setText calls are needed here for those TextViews if they directly reference strings.
        // However, if you want to set them programmatically or from a dynamic source:
        // binding.aboutUsefulnessContent.setText(R.string.about_app_usefulness_content);
        // binding.aboutCreditsContent.setText(R.string.about_credits_content);
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