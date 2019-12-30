package com.application.bris.brisi_pemutus.api.service;

import android.content.res.Resources;

import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.ParseResponseDataInstansi;
import com.application.bris.brisi_pemutus.api.model.request.EmptyRequest;
import com.application.bris.brisi_pemutus.api.model.request.ParseResponseListFoto;
import com.application.bris.brisi_pemutus.api.model.request.agunan.ReqAgunan;
import com.application.bris.brisi_pemutus.api.model.request.agunan_global_idapl_agunan_cif.AgunanGlobal;
import com.application.bris.brisi_pemutus.api.model.request.agunan_set_pengikatan.ReqSetPengikatan;
import com.application.bris.brisi_pemutus.api.model.request.agunan_tanah_kosong.ReqAgunanTanahKosong;
import com.application.bris.brisi_pemutus.api.model.request.agunan_tanah_kosong.ReqSaveAgunanTanahKosong;
import com.application.bris.brisi_pemutus.api.model.request.agunan_terikat.ReqAgunanTerikat;
import com.application.bris.brisi_pemutus.api.model.request.aktivasi.Aktivasi;
import com.application.bris.brisi_pemutus.api.model.request.ambil_alih.ReqAmbilAlih;
import com.application.bris.brisi_pemutus.api.model.request.check_update.CheckUpdate;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.model.request.dashboard_pemrakarsa.RequestDashboardPemrakarsa;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.model.request.data_lengkap.ReqDataLengkap;
import com.application.bris.brisi_pemutus.api.model.request.delete_agunan.ReqDeleteAgunan;
import com.application.bris.brisi_pemutus.api.model.request.delete_aom.ReqDeleteAom;
import com.application.bris.brisi_pemutus.api.model.request.firebase.ReqFirebase;
import com.application.bris.brisi_pemutus.api.model.request.history_putusan.ReqHistoryPutusan;
import com.application.bris.brisi_pemutus.api.model.request.id_aplikasi.ReqIdAplikasi;
import com.application.bris.brisi_pemutus.api.model.request.ikat_agunan.ReqIkatAgunan;
import com.application.bris.brisi_pemutus.api.model.request.info_agunan.ReqInfoAgunan;
import com.application.bris.brisi_pemutus.api.model.request.insert_update_aom.InsertUpdateAom;
import com.application.bris.brisi_pemutus.api.model.request.kelengkapan_dokumen.ReqKelengkapanDokumen;
import com.application.bris.brisi_pemutus.api.model.request.list_disposisi.ReqListDisposisi;
import com.application.bris.brisi_pemutus.api.model.request.list_hotprospek.ReqHotProspek;
import com.application.bris.brisi_pemutus.api.model.request.list_user.listUser;
import com.application.bris.brisi_pemutus.api.model.request.lkn.ReqLkn;
import com.application.bris.brisi_pemutus.api.model.request.login.LoginRequest;
import com.application.bris.brisi_pemutus.api.model.request.performance.ReqPerformanceAo;
import com.application.bris.brisi_pemutus.api.model.request.performance_cabang.ReqPerformanceCabang;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqPutusan;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqSetujuPutusan;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.model.request.req_nik.ReqNik;
import com.application.bris.brisi_pemutus.api.model.request.scoring.ReqScoring;
import com.application.bris.brisi_pemutus.api.model.request.sektor_ekonomi.ReqSektorEkonomi;
import com.application.bris.brisi_pemutus.api.model.request.simpan_disposisi.ReqSimpanDisposisi;
import com.application.bris.brisi_pemutus.api.model.request.validasi_data_finansial.ValidasiDataFinansialKmg;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST(UriApi.pipeline.listPipeline)
    Call<ParseResponseArr> listUser(@Body listUser listUser);

    @POST(UriApi.Login.loginStatus)
    Call<ParseResponse> loginRequest(@Body LoginRequest LoginRequest);

    @POST(UriApi.SecretLogin.secretLogin)
    Call<ParseResponse> secretLogin(@Body LoginRequest LoginRequest);

    @POST(UriApi.Dashboard.dashboard)
    Call<ParseResponse> dashboardRequest(@Body RequestDashboard RequestDashboard);

    @POST(UriApi.DashboardPemrakarsa.dashboardPemrakarsa)
    Call<ParseResponse> dashboardPemrakarsa(@Body RequestDashboardPemrakarsa RequestDashboardPemrakarsa);


    @POST(UriApi.getDataCabang.dataCabang)
    Call<ParseResponseArr> dataCabang(@Body RequestDataCabang RequestDataCabang);

    @POST(UriApi.getAo.dataAo)
    Call<ParseResponse> dataAo(@Body RequestDataCabang RequestDataCabang);

    //tipe request sama yaitu data cabang
    @POST(UriApi.getUh.dataUh)
    Call<ParseResponse> dataUh(@Body RequestDataCabang RequestDataCabang);

    //tipe request sama yaitu data cabang
    @POST(UriApi.getPinca.dataPinca)
    Call<ParseResponse> dataPinca(@Body RequestDataCabang RequestDataCabang);

    //tipe request sama yaitu data cabang
    @POST(UriApi.getMmm.dataMmm)
    Call<ParseResponse> dataMmm(@Body RequestDataCabang RequestDataCabang);

    @POST(UriApi.insertUpdateAom.insertUpdateAom)
    Call<ParseResponse> insertUpdateAom(@Body InsertUpdateAom InsertUpdateAom);

    @POST(UriApi.deleteAom.deleteAom)
    Call<ParseResponse> deleteAom(@Body ReqDeleteAom ReqDeleteAom);


    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.aktivasiStatusAom.aktivasiStatusAom)
    Call<ParseResponse> aktivasiStatusAom(@Body ReqDeleteAom ReqDeleteAom);


    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.deaktivasiStatusAom.deaktivasiStatusAom)
    Call<ParseResponse> deaktivasiStatusAom(@Body ReqDeleteAom ReqDeleteAom);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.reactivePasswordAom.reactivePasswordAom)
    Call<ParseResponse> reactivePasswordAom(@Body ReqDeleteAom ReqDeleteAom);

    @POST(UriApi.listHotProspek.listHotProspek)
    Call<ParseResponseArr> listHotProspek(@Body ReqHotProspek ReqHotProspek);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.listPemutus.listPemutus)
    Call<ParseResponseArr> listPemutus(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.listPutusanAkad.listPutusanAkad)
    Call<ParseResponseArr> listPutusanAkad(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.listPutusanCair.listPutusanCair)
    Call<ParseResponseArr> listPutusanCair(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.listPutusanDitolak.listPutusanDitolak)
    Call<ParseResponseArr> listPutusanDitolak(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.listPutusanSetuju.listPutusanSetuju)
    Call<ParseResponseArr> listPutusanSetuju(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.listDeviasi.listDeviasi)
    Call<ParseResponseArr> listDeviasi(@Body ReqPutusan ReqPutusan);

    @POST(UriApi.listDisposisi.listDisposisi)
    Call<ParseResponse> listDisposisi(@Body ReqListDisposisi ReqListDisposisi);

    @POST(UriApi.getAomDisposisi.dataAomDisposisi)
    Call<ParseResponse> getAomDisposisi(@Body ReqKodeSkk ReqKodeSkk);

    @POST(UriApi.getPincaLengkap.dataPincaLengkap)
    Call<ParseResponse> dataPincaLengkap(@Body RequestDataCabang RequestDataCabang);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.cekAdp.cekAdp)
    Call<ParseResponseArr> cekAdp(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.cekMo.cekMo)
    Call<ParseResponseArr> cekMo(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.cekBos.cekBos)
    Call<ParseResponseArr> cekBos(@Body ReqPutusan ReqPutusan);

    //menggunakan objek yang sama untuk request, karna sama menggunakan UID
    @POST(UriApi.cekCs.cekCs)
    Call<ParseResponseArr> cekCs(@Body ReqPutusan ReqPutusan);

    @POST(UriApi.cekAdaPutusan.cekAdaPutusan)
    Call<ParseResponseArr> cekAdaPutusan(@Body ReqPutusan ReqPutusan);

    @POST(UriApi.cekAdaCs.cekAdaCs)
    Call<ParseResponseArr> cekAdaCs(@Body ReqKodeSkk ReqKodeSkk);

    @POST(UriApi.cekAdaAdp.cekAdaAdp)
    Call<ParseResponseArr> cekAdaAdp(@Body ReqKodeSkk ReqKodeSkk);




    @POST(UriApi.pemutusSetuju.pemutusSetuju)
    Call<ParseResponse> pemutusSetuju(@Body ReqSetujuPutusan ReqSetujuPutusan);


    //request object sama dengan setuju, karena bentuk requestnya sama
    @POST(UriApi.pemutusTolak.pemutusTolak)
    Call<ParseResponse> pemutusTolak(@Body ReqSetujuPutusan ReqSetujuPutusan);

    //request object sama dengan setuju, karena bentuk requestnya sama
    @POST(UriApi.pemutusKembalikan.pemutusKembalikan)
    Call<ParseResponse> pemutusKembalikan(@Body ReqSetujuPutusan ReqSetujuPutusan);

    @POST(UriApi.inquiryDataLengkap.inquiryDataLengkap)
    Call<ParseResponse> inquiryDataLengkap(@Body ReqDataLengkap ReqDataLengkap);

    @POST(UriApi.inquiryHistory.inquiryHistory)
    Call<ParseResponse> inquiryHistory(@Body ReqHistoryPutusan ReqHistoryPutusan);

    @POST(UriApi.inquirySektorEkonomi.inquirySektorEkonomi)
    Call<ParseResponse> inquirySektorEkonomi(@Body ReqSektorEkonomi ReqSektorEkonomi);

    @POST(UriApi.inquiryKelengkapanDokumen.inquiryKelengkapanDokumen)
    Call<ParseResponse> inquiryKelengkapanDokumen(@Body ReqKelengkapanDokumen ReqKelengkapanDokumen);

    //object request sama dengan kelengkapan dokumen yaitu idAplikasi
    @POST(UriApi.inquiryPrescreening.inquiryPrescreening)
    Call<ParseResponse> inquiryPrescreening(@Body ReqKelengkapanDokumen ReqKelengkapanDokumen);

    @POST(UriApi.downloadSlik.downloadSlik)
    Call<ParseResponse> downloadSlik (@Body ReqKelengkapanDokumen ReqKelengkapanDokumen);
    @POST(UriApi.downloadSlikPasangan.downloadSlikPasangan)
    Call<ParseResponse> downloadSlikPasangan (@Body ReqKelengkapanDokumen ReqKelengkapanDokumen);

    //object request sama dengan kelengkapan dokumen yaitu idAplikasi
    @POST(UriApi.inquiryRpc.inquiryRpc)
    Call<ParseResponse> inquiryRpc(@Body ReqKelengkapanDokumen ReqKelengkapanDokumen);

    @POST(UriApi.inquiryAgunan.inquiryAgunan)
    Call<ParseResponse> inquiryAgunan(@Body AgunanGlobal AgunanGlobal);

    //object request sama dengan kelengkapan dokumen yaitu idAplikasi
    @POST(UriApi.inquiryInfoAgunan.inquiryInfoAgunan)
    Call<ParseResponse> inquiryInfoAgunan(@Body ReqInfoAgunan ReqInfoAgunan);

    @POST(UriApi.deleteAgunan.deleteAgunan)
    Call<ParseResponse> deleteAgunan(@Body ReqDeleteAgunan ReqDeleteAgunan);

    @POST(UriApi.setPengikatan.setPengikatan)
    Call<ParseResponse> setPengikatan(@Body ReqSetPengikatan ReqSetPengikatan);

    @POST(UriApi.ikatAgunan.ikatAgunan)
    Call<ParseResponse> ikatAgunan(@Body ReqIkatAgunan ReqIkatAgunan);

    @POST(UriApi.jenisKlasifikasi.jenisKlasifikasi)
    Call<ParseResponseArr> jenisKlasifikasi(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.getRankingTotal.getRankingTotal)
    Call<ParseResponseArr> getRankingTotal(@Body EmptyRequest EmptyRequest);

    @POST(UriApi.getPerformanceCabang.getPerformanceCabang)
    Call<ParseResponseArr> getPerformanceCabang(@Body ReqPerformanceCabang ReqPerformanceCabang);

    @POST(UriApi.performanceAo.performanceAo)
    Call<ParseResponse> performanceAo(@Body ReqPerformanceAo ReqPerformanceAo);

    @POST(UriApi.listAmbilAlih.listAmbilAlih)
    Call<ParseResponse> listAmbilAlih(@Body ReqAmbilAlih ReqAmbilAlih);

    @POST(UriApi.listUms.listUms)
    Call<ParseResponse> listUms(@Body ReqKodeSkk ReqKodeSkk);




    @POST(UriApi.inquireLkn.inquireLkn)
    Call<ParseResponse> inquireLkn(@Body ReqLkn ReqLkn);

    @POST(UriApi.inquiryScoring.inquiryScoring)
    Call<ParseResponse> inquiryScoring(@Body ReqScoring ReqScoring);

    @POST(UriApi.listAgunanTerikat.listAgunanTerikat)
    Call<ParseResponseArr> listAgunan(@Body ReqAgunanTerikat ReqAgunanTerikat);

    //req sama dengan listAgunanTerikat
    @POST(UriApi.listAgunanTerikatRiwayat.listAgunanTerikatRiwayat)
    Call<ParseResponseArr> listAgunanTerikatRiwayat(@Body ReqAgunanTerikat ReqAgunanTerikat);

    //req sama dengan agunan
    @POST(UriApi.inquiryTanahKosong.inquiryTanahKosong)
    Call<ParseResponseArr> inquiryTanahKosong(@Body ReqAgunanTanahKosong ReqAgunanTanahKosong);

    @POST(UriApi.inquiryAgunanDeposito.inquiryAgunanDeposito)
    Call<ParseResponse> inquiryAgunanDeposito(@Body AgunanGlobal AgunanGlobal);

    @POST(UriApi.inquiryAgunanKios.inquiryAgunanKios)
    Call<ParseResponseArr> inquiryAgunanKios(@Body AgunanGlobal AgunanGlobal);

    @POST(UriApi.inquiryAgunanKendaraan.inquiryAgunanKendaraan)
    Call<ParseResponse> inquiryAgunanKendaraan(@Body AgunanGlobal AgunanGlobal);


    @POST(UriApi.saveTanahKosong.saveTanahKosong)
    Call<ParseResponse> saveTanahKosong(@Body ReqSaveAgunanTanahKosong ReqSaveAgunanTanahKosong);

    @POST(UriApi.simpanDisposisi.simpanDisposisi)
    Call<ParseResponse> simpanDisposisi(@Body ReqSimpanDisposisi ReqSimpanDisposisi);

    @POST(UriApi.cekUserMip.cekUserMip)
    Call<ParseResponseArr> cekUserMip(@Body ReqNik ReqNik);

    @POST(UriApi.activation.activation)
    Call<ParseResponse> activation (@Body Aktivasi activation);

    @POST(UriApi.checkUpdate.checkUpdate)
    Call<ParseResponse> checkUpdate (@Body CheckUpdate checkupdate);

    @POST(UriApi.inquiryDetailSlik.inquiryDetailSlik)
    Call<ParseResponse> inquiryDetailSlik (@Body ReqIdAplikasi ReqIdAplikasi);


    //KONSUMER KMG
    @POST(UriApi.inquiryDataLengkapKonsumerKmg.inquiryDataLengkapKonsumerKmg)
    Call<ParseResponse> inquiryDataLengkapKonsumerKmg(@Body ReqDataLengkap ReqDataLengkap);

    //menggunakan request kelengkapandokumen karena requestnya sama sama id aplikasi
    @POST(UriApi.inquiryDataFinansialKmg.inquiryDataFinansialKmg)
    Call<ParseResponseDataInstansi> inquiryDataFinansialKmg(@Body ReqKelengkapanDokumen ReqKelengkapanDokumen);

    @POST(UriApi.validasiDataFinansial.validasiDataFinansial)
    Call<ParseResponse> validasiDataFinansial (@Body ValidasiDataFinansialKmg ValidasiDataFinansialKmg);



    @POST(UriApi.updateFirebase.updateFirebase)
    Call<ParseResponse> updateFirebase(@Body ReqFirebase ReqFirebase);


    @Multipart
    @POST(UriApi.uploadImage.uploadImage)
    Call<ParseResponse> uploadImage (@Part MultipartBody.Part file);



    //get  better way to put the key, this one is bad
//    @GET("api/place/nearbysearch/json?sensor=true&key=AIzaSyCQiZIq4rZhxAe1zPsYS8jGt50BWNeMFE0" )
//    Call<ParseResponse> getAlamat(@Query("latitude") String latitude,
//                                               @Query("longitude") String longitude);






}