package com.intern.managementapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {


    Context context;
    List<DashboardModel>dashboardModel;

    public DashboardAdapter(List<DashboardModel>dashboardModel,Context context) {
        this.dashboardModel=dashboardModel;
        this.context=context;
    }


    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_layout, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {

        holder.name.setText(dashboardModel.get(position).getPname());
       holder.realseddate.setText("Released On "+dashboardModel.get(position).getReleasedon().toString());


        holder.pbudget.setText("₹"+dashboardModel.get(position).getPaid()+"/₹"+dashboardModel.get(position).getPbudget());



        if(Float.parseFloat(dashboardModel.get(position).getPaid())==0)
            holder.bar.setBackgroundResource(R.drawable.pinitial);
        else  if(Float.parseFloat(dashboardModel.get(position).getPaid())/Float.parseFloat(dashboardModel.get(position).getPbudget())<=0.5)
            holder.bar.setBackgroundResource(R.drawable.pstart);
        else  if(Float.parseFloat(dashboardModel.get(position).getPaid())/Float.parseFloat(dashboardModel.get(position).getPbudget())==0.5)
            holder.bar.setBackgroundResource(R.drawable.phalf);
       else if(Float.parseFloat(dashboardModel.get(position).getPaid())/Float.parseFloat(dashboardModel.get(position).getPbudget())<=0.75)
            holder.bar.setBackgroundResource(R.drawable.pthreefrth);
       else if(Float.parseFloat(dashboardModel.get(position).getPaid())==Float.parseFloat(dashboardModel.get(position).getPbudget()))
            holder.bar.setBackgroundResource(R.drawable.pfull);
    }

    @Override
    public int getItemCount() {
        return dashboardModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
         View mview;
         TextView name,realseddate,pbudget,paid;
         ImageView bar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
            name=mview.findViewById(R.id.pname);
            realseddate=mview.findViewById(R.id.releaseon);
            pbudget=mview.findViewById(R.id.pbudget);
            bar=mview.findViewById(R.id.progressBar);

        }
    }
}
