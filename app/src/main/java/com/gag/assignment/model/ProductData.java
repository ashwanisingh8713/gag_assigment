package com.gag.assignment.model;

import java.util.List;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class ProductData {


    private String id;
    private String sku;
    private String name;
    private int attribute_set_id;
    private double price;
    private int status;
    private int visibility;
    private String type_id;
    private String created_at;
    private String updated_at;
    private ExtensionAttributesDTO extension_attributes;
    private List<MediaGalleryEntriesDTO> media_gallery_entries;
    private List<CustomAttributesDTO> custom_attributes;

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public int getAttribute_set_id() {
        return attribute_set_id;
    }

    public double getPrice() {
        return price;
    }

    public int getStatus() {
        return status;
    }

    public int getVisibility() {
        return visibility;
    }

    public String getType_id() {
        return type_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public ExtensionAttributesDTO getExtension_attributes() {
        return extension_attributes;
    }

    public void setExtension_attributes(ExtensionAttributesDTO extension_attributes) {
        this.extension_attributes = extension_attributes;
    }

    public List<MediaGalleryEntriesDTO> getMedia_gallery_entries() {
        return media_gallery_entries;
    }

    public List<CustomAttributesDTO> getCustom_attributes() {
        return custom_attributes;
    }


}
