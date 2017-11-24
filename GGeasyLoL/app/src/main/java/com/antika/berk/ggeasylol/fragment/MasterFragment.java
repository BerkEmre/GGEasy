package com.antika.berk.ggeasylol.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.MasterObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.Transformation;


public class MasterFragment extends DialogFragment {
    ImageView hirs1,hirs2,hirs3,hirs4,hirs5,hirs6,hirs7,hirs8,hirs9,hirs10,hirs11,hirs12,hirs13,hirs14,hirs15,
            maharet1,maharet2,maharet3,maharet4,maharet5,maharet6,maharet7,maharet8,maharet9,maharet10,maharet11,
            maharet12,maharet13,maharet14,maharet15,azim1,azim2,azim3,azim4,azim5,azim6,azim7,azim8,azim9,azim10,
            azim11,azim12,azim13,azim14,azim15;

    TextView tv_hirs1,tv_hirs2,tv_hirs6,tv_hirs7,tv_hirs11,
            tv_hirs12,tv_maharet1,tv_maharet2,tv_maharet6,tv_maharet7,tv_maharet11,
            tv_maharet12, tv_azim1,tv_azim2,tv_azim6,tv_azim7,tv_azim11,tv_azim12;
    ImageView frh3,frh4,frh5,frh8,frh9,frh10,frh13,frh14,frh15,frm3,frm4,frm5,frm8,frm9,frm10,frm13,frm14,frm15,fra3,fra4
            ,fra5,fra8,fra9,fra10,fra13,fra14,fra15;
    private ParticipantListObject po;
    public void setMasterObject(ParticipantListObject po) {
        this.po = po;
    }
    List<MasterObject>masterObjects=new ArrayList<MasterObject>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_master, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        frh3=(ImageView)view.findViewById(R.id.frame3);
        frh4=(ImageView)view.findViewById(R.id.frame4);
        frh5=(ImageView)view.findViewById(R.id.frame5);
        frh8=(ImageView)view.findViewById(R.id.frame8);
        frh9=(ImageView)view.findViewById(R.id.frame9);
        frh10=(ImageView)view.findViewById(R.id.frame10);
        frh13=(ImageView)view.findViewById(R.id.frame13);
        frh14=(ImageView)view.findViewById(R.id.frame14);
        frh15=(ImageView)view.findViewById(R.id.frame15);

        fra3=(ImageView)view.findViewById(R.id.fra3);
        fra4=(ImageView)view.findViewById(R.id.fra4);
        fra5=(ImageView)view.findViewById(R.id.fra5);
        fra8=(ImageView)view.findViewById(R.id.fra8);
        fra9=(ImageView)view.findViewById(R.id.fra9);
        fra10=(ImageView)view.findViewById(R.id.fra10);
        fra13=(ImageView)view.findViewById(R.id.fra13);
        fra14=(ImageView)view.findViewById(R.id.fra14);
        fra15=(ImageView)view.findViewById(R.id.fra15);

        frm3=(ImageView)view.findViewById(R.id.frm3);
        frm4=(ImageView)view.findViewById(R.id.frm4);
        frm5=(ImageView)view.findViewById(R.id.frm5);
        frm8=(ImageView)view.findViewById(R.id.frm8);
        frm9=(ImageView)view.findViewById(R.id.frm9);
        frm10=(ImageView)view.findViewById(R.id.frm10);
        frm13=(ImageView)view.findViewById(R.id.frm13);
        frm14=(ImageView)view.findViewById(R.id.frm14);
        frm15=(ImageView)view.findViewById(R.id.frm15);

        hirs1=(ImageView)view.findViewById(R.id.hirs1);
        hirs2=(ImageView)view.findViewById(R.id.hirs2);
        hirs3=(ImageView)view.findViewById(R.id.hirs3);
        hirs4=(ImageView)view.findViewById(R.id.hirs4);
        hirs5=(ImageView)view.findViewById(R.id.hirs5);
        hirs6=(ImageView)view.findViewById(R.id.hirs6);
        hirs7=(ImageView)view.findViewById(R.id.hirs7);
        hirs8=(ImageView)view.findViewById(R.id.hirs8);
        hirs9=(ImageView)view.findViewById(R.id.hirs9);
        hirs10=(ImageView)view.findViewById(R.id.hirs10);
        hirs11=(ImageView)view.findViewById(R.id.hirs11);
        hirs12=(ImageView)view.findViewById(R.id.hirs12);
        hirs13=(ImageView)view.findViewById(R.id.hirs13);
        hirs14=(ImageView)view.findViewById(R.id.hirs14);
        hirs15=(ImageView)view.findViewById(R.id.hirs15);

