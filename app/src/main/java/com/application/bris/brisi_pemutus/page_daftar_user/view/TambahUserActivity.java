package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.data_cabang.RequestDataCabang;
import com.application.bris.brisi_pemutus.api.model.request.insert_update_aom.InsertUpdateAom;
import com.application.bris.brisi_pemutus.api.model.request.req_kode_skk.ReqKodeSkk;
import com.application.bris.brisi_pemutus.api.model.request.req_nik.ReqNik;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.PasswordNewAo;
import com.application.bris.brisi_pemutus.model.data_cabang.Cabang;
import com.application.bris.brisi_pemutus.model.data_cabang.CabangModel;
import com.application.bris.brisi_pemutus.model.kantor_cabang.KantorCabang;
import com.application.bris.brisi_pemutus.model.ums.Ums;
import com.application.bris.brisi_pemutus.model.user_mip.UserMip;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;
import info.hoang8f.widget.FButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TambahUserActivity extends AppCompatActivity {

    TextView info_notice;
    ExtendedEditText namaPegawai, nik, username, no_sk;
    //    ExtendedEditText limit;
    TextFieldBoxes boxNamaPegawai, boxNik, boxUsername, boxLimit, boxNoSk;
    MaterialSpinner role, kantor,spinnerUms;
    FButton simpan, cekUser;
    ApiClientAdapter apiClientAdapter;
    PasswordNewAo passwordNewAo;
    Call<ParseResponse> call;
    RelativeLayout loading;
    LinearLayout content, boxRole, boxKantor,boxUms;
    List<Cabang> dataCabang;
    List<Ums> dataUms;
    List<UserMip> dataMip;
    List<String> dataCabangStringTotal;
    List<String> dataUkerCabangStringTotal;
    List<String> kodeCabangStringTotal;
    List<String> kodeUkerCabangStringTotal;
    AppPreferences appPreferences;

    List<String> dataCabangString;
    List<String> kodeCabangString;
    CabangModel dataCabangSingular;

    List<String> kodeUmsString;
    List<String> namaUmsString;
    List<String> jumlahUhUmsString;


    List<KantorCabang> kantorCabang;

    int jumlahMmm, jumlahAdp, jumlahMo, jumlahUh, jumlahBos, jumlahCs;
    boolean selesaiUker=false;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_user);

        appPreferences = new AppPreferences(this);

        //instantiate objects
        loading = findViewById(R.id.progressbar_loading);

        content = findViewById(R.id.ll_content_tambah_user);
        content.setVisibility(View.GONE);
        namaPegawai = findViewById(R.id.extended_nama);
        nik = findViewById(R.id.extended_nik);
        username = findViewById(R.id.extended_username);
//        limit=findViewById(R.id.extended_limit);
//        limit.addTextChangedListener(new NumberTextWatcherCanNolForThousand(limit));
        no_sk = findViewById(R.id.extended_no_sk_uh);
        info_notice = findViewById(R.id.info_tambah_user);

        role = findViewById(R.id.spMaterial_role);
        kantor = findViewById(R.id.spMaterial_kantor);
        spinnerUms = findViewById(R.id.spMaterial_ums);
        boxRole = findViewById(R.id.ll_role_tambah_user);
        boxKantor = findViewById(R.id.ll_kantor_tambah_user);
        boxUms = findViewById(R.id.ll_kantor_tambah_ums);

        boxNik = findViewById(R.id.text_nik);
        boxNamaPegawai = findViewById(R.id.text_nama);
        boxUsername = findViewById(R.id.text_username);
//        boxLimit=findViewById(R.id.text_limit);
        boxNoSk = findViewById(R.id.text_no_sk_uh);

        //HIDE SEMUA FIELD, BIAR CEK NIK DULU
        boxNamaPegawai.setVisibility(View.GONE);
        boxUsername.setVisibility(View.GONE);
//        boxLimit.setVisibility(View.GONE);
        boxNoSk.setVisibility(View.GONE);
        boxRole.setVisibility(View.GONE);
        boxKantor.setVisibility(View.GONE);

        //button settings

        simpan = findViewById(R.id.btflatSimpan);
        simpan.setButtonColor(getResources().getColor(R.color.colorPrimary));

        cekUser = findViewById(R.id.btflatCekUser);
        cekUser.setButtonColor(getResources().getColor(R.color.colorPrimary));

        apiClientAdapter = new ApiClientAdapter(this);

        AppUtil.toolbarRegular(this, "Tambah User");

        boxNoSk.setVisibility(View.GONE);
//        boxLimit.setVisibility(View.GONE);

        //seleksi role
        final AppPreferences app_pref = new AppPreferences(this);
        if (app_pref.getFidRole().equalsIgnoreCase("76")) { //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pinca, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        } else if (app_pref.getFidRole().equalsIgnoreCase("79")) { //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pincapem_m3, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }
//        else if (app_pref.getJabatan().equalsIgnoreCase("mmm")) { //make sure which roletype is "pincapem"
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pincapem_m3, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            role.setAdapter(adapter);
//        } else if (app_pref.getJabatan().equalsIgnoreCase("marketing manager")) { //make sure which roletype is "pincapem"
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_mm, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            role.setAdapter(adapter);
//        } else if (app_pref.getJabatan().equalsIgnoreCase("ums head")) { //make sure which roletype is "pincapem"
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_UH, android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            role.setAdapter(adapter);
//        }

        //connect to server
        loading.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);

        final AppPreferences apppref = new AppPreferences(this);
        RequestDataCabang req = new RequestDataCabang();
