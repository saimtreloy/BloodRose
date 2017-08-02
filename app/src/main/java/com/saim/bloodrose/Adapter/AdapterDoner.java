package com.saim.bloodrose.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saim.bloodrose.Model.ModelDoner;
import com.saim.bloodrose.R;

import java.util.ArrayList;

/**
 * Created by Android on 7/31/2017.
 */

public class AdapterDoner extends RecyclerView.Adapter<AdapterDoner.DonerViewHolder> {

    ArrayList<ModelDoner> adapterList = new ArrayList<>();
    Context mContext;

    public AdapterDoner(ArrayList<ModelDoner> adapterList) {
        this.adapterList = adapterList;
    }

    public AdapterDoner(ArrayList<ModelDoner> adapterList, Context mContext) {
        this.adapterList = adapterList;
        this.mContext = mContext;
    }

    @Override
    public DonerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_donor, parent, false);
        DonerViewHolder donerViewHolder = new DonerViewHolder(view, adapterList);
        return donerViewHolder;
    }

    @Override
    public void onBindViewHolder(DonerViewHolder holder, int position) {
        holder.txtListFullName.setText(adapterList.get(position).getFullName());
        holder.txtListLocation.setText(adapterList.get(position).getLocationName());
        holder.txtListMobile.setText(adapterList.get(position).getPhoneNumber());
        holder.txtListDonateDate.setText(adapterList.get(position).getDonatedDate());
        holder.txtListBloodGroup.setText(adapterList.get(position).getBloodGroup());
    }

    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class DonerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtListFullName, txtListLocation, txtListMobile, txtListDonateDate, txtListBloodGroup;
        ArrayList<ModelDoner> SuperArrayList = new ArrayList<ModelDoner>();

        public DonerViewHolder(View itemView, ArrayList<ModelDoner> pList) {
            super(itemView);
            txtListFullName = (TextView) itemView.findViewById(R.id.txtListFullName);
            txtListLocation = (TextView) itemView.findViewById(R.id.txtListLocation);
            txtListMobile = (TextView) itemView.findViewById(R.id.txtListMobile);
            txtListDonateDate = (TextView) itemView.findViewById(R.id.txtListDonateDate);
            txtListBloodGroup = (TextView) itemView.findViewById(R.id.txtListBloodGroup);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
