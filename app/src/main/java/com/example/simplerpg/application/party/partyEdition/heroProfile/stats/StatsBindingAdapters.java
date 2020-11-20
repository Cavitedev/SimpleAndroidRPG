package com.example.simplerpg.application.party.partyEdition.heroProfile.stats;

import androidx.databinding.BindingAdapter;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

/**
 * Holds Binding adapters for stats to compiling the data Binding
 */
public class StatsBindingAdapters {

    @BindingAdapter("rcProgress")
    public static void bindXpPercentage(RoundCornerProgressBar bar, float value){
        bar.setProgress(value);
    }
}
