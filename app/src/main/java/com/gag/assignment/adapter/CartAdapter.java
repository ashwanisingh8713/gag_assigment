package com.gag.assignment.adapter;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gag.assignment.R;
import com.gag.assignment.databinding.CartTileBinding;
import com.gag.assignment.db.CartTable;
import com.gag.assignment.holder.CartViewHolder;
import com.gag.assignment.model.ProductAttribute;
import com.gag.assignment.repository.DataManager;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartAdapter extends RecyclerView.Adapter {


    private List<CartTable> mProductList;

    public CartAdapter() { }

    public void setUpdateData(List<CartTable> data) {
        this.mProductList = data;
        if(mProductList.size() == 0) {
            notifyItemRemoved(0);
        } else {
            notifyItemRangeChanged(0, data.size());
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CartTileBinding binding = CartTileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CartViewHolder cartHolder = (CartViewHolder) holder;
        CartTable cartTable = mProductList.get(position);
        ProductAttribute product = cartTable.getProduct();
        int selectedCount = cartTable.getSelectedCount();

        cartHolder.itemBinding.txtQuantity.setText(product.getTitle());

        Double price = product.getPrice();
        Double sPrice = product.getSpecial_price();
        Double finalPrice = 0.0;

        Locale ind = new Locale("en", "IN");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(ind);

        if(sPrice == null || sPrice.intValue() == 0) {
            finalPrice = price;
        } else {
            finalPrice = sPrice;
        }

        if(selectedCount == 0) {
            cartHolder.itemBinding.txtPrice.setVisibility(View.INVISIBLE);
        } else {
            cartHolder.itemBinding.txtPrice.setVisibility(View.VISIBLE);
            cartHolder.itemBinding.txtPrice.setText(dollarFormat.format((finalPrice * selectedCount)));
            cartHolder.itemBinding.txtPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        }

        updateAddBtn(selectedCount, cartHolder.itemBinding);

        cartHolder.itemBinding.btnAdd.setOnClickListener(v->{
            updateSelectedCount(cartTable.getSelectedCount()+1, position);
        });

        cartHolder.itemBinding.btnMinus.setOnClickListener(v->{
            updateSelectedCount((cartTable.getSelectedCount()-1), position);
        });

        cartHolder.itemBinding.btnPlus.setOnClickListener(v->{
            updateSelectedCount(cartTable.getSelectedCount()+1, position);
        });

        cartHolder.itemBinding.minicartTile.setOnClickListener(v->{
            updateSelectedCount(cartTable.getSelectedCount(), position);
        });




    }

    @Override
    public int getItemCount() {
        return mProductList==null ? 0 : mProductList.size();
    }

    private void updateSelectedCount(int count, int currentIndex) {
        mProductList.get(currentIndex).setSelectedCount(count);
        notifyItemChanged(currentIndex);
        DataManager.updateCartTable(mProductList.get(currentIndex).getProduct());

    }

    private void updateAddBtn(int selectedCount, CartTileBinding binding) {
        if (selectedCount == 0) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.minusPlusParent.setVisibility(View.GONE);
        } else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.minusPlusParent.setVisibility(View.VISIBLE);
            binding.txtManualQuanatity.setText("" + selectedCount);
        }
    }




}
