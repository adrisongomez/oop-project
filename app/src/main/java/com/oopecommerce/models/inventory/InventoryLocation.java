package com.oopecommerce.models.inventory;

public class InventoryLocation {
    private String warehouseCode;
    private String streetAddress;
    private String city;
    private String country;

    public InventoryLocation(String warehouseCode, String streetAddress, String city, String country) {
        this.warehouseCode = warehouseCode;
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "InventoryLocation{" +
                "warehouseCode='" + warehouseCode + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public boolean equals(InventoryLocation other) {
        if (other == null) return false;
        return this.warehouseCode.equals(other.warehouseCode) &&
               this.streetAddress.equals(other.streetAddress) &&
               this.city.equals(other.city) &&
               this.country.equals(other.country);
    }
} 