package com.mynotes.save;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mynotes.save.databinding.ActivitySupportBinding;

import java.io.IOException;
import java.io.InputStream;

public class SupportActivity extends AppCompatActivity {

    private ActivitySupportBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set up the toolbar as an ActionBar
        setSupportActionBar(binding.toolbar);

        // Enable the Up button (back arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.menu_support); // Set title from strings
        }

        ImageView whatsappQrCode = binding.whatsappQrCode;

        // Load image from assets
        try {
            // Define the exact path to your QR code within the assets folder
            // Make sure the path matches your structure: assets/img/contact/auth/qrBig.png
            InputStream is = getAssets().open("img/contact/auth/qrBig.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            whatsappQrCode.setImageBitmap(bitmap);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load QR code image.", Toast.LENGTH_LONG).show();
            // You might want to set a placeholder image or hide the ImageView if the image fails to load
        }
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