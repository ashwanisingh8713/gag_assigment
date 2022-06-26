package com.gag.assignment.model;

import java.util.List;

/**
 * Created by ashwanisingh on 25/06/22.
 */

public class ExtensionAttributesDTO {
    private List<Integer> website_ids;
    private List<CategoryLinksDTO> category_links;

    public List<Integer> getWebsite_ids() {
        return website_ids;
    }

    public void setWebsite_ids(List<Integer> website_ids) {
        this.website_ids = website_ids;
    }

    public List<CategoryLinksDTO> getCategory_links() {
        return category_links;
    }

    public void setCategory_links(List<CategoryLinksDTO> category_links) {
        this.category_links = category_links;
    }


}