//        req.setKodeCabang(apppref.getKodeCabang());
        req.setKodeCabang(apppref.getKodeSkk());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dataCabang(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                if (response.isSuccessful()) {
                    loading.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE);
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Cabang>>() {
                        }.getType();

                        dataCabang = gson.fromJson(listDataString, type);
                        dataCabangString = new ArrayList<>();
                        kodeCabangString = new ArrayList<>();
                        dataCabangSingular = new CabangModel(TambahUserActivity.this);
                        dataCabangSingular.clear();
                        for (int i = 0; i < dataCabang.size(); i++) {
                            dataCabangString.add(dataCabang.get(i).getSb_desc());
                            dataCabangSingular.addCabang(dataCabang.get(i));
                            kodeCabangString.add(dataCabang.get(i).getKode_lengkap());

                            try {
                                Log.d("ebdesc ekipaol", dataCabang.get(i).getSb_desc());
                                adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                                kantor.setAdapter(adapter);

                                selesaiUker=true;

                                //                Log.d("selesaiUker",String.valueOf(selesaiUker));
                                kantor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        if(selesaiUker){
                                            Log.d("inilahPosisiKantor", Integer.toString(kantor.getSelectedItemPosition()));
                                            loading.setVisibility(View.VISIBLE);
                                            content.setVisibility(View.GONE);
                                            ReqKodeSkk req = new ReqKodeSkk();

                                            int kodeSkkManual=kantor.getSelectedItemPosition();

//                                Log.d("kantorselectedeki",kantor.getSelectedItem().toString());\

                                            //ini solusi untuk index out of bound waktu dilakukan minus 1, dibikin if kalau indeksnya 0 ambil nomor satu
                                            if(kantor.getSelectedItemPosition()==0){
                                                req.setKodeSkk((kodeCabangString.get(0)));
                                            }
                                            else{
                                                req.setKodeSkk((kodeCabangString.get(kantor.getSelectedItemPosition()-1)));
                                            }
//                            req.setKodeSkk((kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition()-1)));
                                            Call<ParseResponse> call = apiClientAdapter.getApiInterface().listUms(req);
                                            call.enqueue(new Callback<ParseResponse>() {
                                                @Override
                                                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                                    if (response.isSuccessful()) {
                                                        loading.setVisibility(View.GONE);
                                                        content.setVisibility(View.VISIBLE);
                                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                                            String listDataString = response.body().getData().get("listUms").toString();
                                                            Gson gson = new Gson();
                                                            Type type = new TypeToken<List<Ums>>() {
                                                            }.getType();

                                                            dataUms = gson.fromJson(listDataString, type);
                                                            kodeUmsString = new ArrayList<>();
                                                            namaUmsString = new ArrayList<>();
                                                            jumlahUhUmsString = new ArrayList<>();


//                                dataCabangSingular = new CabangModel(TambahUserActivity.this);
//                                dataCabangSingular.clear();
                                                            for (int i = 0; i < dataUms.size(); i++) {
                                                                kodeUmsString.add(dataUms.get(i).getKODE_SKK());
                                                                namaUmsString.add(dataUms.get(i).getKETERANGAN_SKK());
                                                                jumlahUhUmsString.add(dataUms.get(i).getJUMLAH_UH());
//                                                                Log.d("inilahNamaUker", dataUkerCabangStringTotal.get(i));
//                                    kodeCabangString.add(dataCabang.get(i).getKode_lengkap());


                                                            }

                                                            try {
//                                                    Log.d("ebdesc ekipaol", dataCabang.get(i).getSb_desc());
                                                                adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, namaUmsString);
                                                                spinnerUms.setAdapter(adapter);
                                                            } catch (Exception e) {

                                                            }
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<ParseResponse> call, Throwable t) {
                                                    Log.d("LOG D", t.getMessage());
                                                }
                                            });
                                        }


                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });
                                spinnerUms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if(spinnerUms.getSelectedItemPosition()==0){
                                            jumlahUh=Integer.parseInt(jumlahUhUmsString.get(0));
                                        }
                                        else{
                                            jumlahUh=Integer.parseInt(jumlahUhUmsString.get(spinnerUms.getSelectedItemPosition()-1));
                                        }

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });

//        getCountUser();
        if (apppref.getFidRole().equalsIgnoreCase("76")) {
            getUkerPinca();

//                Log.d("selesaiUker",String.valueOf(selesaiUker));
                kantor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        if(selesaiUker){
                            Log.d("inilahPosisiKantor", Integer.toString(kantor.getSelectedItemPosition()));
                            loading.setVisibility(View.VISIBLE);
                            content.setVisibility(View.GONE);
                            ReqKodeSkk req = new ReqKodeSkk();

                            int kodeSkkManual=kantor.getSelectedItemPosition();

//                                Log.d("kantorselectedeki",kantor.getSelectedItem().toString());\

                            //ini solusi untuk index out of bound waktu dilakukan minus 1, dibikin if kalau indeksnya 0 ambil nomor satu
                            if(kantor.getSelectedItemPosition()==0){
                                req.setKodeSkk((kodeUkerCabangStringTotal.get(0)));
                            }
                            else{
                                req.setKodeSkk((kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition()-1)));
                            }
//                            req.setKodeSkk((kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition()-1)));
                            Call<ParseResponse> call = apiClientAdapter.getApiInterface().listUms(req);
                            call.enqueue(new Callback<ParseResponse>() {
                                @Override
                                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                    if (response.isSuccessful()) {
                                        loading.setVisibility(View.GONE);
                                        content.setVisibility(View.VISIBLE);
                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            String listDataString = response.body().getData().get("listUms").toString();
                                            Gson gson = new Gson();
                                            Type type = new TypeToken<List<Ums>>() {
                                            }.getType();

                                            dataUms = gson.fromJson(listDataString, type);
                                            kodeUmsString = new ArrayList<>();
                                            namaUmsString = new ArrayList<>();
                                            jumlahUhUmsString = new ArrayList<>();


//                                dataCabangSingular = new CabangModel(TambahUserActivity.this);
//                                dataCabangSingular.clear();
                                            for (int i = 0; i < dataUms.size(); i++) {
                                                kodeUmsString.add(dataUms.get(i).getKODE_SKK());
                                                namaUmsString.add(dataUms.get(i).getKETERANGAN_SKK());
                                                jumlahUhUmsString.add(dataUms.get(i).getJUMLAH_UH());
                                                Log.d("inilahNamaUker", dataUkerCabangStringTotal.get(i));
//                                    kodeCabangString.add(dataCabang.get(i).getKode_lengkap());


                                            }

                                            try {
//                                                    Log.d("ebdesc ekipaol", dataCabang.get(i).getSb_desc());
                                                adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, namaUmsString);
                                                spinnerUms.setAdapter(adapter);
                                            } catch (Exception e) {

                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ParseResponse> call, Throwable t) {
                                    Log.d("LOG D", t.getMessage());
                                }
                            });
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinnerUms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(spinnerUms.getSelectedItemPosition()==0){
                            jumlahUh=Integer.parseInt(jumlahUhUmsString.get(0));
                        }
                        else{
                            jumlahUh=Integer.parseInt(jumlahUhUmsString.get(spinnerUms.getSelectedItemPosition()-1));
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

        }

        role.setSelection(1);
        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (role.getSelectedItem() == null) {
//                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
//                    kantor.setAdapter(adapter);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("mmm")) {
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                    kantor.setAdapter(adapter);
                    boxNoSk.setVisibility(View.GONE);
                    boxUms.setVisibility(View.GONE);
//                    boxLimit.setVisibility(View.VISIBLE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("pincapem")) {
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangStringTotal);
                    kantor.setAdapter(adapter);
                    boxNoSk.setVisibility(View.GONE);
                    boxUms.setVisibility(View.GONE);
//                    boxLimit.setVisibility(View.VISIBLE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("adp")) {
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                    kantor.setAdapter(adapter);
                    boxNoSk.setVisibility(View.GONE);
                    boxUms.setVisibility(View.GONE);
//                    boxLimit.setVisibility(View.VISIBLE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("mo")) {
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                    kantor.setAdapter(adapter);
                    boxNoSk.setVisibility(View.GONE);
                    boxUms.setVisibility(View.GONE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("uh")) {
                    //semua uker dibawah nya + kc
                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.VISIBLE);
                        boxUms.setVisibility(View.VISIBLE);
                    } else {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.VISIBLE);
                        boxUms.setVisibility(View.VISIBLE);
                    }
//                       boxLimit.setVisibility(View.VISIBLE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("bos")) {
                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.GONE);
                        boxUms.setVisibility(View.GONE);
                    } else {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.GONE);
                        boxUms.setVisibility(View.GONE);
                    }
