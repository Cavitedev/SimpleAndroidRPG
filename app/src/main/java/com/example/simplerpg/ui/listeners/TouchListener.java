package com.example.simplerpg.ui.listeners;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

public final class TouchListener implements View.OnTouchListener {

    private ViewParent viewParent;

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);

            viewParent = view.getParent();
            return true;
        } else {
            return false;
        }
    }

    public ViewParent getViewParent() {
        return viewParent;
    }
}