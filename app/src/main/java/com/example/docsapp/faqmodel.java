package com.example.docsapp;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class faqmodel  {
    String Ques , Ans;
    faqmodel()
    {

    }

    public faqmodel(String ques, String ans) {
        this.Ques = ques;
        this.Ans = ans;
    }

    public String getQues() {
        return Ques;
    }

    public void setQues(String ques) {
        Ques = ques;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }
}

