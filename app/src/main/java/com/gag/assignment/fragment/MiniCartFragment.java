package com.gag.assignment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gag.assignment.adapter.MiniCartAdapter;
import com.gag.assignment.callback.OnMiniCartListener;
import com.gag.assignment.callback.OnProductCartListener;
import com.gag.assignment.databinding.MiniCartLayoutBinding;
import com.gag.assignment.model.ProductAttribute;
import com.gag.assignment.util.AppUtil;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class MiniCartFragment extends BottomSheetDialogFragment implements OnMiniCartListener {

    private MiniCartLayoutBinding binding;
    private ArrayList<ProductAttribute> mProductList;
    private int mCurrentIndex;
    private MiniCartAdapter mAdapter;
    private OnProductCartListener mOnProductCartListener;

    public static MiniCartFragment getInstance(ArrayList<ProductAttribute> data, int currentIndex) {
        MiniCartFragment fragment = new MiniCartFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("currentIndex", currentIndex);
        bundle.putParcelableArrayList("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setOnProductCartListener(OnProductCartListener onProductCartListener) {
        this.mOnProductCartListener = onProductCartListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProductList = getArguments().getParcelableArrayList("data");
        mCurrentIndex = getArguments().getInt("currentIndex");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = MiniCartLayoutBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showSelectedProduct(mCurrentIndex, mProductList);

        mAdapter = new MiniCartAdapter(mProductList, mCurrentIndex);
        mAdapter.setOnMiniCartListener(this);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        binding.minicartRecyclerView.setLayoutManager(llm);
        binding.minicartRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void updateDataOnProductSelection(int currentIndex, ArrayList<ProductAttribute> products) {
        showSelectedProduct(currentIndex, products);
        mProductList = products;
        if(mOnProductCartListener != null) {
            mOnProductCartListener.updateDataOnProductSelection(currentIndex, products);
            mOnProductCartListener.updateUIOnAddBtn(currentIndex, products.get(currentIndex));
        }
    }



    private void showSelectedProduct(int currentIndex, List<ProductAttribute> products) {
        ProductAttribute currentData = products.get(currentIndex);
        AppUtil.loadImage(getContext(), binding.productImg, currentData.getThumbnail());
        binding.txtTitle.setText(currentData.getTitle());
    }


}
