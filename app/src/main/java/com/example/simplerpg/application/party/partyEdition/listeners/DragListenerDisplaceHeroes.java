package com.example.simplerpg.application.party.partyEdition.listeners;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Party;

public class DragListenerDisplaceHeroes implements View.OnDragListener {

    private final Party party;

    public DragListenerDisplaceHeroes(Party party) {
        this.party = party;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        int action = dragEvent.getAction();
        View firstFragmentView = (View) dragEvent.getLocalState();
        switch (action) {
            case DragEvent.ACTION_DRAG_ENDED:
                undoInvisibility(firstFragmentView);
                break;
            case DragEvent.ACTION_DROP:
                performHeroesSwitch((ViewGroup) view, firstFragmentView);
                break;
            default:
                break;

        }
        return true;
    }
    private void undoInvisibility(View view) {
        view.setVisibility(View.VISIBLE);
    }

    private void performHeroesSwitch(ViewGroup secondContainer, View firstFragmentView) {
        ViewGroup firstContainer = (ViewGroup) firstFragmentView.getParent();
        firstContainer.removeView(firstFragmentView);

        View secondFragmentView = secondContainer.getChildAt(0);

        if (secondFragmentView != null) {
            secondContainer.removeViewAt(0);
            firstContainer.addView(secondFragmentView);
        }

        secondContainer.addView(firstFragmentView);

        updatePartyPositions(firstContainer, secondContainer);
    }



    private void updatePartyPositions(ViewGroup firstContainer, ViewGroup secondContainer) {

        int[] firstHeroPosition = calculatePosition(firstContainer);
        int[] secondHeroPosition = calculatePosition(secondContainer);

        party.switchHeros(firstHeroPosition,secondHeroPosition);

    }

    private int[] calculatePosition(View view) {
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