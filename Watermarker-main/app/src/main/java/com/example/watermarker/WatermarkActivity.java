package com.example.watermarker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class WatermarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.watermark_screen);

        ImageView logoutButton = findViewById(R.id.logoutLogo);
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(WatermarkActivity.this, "Déconnexion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        ImageView recoverWatermarkButton = findViewById(R.id.handLogo);
        recoverWatermarkButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecoverWatermarkActivity.class);
            startActivity(intent);
        });

        ImageView recoverImageButton = findViewById(R.id.eraserLogo);
        recoverImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecoverImageActivity.class);
            startActivity(intent);
        });

        ImageView galleryButton = findViewById(R.id.galleryLogo);
        galleryButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
            startActivity(intent);
        });
    }
}
