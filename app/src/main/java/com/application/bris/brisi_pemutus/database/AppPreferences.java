package com.application.bris.brisi_pemutus.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Spinner;

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
        spEditor.putString(Constants.Preferences.TOKEN,token);
        spEditor.commit();
    }
    public String getToken(){
        return sharedPref.getString(Constants.Preferences.TOKEN, "token");
    }
    public void setRoleType(String roletype){
        spEditor.putString(Constants.Preferences.ROLE_TYPE,roletype);
        spEditor.commit();
    }
    public String getRoleType(){
        return sharedPref.getString(Constants.Preferences.TOKEN, "token");
    }
    public void setNamaKanwil(String namaKanwil){
        spEditor.putString(Constants.Preferences.NAMA_KANWIL,namaKanwil);
        spEditor.commit();
    }
    public String getNamaKanwil(){
        return sharedPref.getString(Constants.Preferences.NAMA_KANWIL, "token");
    }
    public void setFidKantor(String fidKantor){
        spEditor.putString(Constants.Preferences.FID_KANTOR,fidKantor);
        spEditor.commit();
    }
    public String getFidkantor(){
        return sharedPref.getString(Constants.Preferences.FID_KANTOR, "token");
    }
    public void setJabatan(String jabatan){
        spEditor.putString(Constants.Preferences.JABATAN,jabatan);
        spEditor.commit();
    }
    public String getJabatan(){
        return sharedPref.getString(Constants.Preferences.JABATAN, "token");
    }
    public void setNamaKantor(String namaKantor){
        spEditor.putString(Constants.Preferences.NAMA_KANTOR,namaKantor);
        spEditor.commit();
    }
    public String getNamaKantor(){
        return sharedPref.getString(Constants.Preferences.NAMA_KANTOR, "token");
    }
    public void setKodeSkk(String kodeSkk){
        spEditor.putString(Constants.Preferences.KODE_SKK,kodeSkk);
        spEditor.commit();
    }
    public String getKodeSkk(){
        return sharedPref.getString(Constants.Preferences.KODE_SKK, "token");
    }
    public void setDsnCode(String dsnCode){
        spEditor.putString(Constants.Preferences.DSN_CODE,dsnCode);
        spEditor.commit();
    }
    public String getDsnCode(){
        return sharedPref.getString(Constants.Preferences.DSN_CODE, "");
    }
    public void setKodeKanwil(String kodeKanwil){
        spEditor.putString(Constants.Preferences.KODE_KANWIL,kodeKanwil);
        spEditor.commit();
    }
    public String getKodeKanwil(){
        return sharedPref.getString(Constants.Preferences.KODE_KANWIL, "token");
    }
    public void setFidRole(String fidRole){
        spEditor.putString(Constants.Preferences.FID_ROLE,fidRole);
        spEditor.commit();
    }
    public String getFidRole(){
        return sharedPref.getString(Constants.Preferences.FID_ROLE, "token");
    }
    public void setUid(String uid){
        spEditor.putString(Constants.Preferences.UID,uid);
        spEditor.commit();
    }
    public String getUid(){
        return sharedPref.getString(Constants.Preferences.UID, "token");
    }
    public void setNik(String nik){
        spEditor.putString(Constants.Preferences.NIK,nik);
        spEditor.commit();
    }
    public void setUsernameUserUnder(String username){
        spEditor.putString(Constants.Preferences.USERNAMEUNDER,username);
        spEditor.commit();
    }
    public String getUsernameUserUnder(){
        return sharedPref.getString(Constants.Preferences.USERNAMEUNDER, "token");
    }
    public String getNik(){
        return sharedPref.getString(Constants.Preferences.NIK, "token");
    }
    public void setNama(String nama){
        spEditor.putString(Constants.Preferences.NAMA,nama);
        spEditor.commit();
    }
    public String getNama(){
        return sharedPref.getString(Constants.Preferences.NAMA, "token");
    }
    public void setKodeCabang(String kodeCabang){
        spEditor.putString(Constants.Preferences.KODE_CABANG,kodeCabang);
        spEditor.commit();
    }
    public String getKodeCabang(){
        return sharedPref.getString(Constants.Preferences.KODE_CABANG, "token");
    }
    public void setUker(String uker){
        spEditor.putString(Constants.Preferences.UKER,uker);
        spEditor.commit();
    }
    public String getUker(){
        return sharedPref.getString(Constants.Preferences.UKER, "token");
    }
    public void setNamaSkk(String namaSkk){
        spEditor.putString(Constants.Preferences.NAMA_SKK,namaSkk);
        spEditor.commit();
    }
    public String getNamaSKK(){
        return sharedPref.getString(Constants.Preferences.NAMA_SKK, "token");
    }
    public void setKodeAo(String kodeAo){
        spEditor.putString(Constants.Preferences.KODE_AO,kodeAo);
        spEditor.commit();
    }
    public String getKodeAo(){
        return sharedPref.getString(Constants.Preferences.KODE_AO, "token");
    }
    public void setKantor(String kantor){
        spEditor.putString(Constants.Preferences.KANTOR,kantor);
        spEditor.commit();
    }
    public String getKantor(){
        return sharedPref.getString(Constants.Preferences.KANTOR, "token");
    }

    public void setLoggedin(String status){
        spEditor.putString(Constants.Preferences.IS_LOGGED_IN,status);
        spEditor.commit();
    }
    public String getLoggedin(){
        return sharedPref.getString(Constants.Preferences.IS_LOGGED_IN, "no");
    }




    //end of login credentials
    public void setRememberMe(String flag){
        spEditor.putString(Constants.Preferences.REMEMBER_ME, flag);
        spEditor.commit();
    }

    public String getRembemberMe(){
        return sharedPref.getString(Constants.Preferences.REMEMBER_ME, "");
    }

    public void setUsername(String username){
        spEditor.putString(Constants.Preferences.USERNAME, username);
        spEditor.commit();
    }

    public String getUsername(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.USERNAME, ""));
    }

    public void setNamaPengguna(String nama){
        spEditor.putString(Constants.Preferences.NAMA_PENGGUNA, nama);
        spEditor.commit();
    }

    public String getNamaPengguna(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.NAMA_PENGGUNA, ""));
    }

    public void setUserId(String id){
        spEditor.putString(Constants.Preferences.USER_ID, id);
        spEditor.commit();
    }

    public String getUserId(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.USER_ID, ""));
    }

    public void setKodePdam(String kodePdam){
        spEditor.putString(Constants.Preferences.KODE_PDAM, kodePdam);
        spEditor.commit();
    }

    public String getKodePdam(){
        return magicCrypt.decrypt(sharedPref.getString(Constants.Preferences.KODE_PDAM, ""));
    }

    public void setFotoNasabahName(String filename){
        spEditor.putString(Constants.Preferences.FOTO_NASABAH_NAME, filename);
        spEditor.commit();
    }

    public String getFotoNasabahName(){
        return sharedPref.getString(Constants.Preferences.FOTO_NASABAH_NAME, "");
    }

    public void setIsActivated(String flag){
        spEditor.putString(Constants.Preferences.IS_ACTIVATED, flag);
        spEditor.commit();
    }
    public String getIsActivated(){
        return sharedPref.getString(Constants.Preferences.IS_ACTIVATED, "");
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
