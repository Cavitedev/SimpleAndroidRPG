package com.example.simplerpg.application.party.partyEdition.heroProfile.stats;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.databinding.FragmentHeroStatsBinding;
import com.example.simplerpg.domain.models.Stats;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;
import java.util.List;

public class HeroStatsFragment extends Fragment {

    private RadarChart statsChart;

    private Stats stats;
    private Context context;

    public enum Context {
        HERO_PROFILE, PARTY_FORMATION
    }

    public HeroStatsFragment() {
    }

    public static HeroStatsFragment newInstance(Stats stats, Context context) {
        HeroStatsFragment fragment = new HeroStatsFragment();
        fragment.stats = stats;
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHeroStatsBinding binding = FragmentHeroStatsBinding.inflate(inflater,
                 container, false);
        binding.setStats(stats);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getUIComponentsID(view);
        configureInitialUIValues();
        updateUI();
    }

    private void getUIComponentsID(View view) {
        statsChart = view.findViewById(R.id.heroStats_statsChart);
        statsChart.setLogEnabled(false);
    }

    public void updateUI() {
        if (stats != null) {

            updateStatsChart();

            if (context != Context.HERO_PROFILE) {
                getView().findViewById(R.id.heroStats_textViewLvl).setVisibility(View.INVISIBLE);
                getView().findViewById(R.id.heroStats_textViewXpRemaining).setVisibility(View.INVISIBLE);
            }
        }
    }

    private void configureInitialUIValues() {
        statsChart.getDescription().setEnabled(false);
        statsChart.setWebLineWidth(1f);
        statsChart.setWebColor(Color.LTGRAY);
        statsChart.setWebLineWidthInner(1f);
        statsChart.setWebColorInner(Color.LTGRAY);
        statsChart.setWebAlpha(100);
        statsChart.setTouchEnabled(false);

        MarkerView mv = new MarkerView(this.getContext(), R.layout.fragment_hero_stats);
        statsChart.setMarker(mv);

        statsChart.setRotationEnabled(false);

        XAxis xAxis = statsChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);

        xAxis.setTextColor(Color.BLACK);

        YAxis yAxis = statsChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(23f);
        yAxis.setDrawLabels(false);
        xAxis.setValueFormatter(new StatsValueFormatter());

        Legend l = statsChart.getLegend();
        l.setEnabled(false);
    }

    private void updateStatsChart() {

        List<RadarEntry> radarStats = new ArrayList<>();

        radarStats.add(new RadarEntry(stats.getStrength()));
        radarStats.add(new RadarEntry(stats.getDexterity()));
        radarStats.add(new RadarEntry(stats.getIntelligence()));
        radarStats.add(new RadarEntry(stats.getConstitution()));
        radarStats.add(new RadarEntry(stats.getSpeed()));

        RadarDataSet set = new RadarDataSet(radarStats, "Stats");
        //set.setColor(Color.rgb(103, 110, 129));
        set.setColor(Color.parseColor("#039BE5"));
        //set.setFillColor(Color.rgb(0, 0, 255));
        set.setFillColor(Color.parseColor("#63CCFF"));
        set.setDrawFilled(true);
        set.setFillAlpha(100);
        set.setLineWidth(2f);
        set.setDrawHighlightCircleEnabled(false);
        set.setDrawHighlightIndicators(false);

        RadarData data = new RadarData(set);

        data.setValueTextSize(8f);
        data.setDrawValues(false);

        statsChart.setData(data);
        statsChart.invalidate();
    }

}