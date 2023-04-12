package com.example.watermarker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RecoverImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recover_original_image_screen);

        ImageView logoutButton = findViewById(R.id.logoutLogo);
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(RecoverImageActivity.this, "Déconnexion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        ImageView watermarkButton = findViewById(R.id.watermarkLogo);
        watermarkButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), WatermarkActivity.class);
            startActivity(intent);
        });

        ImageView recoverWatermarkButton = findViewById(R.id.handLogo);
        recoverWatermarkButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecoverWatermarkActivity.class);
            startActivity(intent);
        });

        ImageView galleryButton = findViewById(R.id.galleryLogo);
        galleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(intent);
        });
    }
}
