package com.example.simplerpg.application.party.partyEdition.heroProfile.abilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Ability;

import java.util.ArrayList;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.ViewHolderData> {

    private ArrayList<Ability> abilities;
    private Context context;
    private OnElementListener onElementListener;

    public AbilitiesAdapter(Context context, ArrayList<Ability> abilities, OnElementListener onElementListener) {
        this.context = context;
        this.abilities = abilities;
        this.onElementListener = onElementListener;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ability_list_item, parent, false);
        return new ViewHolderData(view, onElementListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.assignData(abilities.get(position));
    }

    @Override
    public int getItemCount() {
        return abilities.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name, type, power;
        private OnElementListener onElementListener;

        public ViewHolderData(@NonNull View itemView, OnElementListener onElementListener) {
            super(itemView);
            name = itemView.findViewById(R.id.heroAbilities_listView_name);
            power = itemView.findViewById(R.id.heroAbilities_listView_power);
            type = itemView.findViewById(R.id.heroAbilities_listView_type);
            this.onElementListener = onElementListener;

            itemView.setOnClickListener(this);
        }

        public void assignData(Ability ability) {
            name.setText(ability.getName());
            power.setText(String.valueOf(ability.getPower()));
            type.setText(ability.getType().toString());
        }

        @Override
        public void onClick(View v) {
            onElementListener.onElementClick(getAdapterPosition());
        }
    }

    public interface OnElementListener {
        void onElementClick(int position);
    }
}
