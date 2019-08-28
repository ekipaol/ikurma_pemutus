package com.application.bris.brisi_pemutus.config.globaldata;

import android.content.Context;

import com.application.bris.brisi_pemutus.model.keyvalue.keyvalue;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class GlobalData {

    public static String MENU_ID = "idMenu";
    public static String MENU_ROOT = "rootMenu";



    public static void sampleSegmen(Context context, List<keyvalue> data){
        data.add(new keyvalue("Mikro", "MIKRO"));
        data.add(new keyvalue("Konsumer", "KONSUMER"));
    }

    public static void sampleProductMikro(Context context, List<keyvalue> data){
        data.add(new keyvalue("Mikro 25", "131"));
        data.add(new keyvalue("Mikro 75", "136"));
        data.add(new keyvalue("Mikro 200", "141"));
        data.add(new keyvalue("KUR", "127"));
    }

    public static void sampleProductKonsumer(Context context, List<keyvalue> data){
        data.add(new keyvalue("KPR", "KPR"));
        data.add(new keyvalue("KMJ", "KMJ"));
        data.add(new keyvalue("KMG", "KMG"));
    }

    public static void sampleProgramKpr(Context context, List<keyvalue> data){
        data.add(new keyvalue("KPR FLPP Rumah Tapak Regular", "222"));
        data.add(new keyvalue("KPR MRBH Non Fixed Income s/d 5th", "236"));
        data.add(new keyvalue("KPR MRBH Non Fixed Income s/d 10th", "237"));
        data.add(new keyvalue("KPR FLPP Rumah Tapak Program DP 5%", "239"));
    }

    public static void sampleProgramKmj(Context context, List<keyvalue> data){
        data.add(new keyvalue("KMJ Purna", "98"));
        data.add(new keyvalue("KMJ Purwa", "99"));
    }

    public static void sampleProgramKmg(Context context, List<keyvalue> data){
        data.add(new keyvalue("KMG EmBP", "1"));
        data.add(new keyvalue("KMG non EmBP", "2"));
    }

    public static void bidangUsaha(Context context, List<keyvalue> data){
        data.add(new keyvalue("Perkebunan", "1010"));
        data.add(new keyvalue("Pertanian/Peternakan/Perikanan", "1011"));
        data.add(new keyvalue("Bahan Mentah", "1012"));
        data.add(new keyvalue("Petrolium", "1110"));
        data.add(new keyvalue("Hortikultura", "1130"));
        data.add(new keyvalue("Komputer", "2010"));
        data.add(new keyvalue("Komputer Servis", "2011"));
        data.add(new keyvalue("Medikal", "2110"));
        data.add(new keyvalue("Kimia", "2111"));
        data.add(new keyvalue("Perhotelan", "2210"));
        data.add(new keyvalue("Perbankan", "3010"));
        data.add(new keyvalue("Institusi Financial", "3011"));
        data.add(new keyvalue("Konstruksi", "4010"));
        data.add(new keyvalue("Real Estate", "4020"));
        data.add(new keyvalue("Jasa Real Estate", "5010"));
        data.add(new keyvalue("Jasa Entertainment", "5020"));
        data.add(new keyvalue("Trading/Perdagangan", "5030"));
        data.add(new keyvalue("Jasa Angkutan", "5040"));
        data.add(new keyvalue("Jasa Lainnya", "5030"));
    }

    public static void bidangKerjaan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Karyawan / PNS", "1"));
        data.add(new keyvalue("Karyawan / PNS Program FLPP", "2"));
        data.add(new keyvalue("Karyawan / PNS Program EMBP", "3"));
        data.add(new keyvalue("Karyawan BRIS", "4"));
        data.add(new keyvalue("Wiraswasta", "5"));
        data.add(new keyvalue("Profesional", "6"));
        data.add(new keyvalue("Karyawan / PNS + Wiraswasta", "7"));
        data.add(new keyvalue("Karyawan / PNS + Profesional", "8"));
        data.add(new keyvalue("Profesional + Wiraswasta", "9"));
    }

    public static void tujuanPenggunaan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Barang Modal Kerja", "40"));
        data.add(new keyvalue("Investasi", "41"));
        data.add(new keyvalue("Investasi & Barang Modal Kerja", "46"));
        data.add(new keyvalue("Konsumtif", "45"));
        data.add(new keyvalue("Modal Kerja, Investasi & Konsumtif", "49"));
        data.add(new keyvalue("Produktif Investasi & Konsumtif", "47"));
        data.add(new keyvalue("Produktif Modal Kerja & Konsumtif", "48"));
        data.add(new keyvalue("Takeover", "50"));
        data.add(new keyvalue("Takeover & Barang Modal Kerja", "51"));
        data.add(new keyvalue("Takeover & Investasi", "52"));
        data.add(new keyvalue("Takeover & Konsumtif", "53"));
        data.add(new keyvalue("Takeover, Investasi dan Barang Modal Kerja", "54"));
        data.add(new keyvalue("Takeover, Modal Kerja, Investasi & Konsumtif", "57"));
        data.add(new keyvalue("Takeover, Produktif Investasi & Konsumtif", "55"));
        data.add(new keyvalue("Takeover, Produktif, Modal Kerja & Konsumtif", "56"));
    }

    public static void jenisKelamin(Context context, List<keyvalue> data){
        data.add(new keyvalue("Laki - Laki", "L"));
        data.add(new keyvalue("Perempuan", "P"));
    }

    public static void agama(Context context, List<keyvalue> data){
        data.add(new keyvalue("Islam", "ISL"));
        data.add(new keyvalue("Kristen", "KRI"));
        data.add(new keyvalue("Katholik", "KAT"));
        data.add(new keyvalue("Hindu", "HIN"));
        data.add(new keyvalue("Budha", "BUD"));
        data.add(new keyvalue("Lainnya", "ZZZ"));
    }

    public static void statusMenikah(Context context, List<keyvalue> data){
        data.add(new keyvalue("Belum Menikah", "1"));
        data.add(new keyvalue("Menikah", "2"));
        data.add(new keyvalue("Duda / Janda", "3"));
    }

    public static void tipePendapatan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Wiraswasta & Pendapatan Tetap", "1"));
        data.add(new keyvalue("Wiraswasta", "2"));
    }

    public static void pendidikanTerakhir(Context context, List<keyvalue> data){
        data.add(new keyvalue("SDTT", "1"));
        data.add(new keyvalue("SD", "2"));
        data.add(new keyvalue("SMP", "3"));
        data.add(new keyvalue("SMA", "4"));
        data.add(new keyvalue("Diploma 1", "5"));
        data.add(new keyvalue("Diploma 2", "6"));
        data.add(new keyvalue("Diploma 3", "7"));
        data.add(new keyvalue("S-1", "8"));
        data.add(new keyvalue("S-2", "9"));
        data.add(new keyvalue("S-3", "10"));
    }

    public static void statusTempatDomisili(Context context, List<keyvalue> data){
        data.add(new keyvalue("Milik Sendiri", "0"));
        data.add(new keyvalue("Milik sendiri dan masih diangsur", "1"));
        data.add(new keyvalue("Milik Keluarga", "4"));
        data.add(new keyvalue("Warisan", "5"));
        data.add(new keyvalue("Kontrak", "2"));
        data.add(new keyvalue("Kost", "6"));
        data.add(new keyvalue("Lainnya", "1"));
    }


    //added by eki
    public static void bentukBidangTanah(Context context, List<keyvalue> data){
        data.add(new keyvalue("Segitiga", "0"));
        data.add(new keyvalue("Segiempat", "1"));
        data.add(new keyvalue("Trapesium", "4"));
        data.add(new keyvalue("Tak Beraturan", "5"));

    }

    public static void permukaanTanah(Context context, List<keyvalue> data){
        data.add(new keyvalue("Rata", "0"));
        data.add(new keyvalue("Bergelombang", "1"));
        data.add(new keyvalue("Landai", "4"));
        data.add(new keyvalue("Berkontur", "5"));

    }

    public static void jenisSuratTanah(Context context, List<keyvalue> data){
        data.add(new keyvalue("SHM", "0"));
        data.add(new keyvalue("SHGB", "1"));
        data.add(new keyvalue("SHMASRS", "4"));
        data.add(new keyvalue("Girik", "5"));
        data.add(new keyvalue("Letter C", "1"));
        data.add(new keyvalue("Letter D", "4"));
        data.add(new keyvalue("APHB", "5"));
        data.add(new keyvalue("AJB", "1"));
        data.add(new keyvalue("Surat Keterangan Camat", "4"));
        data.add(new keyvalue("Surat Hijau Surabaya", "5"));
        data.add(new keyvalue("Surat Keterangan Tanah / Sporadik", "1"));
        data.add(new keyvalue("Lainnya", "4"));


    }

    public static void hubDenganPemegangHak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "0"));
        data.add(new keyvalue("Suami/Istri", "1"));
        data.add(new keyvalue("Orangtua", "4"));
        data.add(new keyvalue("Anak", "5"));
        data.add(new keyvalue("Pemilik Sebelumnya", "1"));
        data.add(new keyvalue("Lainnya", "4"));



    }

    public static void jenisBangunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Rumah Tinggal", "0"));
        data.add(new keyvalue("Ruko / Rukan", "1"));
        data.add(new keyvalue("Gedung / Kantor", "4"));
        data.add(new keyvalue("Pabrik", "5"));
        data.add(new keyvalue("Gudang", "1"));
        data.add(new keyvalue("Rumah Panggung Kayu", "4"));



    }

    public static void lokasiBangunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Pasar", "0"));
        data.add(new keyvalue("Non Pasar", "1"));

    }

    public static void jenisAgunanXBRL(Context context, List<keyvalue> data){
        data.add(new keyvalue("Tanah dan Gedung / Ruang Kantor", "0"));
        data.add(new keyvalue("Tanah dan Gudang", "1"));
        data.add(new keyvalue("Tanah dan Rumah Toko/Rumah Kantor untuk tempat tinggal", "4"));
        data.add(new keyvalue("Tanah dan Rumah Tinggal untuk tempat tinggal", "5"));


    }

    public static void hubPenghuniDenganPemegangHak(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "0"));
        data.add(new keyvalue("Suami/Istri", "1"));
        data.add(new keyvalue("Orangtua", "4"));
        data.add(new keyvalue("Anak", "5"));
        data.add(new keyvalue("Pemilik Sebelumnya", "1"));
        data.add(new keyvalue("Penyewa", "1"));
        data.add(new keyvalue("Lainnya", "4"));



    }

    public static void kondisiBangunan(Context context, List<keyvalue> data){
        data.add(new keyvalue("Bangunan Baru", "0"));
        data.add(new keyvalue("Terawat", "1"));
        data.add(new keyvalue("Tidak Terawat", "4"));

    }

    public static void pondasi(Context context, List<keyvalue> data){
        data.add(new keyvalue("Besi", "0"));
        data.add(new keyvalue("Baja", "1"));
        data.add(new keyvalue("Beton", "4"));
        data.add(new keyvalue("Kayu", "4"));

    }

    public static void jenisBangunanSpek(Context context, List<keyvalue> data){
        data.add(new keyvalue("Rumah Tinggal", "0"));


    }

    //tambahan eki untuk tanah kosong

    public static void statusPenggarap(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sewa", "Sewa"));
        data.add(new keyvalue("Penjaga", "Penjaga"));
        data.add(new keyvalue("Pemilik", "Pemilik"));
        data.add(new keyvalue("Lainnya", "Lainnya"));
    }

    public static void hubPenggarapDgPemegang(Context context, List<keyvalue> data){
        data.add(new keyvalue("Sendiri", "Sendiri"));
        data.add(new keyvalue("Suami/Istri", "Suami/Istri"));
        data.add(new keyvalue("Orangtua", "Orangtua"));
        data.add(new keyvalue("Anak", "Anak"));
        data.add(new keyvalue("Pemilik Sebelumnya", "Pemilik Sebelumnya"));
        data.add(new keyvalue("Lain", "Lain"));


    }

    public static void jenisDokumen(Context context, List<keyvalue> data){
        data.add(new keyvalue("SHM", "SHM"));
        data.add(new keyvalue("SHGB", "SHGB"));
        data.add(new keyvalue("SHMRS", "SHMRS"));
        data.add(new keyvalue("Girik", "Girik"));
        data.add(new keyvalue("Letter C", "Letter C"));
        data.add(new keyvalue("Letter D", "Letter D"));
        data.add(new keyvalue("APHB", "APHB"));
        data.add(new keyvalue("AJB", "AJB"));
        data.add(new keyvalue("Surat Keterangan Camat", "Surat Keterangan Camat"));
        data.add(new keyvalue("Surat Hijau Surabaya", "Surat Hijau Surabaya"));
        data.add(new keyvalue("Surat Keterangan Tanah / Sporadik", "Surat Keterangan Tanah / Sporadik"));
        data.add(new keyvalue("Lain", "Lain"));


    }

    public static void checkBpn(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ya", "Ya"));
        data.add(new keyvalue("Tidak", "Tidak"));
    }

    public static void hasilBpn(Context context, List<keyvalue> data){
        data.add(new keyvalue("Ok", "Ok"));
        data.add(new keyvalue("Tidak OK", "Tidak OK"));
    }









//    public static void sampleTindakLanjut(Context context, List<tindaklanjut> data){
//        data.add(new tindaklanjut("By Come - Rabu, 12 April 2019 16.30 WIB", "Menginput data nasabah yang mau pembiayaan"));
//        data.add(new tindaklanjut("By Phone - Senin, 19 Mei 2019 10.30 WIB", "Nelpon nasabah yang mau pembiayaan"));
//        data.add(new tindaklanjut("By Come - Kamis, 26 Mei 2019 10.30 WIB", "Datangin nasabah yang mau pembiayaan"));
//    }

//    public static void sampleDataPembiayaan(Context context, List<datapembiayaan> data){
//        data.add(new datapembiayaan("12000", "Mikro 25", "25000000", "12-02-2018", "Dalam Proses Pengisian Paket Aplikasi"));
//        data.add(new datapembiayaan("13000", "Mikro 75", "75000000", "17-07-2015", "Proses Pengiriman Data ke SYIAR"));
//        data.add(new datapembiayaan("15000", "KUR", "15000000", "17-09-2019", "Dalam Proses Pengisian Paket Aplikasi"));
//    }
}