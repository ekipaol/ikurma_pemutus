package com.application.bris.brisi_pemutus.model.ao_silang_list_user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class ListUser extends RealmObject {

    @SerializedName("fid_JHOOFF")
    @Expose
    private String fid_JHOOFF;
    @SerializedName("LIMIT_MIKRO")
    @Expose
    private String LIMIT_MIKRO;
    @SerializedName("LIMIT_BAWAH_KKB")
    @Expose
    private String LIMIT_BAWAH_KKB;
    @SerializedName("SECRET_CODE")
    @Expose
    private String SECRET_CODE;
    @SerializedName("NAMA_PEGAWAI")
    @Expose
    private String NAMA_PEGAWAI;
    @SerializedName("IMEI")
    @Expose
    private String IMEI;
    @SerializedName("USER_NAME")
    @Expose
    private String USER_NAME;
    @SerializedName("LIMIT_KPR")
    @Expose
    private String LIMIT_KPR;
    @SerializedName("KODE_CABANG")
    @Expose
    private String KODE_CABANG;
    @SerializedName("UID")
    @Expose
    private String UID;
    @SerializedName("LIMIT_BAWAH_KPR")
    @Expose
    private String LIMIT_BAWAH_KPR;
    @SerializedName("NO_PEGAWAI")
    @Expose
    private String NO_PEGAWAI;
    @SerializedName("PASSWORD")
    @Expose
    private String PASSWORD;
    @SerializedName("FID_ROLE")
    @Expose
    private String FID_ROLE;
    @SerializedName("limit")
    @Expose
    private String limit;
    @SerializedName("SK")
    @Expose
    private String SK;
    @SerializedName("LIMIT_KKB")
    @Expose
    private String LIMIT_KKB;
    @SerializedName("status")
    @Expose
    private String status;

    public ListUser(){
        super();
    }

    public String getFid_JHOOFF() {
        return fid_JHOOFF;
    }

    public String getLIMIT_MIKRO() {
        return LIMIT_MIKRO;
    }

    public String getLIMIT_BAWAH_KKB() {
        return LIMIT_BAWAH_KKB;
    }

    public String getSECRET_CODE() {
        return SECRET_CODE;
    }

    public String getNAMA_PEGAWAI() {
        return NAMA_PEGAWAI;
    }

    public String getIMEI() {
        return IMEI;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public String getLIMIT_KPR() {
        return LIMIT_KPR;
    }

    public String getKODE_CABANG() {
        return KODE_CABANG;
    }

    public String getUID() {
        return UID;
    }

    public String getLIMIT_BAWAH_KPR() {
        return LIMIT_BAWAH_KPR;
    }

    public String getNO_PEGAWAI() {
        return NO_PEGAWAI;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public String getFID_ROLE() {
        return FID_ROLE;
    }

    public String getLimit() {
        return limit;
    }

    public String getSK() {
        return SK;
    }

    public String getLIMIT_KKB() {
        return LIMIT_KKB;
    }

    public String getStatus() {
        return status;
    }

    public void setFid_JHOOFF(String fid_JHOOFF) {
        this.fid_JHOOFF = fid_JHOOFF;
    }

    public void setLIMIT_MIKRO(String LIMIT_MIKRO) {
        this.LIMIT_MIKRO = LIMIT_MIKRO;
    }

    public void setLIMIT_BAWAH_KKB(String LIMIT_BAWAH_KKB) {
        this.LIMIT_BAWAH_KKB = LIMIT_BAWAH_KKB;
    }

    public void setSECRET_CODE(String SECRET_CODE) {
        this.SECRET_CODE = SECRET_CODE;
    }

    public void setNAMA_PEGAWAI(String NAMA_PEGAWAI) {
        this.NAMA_PEGAWAI = NAMA_PEGAWAI;
    }

    public void setIMEI(String IMEI) {
        this.IMEI = IMEI;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public void setLIMIT_KPR(String LIMIT_KPR) {
        this.LIMIT_KPR = LIMIT_KPR;
    }

    public void setKODE_CABANG(String KODE_CABANG) {
        this.KODE_CABANG = KODE_CABANG;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public void setLIMIT_BAWAH_KPR(String LIMIT_BAWAH_KPR) {
        this.LIMIT_BAWAH_KPR = LIMIT_BAWAH_KPR;
    }

    public void setNO_PEGAWAI(String NO_PEGAWAI) {
        this.NO_PEGAWAI = NO_PEGAWAI;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setFID_ROLE(String FID_ROLE) {
        this.FID_ROLE = FID_ROLE;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setSK(String SK) {
        this.SK = SK;
    }

    public void setLIMIT_KKB(String LIMIT_KKB) {
        this.LIMIT_KKB = LIMIT_KKB;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
