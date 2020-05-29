package com.application.bris.brisi_pemutus.view.corelayout.profile;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.application.bris.brisi_pemutus.R;
import com.application.bris.brisi_pemutus.api.config.UriApi;
import com.application.bris.brisi_pemutus.database.AppPreferences;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;

import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;


public class FragmentProfile extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView jabatan,kantor,username,nik;
    AutofitTextView nama;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View mview;



    public FragmentProfile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PutusanTab1KPR.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProfile newInstance(String param1, String param2) {
        FragmentProfile fragment = new FragmentProfile();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // HARUS BIKIN BEGINI UNTUK SEMUA FRAGMENT, AGAR NESTEDSCROLLVIEW BISA DIREGISTER KE MATERIALVIEWPAGER
        View v=inflater.inflate(R.layout.fragment_profile,container,false);
        mview=v;
        CircleImageView imageGlide = (CircleImageView) mview.findViewById(R.id.iv_profile);
        imageGlide.setImageResource(R.drawable.generic_user2);

        nama=mview.findViewById(R.id.tv_profile_nama);
        jabatan=mview.findViewById(R.id.tv_content_jabatan);
        kantor=mview.findViewById(R.id.tv_content_kantor);
        username=mview.findViewById(R.id.tv_content_status);
        nik=mview.findViewById(R.id.tv_content_nik);

        AppPreferences appPreferences=new AppPreferences(getContext());
        nama.setText(appPreferences.getNama());
        jabatan.setText(appPreferences.getJabatan());
        kantor.setText(appPreferences.getNamaSKK());
        username.setText(appPreferences.getUid());
        nik.setText(appPreferences.getNik());


        //ranking, delete after done presentation
//        nik.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Intent intent=new Intent(getActivity(), RankingActivity.class);
//                startActivity(intent);
//                return true;
//            }
//        });



        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.generic_user2)
                .error(R.drawable.generic_user2)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();


        Glide.with(this)
                .load(UriApi.Baseurl.URL + UriApi.fotoProfil.urlFotoProfil+appPreferences.getNik())
                .apply(options)
                .into(imageGlide);

        //Glide.with(this).load("http://keenthemes.com/preview/metronic/theme/assets/pages/media/profile/profile_user.jpg").into(imageGlide);
       // Glide.with(this).load(" https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSZ0g-UATwhvdb7Zt7SUoZZEjb2kWnNtQR0KW_lVfb62OYKRItf").into(imageGlide);
        NestedScrollView scrollView=mview.findViewById(R.id.observablescrollview);
        MaterialViewPagerHelper.registerScrollView(getActivity(),scrollView);
        return mview;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
