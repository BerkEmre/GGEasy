package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antika.berk.ggeasylol.fragment.BuildOpenFragment;
import com.antika.berk.ggeasylol.fragment.ChampionChallengeOpenFragment;
import com.antika.berk.ggeasylol.fragment.RuneFragment;
import com.antika.berk.ggeasylol.fragment.TavsiyeFragment;
import com.antika.berk.ggeasylol.helper.RiotApiHelper;
import com.antika.berk.ggeasylol.object.ChampionObject;
import com.antika.berk.ggeasylol.object.ParticipantListObject;
import com.antika.berk.ggeasylol.object.SpellObject;
import com.antika.berk.ggeasylol.R;
import com.antika.berk.ggeasylol.helper.DBHelper;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class ParticipantsAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<ParticipantListObject> mKisiListesi;
    private Context context;
    String sihirdarAdi;
    public ParticipantsAdapter(Activity activity, List<ParticipantListObject> kisiler,String summonerName) {
        context = activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi = kisiler;
        sihirdarAdi=summonerName;
    }

    @Override
    public int getCount() {
        return mKisiListesi.size();
    }

    @Override
    public ParticipantListObject getItem(int position) {
        return mKisiListesi.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View satirView;
        DBHelper dbHelper = new DBHelper(context);

        satirView = mInflater.inflate(R.layout.match_data_list_item, null);
        RelativeLayout back=(RelativeLayout)satirView.findViewById(R.id.back);
        TextView textView = (TextView) satirView.findViewById(R.id.isimsoyisim);
        ImageView imageView = (ImageView) satirView.findViewById(R.id.simge);
        ImageView spell1 = (ImageView) satirView.findViewById(R.id.imageView2);
        ImageView spell2 = (ImageView) satirView.findViewById(R.id.imageView);
        ImageView league = (ImageView) satirView.findViewById(R.id.imageView14);
        RelativeLayout rl_teamcolor1 = (RelativeLayout) satirView.findViewById(R.id.colorlayout1);
        RelativeLayout rl_teamcolor2 = (RelativeLayout) satirView.findViewById(R.id.colorlayout2);
        TextView tv_k = (TextView) satirView.findViewById(R.id.textView19);
        ImageView image_mastery = (ImageView) satirView.findViewById(R.id.imageView18);
        TextView tv_league = (TextView) satirView.findViewById(R.id.textView26);
        TextView tv_divison = (TextView) satirView.findViewById(R.id.textView27);
        TextView tv_point = (TextView) satirView.findViewById(R.id.textView30);
        TextView tv_progress = (TextView) satirView.findViewById(R.id.textView29);
        Button btn=(Button)satirView.findViewById(R.id.button13);
        Button build=(Button)satirView.findViewById(R.id.button15);
        Button rune=(Button)satirView.findViewById(R.id.button16);
        final ParticipantListObject kisi = mKisiListesi.get(position);
        String new_progress = "";
        tv_k.setText(""+kisi.getMaster());
        if(kisi.getMaster_icon()==0)
            image_mastery.setImageResource(R.drawable.seviye0);
        else if(kisi.getMaster_icon()==1)
            image_mastery.setImageResource(R.drawable.seviye1);
        else if(kisi.getMaster_icon()==2)
            image_mastery.setImageResource(R.drawable.seviye2);
        else if(kisi.getMaster_icon()==3)
            image_mastery.setImageResource(R.drawable.seviye3);
        else if(kisi.getMaster_icon()==4)
            image_mastery.setImageResource(R.drawable.seviye4);
        else if(kisi.getMaster_icon()==5)
            image_mastery.setImageResource(R.drawable.seviye5);
        else if(kisi.getMaster_icon()==6)
            image_mastery.setImageResource(R.drawable.seviye6);
        else if(kisi.getMaster_icon()==7)
            image_mastery.setImageResource(R.drawable.seviye7);
        if ((kisi.getMaster())>0) {
            if (kisi.getMaster() > 999999) {
                tv_k.setText("" + (kisi.getMaster() / 1000000) + " M " + ((kisi.getMaster() - 1000000) / 1000) + " K");
            } else if (kisi.getMaster() > 999) {
                tv_k.setText("" + (kisi.getMaster() / 1000) + " K");
            } else {
                tv_k.setText("" + kisi.getMaster());
            }
        }
        if(kisi.getLeague_progress().length()>0)
            tv_progress.setVisibility(View.VISIBLE);
        for (char ch: kisi.getLeague_progress().toCharArray()) {
            if(ch == 'L')
            {
                new_progress+="<font color='#e60000'> X </font>";
            }
            else if(ch == 'W')
            {
                new_progress+="<font color='#33cc33'> O </font>";
            }
            else if(ch == 'N')
            {
                new_progress+="<font color='#e6b800'> - </font>";
            }
        }

        ChampionObject cmp = dbHelper.getChampion(Integer.toString(kisi.getChampionID()));
        if(dbHelper.getChampion(""+kisi.getChampionID()) == null){

        }
        SpellObject spl1 = dbHelper.getSpell(Integer.toString(kisi.getSpell1()));
        SpellObject spl2 = dbHelper.getSpell(Integer.toString(kisi.getSpell2()));

        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/champion/" + cmp.getChampionKey() + ".png").into(imageView);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + spl1.getSpellKey() + ".png").into(spell1);
        Picasso.with(context).load("http://ddragon.leagueoflegends.com/cdn/" + new RiotApiHelper().version + "/img/spell/" + spl2.getSpellKey() + ".png").into(spell2);

        switch (kisi.getLeague())
        {
            case ("BRONZE")     : league.setImageResource(R.drawable.bronze     ); break;
            case ("CHALLENGER") : league.setImageResource(R.drawable.challenger ); break;
            case ("DIAMOND")    : league.setImageResource(R.drawable.diamond    ); break;
            case ("GOLD")       : league.setImageResource(R.drawable.gold       ); break;
            case ("MASTER")     : league.setImageResource(R.drawable.master     ); break;
            case ("PLATINUM")   : league.setImageResource(R.drawable.platinum   ); break;
            case ("PROVISIONAL"): league.setImageResource(R.drawable.provisional); break;
            case ("SILVER")     : league.setImageResource(R.drawable.silver     ); break;
            default             : league.setImageResource(R.drawable.provisional); break;
        }
        if(kisi.getIsim().equals(sihirdarAdi))
            back.setBackgroundColor(0x3907FF07);
        textView.setText(kisi.getIsim());
        if (kisi.getLeague().length()>0)
            tv_league.setText(kisi.getLeague());
        else
            tv_league.setText("UNRANKED");
        tv_divison.setText(kisi.getLeague_division());
        tv_point.setText("LP "+kisi.getLeague_point());
        tv_progress.setText(Html.fromHtml(new_progress));

        if(kisi.getTeam() == 100){
            rl_teamcolor1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            rl_teamcolor2.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        else{
            rl_teamcolor1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            rl_teamcolor2.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                Bundle args1 = new Bundle();
                String data=""+kisi.getChampionID();
                args1.putString("data", data);
                TavsiyeFragment alertDialog = new TavsiyeFragment();
                alertDialog.setArguments(args1);
                alertDialog.show(fm,"fragment_alert");
            }
        });
        build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                Bundle args1 = new Bundle();
                String data=""+kisi.getChampionID();
                args1.putString("data", data);
                BuildOpenFragment alertDialog = new BuildOpenFragment();
                alertDialog.setArguments(args1);
                alertDialog.show(fm,"fragment_alert");
            }
        });
        rune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentActivity activity = (FragmentActivity)(context);
                FragmentManager fm = activity.getSupportFragmentManager();
                RuneFragment alertDialog = new RuneFragment();
                alertDialog.setRuneObject(mKisiListesi.get(position));
                alertDialog.show(fm,"fragment_alert");
            }
        });
        return satirView;
    }
}