//                       boxLimit.setVisibility(View.VISIBLE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("cs")) {
                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.GONE);
                        boxUms.setVisibility(View.GONE);
                    } else {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.GONE);
                    }
//                       boxLimit.setVisibility(View.VISIBLE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("ao")) {
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                    kantor.setAdapter(adapter);
                    boxNoSk.setVisibility(View.GONE);
                    boxUms.setVisibility(View.GONE);
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("aom")) {
                    //semua uker dibawah nya + kc
                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataUkerCabangStringTotal);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.GONE);
                        boxUms.setVisibility(View.VISIBLE);

                    } else {
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                        kantor.setAdapter(adapter);
                        boxNoSk.setVisibility(View.GONE);
                        boxUms.setVisibility(View.VISIBLE);
                    }
//                       boxLimit.setVisibility(View.VISIBLE);
                }
//                else {
//                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
//                    kantor.setAdapter(adapter);
//                    kantor.setSelection(1);
//                    boxNoSk.setVisibility(View.GONE);
////                    boxLimit.setVisibility(View.GONE);
//                }
                username.setText(role.getSelectedItem().toString() + nik.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //on click tombol cek user
        cekUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nik.getText().toString().isEmpty()) {
                    boxNik.setError("Harap isi nik terlebih dahulu", true);
                }
                else if(nik.getText().length()!=6){
                    boxNik.setError("Pastikan NIK 6 digit", true);
                }
                else {
                    cekUserMip();
                }
            }
        });


        //tambah user jika tombol simpan di klik
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("si bang", Integer.toString(role.getCount()));
                if (role.getSelectedItem() == null) {
                    role.setError("Harap pilih role user");

                } else if (kantor.getSelectedItem() == null) {
                    kantor.setError("Harap pilih kantor user");
                } else if (nik.getText().toString().isEmpty()) {
                    boxNik.setError("Harap diisi", true);

                } else if (namaPegawai.getText().toString().isEmpty()) {
                    boxNamaPegawai.setError("Harap diisi", true);

                } else if (username.getText().toString().isEmpty()) {
                    boxUsername.setError("Harap diisi", true);

                }
//                else if (role.getSelectedItem().toString().equalsIgnoreCase("pincapem")) {
////                    if (limit.getText().toString().isEmpty()) {
////                        boxLimit.setError("Harap diisi", true);
////                    } else {
//                        final SweetAlertDialog dialogKonfirm2 = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
//                        dialogKonfirm2.setTitleText("Konfirmasi")
//                                .setContentText("Anda menambahkan " +
//                                        "\nNama : " + namaPegawai.getText().toString() + "" +
//                                        "\nNIK : " + nik.getText().toString() +
//                                        "\nRole : " + role.getSelectedItem().toString() + "" +
//                                        "\nKantor: " + kantor.getSelectedItem().toString() + "" +
//                                        "\nUsername: " + username.getText().toString() + "")
////                                        "\nLimit: Rp." + limit.getText().toString() + "")
//                                .setConfirmText("Konfirmasi")
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        dialogKonfirm2.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//                                        dialogKonfirm2.setContentText("");
//                                        insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm2);
//
//                                    }
//                                }).setCancelText("Batal")
//                                .show();
////                    }
//                }
                else if (role.getSelectedItem().toString().equalsIgnoreCase("uh")) {
                    if (no_sk.getText().toString().isEmpty()) {
                        boxNoSk.setError("Harap diisi", true);
                    }
                    else if(spinnerUms.getSelectedItem().toString().isEmpty()){
                      spinnerUms.setError("Harap pilih");
                    }
                      else {
//                        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        if (apppref.getFidRole().equalsIgnoreCase("76")) {
                            //array ke-0 kantor cabang dia sendiri
                            if (!kantor.getSelectedItem().toString().equalsIgnoreCase(dataUkerCabangStringTotal.get(0))) {
                                getCountUser(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "uh");
                            } else {
                                getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "uh");
//                                getCountUser("uh");
//                                dialogKonfirm.setTitleText("Konfirmasi")
//                                        .setContentText("Anda menambahkan " +
//                                                "\nNama : " + namaPegawai.getText().toString() + "" +
//                                                "\nNIK : " + nik.getText().toString() +
//                                                "\nRole : " + role.getSelectedItem().toString() + "" +
//                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
//                                                "\nUsername: " + username.getText().toString() + "" +
//                                                "\nNo Sk : " + no_sk.getText().toString())
//                                        .setConfirmText("Konfirmasi")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//                                                dialogKonfirm.setContentText("");
//                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
//
//                                            }
//                                        }).setCancelText("Batal")
//                                        .show();
                            }
                        } else {
                            getCountUser(kodeCabangString.get(kantor.getSelectedItemPosition() - 1), "uh");
                        }
                    }
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("mmm")) {
//                    final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);

                    getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "mmm");
//                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
//                        if (!kantor.getSelectedItem().toString().equalsIgnoreCase(dataUkerCabangStringTotal.get(0))) {
//                            getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "mmm");
//                        } else {
//                            getCountUser("");
////                                dialogKonfirm.setTitleText("Konfirmasi")
////                                        .setContentText("Anda menambahkan " +
////                                                "\nNama : " + namaPegawai.getText().toString() + "" +
////                                                "\nNIK : " + nik.getText().toString() +
////                                                "\nRole : " + role.getSelectedItem().toString() + "" +
////                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
////                                                "\nUsername : " + username.getText().toString())
////                                        .setConfirmText("Konfirmasi")
////                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                            @Override
////                                            public void onClick(SweetAlertDialog sDialog) {
////                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
////                                                dialogKonfirm.setContentText("");
////                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
////
////                                            }
////                                        }).setCancelText("Batal")
////                                        .show();
//                        }
//                    } else {
//                        getCountUser("");
////                            dialogKonfirm.setTitleText("Konfirmasi")
////                                    .setContentText("Anda menambahkan " +
////                                            "\nNama : " + namaPegawai.getText().toString() + "" +
////                                            "\nNIK : " + nik.getText().toString() +
////                                            "\nRole : " + role.getSelectedItem().toString() + "" +
////                                            "\nKantor: " + kantor.getSelectedItem().toString() + "" +
////                                            "\nUsername : " + username.getText().toString())
////                                    .setConfirmText("Konfirmasi")
////                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
////                                        @Override
////                                        public void onClick(SweetAlertDialog sDialog) {
////                                            dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
////                                            dialogKonfirm.setContentText("");
////                                            insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
////
////                                        }
////                                    }).setCancelText("Batal")
////                                    .show();
//                    }
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("pincapem")) {
                    getCountUserPinca(kodeCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "pincapem");
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("adp")) {
                    getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "adp");
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("mo")) {
                    getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "mo");
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("bos")) {
                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
                        if (!kantor.getSelectedItem().toString().equalsIgnoreCase(dataUkerCabangStringTotal.get(0))) {
                            getCountUser(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "bos");
                        } else {
                            getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "bos");
//                            getCountUser("bos");
                        }
                    } else {
                        getCountUser(kodeCabangString.get(kantor.getSelectedItemPosition() - 1), "bos");
                    }
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("cs")) {
                    if (apppref.getFidRole().equalsIgnoreCase("76")) {
                        if (!kantor.getSelectedItem().toString().equalsIgnoreCase(dataUkerCabangStringTotal.get(0))) {
                            getCountUser(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "cs");
                        } else {
                            getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "cs");
//                            getCountUser("cs");
                        }
                    } else {
                        getCountUser(kodeCabangString.get(kantor.getSelectedItemPosition() - 1), "cs");
                    }
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("ao")) {
                    getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "ao");
                } else if (role.getSelectedItem().toString().equalsIgnoreCase("aom")) {
                    if (spinnerUms.getSelectedItem().toString().isEmpty()) {
                        spinnerUms.setError("Harap pilih");
                    } else {
                        if (apppref.getFidRole().equalsIgnoreCase("76")) {
                            if (!kantor.getSelectedItem().toString().equalsIgnoreCase(dataUkerCabangStringTotal.get(0))) {
                                getCountUser(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "aom");
                            } else {
                                getCountUserPinca(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "aom");
//                            getCountUser("aom");
                            }
                        } else {
                            getCountUser(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1), "aom");
                        }
                    }
                }
