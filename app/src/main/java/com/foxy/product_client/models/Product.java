package com.foxy.product_client.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("local_image")
    @Expose
    private String localImage;
    @SerializedName("buffer_image")
    @Expose
    private BufferImage bufferImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocalImage() {
        return localImage;
    }

    public void setLocalImage(String localImage) {
        this.localImage = localImage;
    }

    public BufferImage getBufferImage() {
        return bufferImage;
    }

    public void setBufferImage(BufferImage bufferImage) {
        this.bufferImage = bufferImage;
    }


}
