package com.gag.assignment.holder;

import androidx.recyclerview.widget.RecyclerView;

import com.gag.assignment.databinding.CartTileBinding;

public class CartViewHolder extends RecyclerView.ViewHolder {

    public CartTileBinding itemBinding;
    public CartViewHolder(CartTileBinding binding) {
        super(binding.getRoot());
        this.itemBinding = binding;
    }
}
