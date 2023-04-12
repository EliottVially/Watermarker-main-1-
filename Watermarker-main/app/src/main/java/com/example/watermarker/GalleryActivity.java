package com.example.watermarker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import com.chaquo.python.android.AndroidPlatform;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class GalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_screen);

        ImageView logoutButton = findViewById(R.id.logoutLogo);
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(GalleryActivity.this, "Déconnexion", Toast.LENGTH_SHORT).show();
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

        ImageView recoverImageButton = findViewById(R.id.eraserLogo);
        recoverImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), RecoverImageActivity.class);
            startActivity(intent);
        });

        //Use of chaquopy to use python to plot a figure depending on inputs

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
        Python py = Python.getInstance();
        PyObject module = py.getModule("plot");

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    byte[] bytes = module.callAttr("plot",
                                    ((EditText) findViewById(R.id.etX)).getText().toString(),
                                    ((EditText) findViewById(R.id.etY)).getText().toString())
                            .toJava(byte[].class);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);

                    View currentFocus = getCurrentFocus();
                    if (currentFocus != null) {
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                } catch (PyException e) {
                    Toast.makeText(GalleryActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
