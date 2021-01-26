package com.application.bris.brisi_pemutus.model.sektor_ekonomi_kpr;

import com.google.gson.annotations.SerializedName;

public class CodeDesc {
    @SerializedName("DESC1")
    private String desc1;
    @SerializedName("DESC2")
    private String desc2;

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }
}
