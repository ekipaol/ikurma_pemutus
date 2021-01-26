package com.application.bris.brisi_pemutus.api.config;

import com.application.bris.brisi_pemutus.BuildConfig;

public class UriApi {

    public static class Baseurl{
        public static final String URLDEV = "http://10.1.25.55:8080/MobileBRISIAPI-EKI/webresources/"; //DEV
        public static final String URLPROD = "https://intel.brisyariah.co.id:55056/MobileBRISIAPI/webresources/"; //PROD

        public static String URL = (BuildConfig.IS_PRODUCTION) ? URLPROD : URLDEV ; //ENV BASED URI SELECTOR


    }


    public class pipeline {
        public static final String listPipeline = "generic/mikro/pipeline/listPipeline";
    }
    public class Login {
        public static final String loginStatus = "generic/login";
    }

    public class SecretLogin {
        public static final String secretLogin = "generic/loginn";
    }

    public class Dashboard {
        public static final String dashboard = "generic/dashboardPemutus";
    }
    public class DashboardPemrakarsa {
        public static final String dashboardPemrakarsa = "generic/dashboard";
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
    public class getPincaLengkap {
        public static final String dataPincaLengkap = "generic/mikro/usermanagement/listPincaLengkap";
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
    public class cekAdp {
        public static final String cekAdp = "generic/mikro/usermanagement/cekPencairanAdp";
    }
    public class cekMo {
        public static final String cekMo = "generic/mikro/usermanagement/cekPencairanMo";
    }
    public class cekAdaPutusan {
        public static final String cekAdaPutusan = "generic/mikro/usermanagement/cekAdaPutusanPemutus";
    }
    public class cekAdaCs {
        public static final String cekAdaCs = "generic/mikro/usermanagement/cekAdaCs";
    }
    public class cekAdaAdp {
        public static final String cekAdaAdp = "generic/mikro/usermanagement/cekAdaAdp";
    }
    public class insertUpdateAom {
        public static final String insertUpdateAom = "generic/mikro/usermanagement/updateAOM";
    }
    public class listAppraisal {
        public static final String listAppraisal = "  generic/pemutus/listPermintaanApraisal";
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
    public class listPutusanCair {
        public static final String listPutusanCair = "generic/pemutus/listPutusanCair";
    }
    public class listPutusanDitolak {
        public static final String listPutusanDitolak = "generic/pemutus/listPutusanDitolak";
    }
    public class listPutusanSetuju {
        public static final String listPutusanSetuju = "generic/pemutus/listPutusanSetuju";
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
    public class brisNotif {
        public static final String brisnotifRegister = "generic/brisnotifRegister";
    }
    public class downloadSlik {
        public static final String downloadSlik = "generic/mikro/hotprospek/prescreening/downloadSLIK";
    }
    public class downloadSlikPasangan {
        public static final String downloadSlikPasangan = "generic/mikro/hotprospek/prescreening/downloadSLIKPasangan";
    }
    public class inquiryDetailSlik {
        public static final String inquiryDetailSlik = "generic/mikro/hotprospek/memosales/inquiryMemosales";
    }
    public class getRankingTotal {
        public static final String getRankingTotal = "generic/getRankingTotal";
    }

    public class getPerformanceCabang {
        public static final String getPerformanceCabang = "generic/getRankingCabang";
    }
    public class performanceAo {
        public static final String performanceAo = "generic/pemutus/performanceAo";
    }
    public class listAmbilAlih {
        public static final String listAmbilAlih = "generic/pemutus/listAmbilAlih";
    }
    public class listUms {
        public static final String listUms = "generic/mikro/usermanagement/listUms";
    }

    //KPR
    public class konsumerKpr{
        public static final String detailHotprospekKpr = "generic/konsumer/kpr/hotprospek/inquireHotprospek";
        public static final String inquiryDataLengkapKpr = "generic/konsumer/kpr/hotprospek/datalengkap/inquireDataLengkap";
        public static final String inquiryPrescreeningKpr = "generic/konsumer/kpr/hotprospek/prescreening/inquirePrescreening";
        public static final String downloadSlikKpr = "generic/konsumer/kpr/hotprospek/prescreening/downloadSLIK";
        public static final String downloadSlikPasanganKpr = "generic/konsumer/kpr/hotprospek/prescreening/downloadSLIKPasangan";
        public static final String inquiryRemaksSlikKpr = "generic/konsumer/kpr/hotprospek/memosales/inquiryMemosales";
        public static final String inquiryDataFinansialKpr = "generic/konsumer/kpr/hotprospek/datafinansial/inquiryLoadPrescoring";
        public static final String inquiryKelengkapanDokumenKpr = "generic/konsumer/kpr/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
        public static final String inquirySektorEkonomiKpr = "generic/konsumer/kpr/hotprospek/datapby/inquireDataPembiayaan";
        public static final String inquiryScoringKpr = "generic/konsumer/kpr/hotprospek/scoring/inquireScoring";


    }


    //KONSUMER KMG

