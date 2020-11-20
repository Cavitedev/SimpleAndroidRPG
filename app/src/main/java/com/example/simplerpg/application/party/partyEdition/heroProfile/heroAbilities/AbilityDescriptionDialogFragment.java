package com.example.simplerpg.application.party.partyEdition.heroProfile.heroAbilities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Ability;


public class AbilityDescriptionDialogFragment extends DialogFragment {

    private Activity activity;
    private Ability ability;
    private TextView type, name, power, description;
    private ImageView icon;
    private ImageButton closeButton;

    public AbilityDescriptionDialogFragment() {

    }

    public static AbilityDescriptionDialogFragment newInstance(Ability ability) {
        AbilityDescriptionDialogFragment fragment = new AbilityDescriptionDialogFragment();
        fragment.ability = ability;
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return createDialogAbilities();
    }

    private AlertDialog createDialogAbilities() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_ability_description_dialog, null);
        builder.setView(view);


        type = view.findViewById(R.id.ability_type);
        name = view.findViewById(R.id.ability_name);
        power = view.findViewById(R.id.ability_power);
        description = view.findViewById(R.id.ability_description);

        icon = view.findViewById(R.id.ability_icon);

        closeButton = view.findViewById(R.id.ability_closeButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setUIComponentsData();

        return builder.create();
    }

    private void setUIComponentsData() {
        name.setText(ability.getName());
        type.setText(ability.getType().toString());
        power.setText(String.valueOf(ability.getPower()));
        description.setText(ability.getDescription());

        //TODO implement icon
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        } else {
            throw new RuntimeException(context.toString() +
                    "must implement OnFragmentInteractionListener");
        }
    }
}