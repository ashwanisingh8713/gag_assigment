package com.gag.assignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.gag.assignment.BuildConfig;

import java.util.List;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class ProductAttribute implements Parcelable {



    private int selectedCount;
    private String title;
    private double price;
    private String id;

    // Below are from custom attributes
    private String image;
    private String url_key;
    private double special_price;
    private String gift_message_available;
    private String am_recurring_enable;
    private String erp_description;
    private String small_image;
    private String meta_title;
    private String special_from_date;
    private String options_container;
    private String am_subscription_only;
    private String ignore_erp_price;
    private String search_tags;
    private String thumbnail;
    private String ignore_erp_qty;
    private String cost;
    private String erp_loose_item;
    private String erp_item_no;
    private String erp_uom;
    private String msrp_display_actual_price_type;
    private String qty_per_uom;
    private String tax_class_id;
    private String erp_scale_item;
    private String erp_pack_info;
    private String required_options;
    private String ts_packaging_type;
    private String has_options;
    private List<String> category_ids;
    private String new_attr;
    private String country_of_origin;
    private String ts_country_of_origin;
    private String is_promotions;
    private String featured;
    private String best_seller;
    private String manufacturer;
    private String pack_weight_info;
    private String aisle_number;
    private String shop_item;


    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl_key() {
        return url_key;
    }

    public void setUrl_key(String url_key) {
        this.url_key = url_key;
    }

    public double getSpecial_price() {
        return special_price;
    }

    public void setSpecial_price(double special_price) {
        this.special_price = special_price;
    }

    public String getGift_message_available() {
        return gift_message_available;
    }

    public void setGift_message_available(String gift_message_available) {
        this.gift_message_available = gift_message_available;
    }

    public String getAm_recurring_enable() {
        return am_recurring_enable;
    }

    public void setAm_recurring_enable(String am_recurring_enable) {
        this.am_recurring_enable = am_recurring_enable;
    }

    public String getErp_description() {
        return erp_description;
    }

    public void setErp_description(String erp_description) {
        this.erp_description = erp_description;
    }

    public String getSmall_image() {
        return small_image;
    }

    public void setSmall_image(String small_image) {
        this.small_image = small_image;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getSpecial_from_date() {
        return special_from_date;
    }

    public void setSpecial_from_date(String special_from_date) {
        this.special_from_date = special_from_date;
    }

    public String getOptions_container() {
        return options_container;
    }

    public void setOptions_container(String options_container) {
        this.options_container = options_container;
    }

    public String getAm_subscription_only() {
        return am_subscription_only;
    }

    public void setAm_subscription_only(String am_subscription_only) {
        this.am_subscription_only = am_subscription_only;
    }

    public String getIgnore_erp_price() {
        return ignore_erp_price;
    }

    public void setIgnore_erp_price(String ignore_erp_price) {
        this.ignore_erp_price = ignore_erp_price;
    }

    public String getSearch_tags() {
        return search_tags;
    }

    public void setSearch_tags(String search_tags) {
        this.search_tags = search_tags;
    }

    public String getThumbnail() {
        return BuildConfig.IMG_PREFIX+thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIgnore_erp_qty() {
        return ignore_erp_qty;
    }

    public void setIgnore_erp_qty(String ignore_erp_qty) {
        this.ignore_erp_qty = ignore_erp_qty;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getErp_loose_item() {
        return erp_loose_item;
    }

    public void setErp_loose_item(String erp_loose_item) {
        this.erp_loose_item = erp_loose_item;
    }

    public String getErp_item_no() {
        return erp_item_no;
    }

    public void setErp_item_no(String erp_item_no) {
        this.erp_item_no = erp_item_no;
    }

    public String getErp_uom() {
        return erp_uom;
    }

    public void setErp_uom(String erp_uom) {
        this.erp_uom = erp_uom;
    }

    public String getMsrp_display_actual_price_type() {
        return msrp_display_actual_price_type;
    }

    public void setMsrp_display_actual_price_type(String msrp_display_actual_price_type) {
        this.msrp_display_actual_price_type = msrp_display_actual_price_type;
    }

    public String getQty_per_uom() {
        return qty_per_uom;
    }

    public void setQty_per_uom(String qty_per_uom) {
        this.qty_per_uom = qty_per_uom;
    }

    public String getTax_class_id() {
        return tax_class_id;
    }

    public void setTax_class_id(String tax_class_id) {
        this.tax_class_id = tax_class_id;
    }

    public String getErp_scale_item() {
        return erp_scale_item;
    }

    public void setErp_scale_item(String erp_scale_item) {
        this.erp_scale_item = erp_scale_item;
    }

    public String getErp_pack_info() {
        return erp_pack_info;
    }

    public void setErp_pack_info(String erp_pack_info) {
        this.erp_pack_info = erp_pack_info;
    }

    public String getRequired_options() {
        return required_options;
    }

    public void setRequired_options(String required_options) {
        this.required_options = required_options;
    }

    public String getTs_packaging_type() {
        return ts_packaging_type;
    }

    public void setTs_packaging_type(String ts_packaging_type) {
        this.ts_packaging_type = ts_packaging_type;
    }

    public String getHas_options() {
        return has_options;
    }

    public void setHas_options(String has_options) {
        this.has_options = has_options;
    }

    public List<String> getCategory_ids() {
        return category_ids;
    }

    public void setCategory_ids(List<String> category_ids) {
        this.category_ids = category_ids;
    }

    public String getNew_attr() {
        return new_attr;
    }

    public void setNew_attr(String new_attr) {
        this.new_attr = new_attr;
    }

    public String getCountry_of_origin() {
        return country_of_origin;
    }

    public void setCountry_of_origin(String country_of_origin) {
        this.country_of_origin = country_of_origin;
    }

    public String getTs_country_of_origin() {
        return ts_country_of_origin;
    }

    public void setTs_country_of_origin(String ts_country_of_origin) {
        this.ts_country_of_origin = ts_country_of_origin;
    }

    public String getIs_promotions() {
        return is_promotions;
    }

    public void setIs_promotions(String is_promotions) {
        this.is_promotions = is_promotions;
    }

    public String getFeatured() {
        return featured;
    }

    public void setFeatured(String featured) {
        this.featured = featured;
    }

    public String getBest_seller() {
        return best_seller;
    }

    public void setBest_seller(String best_seller) {
        this.best_seller = best_seller;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPack_weight_info() {
        return pack_weight_info;
    }

    public void setPack_weight_info(String pack_weight_info) {
        this.pack_weight_info = pack_weight_info;
    }

    public String getAisle_number() {
        return aisle_number;
    }

    public void setAisle_number(String aisle_number) {
        this.aisle_number = aisle_number;
    }

    public String getShop_item() {
        return shop_item;
    }

    public void setShop_item(String shop_item) {
        this.shop_item = shop_item;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeDouble(this.price);
        dest.writeString(this.id);
        dest.writeString(this.image);
        dest.writeString(this.url_key);
        dest.writeDouble(this.special_price);
        dest.writeString(this.gift_message_available);
        dest.writeString(this.am_recurring_enable);
        dest.writeString(this.erp_description);
        dest.writeString(this.small_image);
        dest.writeString(this.meta_title);
        dest.writeString(this.special_from_date);
        dest.writeString(this.options_container);
        dest.writeString(this.am_subscription_only);
        dest.writeString(this.ignore_erp_price);
        dest.writeString(this.search_tags);
        dest.writeString(this.thumbnail);
        dest.writeString(this.ignore_erp_qty);
        dest.writeString(this.cost);
        dest.writeString(this.erp_loose_item);
        dest.writeString(this.erp_item_no);
        dest.writeString(this.erp_uom);
        dest.writeString(this.msrp_display_actual_price_type);
        dest.writeString(this.qty_per_uom);
        dest.writeString(this.tax_class_id);
        dest.writeString(this.erp_scale_item);
        dest.writeString(this.erp_pack_info);
        dest.writeString(this.required_options);
        dest.writeString(this.ts_packaging_type);
        dest.writeString(this.has_options);
        dest.writeStringList(this.category_ids);
        dest.writeString(this.new_attr);
        dest.writeString(this.country_of_origin);
        dest.writeString(this.ts_country_of_origin);
        dest.writeString(this.is_promotions);
        dest.writeString(this.featured);
        dest.writeString(this.best_seller);
        dest.writeString(this.manufacturer);
        dest.writeString(this.pack_weight_info);
        dest.writeString(this.aisle_number);
        dest.writeString(this.shop_item);
    }

    public void readFromParcel(Parcel source) {
        this.title = source.readString();
        this.price = source.readDouble();
        this.id = source.readString();
        this.image = source.readString();
        this.url_key = source.readString();
        this.special_price = source.readDouble();
        this.gift_message_available = source.readString();
        this.am_recurring_enable = source.readString();
        this.erp_description = source.readString();
        this.small_image = source.readString();
        this.meta_title = source.readString();
        this.special_from_date = source.readString();
        this.options_container = source.readString();
        this.am_subscription_only = source.readString();
        this.ignore_erp_price = source.readString();
        this.search_tags = source.readString();
        this.thumbnail = source.readString();
        this.ignore_erp_qty = source.readString();
        this.cost = source.readString();
        this.erp_loose_item = source.readString();
        this.erp_item_no = source.readString();
        this.erp_uom = source.readString();
        this.msrp_display_actual_price_type = source.readString();
        this.qty_per_uom = source.readString();
        this.tax_class_id = source.readString();
        this.erp_scale_item = source.readString();
        this.erp_pack_info = source.readString();
        this.required_options = source.readString();
        this.ts_packaging_type = source.readString();
        this.has_options = source.readString();
        this.category_ids = source.createStringArrayList();
        this.new_attr = source.readString();
        this.country_of_origin = source.readString();
        this.ts_country_of_origin = source.readString();
        this.is_promotions = source.readString();
        this.featured = source.readString();
        this.best_seller = source.readString();
        this.manufacturer = source.readString();
        this.pack_weight_info = source.readString();
        this.aisle_number = source.readString();
        this.shop_item = source.readString();
    }

    public ProductAttribute() {
    }

    protected ProductAttribute(Parcel in) {
        this.title = in.readString();
        this.price = in.readDouble();
        this.id = in.readString();
        this.image = in.readString();
        this.url_key = in.readString();
        this.special_price = in.readDouble();
        this.gift_message_available = in.readString();
        this.am_recurring_enable = in.readString();
        this.erp_description = in.readString();
        this.small_image = in.readString();
        this.meta_title = in.readString();
        this.special_from_date = in.readString();
        this.options_container = in.readString();
        this.am_subscription_only = in.readString();
        this.ignore_erp_price = in.readString();
        this.search_tags = in.readString();
        this.thumbnail = in.readString();
        this.ignore_erp_qty = in.readString();
        this.cost = in.readString();
        this.erp_loose_item = in.readString();
        this.erp_item_no = in.readString();
        this.erp_uom = in.readString();
        this.msrp_display_actual_price_type = in.readString();
        this.qty_per_uom = in.readString();
        this.tax_class_id = in.readString();
        this.erp_scale_item = in.readString();
        this.erp_pack_info = in.readString();
        this.required_options = in.readString();
        this.ts_packaging_type = in.readString();
        this.has_options = in.readString();
        this.category_ids = in.createStringArrayList();
        this.new_attr = in.readString();
        this.country_of_origin = in.readString();
        this.ts_country_of_origin = in.readString();
        this.is_promotions = in.readString();
        this.featured = in.readString();
        this.best_seller = in.readString();
        this.manufacturer = in.readString();
        this.pack_weight_info = in.readString();
        this.aisle_number = in.readString();
        this.shop_item = in.readString();
    }

    public static final Parcelable.Creator<ProductAttribute> CREATOR = new Parcelable.Creator<ProductAttribute>() {
        @Override
        public ProductAttribute createFromParcel(Parcel source) {
            return new ProductAttribute(source);
        }

        @Override
        public ProductAttribute[] newArray(int size) {
            return new ProductAttribute[size];
        }
    };
}
