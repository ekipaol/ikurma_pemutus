package com.application.bris.brisi_pemutus.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Spinner;

import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.Constants;
import com.application.bris.brisi_pemutus.util.magiccrypt.MagicCrypt;


/**
 * Created by PID on 06/04/2019.
 */

public class AppPreferences {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor spEditor;
    private Context mContext;

    private MagicCrypt magicCrypt = new MagicCrypt();

    public AppPreferences(Context context) {
        this.mContext = context;
        sharedPref = mContext.getSharedPreferences(Constants.Preferences.MAIN_CONFIG, Context.MODE_PRIVATE);
        spEditor = sharedPref.edit();
    }
    //getter & setter data login
    public void setToken(String token){
        spEditor.putString(Constants.Preferences.TOKEN,AppUtil.encrypt(token));
        spEditor.commit();
    }
    public String getToken(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.TOKEN, "token"));
    }
    public void setRoleType(String roletype){
        spEditor.putString(Constants.Preferences.ROLE_TYPE,AppUtil.encrypt(roletype));
        spEditor.commit();
    }
    public String getRoleType(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.TOKEN, "token"));
    }
    public void setNamaKanwil(String namaKanwil){
        spEditor.putString(Constants.Preferences.NAMA_KANWIL,AppUtil.encrypt(namaKanwil));
        spEditor.commit();
    }
    public String getNamaKanwil(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.NAMA_KANWIL, "token"));
    }
    public void setFidKantor(String fidKantor){
        spEditor.putString(Constants.Preferences.FID_KANTOR,AppUtil.encrypt(fidKantor));
        spEditor.commit();
    }
    public String getFidkantor(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.FID_KANTOR, "token"));
    }
    public void setJabatan(String jabatan){
        spEditor.putString(Constants.Preferences.JABATAN,AppUtil.encrypt(jabatan));
        spEditor.commit();
    }
    public String getJabatan(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.JABATAN, "token"));
    }
    public void setNamaKantor(String namaKantor){
        spEditor.putString(Constants.Preferences.NAMA_KANTOR,AppUtil.encrypt(namaKantor));
        spEditor.commit();
    }
    public String getNamaKantor(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.NAMA_KANTOR, "token"));
    }
    public void setKodeSkk(String kodeSkk){
        spEditor.putString(Constants.Preferences.KODE_SKK,AppUtil.encrypt(kodeSkk));
        spEditor.commit();
    }
    public String getKodeSkk(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.KODE_SKK, "token"));
    }
    public void setDsnCode(String dsnCode){
        spEditor.putString(Constants.Preferences.DSN_CODE,AppUtil.encrypt(dsnCode));
        spEditor.commit();
    }
    public String getDsnCode(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.DSN_CODE, "dsncode"));
    }
    public void setKodeKanwil(String kodeKanwil){
        spEditor.putString(Constants.Preferences.KODE_KANWIL,AppUtil.encrypt(kodeKanwil));
        spEditor.commit();
    }
    public String getKodeKanwil(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.KODE_KANWIL, "token"));
    }
    public void setFidRole(String fidRole){
        spEditor.putString(Constants.Preferences.FID_ROLE,AppUtil.encrypt(fidRole));
        spEditor.commit();
    }
    public String getFidRole(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.FID_ROLE, "token"));
    }
    public void setUid(String uid){
        spEditor.putString(Constants.Preferences.UID,AppUtil.encrypt(uid));
        spEditor.commit();
    }
    public String getUid(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.UID, "token"));
    }
    public void setNik(String nik){
        spEditor.putString(Constants.Preferences.NIK,AppUtil.encrypt(nik));
        spEditor.commit();
    }
    public void setUsernameUserUnder(String username){
        spEditor.putString(Constants.Preferences.USERNAMEUNDER,AppUtil.encrypt(username));
        spEditor.commit();
    }
    public String getUsernameUserUnder(){
        return sharedPref.getString(Constants.Preferences.USERNAMEUNDER, "token");
    }
    public String getNik(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.NIK, "token"));
    }
    public void setNama(String nama){
        spEditor.putString(Constants.Preferences.NAMA,AppUtil.encrypt(nama));
        spEditor.commit();
    }
    public String getNama(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.NAMA, "token"));
    }
    public void setKodeCabang(String kodeCabang){
        spEditor.putString(Constants.Preferences.KODE_CABANG,AppUtil.encrypt(kodeCabang));
        spEditor.commit();
    }
    public String getKodeCabang(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.KODE_CABANG, "token"));
    }
    public void setUker(String uker){
        spEditor.putString(Constants.Preferences.UKER,AppUtil.encrypt(uker));
        spEditor.commit();
    }
    public String getUker(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.UKER, "token"));
    }
    public void setNamaSkk(String namaSkk){
        spEditor.putString(Constants.Preferences.NAMA_SKK,AppUtil.encrypt(namaSkk));
        spEditor.commit();
    }
    public String getNamaSKK(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.NAMA_SKK, "token"));
    }
    public void setKodeAo(String kodeAo){
        spEditor.putString(Constants.Preferences.KODE_AO,AppUtil.encrypt(kodeAo));
        spEditor.commit();
    }
    public String getKodeAo(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.KODE_AO, "token"));
    }
    public void setKantor(String kantor){
        spEditor.putString(Constants.Preferences.KANTOR,AppUtil.encrypt(kantor));
        spEditor.commit();
    }
    public String getKantor(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.KANTOR, "token"));
    }

    public void setLoggedin(String status){
        spEditor.putString(Constants.Preferences.IS_LOGGED_IN,AppUtil.encrypt(status));
        spEditor.commit();
    }
    public String getLoggedin(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.IS_LOGGED_IN, "no"));
    }

    public void setCbAmanah(String status){
        spEditor.putString(Constants.Preferences.CB_AMANAH,AppUtil.encrypt(status));
        spEditor.commit();
    }
    public String getCbAmanah(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.CB_AMANAH, "false"));
    }

    public void setReadPreScreening(String status){
        spEditor.putString(Constants.Preferences.READ_PRESCREENING,status);
        spEditor.commit();
    }
    public String getReadPreScreening(){
        return sharedPref.getString(Constants.Preferences.READ_PRESCREENING ,"no");
    }

    public void setReadDataLengkap(String status){
        spEditor.putString(Constants.Preferences.READ_DATA_LENGKAP,status);
        spEditor.commit();
    }
    public String getReadDataLengkap(){
        return sharedPref.getString(Constants.Preferences.READ_DATA_LENGKAP, "no");
    }
    public void setReadSektorEkonomi(String status){
        spEditor.putString(Constants.Preferences.READ_SEKTOR_EKONOMI,status);
        spEditor.commit();
    }
    public String getReadSektorEkonomi(){
        return sharedPref.getString(Constants.Preferences.READ_SEKTOR_EKONOMI, "no");
    }
    public void setReadDataFinansial(String status){
        spEditor.putString(Constants.Preferences.READ_DATA_FINANSIAL,status);
        spEditor.commit();
    }
    public String getReadDataFinansial(){
        return sharedPref.getString(Constants.Preferences.READ_DATA_FINANSIAL, "no");
    }
    public void setReadLkn(String status){
        spEditor.putString(Constants.Preferences.READ_LKN,status);
        spEditor.commit();
    }
    public String getReadLkn(){
        return sharedPref.getString(Constants.Preferences.READ_LKN, "no");
    }
    public void setReadRpc(String status){
        spEditor.putString(Constants.Preferences.READ_RPC,status);
        spEditor.commit();
    }
    public String getReadRpc(){
        return sharedPref.getString(Constants.Preferences.READ_RPC, "no");
    }
    public void setReadAgunan(String status){
        spEditor.putString(Constants.Preferences.READ_AGUNAN,status);
        spEditor.commit();
    }
    public String getReadAgunan(){
        return sharedPref.getString(Constants.Preferences.READ_AGUNAN, "no");
    }
    public void setReadScoring(String status){
        spEditor.putString(Constants.Preferences.READ_SCORING,status);
        spEditor.commit();
    }
    public String getReadScoring(){
        return sharedPref.getString(Constants.Preferences.READ_SCORING, "no");
    }
    public void setReadKelengkapan(String status){
        spEditor.putString(Constants.Preferences.READ_KELENGKAPAN,status);
        spEditor.commit();
    }
    public String getReadKelengkapan(){
        return sharedPref.getString(Constants.Preferences.READ_KELENGKAPAN, "no");
    }
    public void setIdFotoFormulir(String status){
        spEditor.putString(Constants.Preferences.ID_FOTO_FORMULIR,status);
        spEditor.commit();
    }
    public String getIdFotoFormulir(){
        return sharedPref.getString(Constants.Preferences.ID_FOTO_FORMULIR, "0");
    }




    //end of login credentials
    public void setRememberMe(String flag){
        spEditor.putString(Constants.Preferences.REMEMBER_ME, flag);
        spEditor.commit();
    }

    public String getRembemberMe(){
        return sharedPref.getString(Constants.Preferences.REMEMBER_ME, "rememberme");
    }

    public void setUsername(String username){
        spEditor.putString(Constants.Preferences.USERNAME, AppUtil.encrypt(username));
        spEditor.commit();
    }

    public String getUsername(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.USERNAME, "username"));
    }

    public void setNamaPengguna(String nama){
        spEditor.putString(Constants.Preferences.NAMA_PENGGUNA, AppUtil.encrypt(nama));
        spEditor.commit();
    }

    public String getNamaPengguna(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.NAMA_PENGGUNA, "namapengguna"));
    }

    public void setUserId(String id){
        spEditor.putString(Constants.Preferences.USER_ID, AppUtil.encrypt(id));
        spEditor.commit();
    }

    public String getUserId(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.USER_ID, "userid"));
    }


    public void setIsActivated(String flag){
        spEditor.putString(Constants.Preferences.IS_ACTIVATED, flag);
        spEditor.commit();
    }
    public String getIsActivated(){
        return sharedPref.getString(Constants.Preferences.IS_ACTIVATED, "no");
    }

    public void setStatusKodeSkkPinca(String flag){
        spEditor.putString(Constants.Preferences.STATUS_KODE_SKK_PINCA, flag);
        spEditor.commit();
    }
    public String getStatusKodeSkkPinca(){
        return sharedPref.getString(Constants.Preferences.STATUS_KODE_SKK_PINCA, "flag");
    }

    public void setStatusAmbilAlih(String flag){
        spEditor.putString(Constants.Preferences.STATUS_AMBIL_ALIH, flag);
        spEditor.commit();
    }
    public String getStatusAmbilAlih(){
        return sharedPref.getString(Constants.Preferences.STATUS_AMBIL_ALIH, "TIDAK");
    }

    public void setIdPengambilAlih(String flag){
        spEditor.putString(Constants.Preferences.ID_USER_PENGAMBIL_ALIH, AppUtil.encrypt(flag));
        spEditor.commit();
    }
    public String getIdPengambilAlih(){
        return AppUtil.decrypt(sharedPref.getString(Constants.Preferences.ID_USER_PENGAMBIL_ALIH, "0"));
    }

    public void setUpdateNotification(String filename){
        spEditor.putString(Constants.Preferences.UPDATE_NOTIFICATION, filename);
        spEditor.commit();
    }

    public String isUpdateNotificationOn(){
        return sharedPref.getString(Constants.Preferences.UPDATE_NOTIFICATION, "true");
    }

    public void setNotificationVersion(String versionNumber){
        spEditor.putString(Constants.Preferences.NOTIFICATION_VERSION, versionNumber);
        spEditor.commit();
    }

    public String getNotificationVersion(){
        return sharedPref.getString(Constants.Preferences.NOTIFICATION_VERSION, "0");
    }


    public static void clearAll(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.Preferences.MAIN_CONFIG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public void removeByKey(String key) {
        sharedPref.edit().remove(key).commit();
    }
}
