package com.application.bris.brisi_pemutus.model.scoring;

import com.google.gson.annotations.SerializedName;

public class Scoring {
    @SerializedName("KETERGANTUNGAN_PELANGGAN")
    public Long kETERGANTUNGANPELANGGAN;
    @SerializedName("JENIS_PEMBIAYAAN")
    public Long jENISPEMBIAYAAN;
    @SerializedName("LAMA_USAHA")
    public Long lAMAUSAHA;
    @SerializedName("DEVIASI_MIKRO")
    public Long dEVIASIMIKRO;
    @SerializedName("JENIS_PRODUK")
    public Long jENISPRODUK;
    @SerializedName("HUBUNGAN_BANK")
    public Long hUBUNGANBANK;
    @SerializedName("JANGKA_WAKTU")
    public Long jANGKAWAKTU;
    @SerializedName("WILAYAH_PEMASARAN")
    public Long wILAYAHPEMASARAN;
    @SerializedName("pesan")
    public String pesan;
    @SerializedName("RPC_RATIO")
    public Long rPCRATIO;
    @SerializedName("RATIO_AGUNAN")
    public Long ratioAgunan;
    @SerializedName("REPUTASI_USAHA")
    public Long rEPUTASIUSAHA;
    @SerializedName("hasil")
    public String hasil;
    @SerializedName("KETERGANTUNGAN_SUPPLIER")
    public Long kETERGANTUNGANSUPPLIER;
    @SerializedName("PROSPEK_USAHA")
    public Long pROSPEKUSAHA;

    public Scoring(Long kETERGANTUNGANPELANGGAN, Long jENISPEMBIAYAAN, Long lAMAUSAHA, Long dEVIASIMIKRO, Long jENISPRODUK, Long hUBUNGANBANK, Long jANGKAWAKTU, Long wILAYAHPEMASARAN, String pesan, Long rPCRATIO,Long ratioAgunan, Long rEPUTASIUSAHA, String hasil, Long kETERGANTUNGANSUPPLIER, Long pROSPEKUSAHA) {
        this.kETERGANTUNGANPELANGGAN = kETERGANTUNGANPELANGGAN;
        this.jENISPEMBIAYAAN = jENISPEMBIAYAAN;
        this.lAMAUSAHA = lAMAUSAHA;
        this.dEVIASIMIKRO = dEVIASIMIKRO;
        this.jENISPRODUK = jENISPRODUK;
        this.hUBUNGANBANK = hUBUNGANBANK;
        this.jANGKAWAKTU = jANGKAWAKTU;
        this.wILAYAHPEMASARAN = wILAYAHPEMASARAN;
        this.pesan = pesan;
        this.rPCRATIO = rPCRATIO;
        this.rEPUTASIUSAHA = rEPUTASIUSAHA;
        this.hasil = hasil;
        this.kETERGANTUNGANSUPPLIER = kETERGANTUNGANSUPPLIER;
        this.pROSPEKUSAHA = pROSPEKUSAHA;
        this.ratioAgunan=ratioAgunan;
    }

    public Long getRatioAgunan() {
        return ratioAgunan;
    }

    public Long getkETERGANTUNGANPELANGGAN() {
        return kETERGANTUNGANPELANGGAN;
    }

    public Long getjENISPEMBIAYAAN() {
        return jENISPEMBIAYAAN;
    }

    public Long getlAMAUSAHA() {
        return lAMAUSAHA;
    }

    public Long getdEVIASIMIKRO() {
        return dEVIASIMIKRO;
    }

    public Long getjENISPRODUK() {
        return jENISPRODUK;
    }

    public Long gethUBUNGANBANK() {
        return hUBUNGANBANK;
    }

    public Long getjANGKAWAKTU() {
        return jANGKAWAKTU;
    }

    public Long getwILAYAHPEMASARAN() {
        return wILAYAHPEMASARAN;
    }

    public String getPesan() {
        return pesan;
    }

    public Long getrPCRATIO() {
        return rPCRATIO;
    }

    public Long getrEPUTASIUSAHA() {
        return rEPUTASIUSAHA;
    }

    public String getHasil() {
        return hasil;
    }

    public Long getkETERGANTUNGANSUPPLIER() {
        return kETERGANTUNGANSUPPLIER;
    }

    public Long getpROSPEKUSAHA() {
        return pROSPEKUSAHA;
    }
}