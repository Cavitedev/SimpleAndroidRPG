package com.example.simplerpg.ui.heroProperties;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.ui.chartCustom.StatsValueFormatter;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;

public class HeroStats extends Fragment {

    private Bundle bundle;

    private TextView strengthTextView, dexterityTextView, intelligenceTextView, constitutionTextView, speedTextView, lvlTextView, xpTillNextLvlTextView;
    private ProgressBar lvlProgressBar;

    private RadarChart statsChart;

    public HeroStats() {
        // Required empty public constructor

    }

    public static HeroStats newInstance(Bundle bundle) {
        HeroStats fragment = new HeroStats();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero_stats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getComponentsID(view);
        configureInitialUIValues();
        updateUI();
    }

    private void getComponentsID(View view) {
        strengthTextView = view.findViewById(R.id.heroStats_strengh);
        dexterityTextView = view.findViewById(R.id.heroStats_dexterity);
        intelligenceTextView = view.findViewById(R.id.heroStats_intelligence);
        constitutionTextView = view.findViewById(R.id.heroStats_constitution);
        speedTextView = view.findViewById(R.id.heroStats_speed);

        lvlProgressBar = view.findViewById(R.id.heroProfile_lvlProgressBar);
        lvlTextView = view.findViewById(R.id.heroStats_lvl);
        xpTillNextLvlTextView = view.findViewById(R.id.heroStats_lvlXpTillNextLevel);

        statsChart = view.findViewById(R.id.heroStats_statsChart);
        statsChart.setLogEnabled(false);
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

    public void updateUI() {
        if (getArguments() != null) {
            Hero hero = getArguments().getParcelable("hero");

            strengthTextView.setText(String.valueOf(hero.getStats().getStrength()));
            dexterityTextView.setText(String.valueOf(hero.getStats().getDexterity()));
            intelligenceTextView.setText(String.valueOf(hero.getStats().getIntelligence()));
            constitutionTextView.setText(String.valueOf(hero.getStats().getConstitution()));
            speedTextView.setText(String.valueOf(hero.getStats().getSpeed()));

            lvlTextView.setText(String.valueOf(hero.getLvl()));
            xpTillNextLvlTextView.setText(String.valueOf(hero.getExpTillNextLvl()));

            lvlProgressBar.setProgress((int) (hero.getXpPercentageTillCurrentLvl()));

            updateStatsChart();
        }
    }

    private void updateStatsChart() {
        assert getArguments() != null;
        Hero hero = getArguments().getParcelable("hero");
        ArrayList<RadarEntry> radarStats = new ArrayList<>();

        radarStats.add(new RadarEntry(hero.getStats().getStrength()));
        radarStats.add(new RadarEntry(hero.getStats().getDexterity()));
        radarStats.add(new RadarEntry(hero.getStats().getIntelligence()));
        radarStats.add(new RadarEntry(hero.getStats().getConstitution()));
        radarStats.add(new RadarEntry(hero.getStats().getSpeed()));

        RadarDataSet set = new RadarDataSet(radarStats, "Stats");
        set.setColor(Color.rgb(103, 110, 129));
        set.setFillColor(Color.rgb(103, 110, 129));
        set.setDrawFilled(true);
        set.setFillAlpha(180);
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