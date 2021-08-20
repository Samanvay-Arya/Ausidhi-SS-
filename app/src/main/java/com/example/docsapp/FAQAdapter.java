package com.example.docsapp;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docsapp.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FAQAdapter extends FirebaseRecyclerAdapter<faqmodel,FAQAdapter.myviewholder> {
    public FAQAdapter(@NonNull FirebaseRecyclerOptions<faqmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull faqmodel model) {
        holder.ques.setText(model.getQues());
        holder.ans.setText(model.getAns());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.faq_card_design,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        TextView ques , ans;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            ques=(TextView)itemView.findViewById(R.id.faqquestion);
            ans=(TextView)itemView.findViewById(R.id.faqanswer);
        }
    }

}
