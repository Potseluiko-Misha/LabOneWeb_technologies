package org.example.entity;

public class Part {
    private int partId;
    private String name;

    public Part(int partId, String name) {
        this.partId = partId;
        this.name = name;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Part [partId=" + partId + ", name" + name + " ]";
    }
}