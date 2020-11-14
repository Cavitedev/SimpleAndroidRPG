package com.example.simplerpg.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplerpg.R;

public class HeroImageAdapter extends RecyclerView.Adapter<HeroImageAdapter.ViewHolderData> {

    private int[] imagesID;
    private Context context;
    private OnElementListener onElementListener;

    public HeroImageAdapter(Context context, int[] imagesID, OnElementListener onElementListener) {
        this.context = context;
        this.imagesID = imagesID;
        this.onElementListener = onElementListener;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_heroimage, parent, false);
        return new ViewHolderData(view, onElementListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.assignData(imagesID[position]);
    }

    @Override
    public int getItemCount() {
        return imagesID.length;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageViewHero;
        private OnElementListener onElementListener;

        public ViewHolderData(@NonNull View itemView, OnElementListener onElementListener) {
            super(itemView);
            imageViewHero = itemView.findViewById(R.id.singleHeroImage_imageView);
            this.onElementListener = onElementListener;

            itemView.setOnClickListener(this);
        }

        public void assignData(int imageID) {
            imageViewHero.setImageResource(imageID);
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
