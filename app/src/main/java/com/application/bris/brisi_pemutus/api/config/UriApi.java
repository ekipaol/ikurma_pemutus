package com.application.bris.brisi_pemutus.api.config;

public class UriApi {

    public class Baseurl{
        public static final String URL = "http://10.1.25.55:8080/MobileBRISIAPI/webresources/"; //DEV tanpa rev proxy
//       public static final String URL = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/"; //server prod
    }


    public class pipeline {
        public static final String listPipeline = "generic/mikro/pipeline/listPipeline";
    }
    public class Login {
        public static final String loginStatus = "generic/login";
    }

    public class Dashboard {
        public static final String dashboard = "generic/dashboardPemutus";
    }
    public class getDataCabang {
        public static final String dataCabang = "generic/getDataCabang";
    }
    public class getAomDisposisi {
        public static final String dataAomDisposisi = "generic/referal/brisonline/listAOM";
    }
    public class getAo {
        public static final String dataAo = "generic/mikro/usermanagement/listAOM";
    }
    public class getUh {
        public static final String dataUh = "generic/mikro/usermanagement/listUh";
    }
    public class getPinca {
        public static final String dataPinca = "generic/mikro/usermanagement/listPinca";
    }
    public class getMmm {
        public static final String dataMmm = "generic/mikro/usermanagement/listMmm";
    }
    public class cekBos {
        public static final String cekBos = "generic/mikro/usermanagement/cekPencairanBos";
    }
    public class cekCs {
        public static final String cekCs = "generic/mikro/usermanagement/cekPencairanCs";
    }
    public class cekAdaPutusan {
        public static final String cekAdaPutusan = "generic/mikro/usermanagement/cekAdaPutusanPemutus";
    }
    public class cekAdaCs {
        public static final String cekAdaCs = "generic/mikro/usermanagement/cekAdaCs";
    }
    public class insertUpdateAom {
        public static final String insertUpdateAom = "generic/mikro/usermanagement/updateAOM";
    }
    public class deleteAom {
        public static final String deleteAom = "generic/mikro/usermanagement/deleteAOM";
    }
    public class aktivasiStatusAom {
        public static final String aktivasiStatusAom = "generic/mikro/usermanagement/aktivasiStatusAOM";
    }
    public class deaktivasiStatusAom {
        public static final String deaktivasiStatusAom = "generic/mikro/usermanagement/deaktivasiStatusAOM";
    }
    public class reactivePasswordAom {
        public static final String reactivePasswordAom = "generic/mikro/usermanagement/reactivePasswordAOM";
    }
    public class listHotProspek {
        public static final String listHotProspek = "generic/mikro/hotprospek/listHotProspek";
    }
    public class listPemutus {
        public static final String listPemutus = "generic/pemutus/listPutusan";
    }
    public class listPutusanAkad {
        public static final String listPutusanAkad = "generic/pemutus/listPutusanAkad";
    }
    public class listDeviasi {
        public static final String listDeviasi = "generic/pemutus/listPutusanDeviasi";
    }
    public class listDisposisi {
        public static final String listDisposisi = "generic/referal/brisonline/listDisposisi";
    }
    public class simpanDisposisi {
        public static final String simpanDisposisi = "generic/referal/brisonline/simpanDisposisi";
    }
    public class pemutusSetuju {
        public static final String pemutusSetuju = "generic/pemutus/pemutusSetuju";
    }
    public class pemutusTolak {
        public static final String pemutusTolak = "generic/pemutus/pemutusTolak";
    }
    public class pemutusKembalikan {
        public static final String pemutusKembalikan = "generic/pemutus/pemutusKembalikan";
    }
    public class inquiryDataLengkap {
        public static final String inquiryDataLengkap = "generic/mikro/hotprospek/datalengkap/inquireDataLengkap";
    }
    public class inquiryHistory {
        public static final String inquiryHistory = "generic/mikro/hotprospek/history/history";
    }
    public class inquirySektorEkonomi {
        public static final String inquirySektorEkonomi = "generic/mikro/hotprospek/datapembiayaan/inquireDataPembiayaan";
    }
    public class inquiryKelengkapanDokumen {
        public static final String inquiryKelengkapanDokumen = "generic/mikro/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
    }
    public class inquiryPrescreening {
        public static final String inquiryPrescreening = "generic/mikro/hotprospek/prescreening/inquirePrescreening";
    }
    public class inquiryRpc {
        public static final String inquiryRpc = "generic/mikro/hotprospek/rpc/inquireRPC";
    }
    public class inquiryAgunan {
        public static final String inquiryAgunan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_2";
    }
    public class inquiryInfoAgunan {
        public static final String inquiryInfoAgunan = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Pengikatan";
    }
    public class deleteAgunan {
        public static final String deleteAgunan= "generic/ mikro/hotprospek/agunan/HapusDataAgunan";
    }
    public class setPengikatan {
        public static final String setPengikatan= "generic/mikro/hotprospek/agunan/inquiryDataAgunan_SetPengikatan";
    }
    public class ikatAgunan {
        public static final String ikatAgunan= "generic/mikro/hotprospek/agunan/SavePengikatan";
    }
    public class jenisKlasifikasi {
        public static final String jenisKlasifikasi= "generic/mikro/hotprospek/agunan/jenisKlasifikasi";
    }
    public class inquireLkn {
        public static final String inquireLkn= "generic/mikro/hotprospek/lkn/inquireLKN";
    }
    public class inquiryScoring {
        public static final String inquiryScoring = "generic/mikro/hotprospek/scoringtanpajaminan/inquireScoring";
    }
    public class listAgunanTerikat {
        public static final String listAgunanTerikat = "generic/mikro/hotprospek/agunan/CariDataAgunan_Pengikatan";
    }
    public class listAgunanTerikatRiwayat {
        public static final String listAgunanTerikatRiwayat = "generic/mikro/hotprospek/agunan/CariDataAgunan_Pengikatan_Riwayat";
    }
    public class uploadImage {
        public static final String uploadImage = "generic/uploadImage";
    }
    public class saveTanahKosong {
        public static final String saveTanahKosong = "generic/mikro/hotprospek/agunan/saveDataAgunan_Tanahkosong";
    }
    public class inquiryTanahKosong {
        public static final String inquiryTanahKosong = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Tanahkosong";
    }
    public class inquiryAgunanDeposito {
        public static final String inquiryAgunanDeposito = "generic/mikro/hotprospek/agunan/inquiryDataAgunan_Deposito";
    }
    public class inquiryAgunanKios {
        public static final String inquiryAgunanKios = " generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kios";
    }

    public class inquiryAgunanKendaraan {
        public static final String inquiryAgunanKendaraan = " generic/mikro/hotprospek/agunan/inquiryDataAgunan_Kendaraan";
    }

    public class cekUserMip {
        public static final String cekUserMip = " generic/mikro/usermanagement/cekUserMip";
    }

    public class activation {
        public static final String activation = "generic/aktivasi/activate";
    }

    public class checkUpdate {
        public static final String checkUpdate = "generic/getAppVersion";
    }

    public class updateFirebase {
        public static final String updateFirebase = "generic/aktivasi/updateFirebaseMessagingID";
    }







    public class foto {
        public static final String urlFoto = "generic/getImage/";
    }

    public class fotoProfil {
        public static final String urlFotoProfil = "generic/getImageProfile/";
    }














}