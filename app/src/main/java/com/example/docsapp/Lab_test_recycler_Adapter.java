package com.example.docsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class Lab_test_recycler_Adapter extends FirebaseRecyclerAdapter<LabTestModel,Lab_test_recycler_Adapter.LabViewHolder> {

    private Lab_test_recycler_Adapter.OnItemClickListener listener;
    public Lab_test_recycler_Adapter(@NonNull FirebaseRecyclerOptions<LabTestModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public LabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.labtest_recylcer_design, parent, false);
        return new LabViewHolder(View);
    }



    @Override
    protected void onBindViewHolder(@NonNull LabViewHolder holder, int position, @NonNull LabTestModel model) {
        holder.TextViewLabTestName.setText(model.getName());
    }


    public class LabViewHolder extends RecyclerView.ViewHolder {
        public TextView TextViewLabTestName;

        public LabViewHolder(@NonNull View itemView) {
            super(itemView);
            TextViewLabTestName = itemView.findViewById(R.id.lab_test_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION && listener!=null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);

                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DataSnapshot dataSnapshot, int position);
    }
    public void setOnItemCLickListener(Lab_test_recycler_Adapter.OnItemClickListener listener){
        this.listener=listener;

    }
}