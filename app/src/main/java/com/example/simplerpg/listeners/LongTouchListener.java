package com.example.simplerpg.listeners;

import android.content.ClipData;
import android.view.View;
import android.view.ViewParent;

public class LongTouchListener implements View.OnLongClickListener {

    private ViewParent viewParent;

    @Override
    public boolean onLongClick(View view) {
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);

        viewParent = view.getParent();
        return true;
    }

    public ViewParent getViewParent() {
        return viewParent;
    }
}