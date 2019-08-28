package com.application.bris.brisi_pemutus.page_daftar_user.view;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import com.application.bris.brisi_pemutus.api.model.request.list_user.listUser;
import com.application.bris.brisi_pemutus.api.model.request.req_nik.ReqNik;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.PasswordNewAo;
import com.application.bris.brisi_pemutus.model.dashboard.DashboardCred;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.data_cabang.Cabang;
import com.application.bris.brisi_pemutus.model.data_cabang.CabangModel;
import com.application.bris.brisi_pemutus.model.kantor_cabang.KantorCabang;
import com.application.bris.brisi_pemutus.model.realm_model.Daftar_KC;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.model.user_mip.UserMip;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterDaftarUser;
import com.application.bris.brisi_pemutus.page_daftar_user.adapters.AdapterStatusUser;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.application.bris.brisi_pemutus.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import fr.ganfra.materialspinner.MaterialSpinner;
import info.hoang8f.widget.FButton;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class TambahUserActivity extends AppCompatActivity {
//EditText namaPegawai,nik,username,limit;
    TextView info_notice;
ExtendedEditText namaPegawai,nik,username,limit,no_sk;
TextFieldBoxes boxNamaPegawai,boxNik,boxUsername,boxLimit,boxNoSk;
MaterialSpinner role,kantor;
FButton simpan,cekUser;
ApiClientAdapter apiClientAdapter;
PasswordNewAo passwordNewAo;
Call<ParseResponse> call;
RelativeLayout loading;
LinearLayout content,boxRole,boxKantor;
List<Cabang>dataCabang;
List<UserMip>dataMip;
List<String> dataCabangStringTotal;
List<String> kodeCabangStringTotal;
AppPreferences appPreferences;

List<String> dataCabangString;
CabangModel dataCabangSingular;

List<KantorCabang> kantorCabang;

int jumlahBos,jumlahUh,jumlahCs;

    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_user);

        appPreferences=new AppPreferences(this);

        //instantiate objects
        loading=findViewById(R.id.progressbar_loading);

        content=findViewById(R.id.ll_content_tambah_user);
        content.setVisibility(View.GONE);
        namaPegawai=findViewById(R.id.extended_nama);
        nik=findViewById(R.id.extended_nik);
        username=findViewById(R.id.extended_username);
        limit=findViewById(R.id.extended_limit);
        limit.addTextChangedListener(new NumberTextWatcherCanNolForThousand(limit));
        no_sk=findViewById(R.id.extended_no_sk_uh);
        info_notice=findViewById(R.id.info_tambah_user);





        role=findViewById(R.id.spMaterial_role);
        kantor=findViewById(R.id.spMaterial_kantor);
        boxRole=findViewById(R.id.ll_role_tambah_user);
        boxKantor=findViewById(R.id.ll_kantor_tambah_user);

        boxNik=findViewById(R.id.text_nik);
        boxNamaPegawai=findViewById(R.id.text_nama);
        boxUsername=findViewById(R.id.text_username);
        boxLimit=findViewById(R.id.text_limit);
        boxNoSk=findViewById(R.id.text_no_sk_uh);

        //HIDE SEMUA FIELD, BIAR CEK NIK DULU
        boxNamaPegawai.setVisibility(View.GONE);
        boxUsername.setVisibility(View.GONE);
        boxLimit.setVisibility(View.GONE);
        boxNoSk.setVisibility(View.GONE);
        boxRole.setVisibility(View.GONE);
        boxKantor.setVisibility(View.GONE);

        //button settings

        simpan=findViewById(R.id.btflatSimpan);
        simpan.setButtonColor(getResources().getColor(R.color.colorPrimary));

        cekUser=findViewById(R.id.btflatCekUser);
        cekUser.setButtonColor(getResources().getColor(R.color.colorPrimary));


        apiClientAdapter = new ApiClientAdapter(this);

        AppUtil.toolbarRegular(this,"Tambah User");

        boxNoSk.setVisibility(View.GONE);
        boxLimit.setVisibility(View.GONE);

        //seleksi role
        AppPreferences app_pref=new AppPreferences(this);
        if(app_pref.getJabatan().equalsIgnoreCase("pinca")){ //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pinca, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }
        else  if(app_pref.getJabatan().equalsIgnoreCase("pincapem")){ //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pincapem_m3, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }
        else  if(app_pref.getJabatan().equalsIgnoreCase("mmm")){ //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_pincapem_m3, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }
        else  if(app_pref.getJabatan().equalsIgnoreCase("marketing manager")){ //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_mm, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }
        else  if(app_pref.getJabatan().equalsIgnoreCase("ums head")){ //make sure which roletype is "pincapem"
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.role_UH, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            role.setAdapter(adapter);
        }





        //connect to server
        loading.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
        RequestDataCabang req = new RequestDataCabang();
        AppPreferences apppref=new AppPreferences(this);
        req.setKodeCabang(apppref.getKodeCabang());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().dataCabang(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                if(response.isSuccessful()){
                    loading.setVisibility(View.GONE);
                    content.setVisibility(View.VISIBLE  );
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<Cabang>>() {}.getType();

                        dataCabang = gson.fromJson(listDataString, type);
                       dataCabangString=new ArrayList<>();
//                        Realm.init(TambahUserActivity.this);
//                        RealmConfiguration config = new RealmConfiguration.Builder()
//                                .deleteRealmIfMigrationNeeded()
//                                .build();


                        dataCabangSingular=new CabangModel(TambahUserActivity.this);
                        dataCabangSingular.clear();
                        for (int i = 0; i <dataCabang.size() ; i++) {
                            dataCabangString.add(dataCabang.get(i).getSb_desc());
                            dataCabangSingular.addCabang(dataCabang.get(i));

                            try{
                                Log.d("ebdesc ekipaol",dataCabang.get(i).getSb_desc());

                                adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                                kantor.setAdapter(adapter);
                            }
                            catch(Exception e){

                            }
//                            realm.beginTransaction();
//                            realm.copyToRealm(dataCabang.get(i));
                        }
//                        realm.commitTransaction();


                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());
            }
        });

        getCountUser();

        role.setSelection(1);

        role.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   if(role.getSelectedItem()==null){
//                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
//                    kantor.setAdapter(adapter);
                }
                else if(role.getSelectedItem().toString().equalsIgnoreCase("pincapem")){
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangStringTotal);
                    kantor.setAdapter(adapter);
                       boxNoSk.setVisibility(View.GONE);
                       boxLimit.setVisibility(View.VISIBLE);
                }
                   else if(role.getSelectedItem().toString().equalsIgnoreCase("uh")){
                       adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                       kantor.setAdapter(adapter);
                       boxNoSk.setVisibility(View.VISIBLE);
//                       boxLimit.setVisibility(View.VISIBLE);
                   }
                else{
                    adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangString);
                    kantor.setAdapter(adapter);
                    kantor.setSelection(1);
                    boxNoSk.setVisibility(View.GONE);
                    boxLimit.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });






        //on click tombol cek user
        cekUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nik.getText().toString().isEmpty()){
                    boxNik.setError("Harap isi nik terlebih dahulu",true);
                }
                else{
                    cekUserMip();
                }
            }
        });







        //tambah user jika tombol simpan di klik

       simpan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d("si bang",Integer.toString(role.getCount()));
                if(role.getSelectedItem()==null){
                    role.setError("Harap pilih role user");

                }
                else if(kantor.getSelectedItem()==null){
                    kantor.setError("Harap pilih kantor user");
                }

                 else if(nik.getText().toString().isEmpty()){
                   boxNik.setError("Harap diisi",true);

               }
                 else if(namaPegawai.getText().toString().isEmpty()){
                     boxNamaPegawai.setError("Harap diisi",true);

                 }
                else if(username.getText().toString().isEmpty()){
                   boxUsername.setError("Harap diisi",true);

               }
                else if(role.getSelectedItem().toString().equalsIgnoreCase("pincapem")){
                    if(limit.getText().toString().isEmpty()){
                        boxLimit.setError("Harap diisi",true);
                    }
                    else{
                        final SweetAlertDialog dialogKonfirm2   = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        dialogKonfirm2.setTitleText("Konfirmasi")
                                .setContentText("Anda menambahkan " +
                                        "\nNama : "+namaPegawai.getText().toString()+"" +
                                        "\nNIK : "+nik.getText().toString()+
                                        "\nRole : "+role.getSelectedItem().toString()+"" +
                                        "\nKantor: "+kantor.getSelectedItem().toString()+"" +
                                        "\nUsername: "+username.getText().toString()+"" +
                                        "\nLimit: Rp."+limit.getText().toString()+"")
                                .setConfirmText("Konfirmasi")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        dialogKonfirm2.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                        dialogKonfirm2.setContentText("");
                                        insertUpdateAom(namaPegawai.getText().toString(),nik.getText().toString(),username.getText().toString(),dialogKonfirm2);

                                    }
                                }).setCancelText("Batal")
                                .show();
                    }


                }
              else if(role.getSelectedItem().toString().equalsIgnoreCase("uh")){
                  if(no_sk.getText().toString().isEmpty()){
                      boxNoSk.setError("Harap diisi",true);
                  }
                  else{

                      final SweetAlertDialog dialogKonfirm2   = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
                      dialogKonfirm2.setTitleText("Konfirmasi")
                              .setContentText("Anda menambahkan " +
                                      "\nNama : "+namaPegawai.getText().toString()+"" +
                                      "\nNIK : "+nik.getText().toString()+
                                      "\nRole : "+role.getSelectedItem().toString()+"" +
                                      "\nKantor: "+kantor.getSelectedItem().toString()+"" +
                                      "\nUsername: "+username.getText().toString()+"" +
                                      "\nNo Sk : "+no_sk.getText().toString())
                              .setConfirmText("Konfirmasi")
                              .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                  @Override
                                  public void onClick(SweetAlertDialog sDialog) {
                                      dialogKonfirm2.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                      dialogKonfirm2.setContentText("");
                                      insertUpdateAom(namaPegawai.getText().toString(),nik.getText().toString(),username.getText().toString(),dialogKonfirm2);

                                  }
                              }).setCancelText("Batal")
                              .show();

                  }
                }


                else{
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

                         final SweetAlertDialog dialogKonfirm   = new SweetAlertDialog(TambahUserActivity.this, SweetAlertDialog.NORMAL_TYPE);
                         dialogKonfirm.setTitleText("Konfirmasi")
                                 .setContentText("Anda menambahkan " +
                                         "\nNama : "+namaPegawai.getText().toString()+"" +
                                         "\nNIK : "+nik.getText().toString()+
                                         "\nRole : "+role.getSelectedItem().toString()+"" +
                                         "\nKantor: "+kantor.getSelectedItem().toString()+"" +
                                         "\nUsername : "+username.getText().toString())
                                 .setConfirmText("Konfirmasi")
                                 .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                     @Override
                                     public void onClick(SweetAlertDialog sDialog) {
                                         dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                                         dialogKonfirm.setContentText("");
                                         insertUpdateAom(namaPegawai.getText().toString(),nik.getText().toString(),username.getText().toString(),dialogKonfirm);

                                     }
                                 }).setCancelText("Batal")
                                 .show();






                }






           }
       });


    }
    public void insertUpdateAom(String nama, String nik, final String username, final SweetAlertDialog dialog){
        apiClientAdapter=new ApiClientAdapter(this);
        AppPreferences apppref=new AppPreferences(TambahUserActivity.this);
        //  progressbar_loading.setVisibility(View.VISIBLE);
        InsertUpdateAom req = new InsertUpdateAom();
        req.setNama(nama);
        req.setUid(0); //UID diisi 0 jika ingin insert, diisi selain 0 jika update
        req.setNoPegawai(nik);
        req.setUsername(username);
        req.setPassword("");

        //karena yang keluar option kantor hanya jika membuat user pincapem, maka ada kodecabang dikirim berdasarkan kantor yang dipilih
        //selain pembuata user pincapem, maka akan dikirim kodecabang yang sama dengan user yang login.
        Log.d("ekipaolroleselected",role.getSelectedItem().toString());
//        if(role.getSelectedItem().toString().equalsIgnoreCase("pincapem")){
//            req.setKodeCabang(kodeCabangStringTotal.get(kantor.getSelectedItemPosition()-1));
//        }
//        else{
//            req.setKodeCabang(apppref.getKodeCabang());
//        }

        req.setKodeCabang(apppref.getKodeSkk());

        req.setUidCreator(Integer.parseInt(apppref.getUid()));

        //fid role conditioning
        if(role.getSelectedItem().toString().equalsIgnoreCase("uh")){
            req.setFid_role(71);
            req.setNoSk(no_sk.getText().toString());
            if(jumlahUh>=1){

                //delete this after done testing
//                insertOperation(username,dialog,req);


                //uncomment after testing
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText("Sudah ada 1 UH yang aktif, harap nonaktifkan UH terlebih dahulu sebelum menambah UH baru\n");
                dialog.setConfirmText("Ok");
                dialog.showCancelButton(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                        recreate();
                    }
                });


            }
            else{
                insertOperation(username,dialog,req);
            }

        }
        else if(role.getSelectedItem().toString().equalsIgnoreCase("aom")){
            req.setFid_role(8);
            req.setKodeCabang(apppref.getKodeCabang());
            insertOperation(username,dialog,req);

        }
        else if(role.getSelectedItem().toString().equalsIgnoreCase("cs")){
            req.setFid_role(23);
            if(jumlahCs >=1){
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText("Sudah ada 1 CS yang aktif, harap nonaktifkan CS terlebih dahulu sebelum menambah CS baru\n");
                dialog.setConfirmText("Ok");
                dialog.showCancelButton(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                    }
                });
                recreate();

            }
            else{
                insertOperation(username,dialog,req);
            }
        }
        else if(role.getSelectedItem().toString().equalsIgnoreCase("bos")){
            req.setFid_role(26);
            if(jumlahBos>=1){
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText("Sudah ada 1 BOS yang aktif, harap nonaktifkan BOS terlebih dahulu sebelum menambah BOS baru\n");
                dialog.setConfirmText("Ok");
                dialog.showCancelButton(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                    }
                });
                recreate();

            }
            else{
                insertOperation(username,dialog,req);
            }
        }
        else if(role.getSelectedItem().toString().equalsIgnoreCase("pincapem")){
            req.setFid_role(79);
            Log.d("kodeCabangSETREQ",kodeCabangStringTotal.get(kantor.getSelectedItemPosition()-1));
            req.setKodeCabang(kodeCabangStringTotal.get(kantor.getSelectedItemPosition()-1));

            if(kantorCabang.get(kantor.getSelectedItemPosition()-1).getJumlah_pincapem_aktif().equalsIgnoreCase("1")){
                Toast.makeText(this, kantorCabang.get(kantor.getSelectedItemPosition()-1).getSb_desc(), Toast.LENGTH_SHORT).show();
                dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                dialog.setTitleText("Gagal");
                dialog.setContentText("Sudah ada PINCAPEM yang aktif, harap nonaktifkan PINCAPEM terlebih dahulu sebelum menambah PINCAPEM baru\n");
                dialog.setConfirmText("Ok");
                dialog.showCancelButton(false);
                dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        dialog.dismissWithAnimation();
                        recreate();
                    }
                });


            }
            else{
                insertOperation(username,dialog,req);
            }
        }



    }

    private  void insertOperation( final String username, final SweetAlertDialog dialog,InsertUpdateAom req){
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().insertUpdateAom(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        Gson gson = new Gson();
                        Type type = new TypeToken<PasswordNewAo>() {}.getType();

                        passwordNewAo = gson.fromJson(response.body().getData().toString(), type);
                        dialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                        dialog.setTitleText("Berhasil");
                        dialog.setContentText("User baru telah ditambah\nRole : "+role.getSelectedItem().toString()+"\nUsername : "+username+"\nPassword : "+passwordNewAo.getPassword());
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
                                ClipData clip = ClipData.newPlainText("password", "Username : "+username+"\nPassword : "+passwordNewAo.getPassword());
                                clipboard.setPrimaryClip(clip);
                                Toasty.success(TambahUserActivity.this,"Data telah disalin", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    else{
                        dialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                        dialog.setTitleText("Gagal");
                        dialog.setConfirmText("Coba Lagi");
                        dialog.setContentText(response.body().getMessage()+"\n");
                       hideForm();

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

    public void getCountUser(){

        //method ini digunakan untuk dapet jumlah bos,cs kalo yg login UH, dan jumlah UH,pincapem,MMM serta list KCP jika yg login pinca
//delete apiclient extra parameter
        apiClientAdapter=new ApiClientAdapter(this);
        //  dataUser = getListUser();
        final AppPreferences apppref=new AppPreferences(TambahUserActivity.this);
        // progressbar_loading.setVisibility(View.VISIBLE);
        RequestDataCabang req = new RequestDataCabang();
        req.setKodeCabang(apppref.getKodeCabang());

        //conditioning list yang ditampilkan
        if(apppref.getFidRole().equalsIgnoreCase("79")){//MMM
            call = apiClientAdapter.getApiInterface().dataUh(req);
            req.setKodeCabang(apppref.getKodeCabang());
            req.setKodeSkk(apppref.getKodeSkk());
        }
        else if(apppref.getFidRole().equalsIgnoreCase("71")){//UH
            call = apiClientAdapter.getApiInterface().dataAo(req);
            req.setKodeCabang(apppref.getKodeCabang());
        }
        else if(apppref.getFidRole().equalsIgnoreCase("76")){//PINCA
            call = apiClientAdapter.getApiInterface().dataPinca(req);
            req.setKodeCabang(apppref.getKodeCabang());
            req.setKodeSkk(apppref.getKodeSkk());
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                // progressbar_loading.setVisibility(View.GONE);

                if(response.isSuccessful()){
                    if(response.body().getStatus().equalsIgnoreCase("00")){

                        if(apppref.getFidRole().equalsIgnoreCase("76")){
//                            String listDataString = response.body().getData().get("listUh").toString();
                            String listDataKantorCabangString=response.body().getData().get("listKcp").toString();
                             dataCabangStringTotal=new ArrayList<>();
                             kodeCabangStringTotal=new ArrayList<>();
                            Gson gson = new Gson();
                            Type type1 = new TypeToken<List<KantorCabang>>() {}.getType();
                            kantorCabang = gson.fromJson(listDataKantorCabangString, type1);

                                    for (int i = 0; i <kantorCabang.size() ; i++) {
                                        dataCabangStringTotal.add(kantorCabang.get(i).getSb_desc());
                                        kodeCabangStringTotal.add(kantorCabang.get(i).getKode_cabang());
                                         Log.d("inilahKantorCabang",kantorCabang.get(i).getSb_desc());
//                                        Log.d("inilahKodeCabang",kantorCabang.get(i).getKode_cabang());
        }
                            loading.setVisibility(View.GONE);
                            content.setVisibility(View.VISIBLE);
                            adapter = new ArrayAdapter<>(TambahUserActivity.this, android.R.layout.simple_list_item_1, dataCabangStringTotal);
                            kantor.setAdapter(adapter);


//                            jumlahBos=Integer.parseInt(response.body().getData().get("jumlahBOS").toString());
//                            jumlahUh=Integer.parseInt(response.body().getData().get("jumlahUH").toString());
//                            jumlahCs=Integer.parseInt(response.body().getData().get("jumlahCS").toString());

                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("71")){
                            String listDataString = response.body().getData().get("listAom").toString();

                        }
                        else if(apppref.getFidRole().equalsIgnoreCase("79")){
                            String listDataString = response.body().getData().get("listUh").toString();
                            jumlahBos=Integer.parseInt(response.body().getData().get("jumlahBOS").toString());
                            jumlahUh=Integer.parseInt(response.body().getData().get("jumlahUH").toString());
                            jumlahCs=Integer.parseInt(response.body().getData().get("jumlahCS").toString());

                        }
                    }
                }
                else {
                    Toast.makeText(TambahUserActivity.this, "Error occured", Toast.LENGTH_SHORT).show();
                    finish();
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

    private void cekUserMip(){
        final SweetAlertDialog dialog1=new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        dialog1.setTitleText("Mengecek NIK");
        dialog1.show();


        //make request to middle tier
        apiClientAdapter= new ApiClientAdapter(this);
        ReqNik req = new ReqNik();
        final AppPreferences appPreferences =new AppPreferences(this);
        req.setNik(nik.getText().toString());
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekUserMip(req);
        call.enqueue(new Callback<ParseResponseArr>() {


            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if(response.isSuccessful()){

                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataPutusanString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<UserMip>>() {}.getType();
                        dataMip=gson.fromJson(listDataPutusanString,type2);

                        //kalau tidak ada data dalam putusan, maka akan mengembalikan array kosong dalam bentuk string
                        //maka, jika kembaliannya bukan array kosong, berarti masih ada putusan yang perlu diputus
                        if(listDataPutusanString.equalsIgnoreCase("[]")){
                            dialog1.dismissWithAnimation();
                            SweetAlertDialog dialogError=new SweetAlertDialog(TambahUserActivity.this,SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("NIK Tidak Ditemukan di Database MIP").show();
//                            ((Activity)context).recreate();
                        }
                        else{
                            dialog1.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                            dialog1.setTitleText("Berhasil");
                            dialog1.setContentText("User ditemukan dengan nama : "+dataMip.get(0).getNamaUser()+"\n\n");
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



                    }
                    else{
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

    private void showForm(){
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


        if(appPreferences.getFidRole().equalsIgnoreCase("79")||appPreferences.getFidRole().equalsIgnoreCase("72")){
            boxLimit.setVisibility(View.VISIBLE);
            boxNoSk.setVisibility(View.VISIBLE);
        }

        boxRole.setVisibility(View.VISIBLE);
        boxKantor.setVisibility(View.VISIBLE);
    }

    private void hideForm(){
        info_notice.setVisibility(View.VISIBLE);
        simpan.setVisibility(View.GONE);
        cekUser.setVisibility(View.VISIBLE);

        boxNamaPegawai.setVisibility(View.GONE);
        boxUsername.setVisibility(View.GONE);
        namaPegawai.setInputType(InputType.TYPE_CLASS_TEXT);
        namaPegawai.setFocusable(true);
        nik.setInputType(InputType.TYPE_CLASS_TEXT);
        nik.setFocusable(true);

        if(appPreferences.getFidRole().equalsIgnoreCase("79")||appPreferences.getFidRole().equalsIgnoreCase("72")){
            boxLimit.setVisibility(View.GONE);
            boxNoSk.setVisibility(View.GONE);
        }

        boxRole.setVisibility(View.GONE);
        boxKantor.setVisibility(View.GONE);
    }


}
