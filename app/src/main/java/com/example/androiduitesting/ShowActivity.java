package com.example.androiduitesting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class ShowActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setContentView(R.layout.show_activity);
        String name = getIntent().getStringExtra("name");
        TextView text = findViewById(R.id.editText);
        text.setText(name);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent back = new Intent(ShowActivity.this, MainActivity.class);
                //startActivity(back);
                finish();
            }
        });
    }

}
