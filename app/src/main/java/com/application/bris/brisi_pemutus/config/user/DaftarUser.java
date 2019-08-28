package com.application.bris.brisi_pemutus.config.user;

import android.content.Context;

import com.application.bris.brisi_pemutus.model.user.User;

import java.util.List;

public class DaftarUser {

    public static void listUser(Context context, List<User> users) {


        //USER PINCAPEM
        users.add(new User("Eki Yusandhi","KC Wahid Hasyim","Marketing Manager","500,000,000","Tidak Aktif","Tidak Terkunci","616515","166526"));
        users.add(new User("Aleksander Daskevitch","KC Wahid Hasyim","MMM","300,000,000","Tidak Aktif","Tidak Terkunci","255256","11222"));
        users.add(new User("Tarmidi Georger","KC Wahid Hasyim","Marketing Manager","300,000,000","Aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Little Red Riding Hood","KCP Fatmawati","Pincapem","400,000,000","Tidak Aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Sumiyadi","KC Wahid Hasyim","Marketing Manager","300,000,000","Tidak Aktif","Tidak Terkunci","544422","53346"));
       users.add(new User("Adriana Grando","KCP Fatmawati","Pincapem","300,000,000","Aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Marijan Mbah","KCP Tanah Abang","Pincapem","300,000,000","Tidak Aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Adi Prastyo","KCP Tanah Abang","Pincapem","300,000,000","Tidak aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Mahmud Asyafar","KCP Bendungan Hilir","Pincapem","300,000,000","Tidak aktif","Tidak Terkunci","544422","53346"));

        users.add(new User("Lontong Daskaevitch","KCP Bendungan Hilir","MMM","300,000,000","Aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Alek Rendang","KCP Bendungan Hilir","MMM","300,000,000","Aktif","Tidak Terkunci","544422","53346"));
        users.add(new User("Kayupuk Guri","KCP Bendungan Hilir","MMM","300,000,000","Tidak aktif","Tidak Terkunci","544422","53346"));


        //USER MMM
//        users.add(new User("Debora Paulina","KC Wahid Hasyim","Unit Head","500,000,000","Tidak Aktif","Tidak Terkunci","616515","166526"));
//        users.add(new User("Alif lam Mim","KC Wahid Hasyim","Unit Head","500,000,000","Aktif","Tidak Terkunci","616515","166526"));

        //USER UH
//        users.add(new User("Muhammad Alek","KC Wahid Hasyim","AOM","500,000,000","Tidak Aktif","Tidak Terkunci","616515","166526"));
//        users.add(new User("Alif Lam Ra","KC Wahid Hasyim","AOM","500,000,000","Aktif","Tidak Terkunci","616515","166526"));

        //USER MM
//        users.add(new User("Lam Yalid","KC Wahid Hasyim","AO","500,000,000","Tidak Aktif","Tidak Terkunci","616515","166526"));
//        users.add(new User("Walam Yulad","KC Wahid Hasyim","AO","500,000,000","Aktif","Tidak Terkunci","616515","166526"));


    }

    public void addUser(String nama,String kantor,String jabatan,String limit,String uid,List<User> users){
    users.add(new User(nama,kantor,jabatan,limit,"Aktif","Tidak Terkunci",uid,"12345678"));
    }
}
