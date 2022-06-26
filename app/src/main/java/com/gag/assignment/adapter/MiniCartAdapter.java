package com.gag.assignment.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gag.assignment.R;
import com.gag.assignment.callback.OnMiniCartListener;
import com.gag.assignment.databinding.MiniCartTileBinding;
import com.gag.assignment.holder.MiniCartViewHolder;
import com.gag.assignment.model.ProductAttribute;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class MiniCartAdapter extends RecyclerView.Adapter {

    private ArrayList<ProductAttribute> mProductList;
    private int mCurrentIndex;

    private OnMiniCartListener mOnMiniCartListener;

    public void setOnMiniCartListener(OnMiniCartListener onMiniCartListener) {
        this.mOnMiniCartListener = onMiniCartListener;
    }

    public MiniCartAdapter(ArrayList<ProductAttribute> data, int currentIndex) {
        this.mProductList = data;
        mCurrentIndex = currentIndex;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MiniCartTileBinding itemBinding = MiniCartTileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MiniCartViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MiniCartViewHolder miniCartHolder = (MiniCartViewHolder) holder;
        Context context = holder.itemView.getContext();
        ProductAttribute product = mProductList.get(position);

        miniCartHolder.itemBinding.txtQuantity.setText(product.getPack_weight_info());
        int selectedCount = product.getSelectedCount();

        tileOutline(position, miniCartHolder.itemBinding);

        Double price = product.getPrice();
        Double sPrice = product.getSpecial_price();

        Locale ind = new Locale("en", "IN");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(ind);


        if (sPrice == null || sPrice.intValue() == 0) {
            miniCartHolder.itemBinding.txtSpecialPrice.setVisibility(View.GONE);
            miniCartHolder.itemBinding.txtPrice.setText(dollarFormat.format(price));
            miniCartHolder.itemBinding.txtPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            miniCartHolder.itemBinding.txtPrice.setPadding(0, 0, 0, 0);
            miniCartHolder.itemBinding.txtPrice.setTextColor(miniCartHolder.itemBinding.txtQuantity.getCurrentTextColor());
            miniCartHolder.itemBinding.txtPrice.setPaintFlags(miniCartHolder.itemBinding.txtPrice.getPaintFlags());
        } else {
            miniCartHolder.itemBinding.txtSpecialPrice.setVisibility(View.VISIBLE);
            miniCartHolder.itemBinding.txtSpecialPrice.setText(dollarFormat.format(sPrice));
            miniCartHolder.itemBinding.txtSpecialPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

            miniCartHolder.itemBinding.txtPrice.setText(dollarFormat.format(price));
            miniCartHolder.itemBinding.txtPrice.setPaintFlags(miniCartHolder.itemBinding.txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            miniCartHolder.itemBinding.txtPrice.setTextColor(Color.RED);
            miniCartHolder.itemBinding.txtPrice.setPadding((int) miniCartHolder.itemBinding.getRoot().getContext().getResources().getDimension(R.dimen.content_inbetween_gap), 0, 0, 0);
        }

        updateAddBtn(selectedCount, miniCartHolder.itemBinding);

        miniCartHolder.itemBinding.btnAdd.setOnClickListener(v -> {
            updateSelectedCount(product.getSelectedCount() + 1, position);
        });

        miniCartHolder.itemBinding.btnMinus.setOnClickListener(v -> {
            int newCount = product.getSelectedCount() - 1;
            if (newCount >= 0)
                updateSelectedCount((product.getSelectedCount() - 1), position);
        });

        miniCartHolder.itemBinding.btnPlus.setOnClickListener(v -> {
            updateSelectedCount(product.getSelectedCount() + 1, position);
        });

        miniCartHolder.itemBinding.minicartTile.setOnClickListener(v -> {
            updateSelectedCount(product.getSelectedCount(), position);
        });

    }

    @Override
    public int getItemCount() {
        return mProductList.size();
    }

    private void updateSelectedCount(int count, int currentIndex) {
        mProductList.get(currentIndex).setSelectedCount(count);
        setCurrentIndex(currentIndex);
        notifyItemRangeChanged(0, mProductList.size());
        if (mOnMiniCartListener != null) {
            mOnMiniCartListener.updateDataOnProductSelection(mCurrentIndex, mProductList);
        }
    }

    private void updateAddBtn(int selectedCount, MiniCartTileBinding binding) {
        if (selectedCount == 0) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.minusPlusParent.setVisibility(View.GONE);
        } else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.minusPlusParent.setVisibility(View.VISIBLE);
            binding.txtManualQuanatity.setText("" + selectedCount);
        }
    }

    private void tileOutline(int position, MiniCartTileBinding binding) {
        if (mCurrentIndex == position) {
            binding.minicartTile.setBackground(binding.getRoot().getContext().getDrawable(R.drawable.tile_outline));
        } else {
            binding.minicartTile.setBackground(null);
        }

    }


    private void setCurrentIndex(int currentIndex) {
        mCurrentIndex = currentIndex;
    }

}
