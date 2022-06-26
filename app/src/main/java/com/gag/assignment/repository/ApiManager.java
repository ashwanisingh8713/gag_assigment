package com.gag.assignment.repository;

import static com.gag.assignment.repository.ProductConstants.*;

import android.util.Log;

import com.gag.assignment.db.CartTable;
import com.gag.assignment.model.ProductAttribute;
import com.gag.assignment.model.CustomAttributesDTO;
import com.gag.assignment.net.ServiceFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ashwanisingh on 25/06/22.
 */

public class ApiManager {

    private static String TAG = "ApiManager";

    private OnResponse mOnResponse;

    public ApiManager(OnResponse onResponse) {
        mOnResponse = onResponse;
    }


    private Observable<ProductAttribute> getProduct(String productId) {
        return ServiceFactory.getServiceAPIs().getProduct(productId)
                .subscribeOn(Schedulers.newThread())
                .map(data -> {
                    final ProductAttribute custAttri = new ProductAttribute();
                    final List<CustomAttributesDTO> custom_attributes = data.getCustom_attributes();
                    custAttri.setId(data.getId());
                    custAttri.setTitle(data.getName());
                    custAttri.setPrice(data.getPrice());
                    for (CustomAttributesDTO dto : custom_attributes) {
                        final String attrCode = dto.getAttribute_code();
                        if (dto.getValue() != null)
                            switch (attrCode) {
                                case image:
                                    custAttri.setImage(dto.getValue().toString());
                                    break;
                                case url_key:
                                    custAttri.setUrl_key(dto.getValue().toString());
                                    break;
                                case special_price:
                                    custAttri.setSpecial_price(new Double(dto.getValue().toString()));
                                    break;
                                case gift_message_available:
                                    custAttri.setGift_message_available(dto.getValue().toString());
                                    break;
                                case am_recurring_enable:
                                    custAttri.setAm_recurring_enable(dto.getValue().toString());
                                    break;
                                case erp_description:
                                    custAttri.setErp_description(dto.getValue().toString());
                                    break;
                                case small_image:
                                    custAttri.setSmall_image(dto.getValue().toString());
                                    break;
                                case meta_title:
                                    custAttri.setMeta_title(dto.getValue().toString());
                                    break;
                                case special_from_date:
                                    custAttri.setSpecial_from_date(dto.getValue().toString());
                                    break;
                                case options_container:
                                    custAttri.setOptions_container(dto.getValue().toString());
                                    break;
                                case am_subscription_only:
                                    custAttri.setAm_subscription_only(dto.getValue().toString());
                                    break;
                                case ignore_erp_price:
                                    custAttri.setIgnore_erp_price(dto.getValue().toString());
                                    break;
                                case search_tags:
                                    custAttri.setSearch_tags(dto.getValue().toString());
                                    break;
                                case thumbnail:
                                    custAttri.setThumbnail(dto.getValue().toString());
                                    break;
                                case ignore_erp_qty:
                                    custAttri.setIgnore_erp_qty(dto.getValue().toString());
                                    break;
                                case cost:
                                    custAttri.setCost(dto.getValue().toString());
                                    break;
                                case erp_loose_item:
                                    custAttri.setErp_loose_item(dto.getValue().toString());
                                    break;
                                case erp_item_no:
                                    custAttri.setErp_item_no(dto.getValue().toString());
                                    break;
                                case erp_uom:
                                    custAttri.setErp_uom(dto.getValue().toString());
                                    break;
                                case msrp_display_actual_price_type:
                                    custAttri.setMsrp_display_actual_price_type(dto.getValue().toString());
                                    break;
                                case qty_per_uom:
                                    custAttri.setQty_per_uom(dto.getValue().toString());
                                    break;
                                case tax_class_id:
                                    custAttri.setTax_class_id(dto.getValue().toString());
                                    break;
                                case erp_scale_item:
                                    custAttri.setErp_scale_item(dto.getValue().toString());
                                    break;
                                case erp_pack_info:
                                    custAttri.setErp_pack_info(dto.getValue().toString());
                                    break;
                                case required_options:
                                    custAttri.setRequired_options(dto.getValue().toString());
                                    break;
                                case ts_packaging_type:
                                    custAttri.setTs_packaging_type(dto.getValue().toString());
                                    break;
                                case has_options:
                                    custAttri.setHas_options(dto.getValue().toString());
                                    break;
                                case category_ids:
                                    custAttri.setCategory_ids(new ArrayList<String>((Collection<String>) dto.getValue()));
                                    break;
                                case new_attr:
                                    custAttri.setNew_attr(dto.getValue().toString());
                                    break;
                                case country_of_origin:
                                    custAttri.setCountry_of_origin(dto.getValue().toString());
                                    break;
                                case ts_country_of_origin:
                                    custAttri.setTs_country_of_origin(dto.getValue().toString());
                                    break;
                                case is_promotions:
                                    custAttri.setIs_promotions(dto.getValue().toString());
                                    break;
                                case featured:
                                    custAttri.setFeatured(dto.getValue().toString());
                                    break;
                                case best_seller:
                                    custAttri.setBest_seller(dto.getValue().toString());
                                    break;
                                case manufacturer:
                                    custAttri.setManufacturer(dto.getValue().toString());
                                    break;
                                case pack_weight_info:
                                    custAttri.setPack_weight_info(dto.getValue().toString());
                                    break;
                                case aisle_number:
                                    custAttri.setAisle_number(dto.getValue().toString());
                                    break;
                                case shop_item:
                                    custAttri.setShop_item(dto.getValue().toString());
                                    break;
                            }
                    }
                    return custAttri;
                }).onErrorReturn(throwable -> new ProductAttribute());
    }

    public void fetchProduct() {
        Observable.zip(getProduct(ProductConstants.Product_ID_Milk_1_Gallon),
                        getProduct(ProductConstants.Product_ID_Milk_500_Ml),
                        DataManager.getCartAllProduct(),
                        (product1, product2, cartProducts) -> combineProductVariety(product1, product2, cartProducts))
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }


    private Observer<List<ProductAttribute>> getObserver() {
        return new Observer<List<ProductAttribute>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<ProductAttribute> productList) {
                if (mOnResponse != null) {
                    mOnResponse.onSuccess(productList);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, " onError : " + e.getMessage());
                if (mOnResponse != null) {
                    mOnResponse.onFailed(e);
                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG, " onComplete");
                if (mOnResponse != null) {
                    mOnResponse.onCompleted();
                }
            }
        };
    }

    public List<ProductAttribute> combineProductVariety(ProductAttribute product1, ProductAttribute product2,
                                                        List<CartTable> cartProducts) {
        List<ProductAttribute> products = new ArrayList<>();

        String product1_id = "";
        String product2_id = "";

        if (product1.getId() != null) {
            product1_id = product1.getId();
            products.add(product1);
        }
        if (product2.getId() != null) {
            product2_id = product2.getId();
            products.add(product2);
        }

        for(CartTable cart: cartProducts) {
            String id = cart.getProductId();
            int selectCount = cart.getSelectedCount();
            if(product1_id.equals(id)) {
                product1.setSelectedCount(selectCount);
            }
            else if(product2_id.equals(id)) {
                product2.setSelectedCount(selectCount);
            }
        }

        return products;
    }



}