    public class inquiryDataLengkapKonsumerKmg {
        public static final String inquiryDataLengkapKonsumerKmg = "generic/konsumer/kmg/hotprospek/datalengkap/inquireDataLengkap";
    }
    public class inquiryDataFinansialKmg {
        public static final String inquiryDataFinansialKmg = "generic/konsumer/kmg/hotprospek/datafinansial/inquiryLoadPrescoring";
    }
    public class validasiDataFinansial {
        public static final String validasiDataFinansial = " generic/konsumer/kmg/hotprospek/datafinansial/validasiPlafond";
    }
    public class inquiryScoringKmg {
        public static final String inquiryScoringKmg = "generic/konsumer/kmg/hotprospek/scoring/inquireScoring";
    }

    public class detailHotprospek {
        public static final String detailHotprospek = "generic/konsumer/kmg/hotprospek/inquireHotprospek";
    }

    public class detailHotprospekMikro {
        public static final String detailHotprospekMikro = "generic/mikro/konsumer/kmg/hotprospek/inquireHotprospek";
    }



    public class getPdf {
        public static final String urlPdf = "generic/getPdf/";
    }

    public class inquiryKelengkapanDokumenKmg {
        public static final String inquiryKelengkapanDokumenKonsumer = "generic/konsumer/kmg/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";
    }
    public class inquirySektorEkonomiKmg {
        public static final String inquirySektorEkonomiKmg = "generic/konsumer/kmg/hotprospek/datapby/inquireDataPembiayaan";
    }

    public class pemutusKembalikanKmg {
        public static final String pemutusKembalikanKmg = "generic/pemutus/konsumer/pemutusKembalikan";
    }

    public class pemutusSetujuKmg {
        public static final String pemutusSetujuKmg = "generic/pemutus/konsumer/pemutusSetuju";
    }

    public class pemutusTolakKmg {
        public static final String pemutusTolakKmg = "generic/pemutus/konsumer/pemutusTolak";
    }

    public class reqAoSilang {
        public static final String listKanwil = "generic/mikro/ukermanagement/listKanwil";
        public static final String listCabang =  "generic/mikro/ukermanagement/listSKK";
        public static final String listRsc = "generic/mikro/ukermanagement/listRSC";
        public static final String listUser = "generic/mikro/usermanagement/listUser";
        public static final String kirimApraisal = "generic/pemutus/apraisalKirimKeApraisal";
        public static final String appraisalKembalikanKeAo = "generic/pemutus/apraisalKembalikanKeAO";


    }

    public class inquiryHistoryKmg {
        public static final String inquiryHistoryKmg = "generic/konsumer/kmg/hotprospek/history/history";
    }

    //KONSUMER MIKRO
    public class inquiryDataLengkapKonsumerMikro {
        public static final String inquiryDataLengkapKonsumerMikro = "generic/mikro/konsumer/kmg/hotprospek/datalengkap/inquireDataLengkap";
    }

    public class multiFaedahMikro {
        public static final String inquiryDataLengkapKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/datalengkap/inquireDataLengkap";

        public static final String inquiryPrescreeningKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/prescreening/inquirePrescreening";

        public static final String inquiryRemaksSlikKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/memosales/inquiryMemosales";

        public static final String inquirySektorEkonomiKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/datapby/inquireDataPembiayaan";

        public static final String inquiryDataFinansialKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/datafinansial/inquiryLoadPrescoring";
        public static final String inquiryScoringKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/scoring/inquireScoring";

        public static final String inquiryKelengkapanDokumenKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/kelengkapandokumen/inquireKelengkapanDokumen";


        public static final String downloadSlikKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/prescreening/downloadSLIK";

        public static final String downloadSlikPasanganKmgMikro = "generic/mikro/konsumer/kmg/hotprospek/prescreening/downloadSLIKPasangan";

    }

    public class monitoring {
        public static final String listMonitoringNasabah = "generic/monitor/listNasabah";
        public static final String listMonitoringAo = "generic/monitor/listTargetKCP";
        public static final String listMonitoringKcp = "generic/monitor/listTargetKC";
        public static final String listMonitoringKp = "generic/monitor/listKP";
        public static final String listMonitoringSalamDigital = "generic/monitor/getMonitoringSalamDigital";
        public static final String getSaldoNasabah = "generic/getSaldoNasabah";
        public static final String getRiwayatMutasi = "generic/monitor/getRiwayatMutasi";
        public static final String rankingAoTop = "generic/monitor/getRankingAoTop";
        public static final String rankingAoBottom = "generic/monitor/getRankingAoWorst";
        public static final String getRataRata = "generic/monitor/getRatarata";
    }

    public class flpp{
        public static final String listHasilPraujiFlpp = "generic/konsumer/kpr/hotprospek/listHasilPraujiFlpp";
        public static final String pemutusSetujuFlpp = "generic/pemutus/konsumer/pemutusSetujuFlpp";
        public static final String inquirySektorEkonomiFlpp = "generic/konsumer/kpr/hotprospek/datapby/inquireDataPembiayaanFlpp";
    }


    public class foto {
        public static final String urlFoto = "generic/getImage/";
    }

    public class fotoProfil {
        public static final String urlFotoProfil = "generic/getImageProfile/";
    }














}