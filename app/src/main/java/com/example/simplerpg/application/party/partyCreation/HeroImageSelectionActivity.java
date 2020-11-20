package com.example.simplerpg.application.party.partyCreation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplerpg.R;

import java.lang.reflect.Field;

public class HeroImageSelectionActivity extends AppCompatActivity implements HeroImageAdapter.OnElementListener {

    private RecyclerView recyclerView;

    public static HeroImageSelectionActivity newInstance() {
        HeroImageSelectionActivity fragment = new HeroImageSelectionActivity();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_image_selection);

        recyclerView = findViewById(R.id.heroImageSelection_recyclerView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        int[] imageCodes = new int[19];
        Class res = R.drawable.class;
        for (int i = 0; i < imageCodes.length; i++) {
            try {
                Field field = res.getField("hero" + i);
                imageCodes[i] = field.getInt(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        recyclerView.setAdapter(new HeroImageAdapter(this, imageCodes, this));
    }

    @Override
    public void onElementClick(int position) {
        Intent dataBack = new Intent();
        dataBack.putExtra("heroImage", "hero" + position);
        setResult(Activity.RESULT_OK, dataBack);
        finish();
    }
}