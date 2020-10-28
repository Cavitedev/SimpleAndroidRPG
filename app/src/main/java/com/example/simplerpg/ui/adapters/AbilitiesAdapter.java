package com.example.simplerpg.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Ability;

import java.util.ArrayList;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.ViewHolderData> {

    private ArrayList<Ability> abilities;
    private Context context;

    public AbilitiesAdapter(Context context, ArrayList<Ability> abilities) {
        this.context = context;
        this.abilities = abilities;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ability_list_item, parent, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.assignData(abilities.get(position));
    }

    @Override
    public int getItemCount() {
        return abilities.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView name, type, power;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.heroAbilities_listView_name);
            power = itemView.findViewById(R.id.heroAbilities_listView_power);
            type = itemView.findViewById(R.id.heroAbilities_listView_type);

        }

        public void assignData(Ability ability) {
            name.setText(ability.getName());
            power.setText(String.valueOf(ability.getPower()));
            type.setText(ability.getType().toString());
        }
    }
}