//                    else {
//                        dialogKonfirm.setTitleText("Konfirmasi")
//                                .setContentText("Anda menambahkan " +
//                                        "\nNama : " + namaPegawai.getText().toString() + "" +
//                                        "\nNIK : " + nik.getText().toString() +
//                                        "\nRole : " + role.getSelectedItem().toString() + "" +
//                                        "\nKantor: " + kantor.getSelectedItem().toString() + "" +
//                                        "\nUsername : " + username.getText().toString())
//                                .setConfirmText("Konfirmasi")
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sDialog) {
//                                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//                                        dialogKonfirm.setContentText("");
//                                        insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
//
//                                    }
//                                }).setCancelText("Batal")
//                                .show();
//                    }
                //logic menyimpan jika semua field sudah diisi

//                     AlertDialog.Builder builder = new AlertDialog.Builder(TambahUserActivity.this);
//
//                     builder.setTitle("Konfirmasi");
//                     builder.setMessage("Anda menambahkan " +
//                             "\nNama : "+namaPegawai.getText().toString()+"" +
//                             "\nNIK : "+nik.getText().toString()+
//                             "\nRole : "+role.getSelectedItem().toString()+"" +
//                             "\nKantor: "+kantor.getSelectedItem().toString()+"" +
//                             "\nUsername : "+username.getText().toString()+
//                             "\nLimit : "+limit.getText().toString());
//                     builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
//                         public void onClick(DialogInterface dialog, int id) {
//
//                             //simpan database jika oke
//                             Toast.makeText(TambahUserActivity.this, "Data user tersimpan", Toast.LENGTH_SHORT).show();
//                         }
//                     });
//                     builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                         public void onClick(DialogInterface dialog, int id) {
//                             // User cancelled the dialog
//                         }
//                     });
//                     builder.create();
//                     builder.show();
//                    final SweetAlertDialog dialog1=new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//                    dialog1.setTitleText("Harap tunggu");
//                    dialog1.setCancelable(false);
//                    dialog1.getProgressHelper().setBarColor(Color.parseColor("#fd9c00"));
//                    dialog1.show();

//                    final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
//                    dialogKonfirm.setTitleText("Konfirmasi")
//                            .setContentText("Anda menambahkan " +
//                                    "\nNama : " + namaPegawai.getText().toString() + "" +
//                                    "\nNIK : " + nik.getText().toString() +
//                                    "\nRole : " + role.getSelectedItem().toString() + "" +
//                                    "\nKantor: " + kantor.getSelectedItem().toString() + "" +
//                                    "\nUsername : " + username.getText().toString())
//                            .setConfirmText("Konfirmasi")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//                                    dialogKonfirm.setContentText("");
//                                    insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
//
//                                }
//                            }).setCancelText("Batal")
//                            .show();

            }
        });
    }

    public void insertUpdateAom(String nama, String nik, final String username, final SweetAlertDialog dialog) {
        apiClientAdapter = new ApiClientAdapter(this);
        AppPreferences apppref = new AppPreferences(TambahUserActivity.this);
        //  progressbar_loading.setVisibility(View.VISIBLE);
        InsertUpdateAom req = new InsertUpdateAom();
        req.setNama(nama);
        req.setUid(0); //UID diisi 0 jika ingin insert, diisi selain 0 jika update
        req.setNoPegawai(nik);
        req.setUsername(username);
        req.setPassword("");

        //karena yang keluar option kantor hanya jika membuat user pincapem, maka ada kodecabang dikirim berdasarkan kantor yang dipilih
        //selain pembuata user pincapem, maka akan dikirim kodecabang yang sama dengan user yang login.
        Log.d("ekipaolroleselected", role.getSelectedItem().toString());
//        if(role.getSelectedItem().toString().equalsIgnoreCase("pincapem")){
//            req.setKodeCabang(kodeCabangStringTotal.get(kantor.getSelectedItemPosition()-1));
//        }
//        else{
//            req.setKodeCabang(apppref.getKodeCabang());
//        }

//        req.setKodeCabang(apppref.getKodeSkk());
        if (apppref.getFidRole().equalsIgnoreCase("79") ||
                apppref.getFidRole().equalsIgnoreCase("71") ||
                apppref.getFidRole().equalsIgnoreCase("72")) {
            req.setKodeCabang(apppref.getKodeSkk());
        } else if (apppref.getFidRole().equalsIgnoreCase("76")) {
            if (role.getSelectedItem().toString().equalsIgnoreCase("mmm") ||
                    role.getSelectedItem().toString().equalsIgnoreCase("adp") ||
                    role.getSelectedItem().toString().equalsIgnoreCase("mo") ||
                    role.getSelectedItem().toString().equalsIgnoreCase("ao")) {
                req.setKodeCabang(apppref.getKodeSkk());
            } else {
                if (role.getSelectedItem().toString().equalsIgnoreCase("uh") ||
                        role.getSelectedItem().toString().equalsIgnoreCase("cs") ||
                        role.getSelectedItem().toString().equalsIgnoreCase("bos") ||
                        role.getSelectedItem().toString().equalsIgnoreCase("aom")) {
                    req.setKodeCabang(kodeUkerCabangStringTotal.get(kantor.getSelectedItemPosition() - 1));
                } else {
                    req.setKodeCabang(kodeCabangStringTotal.get(kantor.getSelectedItemPosition() - 1));
                }
            }
        }

        req.setUidCreator(Integer.parseInt(apppref.getUid()));

        //fid role conditioning
        if (role.getSelectedItem().toString().equalsIgnoreCase("mmm")) {
            req.setFid_role(72);
            insertOperation(username, dialog, req);

//            if (kantorCabang.get(kantor.getSelectedItemPosition() - 1).getJumlah_pincapem_aktif().equalsIgnoreCase("1")) {
//                Toast.makeText(this, kantorCabang.get(kantor.getSelectedItemPosition() - 1).getSb_desc(), Toast.LENGTH_SHORT).show();
//                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                dialog.setTitleText("Gagal");
//                dialog.setContentText("Sudah ada MMM yang aktif, harap nonaktifkan MMM terlebih dahulu sebelum menambah MMM baru\n");
//                dialog.setConfirmText("Ok");
//                dialog.showCancelButton(false);
//                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.dismissWithAnimation();
//                        recreate();
//                    }
//                });
//            } else {
//                insertOperation(username, dialog, req);
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("pincapem")) {
            req.setFid_role(79);
            Log.d("kodeCabangSETREQ", kodeCabangStringTotal.get(kantor.getSelectedItemPosition() - 1));
//            req.setKodeCabang(kodeCabangStringTotal.get(kantor.getSelectedItemPosition() - 1));
            insertOperation(username, dialog, req);

//            if (kantorCabang.get(kantor.getSelectedItemPosition() - 1).getJumlah_pincapem_aktif().equalsIgnoreCase("1")) {
//                Toast.makeText(this, kantorCabang.get(kantor.getSelectedItemPosition() - 1).getSb_desc(), Toast.LENGTH_SHORT).show();
//                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                dialog.setTitleText("Gagal");
//                dialog.setContentText("Sudah ada PINCAPEM yang aktif, harap nonaktifkan PINCAPEM terlebih dahulu sebelum menambah PINCAPEM baru\n");
//                dialog.setConfirmText("Ok");
//                dialog.showCancelButton(false);
//                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.dismissWithAnimation();
//                        recreate();
//                    }
//                });
//            } else {
//                insertOperation(username, dialog, req);
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("adp")) {
            req.setFid_role(22);
            insertOperation(username, dialog, req);

//            if (kantorCabang.get(kantor.getSelectedItemPosition() - 1).getJumlah_pincapem_aktif().equalsIgnoreCase("1")) {
//                Toast.makeText(this, kantorCabang.get(kantor.getSelectedItemPosition() - 1).getSb_desc(), Toast.LENGTH_SHORT).show();
//                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                dialog.setTitleText("Gagal");
//                dialog.setContentText("Sudah ada ADP yang aktif, harap nonaktifkan ADP terlebih dahulu sebelum menambah ADP baru\n");
//                dialog.setConfirmText("Ok");
//                dialog.showCancelButton(false);
//                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.dismissWithAnimation();
//                        recreate();
//                    }
//                });
//            } else {
//                insertOperation(username, dialog, req);
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("mo")) {
            req.setFid_role(25);
            insertOperation(username, dialog, req);

//            if (kantorCabang.get(kantor.getSelectedItemPosition() - 1).getJumlah_pincapem_aktif().equalsIgnoreCase("1")) {
//                Toast.makeText(this, kantorCabang.get(kantor.getSelectedItemPosition() - 1).getSb_desc(), Toast.LENGTH_SHORT).show();
//                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                dialog.setTitleText("Gagal");
//                dialog.setContentText("Sudah ada MO yang aktif, harap nonaktifkan MO terlebih dahulu sebelum menambah MO baru\n");
//                dialog.setConfirmText("Ok");
//                dialog.showCancelButton(false);
//                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.dismissWithAnimation();
//                        recreate();
//                    }
//                });
//            } else {
//                insertOperation(username, dialog, req);
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("uh")) {
            req.setFid_role(71);
            req.setNoSk(no_sk.getText().toString());

            if(spinnerUms.getSelectedItemPosition()==0){
                req.setKodeCabang(kodeUmsString.get(0));
            }
            else{
                req.setKodeCabang(kodeUmsString.get(spinnerUms.getSelectedItemPosition()-1));
            }

            insertOperation(username, dialog, req);
//            if (apppref.getFidRole().equalsIgnoreCase("76")) {
//                insertOperation(username, dialog, req);
//            } else {
//                 else{
//                    insertOperation(username, dialog, req);
//                }
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("bos")) {
            req.setFid_role(26);
            insertOperation(username, dialog, req);
//            if (jumlahBos >= 1) {
//                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                dialog.setTitleText("Gagal");
//                dialog.setContentText("Sudah ada 1 BOS yang aktif, harap nonaktifkan BOS terlebih dahulu sebelum menambah BOS baru\n");
//                dialog.setConfirmText("Ok");
//                dialog.showCancelButton(false);
//                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.dismissWithAnimation();
//                        recreate();
//                    }
//                });
//            } else {
//                insertOperation(username, dialog, req);
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("cs")) {
            req.setFid_role(23);
            insertOperation(username, dialog, req);
//            if (apppref.getFidRole().equalsIgnoreCase("76")) {
////                req.setKodeCabang(kodeCabangStringTotal.get(kantor.getSelectedItemPosition() - 1));
//                getCountUserPinca(kodeCabangStringTotal.get(kantor.getSelectedItemPosition() - 1));
//
//                Log.d("Jumlah CS 1", String.valueOf(jumlahCs));
//            }
//            if (apppref.getFidRole().equalsIgnoreCase("76")) {
//                Log.d("Jumlah CS", String.valueOf(jumlahCs));
//                insertOperation(username, dialog, req);
//            } else {
//                if (jumlahCs >= 1) {
//                    dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                    dialog.setTitleText("Gagal");
//                    dialog.setContentText("Sudah ada 1 CS yang aktif, harap nonaktifkan CS terlebih dahulu sebelum menambah CS baru\n");
//                    dialog.setConfirmText("Ok");
//                    dialog.showCancelButton(false);
//                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                        @Override
//                        public void onClick(SweetAlertDialog sweetAlertDialog) {
//                            dialog.dismissWithAnimation();
//                            recreate();
//                        }
//                    });
//                } else {
//                    insertOperation(username, dialog, req);
//                }
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("ao")) {
            req.setFid_role(100);
            insertOperation(username, dialog, req);

//            if (kantorCabang.get(kantor.getSelectedItemPosition() - 1).getJumlah_pincapem_aktif().equalsIgnoreCase("1")) {
//                Toast.makeText(this, kantorCabang.get(kantor.getSelectedItemPosition() - 1).getSb_desc(), Toast.LENGTH_SHORT).show();
//                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                dialog.setTitleText("Gagal");
//                dialog.setContentText("Sudah ada AO yang aktif, harap nonaktifkan AO terlebih dahulu sebelum menambah AO baru\n");
//                dialog.setConfirmText("Ok");
//                dialog.showCancelButton(false);
//                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        dialog.dismissWithAnimation();
//                        recreate();
//                    }
//                });
//            } else {
//                insertOperation(username, dialog, req);
//            }
        } else if (role.getSelectedItem().toString().equalsIgnoreCase("aom")) {
            req.setFid_role(8);
            if(spinnerUms.getSelectedItemPosition()==0){
                req.setKodeCabang(kodeUmsString.get(0));
            }
            else{
                req.setKodeCabang(kodeUmsString.get(spinnerUms.getSelectedItemPosition()-1));
            }
//            req.setKodeCabang(apppref.getKodeCabang());
            insertOperation(username, dialog, req);
        }
    }

    private void insertOperation(final String username, final SweetAlertDialog dialog, InsertUpdateAom req) {
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().insertUpdateAom(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        Gson gson = new Gson();
                        Type type = new TypeToken<PasswordNewAo>() {
                        }.getType();

                        passwordNewAo = gson.fromJson(response.body().getData().toString(), type);
                        dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("Berhasil");
                        dialog.setContentText("User baru telah ditambah\nRole : " + role.getSelectedItem().toString() + "\nUsername : " + username + "\nPassword : " + passwordNewAo.getPassword());
                        dialog.setConfirmText("Salin Data");
                        dialog.setCancelText("Tutup");
                        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                                finish();
                            }
                        });
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                                ClipData clip = ClipData.newPlainText("password", "Username : " + username + "\nPassword : " + passwordNewAo.getPassword());
                                clipboard.setPrimaryClip(clip);
                                Toasty.success(TambahUserActivity.this, "Data telah disalin", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Gagal");
                        dialog.setConfirmText("Coba Lagi");
                        dialog.setContentText("Timeout Silahkan Coba Lagi" + "\n");
//                        hideForm();
                        dialog.showCancelButton(false);
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                                recreate();
                            }
                        });
                    }
                }
            }


            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText(t.getMessage());
                dialog.setCancelText("OK");
            }
        });
    }

    private void getUkerPinca() {
        apiClientAdapter = new ApiClientAdapter(TambahUserActivity.this);
        final AppPreferences apppref = new AppPreferences(TambahUserActivity.this);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(apppref.getKodeSkk());
        req.setKodeSkk(apppref.getKodeSkk());
        call = apiClientAdapter.getApiInterface().dataPinca(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call2, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataKantorCabangString = response.body().getData().get("listKcp").toString();
                        dataCabangStringTotal = new ArrayList<>();
                        dataUkerCabangStringTotal = new ArrayList<>();
                        kodeCabangStringTotal = new ArrayList<>();
                        kodeUkerCabangStringTotal = new ArrayList<>();
                        Gson gson = new Gson();
                        Type type1 = new TypeToken<List<KantorCabang>>() {
                        }.getType();
                        kantorCabang = gson.fromJson(listDataKantorCabangString, type1);
                        dataUkerCabangStringTotal.add(apppref.getNamaKantor());
                        kodeUkerCabangStringTotal.add(apppref.getKodeSkk());

                        for (int i = 0; i < kantorCabang.size(); i++) {
                            dataCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
                            dataUkerCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
                            kodeCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
                            kodeUkerCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
                            Log.d("inilahKantorCabang", kantorCabang.get(i).getSb_desc());
                        }


                        for (int i = 0; i <dataUkerCabangStringTotal.size() ; i++) {
                            Log.d("inilahNamaUker", dataUkerCabangStringTotal.get(i));
                            Log.d("inilahKodeUker", kodeUkerCabangStringTotal.get(i));
                        }

                        loading.setVisibility(View.GONE);
                        content.setVisibility(View.VISIBLE);
                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangStringTotal);
                        kantor.setAdapter(adapter);
                        selesaiUker=true;

//                        kantor.setSelection(1,false);



                    } else {
                        Toast.makeText(TambahUserActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call2, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toast.makeText(TambahUserActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void getCountUser(final String kodeSkk, final String user) {

        //method ini digunakan untuk dapet jumlah bos,cs kalo yg login UH, dan jumlah UH,pincapem,MMM serta list KCP jika yg login pinca
        //delete apiclient extra parameter
        apiClientAdapter = new ApiClientAdapter(this);
        final AppPreferences apppref = new AppPreferences(TambahUserActivity.this);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(apppref.getKodeCabang());

        //conditioning list yang ditampilkan
//        if (apppref.getFidRole().equalsIgnoreCase("79")) {//MMM
//            call = apiClientAdapter.getApiInterface().dataUh(req);
//            req.setKodeCabang(apppref.getKodeCabang());
//            req.setKodeSkk(apppref.getKodeSkk());
//        } else
        if (apppref.getFidRole().equalsIgnoreCase("71")) {//UH
            call = apiClientAdapter.getApiInterface().dataAo(req);
            req.setKodeCabang(apppref.getKodeCabang());
        } else {
            call = apiClientAdapter.getApiInterface().dataUh(req);
            req.setKodeCabang(kodeSkk);
            req.setKodeSkk(kodeSkk);
        }
//        else if (apppref.getFidRole().equalsIgnoreCase("76")) {//PINCA
//            call = apiClientAdapter.getApiInterface().dataPinca(req);
//            req.setKodeCabang(apppref.getKodeCabang());
//            req.setKodeSkk(apppref.getKodeSkk());
//        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
//                        if (apppref.getFidRole().equalsIgnoreCase("71")) {
//                            String listDataString = response.body().getData().get("listAom").toString();
//                        } else {
                        jumlahBos = Integer.parseInt(response.body().getData().get("jumlahBOS").toString());
//                        jumlahUh = Integer.parseInt(response.body().getData().get("jumlahUH").toString());
                        jumlahCs = Integer.parseInt(response.body().getData().get("jumlahCS").toString());
                        if (user.equalsIgnoreCase("uh")) {
                            if (jumlahUh >= 1) {
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada UH yang aktif, harap nonaktifkan UH terlebih dahulu sebelum menambah UH baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                dialogKonfirm.setTitleText("Konfirmasi");
                                dialogKonfirm.setContentText("Anda menambahkan " +
                                        "\nNama : " + namaPegawai.getText().toString() + "" +
                                        "\nNIK : " + nik.getText().toString() +
                                        "\nRole : " + role.getSelectedItem().toString() + "" +
                                        "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                        "\nUsername: " + username.getText().toString() + "" +
                                        "\nNo Sk : " + no_sk.getText().toString());
                                dialogKonfirm.setConfirmText("Konfirmasi");
                                dialogKonfirm.setCancelText("Batal");
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                        dialogKonfirm.setContentText("");
                                        insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
                                    }
                                }).show();
                            }
                        } else if (user.equalsIgnoreCase("bos")) {
                            if (jumlahBos >= 1) {
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada BOS yang aktif, harap nonaktifkan BOS terlebih dahulu sebelum menambah BOS baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername : " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("cs")) {
                            if (apppref.getFidRole().equalsIgnoreCase("76")) {
                                if (jumlahCs >= 1) {
                                    dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialogKonfirm.setTitleText("Gagal");
                                    dialogKonfirm.setContentText("Sudah ada CS yang aktif, harap nonaktifkan CS terlebih dahulu sebelum menambah CS baru\n");
                                    dialogKonfirm.setConfirmText("Ok");
                                    dialogKonfirm.showCancelButton(false);
                                    dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            dialogKonfirm.dismissWithAnimation();
                                            recreate();
                                        }
                                    }).show();
                                } else {
                                    dialogKonfirm.setTitleText("Konfirmasi")
                                            .setContentText("Anda menambahkan " +
                                                    "\nNama : " + namaPegawai.getText().toString() + "" +
                                                    "\nNIK : " + nik.getText().toString() +
                                                    "\nRole : " + role.getSelectedItem().toString() + "" +
                                                    "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                    "\nUsername : " + username.getText().toString())
                                            .setConfirmText("Konfirmasi")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                    dialogKonfirm.setContentText("");
                                                    insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                                }
                                            }).setCancelText("Batal")
                                            .show();
                                }
                            } else {
                                if (jumlahCs >= 1) {
                                    dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialogKonfirm.setTitleText("Gagal");
                                    dialogKonfirm.setContentText("Sudah ada CS yang aktif, harap nonaktifkan CS terlebih dahulu sebelum menambah CS baru\n");
                                    dialogKonfirm.setConfirmText("Ok");
                                    dialogKonfirm.showCancelButton(false);
                                    dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            dialogKonfirm.dismissWithAnimation();
                                            recreate();
                                        }
                                    }).show();
                                } else {
                                    dialogKonfirm.setTitleText("Konfirmasi")
                                            .setContentText("Anda menambahkan " +
                                                    "\nNama : " + namaPegawai.getText().toString() + "" +
                                                    "\nNIK : " + nik.getText().toString() +
                                                    "\nRole : " + role.getSelectedItem().toString() + "" +
                                                    "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                    "\nUsername : " + username.getText().toString())
                                            .setConfirmText("Konfirmasi")
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                    dialogKonfirm.setContentText("");
                                                    insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                                }
                                            }).setCancelText("Batal")
                                            .show();
                                }
                            }
                        } else if (user.equalsIgnoreCase("aom")) {
                            dialogKonfirm.setTitleText("Konfirmasi")
                                    .setContentText("Anda menambahkan " +
                                            "\nNama : " + namaPegawai.getText().toString() + "" +
                                            "\nNIK : " + nik.getText().toString() +
                                            "\nRole : " + role.getSelectedItem().toString() + "" +
                                            "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                            "\nUsername : " + username.getText().toString())
                                    .setConfirmText("Konfirmasi")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                            dialogKonfirm.setContentText("");
                                            insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                        }
                                    }).setCancelText("Batal")
                                    .show();
                        }
