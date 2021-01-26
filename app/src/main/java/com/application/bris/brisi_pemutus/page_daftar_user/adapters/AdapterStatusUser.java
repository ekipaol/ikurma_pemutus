package com.application.bris.brisi_pemutus.page_daftar_user.adapters;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
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
import com.application.bris.brisi_pemutus.api.model.ParseResponseArr;
import com.application.bris.brisi_pemutus.api.model.request.dashboard.RequestDashboard;
import com.application.bris.brisi_pemutus.api.model.request.delete_aom.ReqDeleteAom;
import com.application.bris.brisi_pemutus.api.model.request.putusan_pemutus.ReqUid;
import com.application.bris.brisi_pemutus.api.service.ApiClientAdapter;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.application.bris.brisi_pemutus.model.data_ao.Ao;
import com.application.bris.brisi_pemutus.model.list_putusan.Putusan;
import com.application.bris.brisi_pemutus.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterStatusUser extends RecyclerView.Adapter<AdapterStatusUser.StatusViewHolder> implements Filterable {
    private Context context;
    private List<Ao> data;
    private List<Ao> datafiltered;
    ApiClientAdapter apiClientAdapter;
    List<String> listPincapem = new ArrayList<>();
    List<String> listUh = new ArrayList<>();
    List<String> listBos = new ArrayList<>();
    List<String> listCsKc = new ArrayList<>();
    List<String> listCsKcp = new ArrayList<>();
    final Set<String> pincapemDuplikat = new HashSet<String>();
    List<String> listPincapemDuplikat = new ArrayList<>();
    List<String> ListAktifPincapem;
    List<Ao> dataPutusan;
    final Set<String> set1 = new HashSet<String>();
    Ao userTumbal;
    int statusM3 = 0;
    int statusMm = 0;
    int statusPincapem = 0;
    int statusAdp = 0;
    int statusMo = 0;
    int statusUh = 0;
    int statusAom = 0;
    int statusAo = 0;
    int statusCs = 0;
    int statusBos = 0;

    AppPreferences apppref;

    Call<ParseResponseArr> call;

    String statusAktif;
    String jabatan;

    public AdapterStatusUser(Context context, List<Ao> data) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;

        apppref = new AppPreferences(context);

        for (int i = 0; i < data.size(); i++) {
            userTumbal = data.get(i);
//            if (userTumbal.getStatus().equalsIgnoreCase("aktif") &&
//                    userTumbal.getFid_role().equalsIgnoreCase("marketing manager")) {
//                //indikator sudah ada mm yang aktif atau belum
//                statusmm++;
//
//            } else
            if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("72")) {
                // indikator jumlah m3 yang aktif
                statusM3++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("79")) {
                statusPincapem++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("22")) {
                statusAdp++;
            }  else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("25")) {
                statusMo++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("71")) {
                statusUh++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("23")) {
                statusCs++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("26")) {
                statusBos++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("8")) {
                statusAom++;
            } else if (userTumbal.getStatus().equalsIgnoreCase("aktif") && userTumbal.getFid_role().equalsIgnoreCase("100")) {
                statusAo++;
            }

            //memasukkan daftar kantor pincapem yang aktif kedalam list, agar bisa diseleksi di method onbindviewholder
            if (userTumbal.getFid_role().equalsIgnoreCase("79") && userTumbal.getStatus().equalsIgnoreCase("aktif")) {
                listPincapem.add(userTumbal.getKode_skk());
            } else if (userTumbal.getFid_role().equalsIgnoreCase("71") && userTumbal.getStatus().equalsIgnoreCase("aktif")) {
                listUh.add(userTumbal.getKode_skk());
            } else if (userTumbal.getFid_role().equalsIgnoreCase("26") && userTumbal.getStatus().equalsIgnoreCase("aktif")) {
                listBos.add(userTumbal.getKode_skk());
            } else if (userTumbal.getFid_role().equalsIgnoreCase("23") && userTumbal.getStatus().equalsIgnoreCase("aktif")) {
                if (userTumbal.getSbdesc().substring(0, 3).equalsIgnoreCase("KCP")) {
                    listCsKcp.add(userTumbal.getKode_skk());
                } else {
                    listCsKc.add(userTumbal.getKode_skk());
                }
            }
        }
        Log.d("list pincapem duplikat", pincapemDuplikat.toString());
        Log.d("list pincapem aktif", listPincapem.toString());
    }

    @NonNull
    @Override
    public AdapterStatusUser.StatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_status_user, parent, false);
        apiClientAdapter = new ApiClientAdapter(view.getContext());
        if (userTumbal.getStatus().equalsIgnoreCase("aktif")) {
            statusAktif = "aktif";
        } else if (userTumbal.getStatus().equalsIgnoreCase("tidak aktif")) {
            statusAktif = "tidak aktif";
        }

        if (userTumbal.getFid_role().equalsIgnoreCase("72")) {
            jabatan = "mmm";
        }
