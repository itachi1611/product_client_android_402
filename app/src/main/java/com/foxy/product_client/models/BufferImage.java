
package com.foxy.product_client.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BufferImage {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("data")
    @Expose
    private List<Integer> data = null;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }

}
