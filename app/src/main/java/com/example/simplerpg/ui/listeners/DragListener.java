package com.example.simplerpg.ui.listeners;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Party;

public class DragListener implements View.OnDragListener {

    private LongTouchListener myLongTouchListener;
    private ViewGroup firstContainer;
    private Party party;

    public DragListener(LongTouchListener myLongTouchListener, Party party) {
        this.myLongTouchListener = myLongTouchListener;
        this.party = party;
    }

    @Override
    public boolean onDrag(View v, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        View view = (View) dragEvent.getLocalState();
        switch (action) {
            case DragEvent.ACTION_DRAG_ENTERED:
            case DragEvent.ACTION_DRAG_EXITED:
                v.invalidate();
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                view.setVisibility(View.VISIBLE);
                v.invalidate();
                break;
            case DragEvent.ACTION_DROP:
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);

                firstContainer = (ViewGroup) myLongTouchListener.getViewParent();
                ViewGroup container = (ViewGroup) v;

                View child = container.getChildAt(0);

                if (child != null) {
                    container.removeViewAt(0);
                    firstContainer.addView(child);
                }

                container.addView(view);
                view.setVisibility(View.VISIBLE);

                managePartyChanges(firstContainer, container);

                break;
            default:
                break;

        }

        return true;
    }

    private void managePartyChanges(ViewGroup firstContainer, ViewGroup secondContainer) {
        int[] firstViewPosition = position(firstContainer);
        int[] secondViewPosition = position(secondContainer);

        Hero firstHero = party.getHeroAt(firstViewPosition[0], firstViewPosition[1]);
        Hero secondHero = party.getHeroAt(secondViewPosition[0], secondViewPosition[1]);

        party.putHeroAt(firstHero, secondViewPosition[0], secondViewPosition[1]);
        party.putHeroAt(secondHero, firstViewPosition[0], firstViewPosition[1]);

    }

    private int[] position(View view) {
        int[] position = new int[2];
        switch (view.getId()) {
            case R.id.upLeft:
                position[0] = 0;
                position[1] = 0;
                break;
            case R.id.upCenter:
                position[0] = 1;
                position[1] = 0;
                break;
            case R.id.upRight:
                position[0] = 2;
                position[1] = 0;
                break;
            case R.id.downLeft:
                position[0] = 0;
                position[1] = 1;
                break;
            case R.id.downCenter:
                position[0] = 1;
                position[1] = 1;
                break;
            case R.id.downRight:
                position[0] = 2;
                position[1] = 1;
                break;
        }
        return position;
    }

}