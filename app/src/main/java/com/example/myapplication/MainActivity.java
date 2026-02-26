package com.example.myapplication;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    LinearLayout r;
    TextView t;
    Button btn;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        r = findViewById(R.id.rootLayout);
        btn = findViewById(R.id.btnTheme);
        seekBar = findViewById(R.id.seekFont);
        t = findViewById(R.id.txtStatus);
        SharedPreferences sp = getSharedPreferences("settings", MODE_PRIVATE);

        boolean dark = sp.getBoolean("dark_mode", false);
        applyTheme(dark);

        float size = sp.getFloat("font_size", 18f);
        t.setTextSize(size);
        seekBar.setProgress((int) size);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean v1 = !sp.getBoolean("dark_mode", false);
                sp.edit().putBoolean("dark_mode", v1).apply();
                applyTheme(v1);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                t.setTextSize(progress);
                sp.edit().putFloat("font_size", progress).apply();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    private void applyTheme(boolean d) {
        if (d) {
            r.setBackgroundColor(Color.BLACK);
            t.setTextColor(Color.WHITE);  // FIXED
        } else {
            r.setBackgroundColor(Color.WHITE);
            t.setTextColor(Color.BLACK);  // FIXED
        }
    }
}