        maharet1=(ImageView)view.findViewById(R.id.maharet1);
        maharet2=(ImageView)view.findViewById(R.id.maharet2);
        maharet3=(ImageView)view.findViewById(R.id.maharet3);
        maharet4=(ImageView)view.findViewById(R.id.maharet4);
        maharet5=(ImageView)view.findViewById(R.id.maharet5);
        maharet6=(ImageView)view.findViewById(R.id.maharet6);
        maharet7=(ImageView)view.findViewById(R.id.maharet7);
        maharet8=(ImageView)view.findViewById(R.id.maharet8);
        maharet9=(ImageView)view.findViewById(R.id.maharet9);
        maharet10=(ImageView)view.findViewById(R.id.maharet10);
        maharet11=(ImageView)view.findViewById(R.id.maharet11);
        maharet12=(ImageView)view.findViewById(R.id.maharet12);
        maharet13=(ImageView)view.findViewById(R.id.maharet13);
        maharet14=(ImageView)view.findViewById(R.id.maharet14);
        maharet15=(ImageView)view.findViewById(R.id.maharet15);

        azim1=(ImageView)view.findViewById(R.id.azim1);
        azim2=(ImageView)view.findViewById(R.id.azim2);
        azim3=(ImageView)view.findViewById(R.id.azim3);
        azim4=(ImageView)view.findViewById(R.id.azim4);
        azim5=(ImageView)view.findViewById(R.id.azim5);
        azim6=(ImageView)view.findViewById(R.id.azim6);
        azim7=(ImageView)view.findViewById(R.id.azim7);
        azim8=(ImageView)view.findViewById(R.id.azim8);
        azim9=(ImageView)view.findViewById(R.id.azim9);
        azim10=(ImageView)view.findViewById(R.id.azim10);
        azim11=(ImageView)view.findViewById(R.id.azim11);
        azim12=(ImageView)view.findViewById(R.id.azim12);
        azim13=(ImageView)view.findViewById(R.id.azim13);
        azim14=(ImageView)view.findViewById(R.id.azim14);
        azim15=(ImageView)view.findViewById(R.id.azim15);

        tv_hirs1=(TextView)view.findViewById(R.id.tv_hirs1);
        tv_hirs2=(TextView)view.findViewById(R.id.tv_hirs2);
        tv_hirs6=(TextView)view.findViewById(R.id.tv_hirs6);
        tv_hirs7=(TextView)view.findViewById(R.id.tv_hirs7);
        tv_hirs11=(TextView)view.findViewById(R.id.tv_hirs11);
        tv_hirs12=(TextView)view.findViewById(R.id.tv_hirs12);

        tv_maharet1=(TextView)view.findViewById(R.id.tv_maharet1);
        tv_maharet2=(TextView)view.findViewById(R.id.tv_maharet2);
        tv_maharet6=(TextView)view.findViewById(R.id.tv_maharet6);
        tv_maharet7=(TextView)view.findViewById(R.id.tv_maharet7);
        tv_maharet11=(TextView)view.findViewById(R.id.tv_maharet11);
        tv_maharet12=(TextView)view.findViewById(R.id.tv_maharet12);

        tv_azim1=(TextView)view.findViewById(R.id.tv_azim1);
        tv_azim2=(TextView)view.findViewById(R.id.tv_azim2);
        tv_azim6=(TextView)view.findViewById(R.id.tv_azim6);
        tv_azim7=(TextView)view.findViewById(R.id.tv_azim7);
        tv_azim11=(TextView)view.findViewById(R.id.tv_azim11);
        tv_azim12=(TextView)view.findViewById(R.id.tv_azim12);
        Picasso.with(getContext()).load(R.drawable.hirs3).transform(new CircleTransform()).into(hirs3);
        Picasso.with(getContext()).load(R.drawable.hirs4).transform(new CircleTransform()).into(hirs4);
        Picasso.with(getContext()).load(R.drawable.hirs5).transform(new CircleTransform()).into(hirs5);
        Picasso.with(getContext()).load(R.drawable.hirs8).transform(new CircleTransform()).into(hirs8);
        Picasso.with(getContext()).load(R.drawable.hirs9).transform(new CircleTransform()).into(hirs9);
        Picasso.with(getContext()).load(R.drawable.hirs10).transform(new CircleTransform()).into(hirs10);
        Picasso.with(getContext()).load(R.drawable.hirs13).transform(new CircleTransform()).into(hirs13);
        Picasso.with(getContext()).load(R.drawable.hirs14).transform(new CircleTransform()).into(hirs14);
        Picasso.with(getContext()).load(R.drawable.hirs15).transform(new CircleTransform()).into(hirs15);

        Picasso.with(getContext()).load(R.drawable.azim3).transform(new CircleTransform()).into(azim3);
        Picasso.with(getContext()).load(R.drawable.azim4).transform(new CircleTransform()).into(azim4);
        Picasso.with(getContext()).load(R.drawable.azim5).transform(new CircleTransform()).into(azim5);
        Picasso.with(getContext()).load(R.drawable.azim8).transform(new CircleTransform()).into(azim8);
        Picasso.with(getContext()).load(R.drawable.azim9).transform(new CircleTransform()).into(azim9);
        Picasso.with(getContext()).load(R.drawable.azim10).transform(new CircleTransform()).into(azim10);
        Picasso.with(getContext()).load(R.drawable.azim13).transform(new CircleTransform()).into(azim13);
        Picasso.with(getContext()).load(R.drawable.azim14).transform(new CircleTransform()).into(azim14);
        Picasso.with(getContext()).load(R.drawable.azim15).transform(new CircleTransform()).into(azim15);