//                        }
                    } else {
                        Toast.makeText(TambahUserActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toast.makeText(TambahUserActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void getCountUserPinca(final String kodeSkk, final String user) {
        apiClientAdapter = new ApiClientAdapter(TambahUserActivity.this);
        final AppPreferences apppref = new AppPreferences(TambahUserActivity.this);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(apppref.getKodeSkk());
        req.setKodeSkk(kodeSkk);
        call = apiClientAdapter.getApiInterface().dataPincaLengkap(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call2, Response<ParseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        String listDataKantorCabangString = response.body().getData().get("listKcp").toString();
//                        dataCabangStringTotal = new ArrayList<>();
//                        dataUkerCabangStringTotal = new ArrayList<>();
//                        kodeCabangStringTotal = new ArrayList<>();
//                        kodeUkerCabangStringTotal = new ArrayList<>();
                        Gson gson = new Gson();
                        Type type1 = new TypeToken<List<KantorCabang>>() {
                        }.getType();
                        kantorCabang = gson.fromJson(listDataKantorCabangString, type1);
//                        dataUkerCabangStringTotal.add(apppref.getNamaKantor());
//                        kodeUkerCabangStringTotal.add(apppref.getKodeSkk());
//
//                        for (int i = 0; i < kantorCabang.size(); i++) {
//                            dataCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
//                            dataUkerCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
//                            kodeCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
//                            kodeUkerCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
//                            Log.d("inilahKantorCabang", kantorCabang.get(i).getSb_desc());
//                        }
//                        loading.setVisibility(View.GONE);
//                        content.setVisibility(View.VISIBLE);
//                        adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangStringTotal);
//                        kantor.setAdapter(adapter);

                        jumlahMmm = Integer.parseInt(response.body().getData().get("jumlahMMM").toString());
                        jumlahAdp = Integer.parseInt(response.body().getData().get("jumlahADP").toString());
                        jumlahMo = Integer.parseInt(response.body().getData().get("jumlahMO").toString());
//                        jumlahUh = Integer.parseInt(response.body().getData().get("jumlahUH").toString());
                        jumlahBos = Integer.parseInt(response.body().getData().get("jumlahBOS").toString());
                        jumlahCs = Integer.parseInt(response.body().getData().get("jumlahCS").toString());
                        Log.d("Cek Jumlah CS", String.valueOf(jumlahCs));
                        if (user.equalsIgnoreCase("mmm")) {
                            if (jumlahMmm >= 2) {
                                Log.d("Jumlah MMM > 2", "Here");
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada 2 MMM yang aktif, harap nonaktifkan MMM terlebih dahulu sebelum menambah MMM baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                Log.d("Jumlah MMM < 2", "Here");
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername: " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("pincapem")) {
                            int posisi = 0;
                            for (int i = 0; i < kantorCabang.size(); i++) {
                                if (kodeSkk.equalsIgnoreCase(kantorCabang.get(i).getKode_cabang())) {
                                    posisi = i;
                                }
                            }
                            if (kantorCabang.get(posisi).getJumlah_pincapem_aktif().equalsIgnoreCase("1")) {
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada PINCAPEM yang aktif, harap nonaktifkan PINCAPEM terlebih dahulu sebelum menambah PINCAPEM baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername: " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
//                            if (jumlahMmm >= 1) {
//                                Log.d("Jumlah MMM > 1", "Here");
//                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
//                                dialogKonfirm.setTitleText("Gagal");
//                                dialogKonfirm.setContentText("Sudah ada MMM yang aktif, harap nonaktifkan MMM terlebih dahulu sebelum menambah MMM baru\n");
//                                dialogKonfirm.setConfirmText("Ok");
//                                dialogKonfirm.showCancelButton(false);
//                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                        dialogKonfirm.dismissWithAnimation();
//                                        recreate();
//                                    }
//                                }).show();
//                            } else {
//                                Log.d("Jumlah MMM < 1", "Here");
//                                dialogKonfirm.setTitleText("Konfirmasi")
//                                        .setContentText("Anda menambahkan " +
//                                                "\nNama : " + namaPegawai.getText().toString() + "" +
//                                                "\nNIK : " + nik.getText().toString() +
//                                                "\nRole : " + role.getSelectedItem().toString() + "" +
//                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
//                                                "\nUsername: " + username.getText().toString())
//                                        .setConfirmText("Konfirmasi")
//                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                            @Override
//                                            public void onClick(SweetAlertDialog sDialog) {
//                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
//                                                dialogKonfirm.setContentText("");
//                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);
//
//                                            }
//                                        }).setCancelText("Batal")
//                                        .show();
//                            }
                        } else if (user.equalsIgnoreCase("adp")) {
                            if (jumlahAdp >= 1) {
                                Log.d("Jumlah ADP > 1", "Here");
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada ADP yang aktif, harap nonaktifkan ADP terlebih dahulu sebelum menambah ADP baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                Log.d("Jumlah ADP < 1", "Here");
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername: " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("mo")) {
                            if (jumlahMo >= 1) {
                                Log.d("Jumlah MO > 1", "Here");
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada MO yang aktif, harap nonaktifkan MO terlebih dahulu sebelum menambah MO baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                Log.d("Jumlah MO < 1", "Here");
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername: " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("uh")) {
                            if (jumlahUh >= 1) {
                                Log.d("Jumlah UH > 1", "Here");
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada UH yang aktif, harap nonaktifkan UH terlebih dahulu sebelum menambah UH baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                Log.d("Jumlah UH < 1", "Here");
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername: " + username.getText().toString() + "" +
                                                "\nNo Sk : " + no_sk.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("bos")) {
                            if (jumlahBos >= 1) {
                                Log.d("Jumlah BOS > 1", "Here");
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada BOS yang aktif, harap nonaktifkan BOS terlebih dahulu sebelum menambah BOS baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                Log.d("Jumlah BOS < 1", "Here");
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername : " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("cs")) {
                            if (jumlahCs >= 2) {
                                Log.d("Jumlah CS > 2", "Here");
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText("Sudah ada 2 CS yang aktif, harap nonaktifkan CS terlebih dahulu sebelum menambah CS baru\n");
                                dialogKonfirm.setConfirmText("Ok");
                                dialogKonfirm.showCancelButton(false);
                                dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialogKonfirm.dismissWithAnimation();
                                        recreate();
                                    }
                                }).show();
                            } else {
                                Log.d("Jumlah CS < 2", "Here");
                                dialogKonfirm.setTitleText("Konfirmasi")
                                        .setContentText("Anda menambahkan " +
                                                "\nNama : " + namaPegawai.getText().toString() + "" +
                                                "\nNIK : " + nik.getText().toString() +
                                                "\nRole : " + role.getSelectedItem().toString() + "" +
                                                "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                                "\nUsername : " + username.getText().toString())
                                        .setConfirmText("Konfirmasi")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                                dialogKonfirm.setContentText("");
                                                insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                            }
                                        }).setCancelText("Batal")
                                        .show();
                            }
                        } else if (user.equalsIgnoreCase("ao")) {
                            dialogKonfirm.setTitleText("Konfirmasi")
                                    .setContentText("Anda menambahkan " +
                                            "\nNama : " + namaPegawai.getText().toString() + "" +
                                            "\nNIK : " + nik.getText().toString() +
                                            "\nRole : " + role.getSelectedItem().toString() + "" +
                                            "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                            "\nUsername : " + username.getText().toString())
                                    .setConfirmText("Konfirmasi")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                            dialogKonfirm.setContentText("");
                                            insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                        }
                                    }).setCancelText("Batal")
                                    .show();
                        } else if (user.equalsIgnoreCase("aom")) {
                            dialogKonfirm.setTitleText("Konfirmasi")
                                    .setContentText("Anda menambahkan " +
                                            "\nNama : " + namaPegawai.getText().toString() + "" +
                                            "\nNIK : " + nik.getText().toString() +
                                            "\nRole : " + role.getSelectedItem().toString() + "" +
                                            "\nKantor: " + kantor.getSelectedItem().toString() + "" +
                                            "\nUsername : " + username.getText().toString())
                                    .setConfirmText("Konfirmasi")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                            dialogKonfirm.setContentText("");
                                            insertUpdateAom(namaPegawai.getText().toString(), nik.getText().toString(), username.getText().toString(), dialogKonfirm);

                                        }
                                    }).setCancelText("Batal")
                                    .show();
                        }

                    } else {
                        Toast.makeText(TambahUserActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call2, Throwable t) {
                Log.d("LOG D", t.getMessage());
                Toast.makeText(TambahUserActivity.this, "Terjadi kesalahan pada server", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void cekUserMip() {
        final SweetAlertDialog dialog1 = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        dialog1.setTitleText("Mengecek NIK");
        dialog1.show();

        //make request to middle tier
        apiClientAdapter = new ApiClientAdapter(this);
        ReqNik req = new ReqNik();
        final AppPreferences appPreferences = new AppPreferences(this);
        req.setNik(nik.getText().toString());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekUserMip(req);
        call.enqueue(new Callback<ParseResponseArr>() {


            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataPutusanString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<UserMip>>() {
                        }.getType();
                        dataMip = gson.fromJson(listDataPutusanString, type2);

                        //kalau tidak ada data dalam putusan, maka akan mengembalikan array kosong dalam bentuk string
                        //maka, jika kembaliannya bukan array kosong, berarti masih ada putusan yang perlu diputus
                        if (listDataPutusanString.equalsIgnoreCase("[]")) {
                            dialog1.dismissWithAnimation();
                            SweetAlertDialog dialogError = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("NIK Tidak Ditemukan di Database MIP").show();
//                            ((Activity)context).recreate();
                        } else {
                            dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog1.setTitleText("Berhasil");
                            dialog1.setContentText("User ditemukan dengan nama : " + dataMip.get(0).getNamaUser() + "\n\n");
                            dialog1.setConfirmText("Tutup");
                            dialog1.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    dialog1.dismissWithAnimation();
                                }
                            });
                            showForm();

                            namaPegawai.setText(dataMip.get(0).getNamaUser());
                        }
                    } else {
                        Toast.makeText(TambahUserActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Toast.makeText(TambahUserActivity.this, "gagal terhubung ke server", Toast.LENGTH_SHORT).show();
                Log.d("LOG D", t.getMessage());
            }
        });
    }

    private void showForm() {
        info_notice.setVisibility(View.GONE);
        simpan.setVisibility(View.VISIBLE);
        cekUser.setVisibility(View.GONE);

        boxNamaPegawai.setVisibility(View.VISIBLE);
        boxUsername.setVisibility(View.VISIBLE);

        boxNik.setHasClearButton(false);
        boxNamaPegawai.setHasClearButton(false);

        namaPegawai.setInputType(InputType.TYPE_NULL);
        namaPegawai.setFocusable(false);
        nik.setInputType(InputType.TYPE_NULL);
        nik.setFocusable(false);
        username.setInputType(InputType.TYPE_NULL);
        username.setFocusable(false);


        if (appPreferences.getFidRole().equalsIgnoreCase("79") || appPreferences.getFidRole().equalsIgnoreCase("72")) {
//            boxLimit.setVisibility(View.VISIBLE);
            boxNoSk.setVisibility(View.VISIBLE);
        }

        boxRole.setVisibility(View.VISIBLE);
        boxKantor.setVisibility(View.VISIBLE);
    }

    private void hideForm() {
        info_notice.setVisibility(View.VISIBLE);
        simpan.setVisibility(View.GONE);
        cekUser.setVisibility(View.VISIBLE);

        boxNamaPegawai.setVisibility(View.GONE);
        boxUsername.setVisibility(View.GONE);
        namaPegawai.setInputType(InputType.TYPE_NULL);
        namaPegawai.setFocusable(true);
        nik.setInputType(InputType.TYPE_NULL);
        nik.setFocusable(true);
        nik.setEnabled(true);

        if (appPreferences.getFidRole().equalsIgnoreCase("79") || appPreferences.getFidRole().equalsIgnoreCase("72")) {
//            boxLimit.setVisibility(View.GONE);
            boxNoSk.setVisibility(View.GONE);
        }

        boxRole.setVisibility(View.GONE);
        boxKantor.setVisibility(View.GONE);
    }
}
