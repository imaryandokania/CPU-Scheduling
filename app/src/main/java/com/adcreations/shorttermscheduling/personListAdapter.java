package com.adcreations.shorttermscheduling;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class personListAdapter extends ArrayAdapter<person>{

    private static final String Tag="PersonalListAdpater";
    private Context mContext;
    int mResource;

    public personListAdapter(Context context, int resource, ArrayList<person> objects)
    {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        int process=getItem(position).getProcess();
        int but=getItem(position).getBut();
        int wt=getItem(position).getWt();
        int tat=getItem(position).getTat();
        int at=getItem(position).getAt();
        int priority=getItem(position).getPriority();
        float awt=getItem(position).getAwt();
        float atat=getItem(position).getAtat();
        int n=getItem(position).getN();
        person per=new person(process,but,wt,tat,at,priority,awt,atat,n);
        LayoutInflater inflater=LayoutInflater.from((Context) mContext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView tvprocess=(TextView) convertView.findViewById(R.id.textView1);
        TextView tvbut=(TextView) convertView.findViewById(R.id.textView2);
        TextView tvwt=(TextView) convertView.findViewById(R.id.textView3);
        TextView tvtat=(TextView) convertView.findViewById(R.id.textView4);
        TextView tvat=(TextView) convertView.findViewById(R.id.textView5);
        TextView tvprio=(TextView) convertView.findViewById(R.id.textView6);
        if(position==0)
        {
            tvprocess.setTextColor(Color.parseColor("#040303"));
            tvprocess.setText("Process");
            tvbut.setTextColor(Color.parseColor("#040303"));
            tvbut.setText("BT");
            tvwt.setTextColor(Color.parseColor("#040303"));
            tvwt.setText("WT");
            tvtat.setTextColor(Color.parseColor("#040303"));
            tvtat.setText("TAT");
            tvat.setTextColor(Color.parseColor("#040303"));
            tvat.setText("AT");
            tvprio.setTextColor(Color.parseColor("#040303"));
            tvprio.setText("Priority");
        }
        else if(position==n+1)
        {
            tvprocess.setTextColor(Color.parseColor("#E8473B"));
            tvprocess.setText("Avg WT:");
            tvbut.setTextColor(Color.parseColor("#040303"));
            tvbut.setText(Float.toString(awt));
            tvwt.setText("       ");
            tvtat.setText("      ");
            tvat.setTextColor(Color.parseColor("#E8473B"));
            tvat.setText("AVG TAT:");
            tvprio.setTextColor(Color.parseColor("#040303"));
            tvprio.setText(Float.toString(atat));
        }
        else {
            tvprocess.setText(Integer.toString(process));
            tvbut.setText(Integer.toString(but));
            tvwt.setText(Integer.toString(wt));
            tvtat.setText(Integer.toString(tat));
            tvat.setText(Integer.toString(at));
            tvprio.setText(Integer.toString(priority));
        }
        return convertView;
        //return super.getView(position, convertView, parent);
    }

}
