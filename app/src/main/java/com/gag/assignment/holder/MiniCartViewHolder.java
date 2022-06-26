package com.gag.assignment.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.gag.assignment.databinding.MiniCartLayoutBinding;
import com.gag.assignment.databinding.MiniCartTileBinding;
import com.gag.assignment.model.ProductAttribute;


/**
 * Created by ashwanisingh on 25/06/22.
 */

public class MiniCartViewHolder extends RecyclerView.ViewHolder {

    public MiniCartTileBinding itemBinding;

    public MiniCartViewHolder(MiniCartTileBinding itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
    }

    public void bind(ProductAttribute product) {
        if(product.getSelectedCount() == 0) {
            itemBinding.btnAdd.setVisibility(View.VISIBLE);
            itemBinding.minusPlusParent.setVisibility(View.GONE);
        } else {
            itemBinding.btnAdd.setVisibility(View.VISIBLE);
            itemBinding.minusPlusParent.setVisibility(View.GONE);
        }
    }

}
