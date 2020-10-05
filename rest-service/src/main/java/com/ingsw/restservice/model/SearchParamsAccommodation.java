package com.ingsw.restservice.model;

public class SearchParamsAccommodation {

    private String currentSearchString,currentCategory,currentSubCategory;
    private String orderBy;
    private String direction;
    private String latitude,longitude;
    int currentPage;

    public SearchParamsAccommodation(Builder builder) {
        this.currentCategory=builder.currentCategory;
        this.currentPage =builder.currentPage;
        this.currentSubCategory=builder.currentSubCategory;
        this.currentSearchString=builder.currentSearchString;
        this.direction=builder.direction;
        this.orderBy=builder.orderBy;
        this.latitude=builder.latitude;
        this.longitude=builder.longitude;
    }

    public static class Builder {
        private String currentSearchString="",currentCategory="",currentSubCategory="";
        private String orderBy="id";
        private String direction="DESC";
        private String latitude,longitude;
        int currentPage =0;

        public Builder setCurrentSearchString(String currentSearchParam) {
            this.currentSearchString = currentSearchParam;
            return this;
        }

        public Builder setCurrentCategory(String currentCategory) {
            this.currentCategory = currentCategory;
            return this;
        }

        public Builder setCurrentSubCategory(String currentSubCategory) {
            this.currentSubCategory = currentSubCategory;
            return this;
        }

        public Builder setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
            return this;
        }
        public Builder setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }
        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLatitude(String latitude) {
            this.latitude = latitude;
            return this;
        }
        public Builder setLongitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public SearchParamsAccommodation create() {
            return new SearchParamsAccommodation(this);
        }

    }

    public String getCurrentSearchString() {
        return currentSearchString;
    }

    public String getCurrentCategory() {
        return currentCategory;
    }

    public String getCurrentSubCategory() {
        return currentSubCategory;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getDirection() {
        return direction;
    }



}