//        else if (userTumbal.getFid_role().equalsIgnoreCase("marketing manager")) {
//            jabatan = "mm";
//        }
        else if (userTumbal.getFid_role().equalsIgnoreCase("79")) {
            jabatan = "pincapem";
        }
        return new AdapterStatusUser.StatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterStatusUser.StatusViewHolder holder, final int position) {
        final Ao datas = datafiltered.get(position);
        //final User datas = data.get(position);


        holder.tv_nama.setText(datas.getNama_pegawai());
        if (datas.getFid_role().equalsIgnoreCase("26")) {
            holder.tv_jabatan.setText(datas.getJabatan() + "S");
        } else {
            holder.tv_jabatan.setText(datas.getJabatan());
        }
        holder.tv_kantor.setText(datas.getSbdesc());
        holder.tv_status.setText(datas.getStatus());
        if (holder.tv_status.getText().toString().equalsIgnoreCase("tidak aktif")) {
            holder.tv_status.setBackgroundResource(R.drawable.round3);
        } else if (holder.tv_status.getText().toString().equalsIgnoreCase("aktif")) {
            holder.tv_status.setBackgroundResource(R.drawable.round2);
        }
        holder.btStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("status-bar", jabatan + " " + statusAktif + " " + statusMm + " " + datas.getStatus());

                //logika agar tidak ada lebih dari 2 MM yang aktif;
//                if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusmm >= 1 && datas.getJabatan().equalsIgnoreCase("Marketing manager")) {
//                    AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Tidak bisa mengaktifkan lebih dari 1 Marketing Manager");
//                }
//
//                if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusmm == 0 && datas.getJabatan().equalsIgnoreCase("Marketing manager")) {
//
//                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                            .setTitleText("Konfirmasi")
//                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " menjadi aktif?")
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
//                                }
//                            }).setCancelText("Batal")
//                            .show();
//                    notifyDataSetChanged();
//                }

//                if (datas.getStatus().equalsIgnoreCase("aktif") && statusmm >= 1 && datas.getJabatan().equalsIgnoreCase("Marketing manager")) {
//
//                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                            .setTitleText("Konfirmasi")
//                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " ?")
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
//
//                }

                //logika untuk validasi agar hanya ada 1 pincapem yang aktif
                //Jika tombol aktivasi diklik pada user dengan jabatan pincapem yang tidak aktif, maka dilakukan pengecekan apakah kantor pincapem merupakan kantor yang sudah memiliki pincapem aktif

                if (datas.getFid_role().equalsIgnoreCase("79") && datas.getStatus().equalsIgnoreCase("tidak aktif")) {
                    int statusSama = 0;
                    for (int i = 0; i < listPincapem.size(); i++) {
                        if (datas.getKode_skk().equalsIgnoreCase(listPincapem.get(i))) {
                            AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), datas.getSbdesc() + " masih memiliki pincapem aktif");
                            statusSama = 1;
                        }
                    }
                    if (statusSama == 0) {
                        //jika kantor tidak memiliki pincapem aktif, maka alert dialog muncul
                        aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                    }
                } else if (datas.getFid_role().equalsIgnoreCase("79") && datas.getStatus().equalsIgnoreCase("aktif")) {
                    cekAdaPutusanPemutus(datas.getUid(), datas.getJabatan(), datas.getNama_pegawai(), datas.getSbdesc(), "Pincapem");
                }

                //Logika agar maksimal hanya ada 2 MMM di satu cabang
                //masih bisa melakukan pergantian stats MMM jika <2 MMM
                //
                if (datas.getFid_role().equalsIgnoreCase("72") && datas.getStatus().equalsIgnoreCase("tidak aktif") && statusM3 >= 2) {
                    AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada 2 MMM yang aktif di " + datas.getSbdesc());
                } else if (datas.getFid_role().equalsIgnoreCase("72") && datas.getStatus().equalsIgnoreCase("tidak aktif") && statusM3 < 2) {
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " menjadi aktif?")
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

                } else if (datas.getFid_role().equalsIgnoreCase("72") && datas.getStatus().equalsIgnoreCase("aktif")) {
                    cekAdaPutusanPemutus(datas.getUid(), datas.getJabatan(), datas.getNama_pegawai(), datas.getSbdesc(), "MMM");
//                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
//                            .setTitleText("Konfirmasi")
//                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " ?")
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
                }

                if (datas.getFid_role().equalsIgnoreCase("22") && datas.getStatus().equalsIgnoreCase("tidak aktif") && statusAdp >= 1) {
                    AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada ADP yang aktif di " + datas.getSbdesc());
                } else if (datas.getFid_role().equalsIgnoreCase("22") && datas.getStatus().equalsIgnoreCase("tidak aktif") && statusAdp < 1) {
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " menjadi aktif?")
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

                } else if (datas.getFid_role().equalsIgnoreCase("22") && datas.getStatus().equalsIgnoreCase("aktif")) {
                    cekPencairan(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc(), "ADP");
                }

                if (datas.getFid_role().equalsIgnoreCase("25") && datas.getStatus().equalsIgnoreCase("tidak aktif") && statusAdp >= 1) {
                    AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada MO yang aktif di " + datas.getSbdesc());
                } else if (datas.getFid_role().equalsIgnoreCase("25") && datas.getStatus().equalsIgnoreCase("tidak aktif") && statusAdp < 1) {
                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " menjadi aktif?")
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

                } else if (datas.getFid_role().equalsIgnoreCase("25") && datas.getStatus().equalsIgnoreCase("aktif")) {
                    cekPencairan(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc(), "MO");
                }

                if (apppref.getFidRole().equalsIgnoreCase("76")) {

                    if (datas.getFid_role().equalsIgnoreCase("71") && datas.getStatus().equalsIgnoreCase("tidak aktif")) {
                        int statusSama = 0;
                        for (int i = 0; i < listUh.size(); i++) {
                            if (datas.getKode_skk().equalsIgnoreCase(listUh.get(i))) {
                                AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada UH yang aktif di " + datas.getSbdesc() + " Silahkan nonaktifkan terlebih dahulu UH yang ada di lokasi yang merupakan " + datas.getSbdesc());
                                statusSama = 1;
                            }
                        }
                        if (statusSama == 0) {
                            aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                        }
                    } else if (datas.getFid_role().equalsIgnoreCase("71") && datas.getStatus().equalsIgnoreCase("aktif")) {
                        cekAdaPutusanUh(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc());
                    }

                    if (datas.getFid_role().equalsIgnoreCase("26") && datas.getStatus().equalsIgnoreCase("tidak aktif")) {
                        int statusSama = 0;
                        for (int i = 0; i < listBos.size(); i++) {
                            if (datas.getKode_skk().equalsIgnoreCase(listBos.get(i))) {
                                AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada BOS yang aktif di " + datas.getSbdesc() + " Silahkan nonaktifkan terlebih dahulu BOS yang ada di lokasi yang merupakan " + datas.getSbdesc());
                                statusSama = 1;
                            }
                        }
                        if (statusSama == 0) {
                            aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                        }
                    } else if (datas.getFid_role().equalsIgnoreCase("26") && datas.getStatus().equalsIgnoreCase("aktif")) {
                        cekPencairan(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc(), "BOS");
                    }

                    if (datas.getFid_role().equalsIgnoreCase("23") && datas.getStatus().equalsIgnoreCase("tidak aktif")) {
                        int jmlCsKc = 0;
                        int jmlCsKcp = 0;
                        for (int i = 0; i < listCsKc.size(); i++) {
                            if (listCsKc.get(i).equalsIgnoreCase(datas.getKode_skk())) {
                                jmlCsKc++;
                            }
                        }

                        for (int i = 0; i < listCsKcp.size(); i++) {
                            if (listCsKcp.get(i).equalsIgnoreCase(datas.getKode_skk())) {
                                jmlCsKcp++;
                            }
                        }
                        Log.d("Cek jmlCsKc", String.valueOf(jmlCsKc));
                        Log.d("Cek jmlCsKcp", String.valueOf(jmlCsKcp));
                        if (jmlCsKc >= 2) {
                            AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada 2 CS yang aktif di " + datas.getSbdesc() + " Silahkan nonaktifkan terlebih dahulu CS yang ada di lokasi yang merupakan " + datas.getSbdesc());
                        } else if (jmlCsKcp >= 1) {
                            AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada CS yang aktif di " + datas.getSbdesc() + " Silahkan nonaktifkan terlebih dahulu CS yang ada di lokasi yang merupakan " + datas.getSbdesc());
                        } else {
                            aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                        }
                    }
                    //cek apa CS punya pencairan sebelum menonaktifkan
                    else if (datas.getStatus().equalsIgnoreCase("aktif") && datas.getFid_role().equalsIgnoreCase("23")) {
                        cekPencairan(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc(), "CS");
                    }
                } else {

                    //logika untuk validasi agar hanya ada 1 UH yang aktif
                    //Jika tombol aktivasi diklik pada user dengan jabatan pincapem yang tidak aktif, maka dilakukan pengecekan apakah kantor UH merupakan kantor yang sudah memiliki UH aktif

//                    if (datas.getFid_role().equalsIgnoreCase("71") && datas.getStatus().equalsIgnoreCase("tidak aktif")) {
//                        int statusSama = 0;
//                        for (int i = 0; i < listUh.size(); i++) {
//                            Log.d("Cek listUh", listUh.get(i));
//                            Log.d("Cek kodeLengkap", datas.getKode_skk());
//                            if (datas.getKode_skk().equalsIgnoreCase(listUh.get(i))) {
//                                AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Sudah ada UH yang aktif di " + datas.getSbdesc() + " Silahkan nonaktifkan terlebih dahulu UH yang ada di lokasi yang merupakan " + datas.getSbdesc());
//                                statusSama = 1;
//                            }
//                        }
//                        if (statusSama == 0) {
//                            //jika kantor tidak memiliki uh aktif, maka alert dialog muncul
//
////                            AlertDialog.Builder builder = new AlertDialog.Builder(holder.tv_jabatan.getContext());
////                            // Add the buttons
////                            builder.setTitle("Konfirmasi");
////
////                            builder.setMessage("Anda akan mengganti status "+datas.getJabatan()+" "+datas.getNama_pegawai+" menjadi aktif?");
////                            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
////                                public void onClick(DialogInterface dialog, int id) {
////                                    //ubah status di database jika oke
////                                    Toast.makeText(holder.tv_jabatan.getContext(), "Status "+datas.getNama_pegawai+" diubah menjadi aktif", Toast.LENGTH_SHORT).show();
////
////                                    //reload adapter disini
////                                    notifyDataSetChanged();
////                                }
////                            });
////                            builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
////                                public void onClick(DialogInterface dialog, int id) {
////                                    // User cancelled the dialog
////                                }
////                            });
////                            builder.create();
////                            builder.show();
//                            aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
//                        }
//                    } else if (datas.getFid_role().equalsIgnoreCase("71") && datas.getStatus().equalsIgnoreCase("aktif")) {
//                        cekAdaPutusanUh(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc());
//                    }

                    if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusUh >= 1 && datas.getFid_role().equalsIgnoreCase("71")) {
                        AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Tidak bisa mengaktifkan lebih dari 1 UH");
                    } else if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusUh == 0 && datas.getFid_role().equalsIgnoreCase("71")) {
                        aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                    } else if (datas.getStatus().equalsIgnoreCase("aktif") && datas.getFid_role().equalsIgnoreCase("71")) {
                        cekAdaPutusanUh(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc());
                    }

                    //TIDAK BISA LEBIH DARI 1 BOS AKTIF
                    if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusBos >= 1 && datas.getFid_role().equalsIgnoreCase("26")) {
                        AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Tidak bisa mengaktifkan lebih dari 1 BOS");
                    } else if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusBos == 0 && datas.getFid_role().equalsIgnoreCase("26")) {
                        aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                    }

                    //cek apa BOS punya pencairan sebelum menonaktifkan
                    else if (datas.getStatus().equalsIgnoreCase("aktif") && datas.getFid_role().equalsIgnoreCase("26")) {
                        cekPencairan(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc(), "BOS");
                    }

                    //TIDAK BISA LEBIH DARI 1 CS AKTIF
                    if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusCs >= 1 && datas.getFid_role().equalsIgnoreCase("23")) {
                        AppUtil.notifwarning(holder.btStatus.getContext(), holder.tv_jabatan.getRootView(), "Tidak bisa mengaktifkan lebih dari 1 CS");
                    } else if (datas.getStatus().equalsIgnoreCase("tidak aktif") && statusCs == 0 && datas.getFid_role().equalsIgnoreCase("23")) {
                        aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());
                    }

                    //cek apa CS punya pencairan sebelum menonaktifkan
                    else if (datas.getStatus().equalsIgnoreCase("aktif") && datas.getFid_role().equalsIgnoreCase("23")) {
                        cekPencairan(datas.getUid(), datas.getNama_pegawai(), datas.getSbdesc() , "CS");
                    }
                }

                //logika untuk penggantian status AOM
                if (datas.getStatus().equalsIgnoreCase("tidak aktif") && datas.getFid_role().equalsIgnoreCase("8")) {

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

                    aktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getNama_pegawai());

                } else if (datas.getStatus().equalsIgnoreCase("aktif") && datas.getFid_role().equalsIgnoreCase("8")) {

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

                    deaktivasiStatusAom(Integer.parseInt(datas.getUid()), datas.getJabatan(), datas.getNama_pegawai(), datas.getSbdesc());

                }

                //logika untuk penggantian status AO
                if (datas.getStatus().equalsIgnoreCase("tidak aktif") && datas.getFid_role().equalsIgnoreCase("100")) {

                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " menjadi aktif?")
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
                } else if (datas.getStatus().equalsIgnoreCase("aktif") && datas.getFid_role().equalsIgnoreCase("100")) {

                    new SweetAlertDialog(holder.tv_jabatan.getContext(), SweetAlertDialog.NORMAL_TYPE)
                            .setTitleText("Konfirmasi")
                            .setContentText("Anda akan mengganti status " + datas.getJabatan() + " " + datas.getNama_pegawai() + " ?")
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
                if (charString.isEmpty()) {
                    datafiltered = data;
                } else {
                    List<Ao> filteredList = new ArrayList<>();
                    for (Ao row : data) {
                        if (row.getNama_pegawai().toLowerCase().contains(charString.toLowerCase()) || row.getJabatan().toLowerCase().contains(charString.toLowerCase())
                                || row.getSbdesc().toLowerCase().contains(charString.toLowerCase()) || row.getStatus().toLowerCase().contains(charString.toLowerCase())) {
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
        dialogKonfirm.setContentText("Aktifkan status user " + namaPegawai + " ?")
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
                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");
                                        dialogKonfirm.setContentText("Status menjadi Aktif");
                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                                ((Activity) context).recreate();
                                            }
                                        });

                                    } else {
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

    public void deaktivasiStatusAom(final int uid, String jabatan, String namaPegawai, String kantor) {
        final SweetAlertDialog dialogKonfirm = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        dialogKonfirm.setTitleText("Konfirmasi");
        dialogKonfirm.setContentText("Nonaktifkan status user " + jabatan + " " + namaPegawai + ", dari " + kantor + " ?")
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
                                if (response.isSuccessful()) {
                                    if (response.body().getStatus().equalsIgnoreCase("00")) {

                                        dialogKonfirm.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        dialogKonfirm.setTitleText("Berhasil");
                                        dialogKonfirm.setContentText("Status menjadi Tidak Aktif");
                                        dialogKonfirm.setConfirmText("OK");
                                        dialogKonfirm.showCancelButton(false);
                                        dialogKonfirm.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                dialogKonfirm.dismissWithAnimation();
                                                ((Activity) context).recreate();
                                            }
                                        });

                                    } else {
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

    public void cekAdaPutusanUh(final String uid, final String nama, final String kantor) {
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter = new ApiClientAdapter(context);
        RequestDashboard req = new RequestDashboard();
        AppPreferences appPreferences = new AppPreferences(context);
        req.setKodeSkk(appPreferences.getKodeSkk());
        req.setUid(Integer.parseInt(uid));

        // DISINI MEMANGGIL DASHBOARD karena di data dashboard ada list putusan, jadi kalo ada isinya, berarti id tersebut punya putusan
        //
        //
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().dashboardRequest(req);
        call.enqueue(new Callback<ParseResponse>() {


            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataPutusanString = response.body().getData().get("listPutusan").toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<Putusan>>() {
                        }.getType();


                        dataPutusan = gson.fromJson(listDataPutusanString, type2);
                        dialogLoading.dismissWithAnimation();
                        if (dataPutusan.size() > 0) {
                            SweetAlertDialog dialogError = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText("UH masih memiliki putusan yang belum diselesaikan, harap selesaikan putusan terlebih dahulu sebelum menonaktifkan UH").show();
//                            ((Activity)context).recreate();
                        } else {
                            deaktivasiStatusAom(Integer.parseInt(uid), "UH", nama, kantor);
                        }


                    } else {
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

    public void cekPencairan(final String uid, final String nama, final String kantor, final String role) {
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter = new ApiClientAdapter(context);
        ReqUid req = new ReqUid();
        req.setUid(uid);

        if (role.equalsIgnoreCase("ADP")) {
            call  = apiClientAdapter.getApiInterface().cekAdp(req);
        } else if (role.equalsIgnoreCase("MO")) {
            call = apiClientAdapter.getApiInterface().cekMo(req);
        } else if (role.equalsIgnoreCase("BOS")) {
            call = apiClientAdapter.getApiInterface().cekBos(req);
        } else if (role.equalsIgnoreCase("CS")) {
            call = apiClientAdapter.getApiInterface().cekCs(req);
        }

        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataPutusanString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type2 = new TypeToken<List<Putusan>>() {
                        }.getType();

                        dialogLoading.dismissWithAnimation();

                        //comment dlu cek pencairan, karena kata mas wildan dicabut dlu
                        //uncomment semuanya jika validasi diperlukan lagi
//                        if (!listDataPutusanString.equalsIgnoreCase("[]")) {
//                            SweetAlertDialog dialogError = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
//                            dialogError.setTitleText("Gagal");
//                            dialogError.setContentText(role + " masih memiliki pencairan yang belum diselesaikan, harap selesaikan pencairan terlebih dahulu sebelum menonaktifkan " + role).show();
////                            ((Activity)context).recreate();
//                        } else {
                            deaktivasiStatusAom(Integer.parseInt(uid), role, nama, kantor);
//                        }


                    } else {
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

//    public void cekPencairanCs(final String uid, final String nama, final String kantor) {
//        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
//        dialogLoading.setTitleText("Loading");
//        dialogLoading.show();
//
//        apiClientAdapter = new ApiClientAdapter(context);
//        ReqPutusan req = new ReqPutusan();
//        AppPreferences appPreferences = new AppPreferences(context);
//        req.setUid(uid);
//        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekCs(req);
//        call.enqueue(new Callback<ParseResponseArr>() {
//
//
//            @Override
//            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
//
//                if (response.isSuccessful()) {
//
//                    if (response.body().getStatus().equalsIgnoreCase("00")) {
//                        String listDataPutusanString = response.body().getData().toString();
//                        Gson gson = new Gson();
//                        Type type2 = new TypeToken<List<Putusan>>() {
//                        }.getType();
//
//                        dialogLoading.dismissWithAnimation();
//                        if (!listDataPutusanString.equalsIgnoreCase("[]")) {
//                            SweetAlertDialog dialogError = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
//                            dialogError.setTitleText("Gagal");
//                            dialogError.setContentText("CS masih memiliki pencairan yang belum diselesaikan, harap selesaikan pencairan terlebih dahulu sebelum menonaktifkan CS").show();
////                            ((Activity)context).recreate();
//                        } else {
//                            deaktivasiStatusAom(Integer.parseInt(uid), "CS", nama, kantor);
//                        }
//
//
//                    } else {
//                        Toast.makeText(context, "gagal", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
//                Log.d("LOG D", t.getMessage());
//
//            }
//        });
//
//
//    }

    public void cekAdaPutusanPemutus(final String uid, final String jabatan, final String nama, final String kantor, final String role) {
        final SweetAlertDialog dialogLoading = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialogLoading.setTitleText("Loading");
        dialogLoading.show();

        apiClientAdapter = new ApiClientAdapter(context);
        ReqUid req = new ReqUid();
        AppPreferences appPreferences = new AppPreferences(context);
        req.setUid(uid);
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().cekAdaPutusan(req);
        call.enqueue(new Callback<ParseResponseArr>() {


            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {

                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataPutusanString = response.body().getData().toString();
//                        Gson gson = new Gson();
//                        Type type2 = new TypeToken<List<Putusan>>() {}.getType();
                        dialogLoading.dismissWithAnimation();
                        Log.d("listdataputusanstring", listDataPutusanString);

                        //kalau tidak ada data dalam putusan, maka akan mengembalikan array kosong dalam bentuk string
                        //maka, jika kembaliannya bukan array kosong, berarti masih ada putusan yang perlu diputus
                        if (!listDataPutusanString.equalsIgnoreCase("[]")) {
                            SweetAlertDialog dialogError = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE);
                            dialogError.setTitleText("Gagal");
                            dialogError.setContentText(role + " masih memiliki putusan yang belum diselesaikan, harap selesaikan putusan terlebih dahulu sebelum menonaktifkan " + role).show();
//                            ((Activity)context).recreate();
                        } else {
                            deaktivasiStatusAom(Integer.parseInt(uid), jabatan, nama, kantor);
                        }


                    } else {
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


            tv_nama = itemView.findViewById(R.id.tv_namauser);
            tv_kantor = itemView.findViewById(R.id.tv_kantoruser);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_jabatan = itemView.findViewById(R.id.tv_jabatanuser);
            btStatus = itemView.findViewById(R.id.btStatus);

        }
    }
}
