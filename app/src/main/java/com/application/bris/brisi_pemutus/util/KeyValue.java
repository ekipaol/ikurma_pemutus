package com.application.bris.brisi_pemutus.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by idong on 17/05/2019.
 */

public class KeyValue {
    private static HashMap<String, String> mapTypeAddressSearch = new HashMap<>();
    private static HashMap<String, String> mapTypeUsahaorJob = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisKelamin = new HashMap<>();
    private static HashMap<String, String> mapTypeAgama = new HashMap<>();
    private static HashMap<String, String> mapTypeStatusMenikah = new HashMap<>();
    private static HashMap<String, String> mapTypeTipePendapatan = new HashMap<>();
    private static HashMap<String, String> mapTypePendidikanTerakhir = new HashMap<>();
    private static HashMap<String, String> mapTypeStatusTempatDomisili = new HashMap<>();


    private static HashMap<String, String> mapTypeStatusPermohonan = new HashMap<>();
    private static HashMap<String, String> mapTypeHubunganKeluarga = new HashMap<>();
    private static HashMap<String, String> mapTypeLokasiUsaha = new HashMap<>();
    private static HashMap<String, String> mapTypeStatusTempatUsaha = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisTempatUsaha = new HashMap<>();
    private static HashMap<String, String> mapTypeAspekPemasaran = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisUsaha = new HashMap<>();


    private static HashMap<String, String> mapTypeRpcRatio = new HashMap<>();
    private static HashMap<String, String> mapTypeReputasiUsaha = new HashMap<>();
    private static HashMap<String, String> mapTypeRiwayatHubdgnBank = new HashMap<>();
    private static HashMap<String, String> mapTypeLamaUsaha = new HashMap<>();
    private static HashMap<String, String> mapTypeLamaUsahaKur = new HashMap<>();
    private static HashMap<String, String> mapTypeProspekUsaha = new HashMap<>();
    private static HashMap<String, String> mapTypeKetergantunganSupplierdanPelanggan = new HashMap<>();
    private static HashMap<String, String> mapTypeWilayahPemasaran = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisProduk = new HashMap<>();
    private static HashMap<String, String> mapTypeJangkaWaktu = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisPembiayaan = new HashMap<>();

    private static HashMap<String, String> mapTypeJenisBangunan = new HashMap<>();
    private static HashMap<String, String> mapTypeLokasiBangunan = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisAgunanXBRL = new HashMap<>();
    private static HashMap<String, String> mapTypeHubPenghuniDenganPemegangHak = new HashMap<>();
    private static HashMap<String, String> mapTypeKondisiBangunan = new HashMap<>();
    private static HashMap<String, String> mapTypePondasi = new HashMap<>();
    private static HashMap<String, String> mapTypeJenisBangunanSpek = new HashMap<>();
    private static HashMap<String, String> mapTypeDinding = new HashMap<>();
    private static HashMap<String, String> mapTypeAtap = new HashMap<>();

    private static HashMap<String, String> mapTypeCurrentRatio = new HashMap<>();
    private static HashMap<String, String> mapTypeProfitability = new HashMap<>();
    private static HashMap<String, String> mapTypeAgunanRatio = new HashMap<>();

    private static HashMap<String, String> mapTypeAro = new HashMap<>();

    private static HashMap<String, String> mapTypeOperasional = new HashMap<>();



    static {
        init();
    }

