package com.application.bris.brisi_pemutus.page_daftar_user.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.application.bris.brisi_pemutus.adapter.pipeline.PipelineHomeAdapater;
import com.application.bris.brisi_pemutus.api.model.ParseResponse;
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.model.request.delete_aom.ReqDeleteAom;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqPutusan;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.model.user.User;
import com.application.bris.brisi_pemutus.page_daftar_user.view.DetailUserActivity;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.Inflater;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterStatusUser extends RecyclerView.Adapter<AdapterStatusUser.StatusViewHolder> implements Filterable {
    private Context context;
    private List<Ao> data;
    private List<Ao> datafiltered;
    ApiClientAdapter apiClientAdapter;
    List<String> listPincapem=new ArrayList<>();
    List<String> listUh=new ArrayList<>();
    final Set<String> pincapemDuplikat = new HashSet<String>();
    List<String> listPincapemDuplikat = new ArrayList<>();
    List<String> ListAktifPincapem;
    List<Ao> dataPutusan;
    final Set<String> set1 = new HashSet<String>();
    Ao userTumbal;
    int statusm3=0;
    int statusmm=0;
    int statuspincapem=0;
    int statusUh = 0;
    int statusAom=0;
    int statusAo=0;
    int statusCs=0;
    int statusBos=0;

    String statusAktif;
    String jabatan;

    public AdapterStatusUser(Context context, List<Ao> data) {
        this.context = context;
        this.data = data;
        this.datafiltered=data;



        for (int i = 0; i < data.size(); i++) {
            userTumbal=data.get(i);
            if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("marketing manager")){
                //indikator sudah ada mm yang aktif atau belum
               statusmm++;

            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("MMM")){
               // indikator jumlah m3 yang aktif
                statusm3++;
            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("pincapem")){
                statuspincapem++;
            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("Uh")){
                statusUh++;
            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("cs")){
                statusCs++;
            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("bo")){
                statusBos++;
            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("AO MIKRO")){
                statusAom++;
            }
            else if(userTumbal.getStatus().equalsIgnoreCase("aktif")&&userTumbal.getJabatan().equalsIgnoreCase("ao")){
                statusAo++;
            }

            //memasukkan daftar kantor pincapem yang aktif kedalam list, agar bisa diseleksi di method onbindviewholder
            if(userTumbal.getJabatan().equalsIgnoreCase("pincapem")&&userTumbal.getStatus().equalsIgnoreCase("aktif")){
                listPincapem.add(userTumbal.getSbdesc());

            }
            else if(userTumbal.getJabatan().equalsIgnoreCase("unit head")&&userTumbal.getStatus().equalsIgnoreCase("aktif")){
                listUh.add(userTumbal.getSbdesc());

            }

        }
        Log.d("list pincapem duplikat",pincapemDuplikat.toString());
        Log.d("list pincapem aktif",listPincapem.toString());



    }

    @NonNull
    @Override
    public AdapterStatusUser.StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_status_user, parent, false);
        apiClientAdapter=new ApiClientAdapter(view.getContext());
        if(userTumbal.getStatus().equalsIgnoreCase("aktif")){
            statusAktif="aktif";
        }
        else if(userTumbal.getStatus().equalsIgnoreCase("tidak aktif")){
            statusAktif="tidak aktif";
        }

        if(userTumbal.getJabatan().equalsIgnoreCase("mmm")){
            jabatan="mmm";
        }
        else if(userTumbal.getJabatan().equalsIgnoreCase("marketing manager")){
            jabatan="mm";
        }
        else if(userTumbal.getJabatan().equalsIgnoreCase("pincapem")){
            jabatan="pincapem";
        }
        return new AdapterStatusUser.StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterStatusUser.StatusViewHolder holder, final int position) {
        final Ao datas = datafiltered.get(position);
        //final User datas = data.get(position);



        holder.tv_nama.setText(datas.getNama_pegawai());
        if(datas.getJabatan().equalsIgnoreCase("bo")){
            holder.tv_jabatan.setText(datas.getJabatan()+"S");
        }
        else{
            holder.tv_jabatan.setText(datas.getJabatan());
        }
        holder.tv_kantor.setText(datas.getSbdesc());
        holder.tv_status.setText(datas.getStatus());
        if (holder.tv_status.getText().toString().equalsIgnoreCase("tidak aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round3);
        }
        else if(holder.tv_status.getText().toString().equalsIgnoreCase("aktif")){
            holder.tv_status.setBackgroundResource(R.drawable.round2);
        }
        holder.btStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("status-bar",jabatan+" "+statusAktif+" "+statusmm+" "+datas.getStatus());

                //logika agar tidak ada lebih dari 2 MM yang aktif;
            if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusmm>=1&&datas.getJabatan().equalsIgnoreCase("Marketing manager"))
            {

                AppUtil.notifwarning(holder.btStatus.getContext(),holder.tv_jabatan.getRootView(),"Tidak bisa mengaktifkan lebih dari 1 Marketing Manager");



            }

                if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusmm==0&&datas.getJabatan().equalsIgnoreCase("Marketing manager"))
                {

//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//// Add the buttons
//                    builder.setTitle("Konfirmasi");
//
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();

                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" menjadi aktif?")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Berhasil")
                                            .setContentText("Status diubah aktif")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null).showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                }
                            }).setCancelText("Batal")
                            .show();
                    notifyDataSetChanged();
                }


                if(datas.getStatus().equalsIgnoreCase("aktif")&&statusmm>=1&&datas.getJabatan().equalsIgnoreCase("Marketing manager"))
                {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//                    // Add the buttons
//                    builder.setTitle("Konfirmasi");
//                    //    builder.setMessage("Pengeluaran Rp."+inpMakan.getText().toString()+" untuk makan tanggal "+waktu.toString().substring(0,16));
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" ?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+"  diubah menjadi tidak aktif", Toast.LENGTH_SHORT).show();
//
//                            //reload adapter dengan database
//                            notifyDataSetChanged();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();

                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" ?")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Berhasil")
                                            .setContentText("Status diubah tidak aktif")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null).showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    notifyDataSetChanged();
                                }
                            }).setCancelText("Batal")
                            .show();

                }

                //logika untuk validasi agar hanya ada 1 pincapem yang aktif
                //Jika tombol aktivasi diklik pada user dengan jabatan pincapem yang tidak aktif, maka dilakukan pengecekan apakah kantor pincapem merupakan kantor yang sudah memiliki pincapem aktif

                if(datas.getJabatan().equalsIgnoreCase("pincapem")&&datas.getStatus().equalsIgnoreCase("tidak aktif")){
                    int statusSama=0;
                    for (int i = 0; i <listPincapem.size() ; i++) {
                        if(datas.getSbdesc().equalsIgnoreCase(listPincapem.get(i))){
//                            Toast.makeText(context, "Sudah ada PINCAPEM "+datas.getSbdesc()+" yang aktif, harap nonaktifkan terlebih dahulu sebelum mengganti status user "+datas.getNama_pegawai, Toast.LENGTH_LONG).show();


                            AppUtil.notifwarning(holder.btStatus.getContext(),holder.tv_jabatan.getRootView(),datas.getSbdesc()+" masih memiliki pincapem aktif");
                            statusSama=1;
                        }}
                        if(statusSama==0){
                            //jika kantor tidak memiliki pincapem aktif, maka alert dialog muncul

//                            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//                            // Add the buttons
//                            builder.setTitle("Konfirmasi");
//
//                            builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
//                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //ubah status di database jika oke
//                                    Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
//
//                                    //reload adapter disini
//                                    notifyDataSetChanged();
//                                }
//                            });
//                            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // User cancelled the dialog
//                                }
//                            });
//                            builder.create();
//                            builder.show();
//                            new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                                    .setTitleText("Konfirmasi")
//                                    .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" menjadi aktif?")
//                                    .setConfirmText("Ya")
//                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                        @Override
//                                        public void onClick(SweetAlertDialog sDialog) {
//                                            sDialog
//                                                    .setTitleText("Berhasil")
//                                                    .setContentText("Status diubah aktif")
//                                                    .setConfirmText("OK")
//                                                    .setConfirmClickListener(null).showCancelButton(false)
//                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                                            notifyDataSetChanged();
//                                        }
//                                    }).setCancelText("Batal")
//                                    .show();

                            aktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai());


                        }


                }

                else if(datas.getJabatan().equalsIgnoreCase("pincapem")&& datas.getStatus().equalsIgnoreCase("aktif")){

                  cekAdaPutusanPemutus(datas.getUid(),datas.getNama_pegawai());


                }


                //Logika agar maksimal hanya ada 2 MMM di satu cabang
                //masih bisa melakukan pergantian stats MMM jika <2 MMM
                //
                if(datas.getJabatan().equalsIgnoreCase("mmm")&&datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusm3>=2){
//                    Toast.makeText(context, "Sudah ada 2 MMM yang aktif, silahkan nonaktifkan salah satu MMM terlebih dahulu sebelum melanjutkan", Toast.LENGTH_SHORT).show();
                    AppUtil.notifwarning(holder.btStatus.getContext(),holder.tv_jabatan.getRootView(),"Sudah ada 2 MMM yang aktif di "+datas.getSbdesc());
                }

                else if(datas.getJabatan().equalsIgnoreCase("mmm")&&datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusm3<2){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//                    // Add the buttons
//                    builder.setTitle("Konfirmasi");
//                    //    builder.setMessage("Pengeluaran Rp."+inpMakan.getText().toString()+" untuk makan tanggal "+waktu.toString().substring(0,16));
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
//
//                            //reload adapter disini
//                            notifyDataSetChanged();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" menjadi aktif?")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Berhasil")
                                            .setContentText("Status diubah aktif")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null).showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    notifyDataSetChanged();
                                }
                            }).setCancelText("Batal")
                            .show();

                }
                else if(datas.getJabatan().equalsIgnoreCase("mmm")&&datas.getStatus().equalsIgnoreCase("aktif")){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//                    // Add the buttons
//                    builder.setTitle("Konfirmasi");
//                    //    builder.setMessage("Pengeluaran Rp."+inpMakan.getText().toString()+" untuk makan tanggal "+waktu.toString().substring(0,16));
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" ?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi tidak aktif", Toast.LENGTH_SHORT).show();
//
//                            //reload adapter disini
//                            notifyDataSetChanged();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" ?")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Berhasil")
                                            .setContentText("Status diubah tidak aktif")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null).showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    notifyDataSetChanged();
                                }
                            }).setCancelText("Batal")
                            .show();
                }


                //logika untuk validasi agar hanya ada 1 UH yang aktif
                //Jika tombol aktivasi diklik pada user dengan jabatan pincapem yang tidak aktif, maka dilakukan pengecekan apakah kantor UH merupakan kantor yang sudah memiliki UH aktif

                if(datas.getJabatan().equalsIgnoreCase("uh")&&datas.getStatus().equalsIgnoreCase("tidak aktif")){
                    int statusSama=0;
                    for (int i = 0; i <listUh.size() ; i++) {
                        if(datas.getSbdesc().equalsIgnoreCase(listUh.get(i))){
//                            Toast.makeText(context, "Sudah ada Unit Head "+datas.getSbdesc()+" yang aktif, harap nonaktifkan terlebih dahulu sebelum mengganti status user "+datas.getNama_pegawai, Toast.LENGTH_LONG).show();
                            AppUtil.notifwarning(holder.btStatus.getContext(),holder.tv_jabatan.getRootView(),"Sudah ada Unit Head yang aktif di "+datas.getSbdesc()+" Silahkan nonaktifkan terlebih dahulu Unit Head yang ada di lokasi yang merupakan "+datas.getSbdesc());
                            statusSama=1;
                        }}
                        if(statusSama==0){
                            //jika kantor tidak memiliki uh aktif, maka alert dialog muncul

//                            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//                            // Add the buttons
//                            builder.setTitle("Konfirmasi");
//
//                            builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
//                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    //ubah status di database jika oke
//                                    Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
//
//                                    //reload adapter disini
//                                    notifyDataSetChanged();
//                                }
//                            });
//                            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    // User cancelled the dialog
//                                }
//                            });
//                            builder.create();
//                            builder.show();
                            aktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai());


                        }


                }

                else if(datas.getJabatan().equalsIgnoreCase("uh")&& datas.getStatus().equalsIgnoreCase("aktif")){

//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//                    // Add the buttons
//                    builder.setTitle("Konfirmasi");
//                    //    builder.setMessage("Pengeluaran Rp."+inpMakan.getText().toString()+" untuk makan tanggal "+waktu.toString().substring(0,16));
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" ?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi tidak aktif", Toast.LENGTH_SHORT).show();
//
//                            //reload adapter disini
//                            notifyDataSetChanged();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();

                    cekAdaPutusanUh(datas.getUid(),datas.getNama_pegawai());



                }

                //TIDAK BISA LEBIH DARI 1 BOS AKTIF

                if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusBos>=1&&datas.getJabatan().equalsIgnoreCase("bo"))
                {

                    AppUtil.notifwarning(holder.btStatus.getContext(),holder.tv_jabatan.getRootView(),"Tidak bisa mengaktifkan lebih dari 1 BOS");



                }

                else if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusBos==0&&datas.getJabatan().equalsIgnoreCase("bo"))
                {
                    aktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai());
                }

                //cek apa BOS punya pencairan sebelum menonaktifkan
                else if(datas.getStatus().equalsIgnoreCase("aktif")&&datas.getJabatan().equalsIgnoreCase("bo")){

                  cekPencairanBos(datas.getUid(),datas.getNama_pegawai());
                }

                //TIDAK BISA LEBIH DARI 1 CS AKTIF

                if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusCs>=1&&datas.getJabatan().equalsIgnoreCase("cs"))
                {

                    AppUtil.notifwarning(holder.btStatus.getContext(),holder.tv_jabatan.getRootView(),"Tidak bisa mengaktifkan lebih dari 1 CS");



                }

                else if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&statusCs==0&&datas.getJabatan().equalsIgnoreCase("cs"))
                {
                    aktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai());
                }

                //cek apa CS punya pencairan sebelum menonaktifkan
                else if(datas.getStatus().equalsIgnoreCase("aktif")&&datas.getJabatan().equalsIgnoreCase("cs")){
                    cekPencairanCs(datas.getUid(),datas.getNama_pegawai());
                }





                //logika untuk penggantian status AOM
                if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&datas.getJabatan().equalsIgnoreCase("AO MIKRO"))
                {

//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//// Add the buttons
//                    builder.setTitle("Konfirmasi");
//
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();


                    //HAPUS COMMENT UNTUK KODE DIBAWAH JIKA INGIN UNDO
//                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                            .setTitleText("Konfirmasi")
//                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" menjadi aktif?")
//                            .setConfirmText("Ya")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog
//                                            .setTitleText("Berhasil")
//                                            .setContentText("Status diubah aktif")
//                                            .setConfirmText("OK")
//                                            .setConfirmClickListener(null).showCancelButton(false)
//                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                                    notifyDataSetChanged();
//                                }
//                            }).setCancelText("Batal")
//                            .show();

                    aktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai());

                }

               else if(datas.getStatus().equalsIgnoreCase("aktif")&&datas.getJabatan().equalsIgnoreCase("AO MIKRO"))
                {

//                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                            .setTitleText("Konfirmasi")
//                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" ?")
//                            .setConfirmText("Ya")
//                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                @Override
//                                public void onClick(SweetAlertDialog sDialog) {
//                                    sDialog
//                                            .setTitleText("Berhasil")
//                                            .setContentText("Status diubah tidak aktif")
//                                            .setConfirmText("OK")
//                                            .setConfirmClickListener(null).showCancelButton(false)
//                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
//                                    notifyDataSetChanged();
//                                }
//                            }).setCancelText("Batal")
//                            .show();

                    deaktivasiStatusAom(Integer.parseInt(datas.getUid()),datas.getNama_pegawai());

                }

                //logika untuk penggantian status AO
                if(datas.getStatus().equalsIgnoreCase("tidak aktif")&&datas.getJabatan().equalsIgnoreCase("ao"))
                {

//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//// Add the buttons
//                    builder.setTitle("Konfirmasi");
//
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();
//                    notifyDataSetChanged();
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" menjadi aktif?")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Berhasil")
                                            .setContentText("Status diubah aktif")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null).showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    notifyDataSetChanged();
                                }
                            }).setCancelText("Batal")
                            .show();
                }

                else if(datas.getStatus().equalsIgnoreCase("aktif")&&datas.getJabatan().equalsIgnoreCase("ao"))
                {

//                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
//// Add the buttons
//                    builder.setTitle("Konfirmasi");
//
//                    builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" ?");
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                            //ubah status di database jika oke
//                            Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi tidak aktif", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            // User cancelled the dialog
//                        }
//                    });
//                    builder.create();
//                    builder.show();
//                    notifyDataSetChanged();
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai()+" ?")
                            .setConfirmText("Ya")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog
                                            .setTitleText("Berhasil")
                                            .setContentText("Status diubah tidak aktif")
                                            .setConfirmText("OK")
                                            .setConfirmClickListener(null).showCancelButton(false)
                                            .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    notifyDataSetChanged();
                                }
                            }).setCancelText("Batal")
                            .show();
                }



            }
        });

    }

    @Override
    public int getItemCount() {
        //return data.size();
//        tambah jika sudah ada fitur search
        return datafiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    datafiltered = data;
                } else {
                    List<Ao> filteredList = new ArrayList<>();
                    for (Ao row : data){
                        if(row.getNama_pegawai().toLowerCase().contains(charString.toLowerCase()) || row.getJabatan().toLowerCase().contains(charString.toLowerCase())
                                || row.getSbdesc().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    datafiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datafiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                datafiltered = (ArrayList<Ao>) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }

    public void aktivasiStatusAom(final int uid, String namaPegawai) {
        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialogKonfirm.setTitleText("Konfirmasi");
                dialogKonfirm.setContentText("Aktifkan status user "+namaPegawai+" ?")
                .setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        dialogKonfirm.setContentText("");
                        ReqDeleteAom req = new ReqDeleteAom();
                        req.setUid(uid);
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().aktivasiStatusAom(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                if(response.isSuccessful()){
                                    if(response.body().getStatus().equalsIgnoreCase("00")){

                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");
                                        dialogKonfirm.setContentText("Status menjadi Aktif");
                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                                ((Activity)context).recreate();
                                            }
                                        });

                                    }
                                    else{
                                        dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        dialogKonfirm.setTitleText("Gagal");
                                        dialogKonfirm.setContentText(response.body().getMessage());
                                        dialogKonfirm.setConfirmText("Coba lagi");

                                    }
                                }

                            }


                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                Log.d("LOG D", t.getMessage());
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText(t.getMessage());
                                dialogKonfirm.setCancelText("OK");
                            }
                        });

                    }
                }).setCancelText("Batal")
                .show();
    }

    public void deaktivasiStatusAom(final int uid, String namaPegawai) {
        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialogKonfirm.setTitleText("Konfirmasi");
        dialogKonfirm.setContentText("Nonaktifkan status user "+namaPegawai+" ?")
                .setConfirmText("Ya")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        dialogKonfirm.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
                        dialogKonfirm.setContentText("");
                        ReqDeleteAom req = new ReqDeleteAom();
                        req.setUid(uid);
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().deaktivasiStatusAom(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                                if(response.isSuccessful()){
                                    if(response.body().getStatus().equalsIgnoreCase("00")){

                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");
                                        dialogKonfirm.setContentText("Status menjadi Tidak Aktif");
                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                                ((Activity)context).recreate();
                                            }
                                        });

                                    }
                                    else{
                                        dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        dialogKonfirm.setTitleText("Gagal");
                                        dialogKonfirm.setContentText(response.body().getMessage());
                                        dialogKonfirm.setConfirmText("Coba lagi");

                                    }
                                }

                            }


                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                Log.d("LOG D", t.getMessage());
                                dialogKonfirm.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                dialogKonfirm.setTitleText("Gagal");
                                dialogKonfirm.setContentText(t.getMessage());
                                dialogKonfirm.setCancelText("OK");
                            }
                        });

                    }
                }).setCancelText("Batal")
                .show();
    }

    public void cekAdaPutusanUh(final String uid,final String nama){
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter= new ApiClientAdapter(context);
        RequestDashboard req = new RequestDashboard();
        AppPreferences appPreferences =new AppPreferences(context);
        req.setUid(Integer.parseInt(uid));

        // DISINI MEMANGGIL DASHBOARD karena di data dashboard ada list putusan, jadi kalo ada isinya, berarti id tersebut punya putusan
        //
        //
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().dashboardRequest(req);
        call.enqueue(new Callback<ParseResponse>() {


            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                if(response.isSuccessful()){

                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataPutusanString = response.body().getData().get("listPutusan").toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<Putusan>>() {}.getType();


                        dataPutusan=gson.fromJson(listDataPutusanString, type2);
                        dialogLoading.dismissWithAnimation();
                        if(dataPutusan.size()>0){
                            SweetAlertDialog dialogError=new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("UH masih memiliki putusan yang belum diselesaikan, harap selesaikan putusan terlebih dahulu sebelum menonaktifkan UH").show();
//                            ((Activity)context).recreate();
                        }
                        else{
                            deaktivasiStatusAom(Integer.parseInt(uid),nama);
                        }



                    }
                    else{
                        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                Log.d("LOG D", t.getMessage());

            }
        });


    }

    public void cekPencairanBos(final String uid,final String nama){
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter= new ApiClientAdapter(context);
        ReqPutusan req = new ReqPutusan();
        AppPreferences appPreferences =new AppPreferences(context);
        req.setUid(uid);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekBos(req);
        call.enqueue(new Callback<ParseResponseArr>() {


            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if(response.isSuccessful()){

                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataPutusanString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<Putusan>>() {}.getType();

                        dialogLoading.dismissWithAnimation();
                        if(!listDataPutusanString.equalsIgnoreCase("[]")){
                            SweetAlertDialog dialogError=new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("BOS masih memiliki pencairan yang belum diselesaikan, harap selesaikan pencairan terlebih dahulu sebelum menonaktifkan BOS").show();
//                            ((Activity)context).recreate();
                        }
                        else{
                            deaktivasiStatusAom(Integer.parseInt(uid),nama);
                        }



                    }
                    else{
                        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());

            }
        });


    }

    public void cekPencairanCs(final String uid,final String nama){
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter= new ApiClientAdapter(context);
        ReqPutusan req = new ReqPutusan();
        AppPreferences appPreferences =new AppPreferences(context);
        req.setUid(uid);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekCs(req);
        call.enqueue(new Callback<ParseResponseArr>() {


            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if(response.isSuccessful()){

                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataPutusanString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<Putusan>>() {}.getType();

                        dialogLoading.dismissWithAnimation();
                        if(!listDataPutusanString.equalsIgnoreCase("[]")){
                            SweetAlertDialog dialogError=new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("CS masih memiliki pencairan yang belum diselesaikan, harap selesaikan pencairan terlebih dahulu sebelum menonaktifkan CS").show();
//                            ((Activity)context).recreate();
                        }
                        else{
                            deaktivasiStatusAom(Integer.parseInt(uid),nama);
                        }



                    }
                    else{
                        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());

            }
        });


    }

    public void cekAdaPutusanPemutus(final String uid,final String nama){
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter= new ApiClientAdapter(context);
        ReqPutusan req = new ReqPutusan();
        AppPreferences appPreferences =new AppPreferences(context);
        req.setUid(uid);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekAdaPutusan(req);
        call.enqueue(new Callback<ParseResponseArr>() {


            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if(response.isSuccessful()){

                    if(response.body().getStatus().equalsIgnoreCase("00")){
                        String listDataPutusanString = response.body().getData().toString();
//                        Gson gson = new Gson();
//                        Type type2 = new TypeToken<List<Putusan>>() {}.getType();
                        dialogLoading.dismissWithAnimation();
                        Log.d("listdataputusanstring",listDataPutusanString);

                        //kalau tidak ada data dalam putusan, maka akan mengembalikan array kosong dalam bentuk string
                        //maka, jika kembaliannya bukan array kosong, berarti masih ada putusan yang perlu diputus
                        if(!listDataPutusanString.equalsIgnoreCase("[]")){
                            SweetAlertDialog dialogError=new SweetAlertDialog(context,SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("PINCAPEM masih memiliki putusan yang belum diselesaikan, harap selesaikan putusan terlebih dahulu sebelum menonaktifkan PINCAPEM").show();
//                            ((Activity)context).recreate();
                        }
                        else{
                            deaktivasiStatusAom(Integer.parseInt(uid),nama);
                        }



                    }
                    else{
                        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                Log.d("LOG D", t.getMessage());

            }
        });


    }


    public class StatusViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_nama, tv_kantor, tv_status, tv_jabatan;
        private Button btStatus;
        CoordinatorLayout layoutCoord;

        public StatusViewHolder(View itemView) {
            super(itemView);


            tv_nama =  itemView.findViewById(R.id.tv_namauser);
            tv_kantor =  itemView.findViewById(R.id.tv_kantoruser);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_jabatan =  itemView.findViewById(R.id.tv_jabatanuser);
            btStatus = itemView.findViewById(R.id.btStatus);

        }
    }
}