        Picasso.with(getContext()).load(R.drawable.maharet3).transform(new CircleTransform()).into(maharet3);
        Picasso.with(getContext()).load(R.drawable.maharet4).transform(new CircleTransform()).into(maharet4);
        Picasso.with(getContext()).load(R.drawable.maharet5).transform(new CircleTransform()).into(maharet5);
        Picasso.with(getContext()).load(R.drawable.maharet8).transform(new CircleTransform()).into(maharet8);
        Picasso.with(getContext()).load(R.drawable.maharet9).transform(new CircleTransform()).into(maharet9);
        Picasso.with(getContext()).load(R.drawable.maharet10).transform(new CircleTransform()).into(maharet10);
        Picasso.with(getContext()).load(R.drawable.maharet13).transform(new CircleTransform()).into(maharet13);
        Picasso.with(getContext()).load(R.drawable.maharet14).transform(new CircleTransform()).into(maharet14);
        Picasso.with(getContext()).load(R.drawable.maharet15).transform(new CircleTransform()).into(maharet15);
        masterObjects=po.getMasterObjects();

        for (int i=0;i<masterObjects.size();i++){
            switch (masterObjects.get(i).getId()){
                case (6111)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(hirs1);
                    tv_hirs1.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6114)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(hirs2);
                    tv_hirs2.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6121)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs3);
                    frh3.setVisibility(View.VISIBLE);} break;
                case (6122)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs4);
                    frh4.setVisibility(View.VISIBLE);} break;
                case (6123)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs5);
                    frh5.setVisibility(View.VISIBLE);} break;
                case (6131)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(hirs6);
                    tv_hirs6.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6134)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(hirs7);
                    tv_hirs7.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6141)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs8);
                    frh8.setVisibility(View.VISIBLE);} break;
                case (6142)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs9);
                    frh9.setVisibility(View.VISIBLE);} break;
                case (6143)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs10);
                    frh10.setVisibility(View.VISIBLE);} break;
                case (6151)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(hirs11);
                    tv_hirs11.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6154)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(hirs12);
                    tv_hirs12.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6161)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs13);
                    frh13.setVisibility(View.VISIBLE);} break;
                case (6162)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs14);
                    frh14.setVisibility(View.VISIBLE);} break;
                case (6164)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(hirs15);
                    frh15.setVisibility(View.VISIBLE);} break;
                case (6211)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(azim1);
                    tv_azim1.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6212)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(azim2);
                    tv_azim2.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6221)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim3);
                    fra3.setVisibility(View.VISIBLE);} break;
                case (6222)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim4);
                    fra4.setVisibility(View.VISIBLE);} break;
                case (6223)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim5);
                    fra5.setVisibility(View.VISIBLE);} break;
                case (6231)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(azim6);
                    tv_azim6.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6232)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(azim7);
                    tv_azim7.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6241)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim8);
                    fra8.setVisibility(View.VISIBLE);} break;
                case (6242)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim9);
                    fra9.setVisibility(View.VISIBLE);} break;
                case (6243)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim10);
                    fra10.setVisibility(View.VISIBLE);} break;
                case (6251)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(azim11);
                    tv_azim11.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6252)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(azim12);
                    tv_azim12.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6261)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim13);
                    fra13.setVisibility(View.VISIBLE);} break;
                case (6262)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim14);
                    fra14.setVisibility(View.VISIBLE);} break;
                case (6263)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(azim15);
                    fra15.setVisibility(View.VISIBLE);} break;
                case (6311)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(maharet1);
                    tv_maharet1.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6312)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(maharet2);
                    tv_maharet2.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6321)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet3);
                    frm3.setVisibility(View.VISIBLE);} break;
                case (6322)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet4);
                    frm4.setVisibility(View.VISIBLE);} break;
                case (6323)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet5);
                    frm5.setVisibility(View.VISIBLE);} break;
                case (6331)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(maharet6);
                    tv_maharet6.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6332)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(maharet7);
                    tv_maharet7.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6341)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet8);
                    frm8.setVisibility(View.VISIBLE);} break;
                case (6342)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet9);
                    frm9.setVisibility(View.VISIBLE);} break;
                case (6343)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet10);
                    frm10.setVisibility(View.VISIBLE);} break;
                case (6351)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(maharet11);
                    tv_maharet11.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6352)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").into(maharet12);
                    tv_maharet12.setText(""+masterObjects.get(i).getRank()+"/5");} break;
                case (6361)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet13);
                    frm13.setVisibility(View.VISIBLE);} break;
                case (6362)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet14);
                    frm14.setVisibility(View.VISIBLE);} break;
                case (6363)     : {Picasso.with(getContext()).load("http://ddragon.leagueoflegends.com/cdn/"+new RiotApiHelper().version+"/img/mastery/"+masterObjects.get(i).getId()+".png").transform(new CircleTransform()).into(maharet15);
                    frm15.setVisibility(View.VISIBLE);} break;

            }
        }



        return view;
    }
    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
