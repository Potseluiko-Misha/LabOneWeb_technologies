package org.example.entity;
import java.util.Date;

public class Sale {
    private int saleId;
    private int customerId;
    private int partId;
    private int computerId;
    private int sellerId;
    private Date saleDate;

    public Sale(int saleId, int customerId, int partId, int computerId, int sellerId, Date saleDate) {
        this.saleId = saleId;
        this.customerId = customerId;
        this.partId = partId;
        this.computerId = computerId;
        this.sellerId = sellerId;
        this.saleDate = saleDate;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public int getComputerId() {
        return computerId;
    }

    public void setComputerId(int computerId) {
        this.computerId = computerId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public java.sql.Date getSaleDate() {
        return (java.sql.Date) saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "Sale [saleId=" + saleId + ", customerId=" + customerId + ", partId=" + partId + ", computerId=" + computerId
                + ", sellerId=" + sellerId + ", saleDate=" + saleDate + "]";
    }
}