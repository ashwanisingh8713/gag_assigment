package com.gag.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gag.assignment.adapter.CartAdapter;
import com.gag.assignment.databinding.ActivityCartBinding;
import com.gag.assignment.repository.DataManager;
import com.gag.assignment.util.DefaultPref;
import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ashwanisingh on 26/06/22.
 */

public class CartActivity extends AppCompatActivity {

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private ActivityCartBinding binding;
    private CartAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        LinearLayoutManager llm = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(llm);
        mAdapter = new CartAdapter();
        binding.recyclerView.setAdapter(mAdapter);

        showCartData();

        binding.actionBack.setOnClickListener(v -> {
            finish();
        });

        binding.txtCheckout.setOnClickListener(v -> {
            Toast.makeText(this, "No Further Implementation", Toast.LENGTH_LONG).show();
        });

        binding.txtLogout.setOnClickListener(v->{
            mDisposable.clear();
            mDisposable.dispose();

            DefaultPref.getInstance().setIsUserLoggedIn(false);
            FirebaseAuth.getInstance().signOut();
            DataManager.removeAllCart();

            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });

    }

    private void showCartData() {
        mDisposable.add(DataManager.getCartAllProduct()
                .map(products -> products)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                            mAdapter.setUpdateData(products);
                            if(products.size() > 0) {
                                binding.txtCartEmpty.setVisibility(View.GONE);
                            } else {
                                binding.txtCartEmpty.setVisibility(View.VISIBLE);
                            }
                        }, throwable -> {}));
    }

    @Override
    protected void onDestroy() {
        mDisposable.clear();
        mDisposable.dispose();
        super.onDestroy();
    }


}
