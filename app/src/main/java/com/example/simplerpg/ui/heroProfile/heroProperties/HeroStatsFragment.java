package com.example.simplerpg.ui.heroProfile.heroProperties;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.simplerpg.R;
import com.example.simplerpg.chartCustom.StatsValueFormatter;
import com.example.simplerpg.models.Stats;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;

public class HeroStatsFragment extends Fragment {


    private TextView strengthTextView, dexterityTextView, intelligenceTextView, constitutionTextView, speedTextView, lvlTextView, xpTillNextLvlTextView;

    private RoundCornerProgressBar lvlProgressBar;

    private RadarChart statsChart;

    private Stats stats;

    public HeroStatsFragment() {
        // Required empty public constructor
    }

    public static HeroStatsFragment newInstance(Stats stats) {
        HeroStatsFragment fragment = new HeroStatsFragment();
        fragment.stats = stats;
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

        getUIComponentsID(view);
        configureInitialUIValues();
        updateUI();
    }

    private void getUIComponentsID(View view) {
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
        if (stats != null) {

            strengthTextView.setText(String.valueOf(stats.getStrength()));
            dexterityTextView.setText(String.valueOf(stats.getDexterity()));
            intelligenceTextView.setText(String.valueOf(stats.getIntelligence()));
            constitutionTextView.setText(String.valueOf(stats.getConstitution()));
            speedTextView.setText(String.valueOf(stats.getSpeed()));

            lvlTextView.setText(String.valueOf(stats.getLvl()));
            xpTillNextLvlTextView.setText(String.valueOf(stats.getExpTillNextLvl()));

            lvlProgressBar.setProgress((int) (stats.getXpPercentageTillCurrentLvl()));

            updateStatsChart();
        }
    }

    private void updateStatsChart() {

        ArrayList<RadarEntry> radarStats = new ArrayList<>();

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