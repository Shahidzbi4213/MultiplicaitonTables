package com.edu.pk.gulehri.xplytables;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edu.pk.gulehri.xplytables.databinding.SingleItemBinding;

/**
 * Created by Shahid Iqbal on 6/20/2021
 */

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.MyHolder> {
    public static final String CLICKED_POSITION = "Clicked Item Position";
    private Context mContext;

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new MyHolder(SingleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.binding.txtNum.setText(String.format("%d", position + 1));

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, XplyActivity.class);
            intent.putExtra(CLICKED_POSITION, position);
            mContext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private final SingleItemBinding binding;

        public MyHolder(@NonNull SingleItemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
