package com.gag.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.gag.assignment.R;
import com.gag.assignment.alert.ErrorDialog;
import com.gag.assignment.callback.OnProductCartListener;
import com.gag.assignment.databinding.ActivityMainBinding;
import com.gag.assignment.fragment.MiniCartFragment;
import com.gag.assignment.model.ProductAttribute;
import com.gag.assignment.repository.ApiManager;
import com.gag.assignment.repository.DataManager;
import com.gag.assignment.repository.OnResponse;
import com.gag.assignment.util.AppUtil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class MainActivity extends AppCompatActivity implements OnResponse, OnProductCartListener {

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    // Initialize variables
    ActivityMainBinding binding;

    private ApiManager mApiManager;

    private ArrayList<ProductAttribute> mProductList = new ArrayList<>();
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mApiManager = new ApiManager(this);
        mApiManager.fetchProduct();

        binding.quantityParent.setOnClickListener(v -> {
            showMiniCart();
        });

        binding.btnRefresh.setOnClickListener(v -> {
            progressBarVisibility(View.VISIBLE);
            refreshVisibility(View.GONE);
            mApiManager.fetchProduct();
        });

        binding.txtCartBadge.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });
        binding.imgIconBadge.setOnClickListener(v -> {
            Intent intent = new Intent(this, CartActivity.class);
            startActivity(intent);
        });

        showCartData();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDisposable.add(DataManager.combineProductCartData(mProductList)
                .map(productList -> productList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess));
    }

    private void showMiniCart() {
        MiniCartFragment blankFragment = MiniCartFragment.getInstance(mProductList, mCurrentIndex);
        blankFragment.show(getSupportFragmentManager(), blankFragment.getTag());
        blankFragment.setOnProductCartListener(this);
    }


    @Override
    protected void onDestroy() {
        mDisposable.clear();
        mDisposable.dispose();
        super.onDestroy();
    }


    @Override
    public void onSuccess(List<ProductAttribute> productList) {
        if (productList.size() > 0) {
            mProductList = new ArrayList<>(productList);
            showProductData(productList.get(mCurrentIndex));
        }
    }

    @Override
    public void onFailed(Throwable e) {
        progressBarVisibility(View.GONE);
        refreshVisibility(View.VISIBLE);
        ErrorDialog.showErrorDialog(getSupportFragmentManager(), "!! ERROR !!", e.getMessage());
    }

    @Override
    public void onCompleted() {
        progressBarVisibility(View.GONE);
        refreshVisibility(View.GONE);
    }

    private void showProductData(ProductAttribute product) {
        binding.contentParent.setVisibility(View.VISIBLE);
        binding.txtTitle.setText(product.getTitle());
        binding.txtQuantity.setText(product.getPack_weight_info());

        Double price = product.getPrice();
        Double sPrice = product.getSpecial_price();


        Locale ind = new Locale("en", "IN");
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(ind);

        if (sPrice == null || sPrice.intValue() == 0) {
            binding.txtSpecialPrice.setVisibility(View.GONE);
            binding.txtPrice.setText(dollarFormat.format(price));
            binding.txtPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            binding.txtPrice.setPadding(0, 0, 0, 0);
        } else {
            binding.txtSpecialPrice.setVisibility(View.VISIBLE);
            binding.txtSpecialPrice.setText(dollarFormat.format(sPrice));
            binding.txtSpecialPrice.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            binding.txtPrice.setText(dollarFormat.format(price));
            binding.txtPrice.setPaintFlags(binding.txtPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.txtPrice.setTextColor(Color.RED);
            binding.txtPrice.setPadding((int) getResources().getDimension(R.dimen.content_inbetween_gap), 0, 0, 0);
        }


        AppUtil.loadImage(this, binding.productImg, product.getThumbnail());

        updateAddBtn(product.getSelectedCount());

        binding.btnAdd.setOnClickListener(v -> {
            updateSelectedCount(product, true);
        });

        binding.btnMinus.setOnClickListener(v -> {
            int newCount = product.getSelectedCount() - 1;
            if (newCount >= 0)
                updateSelectedCount(product, false);
        });

        binding.btnPlus.setOnClickListener(v -> {
            updateSelectedCount(product, true);
        });
    }

    private void updateAddBtn(int selectedCount) {
        if (selectedCount == 0) {
            binding.btnAdd.setVisibility(View.VISIBLE);
            binding.minusPlusParent.setVisibility(View.GONE);
        } else {
            binding.btnAdd.setVisibility(View.GONE);
            binding.minusPlusParent.setVisibility(View.VISIBLE);
            binding.txtManualQuanatity.setText("" + selectedCount);
        }
    }

    private void progressBarVisibility(int visibility) {
        binding.progressBar.setVisibility(visibility);
    }

    private void refreshVisibility(int visibility) {
        binding.btnRefresh.setVisibility(visibility);
    }

    private void updateSelectedCount(ProductAttribute product, boolean shouldIncreaseCount) {
        int selectedCount = product.getSelectedCount();
        if (shouldIncreaseCount) {
            selectedCount = selectedCount + 1;
        } else {
            selectedCount = selectedCount - 1;
        }
        product.setSelectedCount(selectedCount);
        updateAddBtn(product.getSelectedCount());

        // Update Product in Cart
        updateCart(product);

    }

    @Override
    public void updateDataOnProductSelection(int currentIndex, ArrayList<ProductAttribute> products) {
        mCurrentIndex = currentIndex;
        mProductList = products;
    }

    @Override
    public void updateUIOnAddBtn(int currentIndex, ProductAttribute product) {
        showProductData(product);
        updateCart(product);
    }

    private void updateCart(ProductAttribute product) {
        // Update Product in Cart Table
        DataManager.updateCartTable(product);
    }


    private void badgeUpdate(int badge) {
        if (badge > 0) {
            binding.txtCartBadge.setText("" + badge);
            binding.txtCartBadge.setVisibility(View.VISIBLE);
        } else {
            binding.txtCartBadge.setVisibility(View.GONE);
        }
    }

    private void showCartData() {
        mDisposable.add(DataManager.getCartAllProduct()
                .map(products -> products)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                            badgeUpdate(products.size());
                        }
                ));
    }


}