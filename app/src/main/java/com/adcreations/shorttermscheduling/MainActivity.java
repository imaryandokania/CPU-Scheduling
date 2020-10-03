package com.adcreations.shorttermscheduling;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG="MainActivity";
    int [] processes;
    int [] burst_time;
    int [] arrival_time;
    int []priority;
    ArrayList<Integer> list;
    ArrayList<Integer> list1;
    ArrayList<Integer> list2;
    ArrayList<person> af;
    ArrayList<Integer> list3;
    public int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        final int pos=0;
        setContentView(R.layout.activity_main);
        final ListView mListView=(ListView)findViewById(R.id.listview);
        final Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"- - - - - - - - - Select Scheduling - - - - - - - -","FCFS(Non-Preemptive)", "FCFS(Preemptive)", "SJF(Non-Preemptive)","SJF(Preemptive)","Priority(Non-Preemptive)","Priority(Preemptive)"};
        dropdown.setSelection(pos);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        final ProgressDialog progress = new ProgressDialog(MainActivity.this);
        list = new ArrayList<>();

        list1 = new ArrayList<Integer>();
        list2 = new ArrayList<Integer>();
        af=new ArrayList<>();
        list3 = new ArrayList<Integer>();
        final EditText pid = (EditText)findViewById(R.id.pno);
        final EditText bt = (EditText)findViewById(R.id.btime);
        final EditText at = (EditText)findViewById(R.id.atime);
        final EditText  pu=(EditText)findViewById(R.id.priority) ;
        Button but=(Button)findViewById(R.id.add_process);
        final Button v=(Button)findViewById(R.id.proc);
        Button but2=(Button)findViewById(R.id.clean);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                af.clear();
                list.clear();
                list1.clear();
                list2.clear();
                list3.clear();
                processes=null;
                burst_time=null;
                priority=null;
                adapter.notifyDataSetChanged();
                mListView.invalidateViews();
                mListView.refreshDrawableState();
                pid.setEnabled(true);
                bt.setEnabled(true);
                at.setEnabled(true);
                pu.setEnabled(true);
                count=0;



            }
        });
        int a;
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try
                {
                String l = pid.getText().toString();
                int a = Integer.parseInt(l);
                String z = bt.getText().toString();
                int b = Integer.parseInt(z);
                String g = at.getText().toString();
                int c = Integer.parseInt(g);
                String jj = pu.getText().toString();
                int kl = Integer.parseInt(jj);

                    list.add(a);
                    list1.add(b);
                    list2.add(c);
                    list3.add(kl);
                    pid.getText().clear();
                    bt.getText().clear();
                    at.getText().clear();
                    pu.getText().clear();
                    Toast.makeText(MainActivity.this, "Added process", Toast.LENGTH_SHORT).show();

                }
                   catch (NumberFormatException e)
                   {
                       Toast.makeText(MainActivity.this, "Please Enter all Values", Toast.LENGTH_SHORT).show();
                   }

            }

        });
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ary = dropdown.getSelectedItemPosition();
                String r = Integer.toString(ary);
                pid.setEnabled(false);
                bt.setEnabled(false);
                at.setEnabled(false);
                count++;
                if(count>1)
                {
                    Toast.makeText(MainActivity.this, "Final Answer is already on the screen", Toast.LENGTH_SHORT).show();
                    return;
                }
                pu.setEnabled(false);
                int n = list.size();
                int n1 = list1.size();
                int n3=list2.size();
                int n2=list3.size();
                processes = new int[n];
                arrival_time=new int[n];
                burst_time = new int[n1];
                priority=new int[n2];
                for (int i = 0; i < n; i++) {
                    processes[i] = list.get(i);
                }
                for (int i = 0; i < n1; i++) {
                    burst_time[i] = list1.get(i);
                }
                for (int i = 0; i < n2; i++) {
                    priority[i] = list3.get(i);
                }
                    for (int i = 0; i < n2; i++) {
                       arrival_time[i] = list2.get(i);
                }
                if (ary == 1)                                             //fcfs(non-preemptive)
                {
                    int []p = new int[10];
                    int wt[] = new int[10], tat[] = new int[10];
                    int bt[]=new int[10];
                    for(int i=0;i<n;i++)
                    {
                        bt[i]=burst_time[i];
                        p[i]=i+1;
                    }
                    int total_wt = 0, total_tat = 0;
                    wt[0] = 0;
                    for (int i = 1; i < n; i++) {
                        wt[i] = bt[i-1] + wt[i-1];
                    }
                    for (int i = 0; i < n; i++)
                    {
                        tat[i] = bt[i] + wt[i];
                    }
                    person[] prion =new  person[p.length+1];
                    prion[0] = new person(0,0,0,0,0,0,0,0,0);
                    af.add(prion[0]);
                    for(int i = 0; i < n; i++)
                    {
                        total_wt = total_wt + wt[i];
                        total_tat = total_tat + tat[i];
                    }
                    for (int i = 1; i <=n+1; i++)
                    {

                        prion[i]=new person(p[i-1],bt[i-1],wt[i-1],tat[i-1],0,0,(float)total_wt/n,(float)total_tat/n,n);
                        af.add(prion[i]);
                    }
                    personListAdapter adapter=new personListAdapter(MainActivity.this,R.layout.adapter_view_layout,af);
                    mListView.setAdapter(adapter);


                }
                else if(ary==2)
                {
                    Toast.makeText(MainActivity.this, "Currently Unavailable! Under Development", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(ary==3)
                {
                    int pid[] = new int[10];
                    int at[] = new int[10]; // at means arrival time
                    int bt[] = new int[10]; // bt means burst time
                    int ct[] = new int[10]; // ct means complete time
                    int ta[] = new int[10]; // ta means turn around time
                    int wt[] = new int[10];  //wt means waiting time
                    int f[] = new int[10];  // f means it is flag it checks process is completed or not
                    int st=0, tot=0;
                    float avgwt=0, avgta=0;
                    boolean a = true;
                    for(int i=0;i<n;i++)
                    {  at[i]=arrival_time[i];
                        bt[i]=burst_time[i];
                        pid[i]=i+1;
                    }
                    while(true)
                    {
                        int c=n, min=999;
                        if (tot == n) // total no of process = completed process loop will be terminated
                            break;

                        for (int i=0; i<n; i++)
                        {
                            /*
                             * If i'th process arrival time <= system time and its flag=0 and burst<min
                             * That process will be executed first
                             */
                            if ((at[i] <= st) && (f[i] == 0) && (bt[i]<min))
                            {
                                min=bt[i];
                                c=i;
                            }
                        }

                        /* If c==n means c value can not updated because no process arrival time< system time so we increase the system time */
                        if (c==n)
                            st++;
                        else
                        {
                            ct[c]=st+bt[c];
                            st+=bt[c];
                            ta[c]=ct[c]-at[c];
                            wt[c]=ta[c]-bt[c];
                            f[c]=1;
                            tot++;
                        }
                    }

                    System.out.println("\npid  arrival brust  complete turn waiting");
                    person[] prionp =new  person[bt.length+1];
                    prionp[0] = new person(0,0,0,0,0,0,0,0,0);
                    af.add(prionp[0]);
                    for(int i=0;i<n;i++)
                    {
                        avgwt += wt[i];
                        avgta += ta[i];
                    }
                    for(int i=1;i<=n+1;i++)
                    {
                        prionp[i]=new person(pid[i-1],bt[i-1],wt[i-1],ta[i-1],at[i-1],0,(float)(avgwt/n),(float)(avgta/n),n);
                        af.add(prionp[i]);

                    }

                    personListAdapter adapter=new personListAdapter(MainActivity.this,R.layout.adapter_view_layout,af);
                    mListView.setAdapter(adapter);



                }
                else if(ary==4)
                {
                    Toast.makeText(MainActivity.this, "Currently Unavailable! Under Development", Toast.LENGTH_SHORT).show();
                }
                else if (ary == 5)                      //Priority(non-preemptive)
                {

                    int i;
                    int x, p[], pp[], bt[], w[], t[];
                    float awt, atat;
                    p = new int[10];
                    pp = new int[10];
                    bt = new int[10];
                    w = new int[10];
                    t = new int[10];

                    for (i = 0; i < n; i++)
                    {
                        bt[i] = burst_time[i];
                        pp[i] = priority[i];
                        t[i]=
                        p[i] = i + 1;
                    }

//sorting on the basis of priority
                    for (i = 0; i < n - 1; i++) {
                        for (int j = i + 1; j < n; j++) {
                            if (pp[i] > pp[j]) {
                                x = pp[i];
                                pp[i] = pp[j];
                                pp[j] = x;
                                x = bt[i];
                                bt[i] = bt[j];
                                bt[j] = x;
                                x = p[i];
                                p[i] = p[j];
                                p[j] = x;
                            }
                        }
                    }
                    w[0] = 0;
                    awt = 0;
                    t[0] = bt[0];
                    atat = t[0];
                    for (i = 1; i < n; i++) {
                        w[i] = t[i - 1];
                        awt += w[i];
                        t[i] = w[i] + bt[i];
                        atat += t[i];
                    }

                    person[] prionp =new  person[p.length+1];

                    prionp[0] = new person(0,0,0,0,0,0,0,0,0);
                    af.add(prionp[0]);
                    awt /= n;
                    atat /= n;
                    for (i = 1; i <=n+1; i++)
                    {

                        prionp[i]=new person(p[i-1],bt[i-1],w[i-1],t[i-1],0,pp[i-1],awt,atat,n);
                        af.add(prionp[i]);

                    }
                    personListAdapter adapter=new personListAdapter(MainActivity.this,R.layout.adapter_view_layout,af);
                    mListView.setAdapter(adapter);



                }
                else if(ary==6)
                {
                    Toast.makeText(MainActivity.this, "Currently Unavailable! Under Development", Toast.LENGTH_SHORT).show();
                }

                progress.dismiss();

            }
        });


    }
}