package com.application.bris.brisi_pemutus.page_putusan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.bris.brisi_pemutus.R;
import com.github.florent37.materialviewpager.MaterialViewPagerHelper;


public class PutusanTab1Mikro extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View mview;



    public PutusanTab1Mikro() {
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
    public static PutusanTab1KPR newInstance(String param1, String param2) {
        PutusanTab1KPR fragment = new PutusanTab1KPR();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // HARUS BIKIN BEGINI UNTUK SEMUA FRAGMENT, AGAR NESTEDSCROLLVIEW BISA DIREGISTER KE MATERIALVIEWPAGER
        View v=inflater.inflate(R.layout.fragment_putusan_tab1_mikro,container,false);
        mview=v;
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
