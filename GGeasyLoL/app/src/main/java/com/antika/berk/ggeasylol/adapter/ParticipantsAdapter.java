package com.antika.berk.ggeasylol.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

    public ParticipantsAdapter(Activity activity, List<ParticipantListObject> kisiler) {
        context = activity;
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mKisiListesi = kisiler;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;
        DBHelper dbHelper = new DBHelper(context);

        satirView = mInflater.inflate(R.layout.match_data_list_item, null);

        TextView textView = (TextView) satirView.findViewById(R.id.isimsoyisim);
        ImageView imageView = (ImageView) satirView.findViewById(R.id.simge);
        ImageView spell1 = (ImageView) satirView.findViewById(R.id.imageView2);
        ImageView spell2 = (ImageView) satirView.findViewById(R.id.imageView);
        ImageView league = (ImageView) satirView.findViewById(R.id.imageView14);
        RelativeLayout rl_teamcolor1 = (RelativeLayout) satirView.findViewById(R.id.colorlayout1);
        RelativeLayout rl_teamcolor2 = (RelativeLayout) satirView.findViewById(R.id.colorlayout2);
        TextView tv_kill = (TextView) satirView.findViewById(R.id.tv_kill);
        TextView tv_death = (TextView) satirView.findViewById(R.id.tv_death);
        TextView tv_asist = (TextView) satirView.findViewById(R.id.tv_asist);
        TextView tv_win = (TextView) satirView.findViewById(R.id.tv_win);
        TextView tv_lost = (TextView) satirView.findViewById(R.id.tv_lost);
        TextView tv_played = (TextView) satirView.findViewById(R.id.tv_played);
        TextView tv_league = (TextView) satirView.findViewById(R.id.textView26);
        TextView tv_divison = (TextView) satirView.findViewById(R.id.textView27);
        TextView tv_point = (TextView) satirView.findViewById(R.id.textView30);
        TextView tv_progress = (TextView) satirView.findViewById(R.id.textView29);

        ParticipantListObject kisi = mKisiListesi.get(position);

        String new_progress = "";
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

        textView.setText(kisi.getIsim());
        tv_kill.setText(Integer.toString(kisi.getKill()));
        tv_death.setText(Integer.toString(kisi.getDeath()));
        tv_asist.setText(Integer.toString(kisi.getAsist()));
        tv_win.setText(Integer.toString(kisi.getWin()));
        tv_lost.setText(Integer.toString(kisi.getLost()));
        tv_played.setText(Integer.toString(kisi.getPlayed()));
        tv_league.setText(kisi.getLeague());
        tv_divison.setText(kisi.getLeague_division());
        tv_point.setText(Integer.toString(kisi.getLeague_point()));
        tv_progress.setText(Html.fromHtml(new_progress));

        if(kisi.getTeam() == 100){
            rl_teamcolor1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
            rl_teamcolor2.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        }
        else{
            rl_teamcolor1.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
            rl_teamcolor2.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        }

        return satirView;
    }
}