    public static void init() {

        //Data Type Address Search
        mapTypeAddressSearch.put("Provinsi", "PROPINSI");
        mapTypeAddressSearch.put("Kota/Kabupaten", "KOTA");
        mapTypeAddressSearch.put("Kecamatan", "KECAMATAN");
        mapTypeAddressSearch.put("Kelurahan", "KELURAHAN");
        mapTypeAddressSearch.put("Kodepos", "KODE_POS");

        //Data Type Usaha or Job
        //Usaha
        mapTypeUsahaorJob.put("Perkebunan", "1010");
        mapTypeUsahaorJob.put("Pertanian/Peternakan/Perikanan", "1011");
        mapTypeUsahaorJob.put("Bahan Mentah", "1012");
        mapTypeUsahaorJob.put("Petrolium", "1110");
        mapTypeUsahaorJob.put("Hortikultura", "1130");
        mapTypeUsahaorJob.put("Komputer", "2010");
        mapTypeUsahaorJob.put("Komputer Servis", "2011");
        mapTypeUsahaorJob.put("Medikal", "2110");
        mapTypeUsahaorJob.put("Kimia", "2111");
        mapTypeUsahaorJob.put("Perhotelan", "2210");
        mapTypeUsahaorJob.put("Perbankan", "3010");
        mapTypeUsahaorJob.put("Institusi Financial", "3011");
        mapTypeUsahaorJob.put("Konstruksi", "4010");
        mapTypeUsahaorJob.put("Real Estate", "4020");
        mapTypeUsahaorJob.put("Jasa Real Estate", "5010");
        mapTypeUsahaorJob.put("Jasa Entertainment", "5020");
        mapTypeUsahaorJob.put("Trading/Perdagangan", "5030");
        mapTypeUsahaorJob.put("Jasa Angkutan", "5040");
        mapTypeUsahaorJob.put("Jasa Lainnya", "5030");

        //Job
        mapTypeUsahaorJob.put("Karyawan / PNS", "1");
        mapTypeUsahaorJob.put("Karyawan / PNS Program FLPP", "2");
        mapTypeUsahaorJob.put("Karyawan / PNS Program EMBP", "3");
        mapTypeUsahaorJob.put("Karyawan BRIS", "4");
        mapTypeUsahaorJob.put("Wiraswasta", "5");
        mapTypeUsahaorJob.put("Profesional", "6");
        mapTypeUsahaorJob.put("Karyawan / PNS + Wiraswasta", "7");
        mapTypeUsahaorJob.put("Karyawan / PNS + Profesional", "8");
        mapTypeUsahaorJob.put("Profesional + Wiraswasta", "9");

        //JENIS KELAMIN
        mapTypeJenisKelamin.put("Laki - Laki", "L");
        mapTypeJenisKelamin.put("Perempuan", "P");

        //AGAMA
        mapTypeAgama.put("Islam", "ISL");
        mapTypeAgama.put("Kristen", "KRI");
        mapTypeAgama.put("Katholik", "KAT");
        mapTypeAgama.put("Hindu", "HIN");
        mapTypeAgama.put("Budha", "BUD");
        mapTypeAgama.put("Lainnya", "ZZZ");

        //STATUS MENIKAH
        mapTypeStatusMenikah.put("Belum Menikah", "1");
        mapTypeStatusMenikah.put("Menikah", "2");
        mapTypeStatusMenikah.put("Duda / Janda", "3");

        //TIPE PENDAPATAN
        mapTypeTipePendapatan.put("Wiraswasta & Pendapatan Tetap", "1");
        mapTypeTipePendapatan.put("Wiraswasta", "2");

        //PENDIDIKAN TERAKHIR
        mapTypePendidikanTerakhir.put("SDTT", "1");
        mapTypePendidikanTerakhir.put("SD", "2");
        mapTypePendidikanTerakhir.put("SMP", "3");
        mapTypePendidikanTerakhir.put("SMA", "4");
        mapTypePendidikanTerakhir.put("Diploma 1", "5");
        mapTypePendidikanTerakhir.put("Diploma 2", "6");
        mapTypePendidikanTerakhir.put("Diploma 3", "7");
        mapTypePendidikanTerakhir.put("S-1", "8");
        mapTypePendidikanTerakhir.put("S-2", "9");
        mapTypePendidikanTerakhir.put("S-3", "10");

        //STATUS TEMPAT DOMISILI
        mapTypeStatusTempatDomisili.put("Milik Sendiri", "0");
        mapTypeStatusTempatDomisili.put("Milik sendiri dan masih diangsur", "1");
        mapTypeStatusTempatDomisili.put("Milik Keluarga", "4");
        mapTypeStatusTempatDomisili.put("Warisan", "5");
        mapTypeStatusTempatDomisili.put("Kontrak", "2");
        mapTypeStatusTempatDomisili.put("Kost", "6");
        mapTypeStatusTempatDomisili.put("Lainnya", "1");

        //STATUS PERMOHONAN
        mapTypeStatusPermohonan.put("Baru", "Baru");
        mapTypeStatusPermohonan.put("Lama", "Lama");

        //HUBUNGAN KELUARGA
        mapTypeHubunganKeluarga.put("Pemohon Sendiri", "Pemohon Sendiri");
        mapTypeHubunganKeluarga.put("Pejabat Setempat", "Pejabat Setempat");
        mapTypeHubunganKeluarga.put("Istri/Suami YMP", "Istri/Suami YMP");
        mapTypeHubunganKeluarga.put("Tetangga Usaha", "Tetangga Usaha");
        mapTypeHubunganKeluarga.put("Karyawan YMP", "Karyawan YMP");
        mapTypeHubunganKeluarga.put("Kerabat Pemohon", "Kerabat Pemohon");
        mapTypeHubunganKeluarga.put("Anak/Saudara YMP", "Anak/Saudara YMP");
        mapTypeHubunganKeluarga.put("Tetangga Rumah", "Tetangga Rumah");

        //LOKASI USAHA
        mapTypeLokasiUsaha.put("Pasar Utama", "Pasar Utama");
        mapTypeLokasiUsaha.put("Pasar Sekunder", "Pasar Sekunder");
        mapTypeLokasiUsaha.put("Plasma", "Plasma");

        //STATUS TEMPAT USAHA
        mapTypeStatusTempatUsaha.put("Milik Sendiri - Beli", "Milik Sendiri - Beli");
        mapTypeStatusTempatUsaha.put("Milik Sendiri - Warisan", "Milik Sendiri - Warisan");
        mapTypeStatusTempatUsaha.put("Milik Keluarga", "Milik Keluarga");
        mapTypeStatusTempatUsaha.put("Kredit/Masih Mencicil", "Kredit/Masih Mencicil");
        mapTypeStatusTempatUsaha.put("Sewa", "Sewa");

        //JENIS TEMPAT USAHA
        mapTypeJenisTempatUsaha.put("Los/Lapak/Dasaran", "Los/Lapak/Dasaran");
        mapTypeJenisTempatUsaha.put("Toko/Ruko/Kios", "Toko/Ruko/Kios");
        mapTypeJenisTempatUsaha.put("Warung/Tenda", "Warung/Tenda");
        mapTypeJenisTempatUsaha.put("Gerobak/Berpindah", "Gerobak/Berpindah");
        mapTypeJenisTempatUsaha.put("Rumahan", "Rumahan");

        //ASPEK PEMASARAN
        mapTypeAspekPemasaran.put("Eceran", "Eceran");
        mapTypeAspekPemasaran.put("Grosir", "Grosir");
        mapTypeAspekPemasaran.put("Jasa", "Jasa");
        mapTypeAspekPemasaran.put("Agen", "Agen");
        mapTypeAspekPemasaran.put("Lainnya", "Lainnya");

        //JENIS USAHA
        mapTypeJenisUsaha.put("Sayur-mayur/Buah-buahan/Padi", "Sayur-mayur/Buah-buahan/Padi");
        mapTypeJenisUsaha.put("Sembako/Kelontong", "Sembako/Kelontong");
        mapTypeJenisUsaha.put("Pakaian/Alas Kaki", "Pakaian/Alas Kaki");
        mapTypeJenisUsaha.put("Rongsokan/Barang Bekas", "Rongsokan/Barang Bekas");
        mapTypeJenisUsaha.put("Rumah makan/Cathering (makanan matang)", "Rumah makan/Cathering (makanan matang)");
        mapTypeJenisUsaha.put("Elektronik", "Elektronik");
        mapTypeJenisUsaha.put("Daging/Unggas/Ikan", "Daging/Unggas/Ikan");
        mapTypeJenisUsaha.put("Peralatan Rumah Tangga", "Peralatan Rumah Tangga");
        mapTypeJenisUsaha.put("Industri Rumahan", "Industri Rumahan");
        mapTypeJenisUsaha.put("Bahan Bangunan/Material", "Bahan Bangunan/Material");
        mapTypeJenisUsaha.put("Transportasi", "Transportasi");
        mapTypeJenisUsaha.put("Mebel", "Mebel");
        mapTypeJenisUsaha.put("Supplier (pemasok)", "Supplier (pemasok)");
        mapTypeJenisUsaha.put("Bengkel", "Bengkel");
        mapTypeJenisUsaha.put("Usaha Isi Ulang", "Usaha Isi Ulang");
        mapTypeJenisUsaha.put("Lainnya", "Lainnya");




        mapTypeRpcRatio.put("< 1x", "1");
        mapTypeRpcRatio.put("1  - 2x", "2");
        mapTypeRpcRatio.put("> 2x", "3");



        mapTypeAgunanRatio.put("111%  -  150%", "1");
        mapTypeAgunanRatio.put(">150%  - 175%", "2");
        mapTypeAgunanRatio.put("> 175%", "3");

        mapTypeCurrentRatio.put("< 1", "1");
        mapTypeCurrentRatio.put("1 - 2", "2");
        mapTypeCurrentRatio.put("> 2", "3");

        mapTypeProfitability.put("< 15%", "1");
        mapTypeProfitability.put("15% - 25%", "2");
        mapTypeProfitability.put("> 25%", "3");


        mapTypeReputasiUsaha.put("Opini Negatif dari Pelanggan", "1");
        mapTypeReputasiUsaha.put("Opini Positif/Negatif", "2");
        mapTypeReputasiUsaha.put("Opini Positif", "3");


        mapTypeRiwayatHubdgnBank.put("Sering Terjadi Tunggakan", "1");
        mapTypeRiwayatHubdgnBank.put("Pernah Terjadi Tunggakan", "2");
        mapTypeRiwayatHubdgnBank.put("Pembayaran Selalu Tepat Waktu", "3");

        mapTypeLamaUsaha.put("= 2 tahun", "1");
        mapTypeLamaUsaha.put("> 2 - 5 tahun", "2");
        mapTypeLamaUsaha.put("> 5 tahun", "3");

        mapTypeLamaUsahaKur.put("6 - 12 Bulan", "1");
        mapTypeLamaUsahaKur.put("> 12 - 24 bulan", "2");
        mapTypeLamaUsahaKur.put("> 24 bulan", "3");


        mapTypeProspekUsaha.put("Stabil", "1");
        mapTypeProspekUsaha.put("Berkembang", "2");
        mapTypeProspekUsaha.put("Maju", "3");



        mapTypeKetergantunganSupplierdanPelanggan.put("Terbatas", "1");
        mapTypeKetergantunganSupplierdanPelanggan.put("Beberapa", "2");
        mapTypeKetergantunganSupplierdanPelanggan.put("Banyak dan Beragam", "3");

        mapTypeWilayahPemasaran.put("Lokal", "1");
        mapTypeWilayahPemasaran.put("Dalam Kota", "2");
        mapTypeWilayahPemasaran.put("Antar Kota", "3");

        mapTypeJenisProduk.put("Barang Mewah", "1");
        mapTypeJenisProduk.put("Barang & Jasa Sekunder", "2");
        mapTypeJenisProduk.put("Barang & Jasa Primer", "3");

        mapTypeJangkaWaktu.put("> 3 tahun", "1");
        mapTypeJangkaWaktu.put("1 - 3 tahun", "2");
        mapTypeJangkaWaktu.put("<= 1 tahun", "3");

        mapTypeJenisPembiayaan.put("Musyarakah", "1");
        mapTypeJenisPembiayaan.put("Mudharabah", "2");
        mapTypeJenisPembiayaan.put("Murabahah", "3");


        //
        mapTypeJenisBangunan.put("Rumah Tinggal", "1");
        mapTypeJenisBangunan.put("Ruko / Rukan", "2");
        mapTypeJenisBangunan.put("Gedung / Kantor", "3");
        mapTypeJenisBangunan.put("Pabrik", "4");
        mapTypeJenisBangunan.put("Gudang", "5");
        mapTypeJenisBangunan.put("Rumah Panggung Kayu", "6");

        //
        mapTypeLokasiBangunan.put("Pasar", "Pasar");
        mapTypeLokasiBangunan.put("Non Pasar", "NonPasar");

        //
        mapTypeJenisAgunanXBRL.put("Tanah dan Gedung / Ruang Kantor", "302");
        mapTypeJenisAgunanXBRL.put("Tanah dan Gudang", "304");
        mapTypeJenisAgunanXBRL.put("Tanah dan Rumah Toko/Rumah Kantor untuk tempat tinggal", "306");
        mapTypeJenisAgunanXBRL.put("Tanah dan Rumah Tinggal untuk tempat tinggal", "312");

        //
        mapTypeHubPenghuniDenganPemegangHak.put("Sendiri", "1");
        mapTypeHubPenghuniDenganPemegangHak.put("Suami/Istri", "2");
        mapTypeHubPenghuniDenganPemegangHak.put("Orangtua", "3");
        mapTypeHubPenghuniDenganPemegangHak.put("Anak", "4");
        mapTypeHubPenghuniDenganPemegangHak.put("Pemilik Sebelumnya", "5");
        mapTypeHubPenghuniDenganPemegangHak.put("Penyewa", "6");
        mapTypeHubPenghuniDenganPemegangHak.put("Lainnya", "99");

        //
        mapTypeKondisiBangunan.put("Bangunan Baru", "1");
        mapTypeKondisiBangunan.put("Terawat", "2");
        mapTypeKondisiBangunan.put("Tidak Terawat", "3");

        //
        mapTypePondasi.put("Besi", "1");
        mapTypePondasi.put("Baja", "2");
        mapTypePondasi.put("Beton", "3");
        mapTypePondasi.put("Kayu", "4");

        //
        mapTypeJenisBangunanSpek.put("Rumah Tinggal", "2976");

        //
        mapTypeDinding.put("Batu Bata", "1");
        mapTypeDinding.put("Logam", "2");
        mapTypeDinding.put("Bilik Bambu", "3");
        mapTypeDinding.put("Kayu", "4");

        //
        mapTypeAtap.put("Genteng", "1");
        mapTypeAtap.put("Seng", "2");
        mapTypeAtap.put("Asbes", "3");
        mapTypeAtap.put("Beton", "4");
        mapTypeAtap.put("Sirap", "5");
        mapTypeAtap.put("Kayu", "6");
        mapTypeAtap.put("Jerami", "7");

        //Kendaraan
        mapTypeOperasional.put("Radius <= 25 km", "25");
        mapTypeOperasional.put("Radius <= 50 km", "50");
        mapTypeOperasional.put("Radius > 50 km", "55");

        mapTypeAro.put("Ya", "1");
        mapTypeAro.put("Tidak", "2");

        //Kendaraan
        mapTypeOperasional.put("Radius <= 25 km", "25");
        mapTypeOperasional.put("Radius <= 50 km", "50");
        mapTypeOperasional.put("Radius > 50 km", "55");
    }

