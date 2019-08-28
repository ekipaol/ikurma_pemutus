package com.application.bris.brisi_pemutus.api.model.request.agunan_tanah_kosong;

import com.google.gson.annotations.SerializedName;

public class ReqAgunanTanahKosong {

        @SerializedName("idAgunan")
        private int idAgunan;

        @SerializedName("idApl")
        private int idApl;

    @SerializedName("idCif")
    private int idCif;

    public int getIdCif() {
        return idCif;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }

    public int getIdAgunan() {
            return idAgunan;
        }

        public void setIdAgunan(int idAgunan) {
            this.idAgunan = idAgunan;
        }

        public int getIdApl() {
            return idApl;
        }

        public void setIdApl(int idApl) {
            this.idApl = idApl;
        }

}
