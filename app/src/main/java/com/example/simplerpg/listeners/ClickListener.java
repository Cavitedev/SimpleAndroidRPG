package com.example.simplerpg.listeners;

import android.view.View;

import com.example.simplerpg.models.Hero;

public class ClickListener implements View.OnClickListener {

    private Hero hero;
    private OnElementListener onElementListener;

    public ClickListener(Hero hero, OnElementListener onElementListener) {
        this.hero = hero;
        this.onElementListener = onElementListener;
    }

    @Override
    public void onClick(View v) {
        onElementListener.onElementClick(hero);
    }

    public interface OnElementListener {
        void onElementClick(Hero hero);
    }
}