    public static String getTypeAddressSearch(String param){
        return mapTypeAddressSearch.get(param);
    }

    public static String getTypeUsahaorJob(String param){
        return mapTypeUsahaorJob.get(param);
    }

    public static String getKeyUsahaorJob(String value){
        for (String o : mapTypeUsahaorJob.keySet()){
            if(mapTypeUsahaorJob.get(o).equalsIgnoreCase(value)){
                return o;
            }
        }
        return null;
    }

    public static String getTypeJenisKelamin(String param){
        return mapTypeJenisKelamin.get(param);
    }

    public static String getKeyJenisKelamin(String value){
        for (Map.Entry<String, String> e : mapTypeJenisKelamin.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeAgama(String param){
        return mapTypeAgama.get(param);
    }

    public static String getKeyAgama(String value){
        for (Map.Entry<String, String> e : mapTypeAgama.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeStatusNikah(String param){
        return mapTypeStatusMenikah.get(param);
    }

    public static String getKeyStatusNikah(String value){
        for (String o : mapTypeStatusMenikah.keySet()){
            if(mapTypeStatusMenikah.get(o).equalsIgnoreCase(value)){
                return o;
            }
        }
        return null;
    }

    public static String getTypeTipePendapatan(String param){
        return mapTypeTipePendapatan.get(param);
    }

    public static String getKeyTipePendapatan(String value){
        for (String o : mapTypeTipePendapatan.keySet()){
            if(mapTypeTipePendapatan.get(o).equalsIgnoreCase(value)){
                return o;
            }
        }
        return null;
    }

    public static String getTypePendidikanTerakhir(String param){
        return mapTypePendidikanTerakhir.get(param);
    }

    public static String getKeyPendidikanTerakhir(String value){
        for (String o : mapTypePendidikanTerakhir.keySet()){
            if(mapTypePendidikanTerakhir.get(o).equalsIgnoreCase(value)){
                return o;
            }
        }
        return null;
    }

    public static String getTypeStatusTempatDomisili(String param){
        return mapTypeStatusTempatDomisili.get(param);
    }

    public static String getKeyStatusTempatDomisili(String value){
        for (Map.Entry<String, String> e : mapTypeStatusTempatDomisili.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }





    public static String getTypeStatusPermohonan(String param){
        return mapTypeStatusPermohonan.get(param);
    }

    public static String getKeyStatusPermohonan(String value){
        for (Map.Entry<String, String> e : mapTypeStatusPermohonan.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeHubunganKeluarga(String param){
        return mapTypeHubunganKeluarga.get(param);
    }

    public static String getKeyHubunganKeluarga(String value){
        for (Map.Entry<String, String> e : mapTypeHubunganKeluarga.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeLokasiUsaha(String param){
        return mapTypeLokasiUsaha.get(param);
    }

    public static String getKeyLokasiUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeLokasiUsaha.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeStatusTempatUsaha(String param){
        return mapTypeStatusTempatUsaha.get(param);
    }

    public static String getKeyStatusTempatUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeStatusTempatUsaha.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeJenisTempatUsaha(String param){
        return mapTypeJenisTempatUsaha.get(param);
    }

    public static String getKeyJenisTempatUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeJenisTempatUsaha.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeAspekPemasaran(String param){
        return mapTypeAspekPemasaran.get(param);
    }

    public static String getKeyAspekPemasaran(String value){
        for (Map.Entry<String, String> e : mapTypeAspekPemasaran.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeJenisUsaha(String param){
        return mapTypeAspekPemasaran.get(param);
    }

    public static String getKeyJenisUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeAspekPemasaran.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }







    public static String getTypeRpcRatio(String param){
        return mapTypeRpcRatio.get(param);
    }

    public static String getKeyRpcRatio(String value){
        for (Map.Entry<String, String> e : mapTypeRpcRatio.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }



    public static String getTypeAgunanRatio(String param){
        return mapTypeAgunanRatio.get(param);
    }

    public static String getKeyAgunanRatio(String value){
        for (Map.Entry<String, String> e : mapTypeAgunanRatio.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeCurrentRatio(String param){
        return mapTypeCurrentRatio.get(param);
    }

    public static String getKeyCurrentRatio(String value){
        for (Map.Entry<String, String> e : mapTypeCurrentRatio.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeProfitability(String param){
        return mapTypeProfitability.get(param);
    }

    public static String getKeyProfitability(String value){
        for (Map.Entry<String, String> e : mapTypeProfitability.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }





    public static String getTypeReputasiUsaha(String param){
        return mapTypeReputasiUsaha.get(param);
    }

    public static String getKeyReputasiUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeReputasiUsaha.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeRiwayatHubdgnBank(String param){
        return mapTypeRiwayatHubdgnBank.get(param);
    }

    public static String getKeyRiwayatHubdgnBank(String value){
        for (Map.Entry<String, String> e : mapTypeRiwayatHubdgnBank.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeLamaUsaha(String param){
        return mapTypeLamaUsaha.get(param);
    }

    public static String getKeyLamaUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeLamaUsaha.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeLamaUsahaKur(String param){
        return mapTypeLamaUsahaKur.get(param);
    }

    public static String getKeyLamaUsahaKur(String value){
        for (Map.Entry<String, String> e : mapTypeLamaUsahaKur.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }



    public static String getTypeProspekUsaha(String param){
        return mapTypeProspekUsaha.get(param);
    }

    public static String getKeyProspekUsaha(String value){
        for (Map.Entry<String, String> e : mapTypeProspekUsaha.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeKetergantunganSupplierdanPelanggan(String param){
        return mapTypeKetergantunganSupplierdanPelanggan.get(param);
    }

    public static String getKeyKetergantunganSupplierdanPelanggan(String value){
        for (Map.Entry<String, String> e : mapTypeKetergantunganSupplierdanPelanggan.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }



    public static String getTypeWilayahPemasaran(String param){
        return mapTypeWilayahPemasaran.get(param);
    }

    public static String getKeyWilayahPemasaran(String value){
        for (Map.Entry<String, String> e : mapTypeWilayahPemasaran.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeJenisProduk(String param){
        return mapTypeJenisProduk.get(param);
    }

    public static String getKeyJenisProduk(String value){
        for (Map.Entry<String, String> e : mapTypeJenisProduk.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeJangkaWaktu(String param){
        return mapTypeJangkaWaktu.get(param);
    }

    public static String getKeyJangkaWaktu(String value){
        for (Map.Entry<String, String> e : mapTypeJangkaWaktu.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }



    public static String getTypeJenisPembiayaan(String param){
        return mapTypeJenisPembiayaan.get(param);
    }

    public static String getKeyJenisPembiayaan(String value){
        for (Map.Entry<String, String> e : mapTypeJenisPembiayaan.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }



    public static String getTypeJenisBangunan(String param) {
        return mapTypeJenisBangunan.get(param);
    }

    public static String getKeyJenisBangunan(String value) {
        for (Map.Entry<String, String> e : mapTypeJenisBangunan.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeLokasiBangunan(String param) {
        return mapTypeLokasiBangunan.get(param);
    }

    public static String getKeyLokasiBangunan(String value) {
        for (Map.Entry<String, String> e : mapTypeLokasiBangunan.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeJenisAgunanXBRL(String param) {
        return mapTypeJenisAgunanXBRL.get(param);
    }

    public static String getKeyJenisAgunanXBRL(String value) {
        for (Map.Entry<String, String> e : mapTypeJenisAgunanXBRL.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeHubPenghuniDenganPemegangHak(String param) {
        return mapTypeHubPenghuniDenganPemegangHak.get(param);
    }

    public static String getKeyHubPenghuniDenganPemegangHak(String value) {
        for (Map.Entry<String, String> e : mapTypeHubPenghuniDenganPemegangHak.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeKondisiBangunan(String param) {
        return mapTypeKondisiBangunan.get(param);
    }

    public static String getKeyKondisiBangunan(String value) {
        for (Map.Entry<String, String> e : mapTypeKondisiBangunan.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypePondasi(String param) {
        return mapTypePondasi.get(param);
    }

    public static String getKeyPondasi(String value) {
        for (Map.Entry<String, String> e : mapTypePondasi.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeJenisBangunanSpek(String param) {
        return mapTypeJenisBangunanSpek.get(param);
    }

    public static String getKeyJenisBangunanSpek(String value) {
        for (Map.Entry<String, String> e : mapTypeJenisBangunanSpek.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeDinding(String param) {
        return mapTypeDinding.get(param);
    }

    public static String getKeyDinding(String value) {
        for (Map.Entry<String, String> e : mapTypeDinding.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeAtap(String param) {
        return mapTypeAtap.get(param);
    }

    public static String getKeyAtap(String value) {
        for (Map.Entry<String, String> e : mapTypeAtap.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }

    public static String getTypeAro(String param) {
        return mapTypeAro.get(param);
    }

    public static String getKeyAro(String value) {
        for (Map.Entry<String, String> e : mapTypeAro.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }


    public static String getTypeOperasional(String param) {
        return mapTypeOperasional.get(param);
    }

    public static String getKeyOperasional(String value) {
        for (Map.Entry<String, String> e : mapTypeOperasional.entrySet()){
            String key = e.getKey();
            String val = e.getValue();
            if (val.toString().equalsIgnoreCase(value)){
                return key;
            }
        }
        return null;
    }



}


