package com.oopecommerce.models.products;

import java.util.List;
import java.util.UUID;

public class DigitalProduct extends Product {

    private String fileFormat;
    private double fileSize; // in MB
    private String storage;
    private String downloadUrl;

    public DigitalProduct(UUID id, String name, String description, ProductStatus status,
                          List<ProductMedia> medias, List<ProductVariant> variants,
                          String fileFormat, double fileSize, String storage) {
        super(id, name, description, status, medias, variants);
        this.fileFormat = fileFormat;
        this.fileSize = fileSize;
        this.storage = storage;
        this.downloadUrl = ""; // Initially empty, generated upon request
    }

    public String generateDownloadLink() {
        // In a real application, this would involve a secure token generation
        this.downloadUrl = this.storage + "/" + this.getId() + "/" + "download";
        return this.downloadUrl;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public boolean equals(DigitalProduct product) {
        return super.equals(product) &&
               this.fileFormat.equals(product.getFileFormat()) &&
               this.fileSize == product.getFileSize() &&
               this.storage.equals(product.getStorage());
    }
} 