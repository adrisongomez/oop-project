package com.oopecommerce.models.inventory;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_locations")
public class InventoryLocation {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "warehouse_code")
    private String warehouseCode;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    protected InventoryLocation() {
        // Required by Hibernate
    }

    public InventoryLocation(UUID id, String warehouseCode, String streetAddress, String city, String country) {
        this.id = id;
        this.warehouseCode = warehouseCode;
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
    }

    public InventoryLocation(String warehouseCode, String streetAddress, String city, String country) {
        this(UUID.randomUUID(), warehouseCode, streetAddress, city, country);
    }

    public UUID getId() {
        return id;
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
