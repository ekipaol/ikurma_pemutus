package com.application.bris.brisi_pemutus.page_ambil_alih.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.request.login.LoginRequest;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.login_cred.LoginCred;
import com.application.bris.brisi_pemutus.model.user_ambil_alih.UserAmbilAlih;
import com.application.bris.brisi_pemutus.page_daftar_user.view.DetailUserActivity;
import com.application.bris.brisi_pemutus.view.corelayout.CoreLayoutActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDaftarAmbilAlih extends RecyclerView.Adapter<AdapterDaftarAmbilAlih.UserViewHolder> implements Filterable {
    private Context context;
    private List<Ao> data;
    private List<Ao> datafiltered;
    private List<Ao> datafilteredagain;
    private List<UserAmbilAlih> dataAmbilAlih;
    private List<UserAmbilAlih> dataAmbilAlihFiltered;
    private List<UserAmbilAlih> dataAmbilAlihFilteredAgain;
    AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;
    private LoginCred dataUser;
    SweetAlertDialog dialog1;
    int indikatorAdapter=0;

    public AdapterDaftarAmbilAlih(Context context, List<Ao> data,String role) {
        this.context = context;
        this.data = data;
//        this.datafiltered=data;

        this.datafiltered=new ArrayList<>();
        appPreferences=new AppPreferences(context);
        apiClientAdapter=new ApiClientAdapter(context);

        for (int i = 0; i <data.size() ; i++) {
            //hanya mengambil data pemutus yang aktif saja, dan 1 tingkat dibawah user (pinca hanyabisa ambil putusan pincapem, pincapem hanya bisa ambil putusan uh dan seterusnya
            if(role.equalsIgnoreCase("76")){
                //pinca bisa ambil alih M3, MM, Pincapem,AMM yang aktif
                if(data.get(i).getFid_role().equalsIgnoreCase("79")||data.get(i).getFid_role().equalsIgnoreCase("72")||data.get(i).getFid_role().equalsIgnoreCase("77")||data.get(i).getFid_role().equalsIgnoreCase("121")) {
                    if (data.get(i).getStatus().equalsIgnoreCase("aktif")) {
                        datafiltered.add(data.get(i));
                    }
                }
            }
            else if(role.equalsIgnoreCase("79")){
                //pincapem bisa ambil alih UH doang
                if(data.get(i).getFid_role().equalsIgnoreCase("71")) {
                    if (data.get(i).getStatus().equalsIgnoreCase("aktif")) {
                        datafiltered.add(data.get(i));
                    }
                }
            }
            //user MMM hanya tampil ambil alih UH di cabangnya saja
            else if(role.equalsIgnoreCase("72")){
                if(data.get(i).getFid_role().equalsIgnoreCase("71") ) {
                    if (data.get(i).getStatus().equalsIgnoreCase("aktif")) {
                        datafiltered.add(data.get(i));
                    }
                }
            }

        }
        this.datafilteredagain=this.datafiltered;

    }

    public AdapterDaftarAmbilAlih(Context context, List<UserAmbilAlih> dataUserAmbilAlih){
        this.context = context;
        this.dataAmbilAlih = dataUserAmbilAlih;
        this.dataAmbilAlihFiltered=dataAmbilAlih;
//        this.dataAmbilAlihFiltered=new ArrayList<>();
        appPreferences=new AppPreferences(context);
        apiClientAdapter=new ApiClientAdapter(context);
        indikatorAdapter=1;

//        for (int i = 0; i <dataAmbilAlihFiltered ; i++) {
//
//        }


    }



    @NonNull
    @Override
    public AdapterDaftarAmbilAlih.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_daftar_ambil_alih, parent, false);
        return new AdapterDaftarAmbilAlih.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterDaftarAmbilAlih.UserViewHolder holder, final int position) {
        Log.d("dataambilalihtoppest","confirmed");
        if(indikatorAdapter==0){
            final Ao datas = datafilteredagain.get(position);

//        Log.d("substringekipaol",datas.getSbdesc().substring(0,7));
//        if(datas.getJabatan().equalsIgnoreCase("uh")&&datas.getSbdesc().substring(0,7).equalsIgnoreCase("ums kcp")){
//            holder.cv_content.setVisibility(View.GONE);
//        }


            holder.tv_nama.setText(datas.getNama_pegawai());
            holder.tv_jabatan.setText(datas.getJabatan());

            holder.tv_kantor.setText(datas.getSbdesc());

            holder.tv_uid.setText("UID: "+datas.getUid());


            holder.btDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Ambil alih user :"+datas.getNama_pegawai(), Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(holder.btDetail.getContext(), DetailUserActivity.class);

//                holder.tv_jabatan.getContext().startActivity(intent);
                }
            });

            holder.btDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog1=new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                    dialog1.setTitleText("Harap tunggu");
                    dialog1.setCancelable(true);
                    dialog1.setCancelText("Batal");
                    dialog1.getProgressHelper().setBarColor(Color.parseColor("#fd9c00"));
                    dialog1.show();
                    //start login
                    LoginRequest req = new LoginRequest();
                    req.setUsername(datas.getUsername());
                    req.setPassword("-");
                    req.setDeviceId("-");
                    req.setAppId("BRISI_PEMUTUS");
                    Call<ParseResponse> call = apiClientAdapter.getApiInterface().secretLogin(req);
                    call.enqueue(new Callback<ParseResponse>() {
                        @Override
                        public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                            if(response.isSuccessful()){

                                if(response.body().getStatus().equalsIgnoreCase("00")){
                                    String listDataString = response.body().getData().toString();
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<LoginCred>() {}.getType();

                                    dataUser = gson.fromJson(listDataString, type);

                                    //hanya pemutus yang bisa login,yaitu : uh, pincapem, m3, pinca, mm, AMM

                                        Toasty.success(context,"Berhasil Ambil Alih  "+dataUser.getNama(),Toast.LENGTH_LONG, true).show();

                                        //SIMPAN UID PEMUTUS ORIGINALNYA BRO sebelum preference id direplace
                                        appPreferences.setIdPengambilAlih(appPreferences.getUid());

                                        //ganti preference logged in
                                        appPreferences.setLoggedin("yes");

                                        // Toast.makeText(LoginActivity.this, "Selamat datang "+dataUser.getNama(), Toast.LENGTH_SHORT).show();
                                        //menyimpan data login user dalam session preference
                                        appPreferences.setToken(dataUser.getToken());
                                        appPreferences.setRoleType(dataUser.getStatus());
                                        appPreferences.setNamaKanwil(dataUser.getNama_kanwil());
                                        appPreferences.setFidKantor(dataUser.getFid_kantor());
                                        appPreferences.setJabatan(dataUser.getJabatan());
                                        appPreferences.setNamaKantor(dataUser.getNama_kantor());
                                        appPreferences.setKodeSkk(dataUser.getKode_skk());
                                        appPreferences.setDsnCode(dataUser.getDsn_code());
                                        appPreferences.setKodeKanwil(dataUser.getKode_kanwil());
                                        appPreferences.setFidRole(dataUser.getFid_role());
                                        appPreferences.setUid(dataUser.getUid());
                                        appPreferences.setNik(dataUser.getNik());
                                        appPreferences.setNama(dataUser.getNama());
                                        appPreferences.setKodeCabang(dataUser.getKode_cabang());
                                        appPreferences.setUker(dataUser.getUker());
                                        appPreferences.setNamaSkk(dataUser.getNama_skk());
                                        appPreferences.setKodeAo(dataUser.getKode_ao());
                                        appPreferences.setKantor(dataUser.getKantor());
//                                    appPreferences.setUsername(datas.getUsername());

                                        //set status ambil alih
                                        appPreferences.setStatusAmbilAlih("YA");

                                        //reset sessioon perubahan kode SKK
                                        appPreferences.setStatusKodeSkkPinca("belum berubah");

                                        dialog1.dismissWithAnimation();

                                        Intent intent=new Intent(context, CoreLayoutActivity.class);
                                        context.startActivity(intent);

                                }
                                else{
                                    dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialog1.setTitle("Ambil Alih Gagal");
                                    dialog1.setContentText(response.body().getMessage());
                                    dialog1.setConfirmText("Ok");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ParseResponse> call, Throwable t) {
                            Log.d("LOG D", t.getMessage());
                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog1.setTitle("Ambil Alih Gagal");
                            dialog1.setContentText("Gagal terhubung ke server");
                            dialog1.setConfirmText("Ok");
                        }
                    });
                }
            });

        }
        else {

            final UserAmbilAlih datas = dataAmbilAlihFiltered.get(position);

        Log.d("dataambilalihbot",datas.getNAMA_CABANG());
//        if(datas.getJabatan().equalsIgnoreCase("uh")&&datas.getSbdesc().substring(0,7).equalsIgnoreCase("ums kcp")){
//            holder.cv_content.setVisibility(View.GONE);
//        }


            holder.tv_nama.setText(datas.getNAMA_PEGAWAI());
            holder.tv_jabatan.setText(datas.getROLE());

            holder.tv_kantor.setText(datas.getNAMA_CABANG());

            holder.tv_uid.setText("UID : "+datas.getUID());



            holder.btDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog1=new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                    dialog1.setTitleText("Harap tunggu");
                    dialog1.setCancelable(true);
                    dialog1.setCancelText("Batal");
                    dialog1.getProgressHelper().setBarColor(Color.parseColor("#fd9c00"));
                    dialog1.show();
                    //start login
                    LoginRequest req = new LoginRequest();
                    req.setUsername(datas.getUSER_NAME());
                    req.setPassword("-");
                    req.setDeviceId("-");
                    req.setAppId("BRISI_PEMUTUS");
                    Call<ParseResponse> call = apiClientAdapter.getApiInterface().secretLogin(req);
                    call.enqueue(new Callback<ParseResponse>() {
                        @Override
                        public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                            if(response.isSuccessful()){

                                if(response.body().getStatus().equalsIgnoreCase("00")){
                                    String listDataString = response.body().getData().toString();
                                    Gson gson = new Gson();
                                    Type type = new TypeToken<LoginCred>() {}.getType();

                                    dataUser = gson.fromJson(listDataString, type);

                                    //hanya pemutus yang bisa login,yaitu : uh, pincapem, m3, pinca
                                    if(dataUser.getFid_role().toString().equalsIgnoreCase("72")||dataUser.getFid_role().toString().equalsIgnoreCase("71")||dataUser.getFid_role().toString().equalsIgnoreCase("76")||dataUser.getFid_role().toString().equalsIgnoreCase("79")){
                                        Toasty.success(context,"Berhasil Ambil Alih  "+dataUser.getNama(),Toast.LENGTH_LONG, true).show();

                                        //ganti preference logged in
                                        appPreferences.setLoggedin("yes");

                                        // Toast.makeText(LoginActivity.this, "Selamat datang "+dataUser.getNama(), Toast.LENGTH_SHORT).show();
                                        //menyimpan data login user dalam session preference
                                        appPreferences.setToken(dataUser.getToken());
                                        appPreferences.setRoleType(dataUser.getStatus());
                                        appPreferences.setNamaKanwil(dataUser.getNama_kanwil());
                                        appPreferences.setFidKantor(dataUser.getFid_kantor());
                                        appPreferences.setJabatan(dataUser.getJabatan());
                                        appPreferences.setNamaKantor(dataUser.getNama_kantor());
                                        appPreferences.setKodeSkk(dataUser.getKode_skk());
                                        appPreferences.setDsnCode(dataUser.getDsn_code());
                                        appPreferences.setKodeKanwil(dataUser.getKode_kanwil());
                                        appPreferences.setFidRole(dataUser.getFid_role());
                                        appPreferences.setUid(dataUser.getUid());
                                        appPreferences.setNik(dataUser.getNik());
                                        appPreferences.setNama(dataUser.getNama());
                                        appPreferences.setKodeCabang(dataUser.getKode_cabang());
                                        appPreferences.setUker(dataUser.getUker());
                                        appPreferences.setNamaSkk(dataUser.getNama_skk());
                                        appPreferences.setKodeAo(dataUser.getKode_ao());
                                        appPreferences.setKantor(dataUser.getKantor());
//                                    appPreferences.setUsername(datas.getUsername());

                                        //set status ambil alih
                                        appPreferences.setStatusAmbilAlih("YA");

                                        //reset sessioon perubahan kode SKK
                                        appPreferences.setStatusKodeSkkPinca("belum berubah");

                                        dialog1.dismissWithAnimation();

                                        Intent intent=new Intent(context, CoreLayoutActivity.class);
                                        context.startActivity(intent);
                                    }
                                    else{
                                        dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        dialog1.setTitleText("Gagal");
                                        dialog1.setContentText("User ini tidak memiliki akses untuk aplikasi pemutus");
                                        dialog1.setConfirmText("Kembali");
                                        dialog1.showCancelButton(false);
                                    }


                                }
                                else{
                                    dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    dialog1.setTitle("Ambil Alih Gagal");
                                    dialog1.setContentText(response.body().getMessage());
                                    dialog1.setConfirmText("Ok");
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ParseResponse> call, Throwable t) {
                            Log.d("LOG D", t.getMessage());
                            dialog1.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                            dialog1.setTitle("Ambil Alih Gagal");
                            dialog1.setContentText("Gagal terhubung ke server");
                            dialog1.setConfirmText("Ok");
                        }
                    });
                }
            });

        }



    }



    @Override
    public int getItemCount() {
        //return data.size();
//        tambah jika sudah ada fitur search
//        if(datafiltered!=null){
//            return datafiltered.size();
//        }
//        else
//            return 0;

        if(datafiltered==null){
            return dataAmbilAlihFiltered.size();
        }
        else {
            return datafilteredagain.size();
        }


    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                if(indikatorAdapter==1){

                    if (charString.isEmpty()) {
                        dataAmbilAlihFiltered = dataAmbilAlih;
                    } else {
                        List<UserAmbilAlih> filteredList = new ArrayList<>();
                        for (UserAmbilAlih row : dataAmbilAlih) {
                            if (row.getNAMA_PEGAWAI().toLowerCase().contains(charString.toLowerCase()) || row.getROLE().toLowerCase().contains(charString.toLowerCase())
                                    || row.getNAMA_CABANG().toLowerCase().contains(charString.toLowerCase()) || row.getUSER_NAME().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        dataAmbilAlihFiltered = filteredList;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = dataAmbilAlihFiltered;
                    return filterResults;

                }
                else {
                    if (charString.isEmpty()) {
//                        data=datafiltered;
//                        datafiltered = data;
                        datafilteredagain=datafiltered;
                    } else {
                        List<Ao> filteredList = new ArrayList<>();
                        for (Ao row : datafiltered) {
                            if (row.getNama_pegawai().toLowerCase().contains(charString.toLowerCase()) || row.getJabatan().toLowerCase().contains(charString.toLowerCase())
                                    || row.getSbdesc().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase())) {
                                filteredList.add(row);
                            }
                        }
                        datafilteredagain = filteredList;
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = datafilteredagain;
                    return filterResults;
                }
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                if(indikatorAdapter==0) {
                    datafilteredagain = (ArrayList<Ao>) filterResults.values;
                    notifyDataSetChanged();
                }
                else{
                    dataAmbilAlihFiltered = (ArrayList<UserAmbilAlih>) filterResults.values;
                    notifyDataSetChanged();
                }
            }
        };
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama, tv_kantor, tv_jabatan,tv_uid;
        private Button btDetail;
        CardView cv_content;

        public UserViewHolder(View itemView) {
            super(itemView);

            tv_nama =  itemView.findViewById(R.id.tv_namauser_ambil_alih);
            tv_kantor =  itemView.findViewById(R.id.tv_kantoruser_ambil_alih);
            tv_jabatan =  itemView.findViewById(R.id.tv_jabatanuser_ambil_alih);
            tv_uid=itemView.findViewById(R.id.tv_uid_ambil_alih);
            btDetail=itemView.findViewById(R.id.btDetail_ambil_alih);
            cv_content=itemView.findViewById(R.id.cv_content_user);

        }
    }
}
