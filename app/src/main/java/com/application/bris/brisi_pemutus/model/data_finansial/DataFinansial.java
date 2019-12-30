package com.application.bris.brisi_pemutus.model.data_finansial;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataFinansial {

    @Expose
    private String oMZETUSAHABERSIHPERBULAN;
    @SerializedName("OMZET_USAHA_SETELAH_POTONGAN_PERBULAN")
    @Expose
    private String oMZETUSAHASETELAHPOTONGANPERBULAN;
    @SerializedName("PROSENTASE_UANG_MUKA")
    @Expose
    private String pROSENTASEUANGMUKA;
    @SerializedName("ID_PRESCORING")
    @Expose
    private String iDPRESCORING;
    @SerializedName("BIAYA_RUMAH_TANGGA")
    @Expose
    private String bIAYARUMAHTANGGA;
    @SerializedName("BIAYA_LAIN_LAIN")
    @Expose
    private String bIAYALAINLAIN;
    @SerializedName("LOAN_TO_VALUE")
    @Expose
    private String lOANTOVALUE;
    @SerializedName("FTV")
    @Expose
    private String fTV;
    @SerializedName("NET_WORTH")
    @Expose
    private String nETWORTH;
    @SerializedName("PLAFOND_YANG_DIUSULKAN")
    @Expose
    private String pLAFONDYANGDIUSULKAN;
    @SerializedName("PERMOHONAN_KREDIT")
    @Expose
    private String pERMOHONANKREDIT;
    @SerializedName("ANGSURAN")
    @Expose
    private String aNGSURAN;
    @SerializedName("DEVIASI_DIR")
    @Expose
    private String dEVIASIDIR;
    @SerializedName("BESAR_UANG_MUKA")
    @Expose
    private String bESARUANGMUKA;
    @SerializedName("SUKU_BUNGA")
    @Expose
    private String sUKUBUNGA;
    @SerializedName("MAKS_TENOR_MPP")
    @Expose
    private String mAKSTENORMPP;
    @SerializedName("SUKU_COUNTER")
    @Expose
    private String sUKUCOUNTER;
    @SerializedName("ANGSURAN_USULAN")
    @Expose
    private String aNGSURANUSULAN;
    @SerializedName("BIAYA_TOTAL")
    @Expose
    private String bIAYATOTAL;
    @SerializedName("MAKSIMUM_PLAFOND")
    @Expose
    private String mAKSIMUMPLAFOND;
    @SerializedName("MAKSIMUM_PLAFOND_COUNTER")
    @Expose
    private String mAKSIMUMPLAFONDCOUNTER;
    @SerializedName("PLAFOND_INDUK")
    @Expose
    private String pLAFONDINDUK;
    @SerializedName("POTONGAN_PER_BULAN")
    @Expose
    private String pOTONGANPERBULAN;
    @SerializedName("BIAYA_SEKOLAH_ANAK")
    @Expose
    private String bIAYASEKOLAHANAK;
    @SerializedName("OMZET_PERBULAN")
    @Expose
    private String oMZETPERBULAN;
    @SerializedName("ID_SCORING")
    @Expose
    private String iDSCORING;
    @SerializedName("MAKS_TANGGAL")
    @Expose
    private String mAKSTANGGAL;
    @SerializedName("PENGHASILAN_TETAP_LAIN")
    @Expose
    private String pENGHASILANTETAPLAIN;
    @SerializedName("JANGKA_WAKTU")
    @Expose
    private String jANGKAWAKTU;
    @SerializedName("HARGA_PER_PAKET")
    @Expose
    private String hARGAPERPAKET;
    @SerializedName("ID_TUJUAN")
    @Expose
    private String iDTUJUAN;
    @SerializedName("HARGA_PROPERTY")
    @Expose
    private String hARGAPROPERTY;
    @SerializedName("TANGGAL_LAHIR")
    @Expose
    private String tANGGALLAHIR;
    @SerializedName("JUMLAH_PAKET")
    @Expose
    private String jUMLAHPAKET;
    @SerializedName("NET_MONTHLY_INCOME")
    @Expose
    private String nETMONTHLYINCOME;
    @SerializedName("USIA_MPP")
    @Expose
    private String uSIAMPP;
    @SerializedName("MIN_PLAFON_BERAGUNAN")
    @Expose
    private String mINPLAFONBERAGUNAN;
    @SerializedName("MAX_RPC")
    @Expose
    private String mAXRPC;
    @SerializedName("MAX_PLAFON_INSTANSI")
    @Expose
    private String mAXPLAFONINSTANSI;
    @SerializedName("FID_INSTANSI")
    @Expose
    private String fIDINSTANSI;
    @SerializedName("MAX_JW_4")
    @Expose
    private String mAXJW4;
    @SerializedName("MAX_JW_3")
    @Expose
    private String mAXJW3;
    @SerializedName("MAX_JW_2")
    @Expose
    private String mAXJW2;
    @SerializedName("MAX_JW_1")
    @Expose
    private String mAXJW1;
    @SerializedName("SISA_PLAFOND_INSTANSI")
    @Expose
    private String sISAPLAFONDINSTANSI;
    @SerializedName("KATEGORI")
    @Expose
    private String kATEGORI;
    @SerializedName("MARGIN_RATE_1")
    @Expose
    private String mARGINRATE1;
    @SerializedName("MAX_PLAFON_INDIVIDUAL")
    @Expose
    private String mAXPLAFONINDIVIDUAL;
    @SerializedName("MARGIN_RATE_4")
    @Expose
    private String mARGINRATE4;
    @SerializedName("MARGIN_RATE_3")
    @Expose
    private String mARGINRATE3;
    @SerializedName("MARGIN_RATE_2")
    @Expose
    private String mARGINRATE2;

    public String getMINPLAFONBERAGUNAN() {
        return mINPLAFONBERAGUNAN;
    }

    public void setMINPLAFONBERAGUNAN(String mINPLAFONBERAGUNAN) {
        this.mINPLAFONBERAGUNAN = mINPLAFONBERAGUNAN;
    }

    public String getMAXRPC() {
        return mAXRPC;
    }

    public void setMAXRPC(String mAXRPC) {
        this.mAXRPC = mAXRPC;
    }

    public String getMAXPLAFONINSTANSI() {
        return mAXPLAFONINSTANSI;
    }

    public void setMAXPLAFONINSTANSI(String mAXPLAFONINSTANSI) {
        this.mAXPLAFONINSTANSI = mAXPLAFONINSTANSI;
    }

    public String getFIDINSTANSI() {
        return fIDINSTANSI;
    }

    public void setFIDINSTANSI(String fIDINSTANSI) {
        this.fIDINSTANSI = fIDINSTANSI;
    }

    public String getMAXJW4() {
        return mAXJW4;
    }

    public void setMAXJW4(String mAXJW4) {
        this.mAXJW4 = mAXJW4;
    }

    public String getMAXJW3() {
        return mAXJW3;
    }

    public void setMAXJW3(String mAXJW3) {
        this.mAXJW3 = mAXJW3;
    }

    public String getMAXJW2() {
        return mAXJW2;
    }

    public void setMAXJW2(String mAXJW2) {
        this.mAXJW2 = mAXJW2;
    }

    public String getMAXJW1() {
        return mAXJW1;
    }

    public void setMAXJW1(String mAXJW1) {
        this.mAXJW1 = mAXJW1;
    }

    public String getSISAPLAFONDINSTANSI() {
        return sISAPLAFONDINSTANSI;
    }

    public void setSISAPLAFONDINSTANSI(String sISAPLAFONDINSTANSI) {
        this.sISAPLAFONDINSTANSI = sISAPLAFONDINSTANSI;
    }

    public String getKATEGORI() {
        return kATEGORI;
    }

    public void setKATEGORI(String kATEGORI) {
        this.kATEGORI = kATEGORI;
    }

    public String getMARGINRATE1() {
        return mARGINRATE1;
    }

    public void setMARGINRATE1(String mARGINRATE1) {
        this.mARGINRATE1 = mARGINRATE1;
    }

    public String getMAXPLAFONINDIVIDUAL() {
        return mAXPLAFONINDIVIDUAL;
    }

    public void setMAXPLAFONINDIVIDUAL(String mAXPLAFONINDIVIDUAL) {
        this.mAXPLAFONINDIVIDUAL = mAXPLAFONINDIVIDUAL;
    }

    public String getMARGINRATE4() {
        return mARGINRATE4;
    }

    public void setMARGINRATE4(String mARGINRATE4) {
        this.mARGINRATE4 = mARGINRATE4;
    }

    public String getMARGINRATE3() {
        return mARGINRATE3;
    }

    public void setMARGINRATE3(String mARGINRATE3) {
        this.mARGINRATE3 = mARGINRATE3;
    }

    public String getMARGINRATE2() {
        return mARGINRATE2;
    }

    public void setMARGINRATE2(String mARGINRATE2) {
        this.mARGINRATE2 = mARGINRATE2;
    }

    public String getOMZETUSAHABERSIHPERBULAN() {
        return oMZETUSAHABERSIHPERBULAN;
    }

    public void setOMZETUSAHABERSIHPERBULAN(String oMZETUSAHABERSIHPERBULAN) {
        this.oMZETUSAHABERSIHPERBULAN = oMZETUSAHABERSIHPERBULAN;
    }

    public String getOMZETUSAHASETELAHPOTONGANPERBULAN() {
        return oMZETUSAHASETELAHPOTONGANPERBULAN;
    }

    public void setOMZETUSAHASETELAHPOTONGANPERBULAN(String oMZETUSAHASETELAHPOTONGANPERBULAN) {
        this.oMZETUSAHASETELAHPOTONGANPERBULAN = oMZETUSAHASETELAHPOTONGANPERBULAN;
    }

    public String getPROSENTASEUANGMUKA() {
        return pROSENTASEUANGMUKA;
    }

    public void setPROSENTASEUANGMUKA(String pROSENTASEUANGMUKA) {
        this.pROSENTASEUANGMUKA = pROSENTASEUANGMUKA;
    }

    public String getIDPRESCORING() {
        return iDPRESCORING;
    }

    public void setIDPRESCORING(String iDPRESCORING) {
        this.iDPRESCORING = iDPRESCORING;
    }

    public String getBIAYARUMAHTANGGA() {
        return bIAYARUMAHTANGGA;
    }

    public void setBIAYARUMAHTANGGA(String bIAYARUMAHTANGGA) {
        this.bIAYARUMAHTANGGA = bIAYARUMAHTANGGA;
    }

    public String getBIAYALAINLAIN() {
        return bIAYALAINLAIN;
    }

    public void setBIAYALAINLAIN(String bIAYALAINLAIN) {
        this.bIAYALAINLAIN = bIAYALAINLAIN;
    }

    public String getLOANTOVALUE() {
        return lOANTOVALUE;
    }

    public void setLOANTOVALUE(String lOANTOVALUE) {
        this.lOANTOVALUE = lOANTOVALUE;
    }

    public String getFTV() {
        return fTV;
    }

    public void setFTV(String fTV) {
        this.fTV = fTV;
    }

    public String getNETWORTH() {
        return nETWORTH;
    }

    public void setNETWORTH(String nETWORTH) {
        this.nETWORTH = nETWORTH;
    }

    public String getPLAFONDYANGDIUSULKAN() {
        return pLAFONDYANGDIUSULKAN;
    }

    public void setPLAFONDYANGDIUSULKAN(String pLAFONDYANGDIUSULKAN) {
        this.pLAFONDYANGDIUSULKAN = pLAFONDYANGDIUSULKAN;
    }

    public String getPERMOHONANKREDIT() {
        return pERMOHONANKREDIT;
    }

    public void setPERMOHONANKREDIT(String pERMOHONANKREDIT) {
        this.pERMOHONANKREDIT = pERMOHONANKREDIT;
    }

    public String getANGSURAN() {
        return aNGSURAN;
    }

    public void setANGSURAN(String aNGSURAN) {
        this.aNGSURAN = aNGSURAN;
    }

    public String getDEVIASIDIR() {
        return dEVIASIDIR;
    }

    public void setDEVIASIDIR(String dEVIASIDIR) {
        this.dEVIASIDIR = dEVIASIDIR;
    }

    public String getBESARUANGMUKA() {
        return bESARUANGMUKA;
    }

    public void setBESARUANGMUKA(String bESARUANGMUKA) {
        this.bESARUANGMUKA = bESARUANGMUKA;
    }

    public String getSUKUBUNGA() {
        return sUKUBUNGA;
    }

    public void setSUKUBUNGA(String sUKUBUNGA) {
        this.sUKUBUNGA = sUKUBUNGA;
    }

    public String getMAKSTENORMPP() {
        return mAKSTENORMPP;
    }

    public void setMAKSTENORMPP(String mAKSTENORMPP) {
        this.mAKSTENORMPP = mAKSTENORMPP;
    }

    public String getSUKUCOUNTER() {
        return sUKUCOUNTER;
    }

    public void setSUKUCOUNTER(String sUKUCOUNTER) {
        this.sUKUCOUNTER = sUKUCOUNTER;
    }

    public String getANGSURANUSULAN() {
        return aNGSURANUSULAN;
    }

    public void setANGSURANUSULAN(String aNGSURANUSULAN) {
        this.aNGSURANUSULAN = aNGSURANUSULAN;
    }

    public String getBIAYATOTAL() {
        return bIAYATOTAL;
    }

    public void setBIAYATOTAL(String bIAYATOTAL) {
        this.bIAYATOTAL = bIAYATOTAL;
    }

    public String getMAKSIMUMPLAFOND() {
        return mAKSIMUMPLAFOND;
    }

    public void setMAKSIMUMPLAFOND(String mAKSIMUMPLAFOND) {
        this.mAKSIMUMPLAFOND = mAKSIMUMPLAFOND;
    }

    public String getMAKSIMUMPLAFONDCOUNTER() {
        return mAKSIMUMPLAFONDCOUNTER;
    }

    public void setMAKSIMUMPLAFONDCOUNTER(String mAKSIMUMPLAFONDCOUNTER) {
        this.mAKSIMUMPLAFONDCOUNTER = mAKSIMUMPLAFONDCOUNTER;
    }

    public String getPLAFONDINDUK() {
        return pLAFONDINDUK;
    }

    public void setPLAFONDINDUK(String pLAFONDINDUK) {
        this.pLAFONDINDUK = pLAFONDINDUK;
    }

    public String getPOTONGANPERBULAN() {
        return pOTONGANPERBULAN;
    }

    public void setPOTONGANPERBULAN(String pOTONGANPERBULAN) {
        this.pOTONGANPERBULAN = pOTONGANPERBULAN;
    }

    public String getBIAYASEKOLAHANAK() {
        return bIAYASEKOLAHANAK;
    }

    public void setBIAYASEKOLAHANAK(String bIAYASEKOLAHANAK) {
        this.bIAYASEKOLAHANAK = bIAYASEKOLAHANAK;
    }

    public String getOMZETPERBULAN() {
        return oMZETPERBULAN;
    }

    public void setOMZETPERBULAN(String oMZETPERBULAN) {
        this.oMZETPERBULAN = oMZETPERBULAN;
    }

    public String getIDSCORING() {
        return iDSCORING;
    }

    public void setIDSCORING(String iDSCORING) {
        this.iDSCORING = iDSCORING;
    }

    public String getMAKSTANGGAL() {
        return mAKSTANGGAL;
    }

    public void setMAKSTANGGAL(String mAKSTANGGAL) {
        this.mAKSTANGGAL = mAKSTANGGAL;
    }

    public String getPENGHASILANTETAPLAIN() {
        return pENGHASILANTETAPLAIN;
    }

    public void setPENGHASILANTETAPLAIN(String pENGHASILANTETAPLAIN) {
        this.pENGHASILANTETAPLAIN = pENGHASILANTETAPLAIN;
    }

    public String getJANGKAWAKTU() {
        return jANGKAWAKTU;
    }

    public void setJANGKAWAKTU(String jANGKAWAKTU) {
        this.jANGKAWAKTU = jANGKAWAKTU;
    }

    public String getHARGAPERPAKET() {
        return hARGAPERPAKET;
    }

    public void setHARGAPERPAKET(String hARGAPERPAKET) {
        this.hARGAPERPAKET = hARGAPERPAKET;
    }

    public String getIDTUJUAN() {
        return iDTUJUAN;
    }

    public void setIDTUJUAN(String iDTUJUAN) {
        this.iDTUJUAN = iDTUJUAN;
    }

    public String getHARGAPROPERTY() {
        return hARGAPROPERTY;
    }

    public void setHARGAPROPERTY(String hARGAPROPERTY) {
        this.hARGAPROPERTY = hARGAPROPERTY;
    }

    public String getTANGGALLAHIR() {
        return tANGGALLAHIR;
    }

    public void setTANGGALLAHIR(String tANGGALLAHIR) {
        this.tANGGALLAHIR = tANGGALLAHIR;
    }

    public String getJUMLAHPAKET() {
        return jUMLAHPAKET;
    }

    public void setJUMLAHPAKET(String jUMLAHPAKET) {
        this.jUMLAHPAKET = jUMLAHPAKET;
    }

    public String getNETMONTHLYINCOME() {
        return nETMONTHLYINCOME;
    }

    public void setNETMONTHLYINCOME(String nETMONTHLYINCOME) {
        this.nETMONTHLYINCOME = nETMONTHLYINCOME;
    }

    public String getUSIAMPP() {
        return uSIAMPP;
    }

    public void setUSIAMPP(String uSIAMPP) {
        this.uSIAMPP = uSIAMPP;
    }
}
