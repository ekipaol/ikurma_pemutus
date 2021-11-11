package com.application.bris.brisi_pemutus.model.lkn;

import com.google.gson.annotations.SerializedName;

public class GracePeriod {
    @SerializedName("ID_GRACE_PERIOD")
    private String ID_GRACE_PERIOD;
    @SerializedName("DESC")
    private String DESC;

    public String getID_GRACE_PERIOD() {
        return ID_GRACE_PERIOD;
    }

    public void setID_GRACE_PERIOD(String ID_GRACE_PERIOD) {
        this.ID_GRACE_PERIOD = ID_GRACE_PERIOD;
    }

    public String getDESC() {
        return DESC;
    }

    public void setDESC(String DESC) {
        this.DESC = DESC;
    }
}
