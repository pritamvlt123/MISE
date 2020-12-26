package com.example.mise.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mise.Model.QuestionDetail;
import com.example.mise.R;
import com.example.mise.Utils.PrefManager;

import java.util.HashMap;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.MyViewHolder> {

    private List<QuestionDetail> questionDetailList;
    int count = 0;
    private Context context;
    private RadioGroup lastCheckedRadioGroup = null;
    int selectedId = 0;
    OnclickItem onclickItem;
    String a = "";
    String b = "";
    String c = "";
    String d = "";
    String answer = "";
    int pos = 0;
    HashMap<String, String> answers = new HashMap<>();
    PrefManager pref;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnclickItem onclickItem;
        public TextView question, question_desc;
        public RadioGroup rg_answers;
        public RadioButton rb_1, rb_2, rb_3, rb_4;
        public ImageView im_ques;

        public MyViewHolder(View view, OnclickItem onclickItem) {
            super(view);
            question = (TextView) view.findViewById(R.id.tv_question_no);
            question_desc = (TextView) view.findViewById(R.id.tv_question_desc);
            rg_answers = (RadioGroup) view.findViewById(R.id.rg_answers);
            im_ques = (ImageView) view.findViewById(R.id.im_ques);
            rb_1 = (RadioButton) view.findViewById(R.id.rb_1);
            rb_2 = (RadioButton) view.findViewById(R.id.rb_2);
            rb_3 = (RadioButton) view.findViewById(R.id.rb_3);
            rb_4 = (RadioButton) view.findViewById(R.id.rb_4);
            this.onclickItem = onclickItem;
            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }


    public QuestionsAdapter(List<QuestionDetail> questionDetailList, Context context, OnclickItem onclickItem) {
        this.questionDetailList = questionDetailList;
        this.context = context;
        this.onclickItem = onclickItem;
        pref = new PrefManager(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_questions, parent, false);

        return new MyViewHolder(itemView, onclickItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.question.setText(questionDetailList.get(position).getPMQuestionNo() + ".");
        holder.question_desc.setText(questionDetailList.get(position).getPMQuestionDesc());
        if (!(questionDetailList.get(position).getPMImage() == null || questionDetailList.get(position).getPMImage().isEmpty())) {
            holder.im_ques.setVisibility(View.VISIBLE);
            Glide.with(context).load("https://www.misexam.com/upload/" + questionDetailList.get(position).getPMImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.im_ques);       // int id = (position+1);

        } else {
            holder.im_ques.setVisibility(View.GONE);
        }
//        for(QuestionDetail answers : questionDetail.getPMFirstOption()){
//            RadioButton rb = new RadioButton(QuestionsAdapter.this.context);
//            rb.setId(id++);
//            rb.setText(answers);
//            holder.rg_answers.addView(rb);
//        }

        holder.rg_answers.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
//                        Toast.makeText(QuestionsAdapter.this.context,
//                                "Radio button clicked " + radioGroup.getCheckedRadioButtonId(),
//                                Toast.LENGTH_SHORT).show();
                radioGroup.getChildCount();
                lastCheckedRadioGroup = radioGroup;
                RadioButton radioButton = (RadioButton)radioGroup.findViewById(checkedId);
                String text = radioButton.getText().toString();
                if (lastCheckedRadioGroup == radioGroup) {
                    answer = questionDetailList.get(position).getPMAnsware();
                    //count++;
                    //Toast.makeText(context, "Checked" + count, Toast.LENGTH_SHORT).show();
                    if (onclickItem != null) {
                        if (holder.rb_1.isChecked()) {
                            selectedId = radioGroup.getCheckedRadioButtonId();
                                    if(questionDetailList.get(position).getPMAnsware().equals("a"))
                                    {
                                        a=text;
                                    }
                            if (text.equals(a)) {
                              //  a = String.valueOf(holder.rb_1.getId());
                                answers.put(questionDetailList.get(position).getPMQuestionDesc(), a);
                                pref.setAnswers(answers, "answers");
                                //count++;
                            }

                        } else if (holder.rb_2.isChecked()) {
                            selectedId = radioGroup.getCheckedRadioButtonId();
                            if(questionDetailList.get(position).getPMAnsware().equals("b"))
                            {
                                b=text;
                            }
                            if (text.equals(b)) {
                                //  a = String.valueOf(holder.rb_1.getId());
                                answers.put(questionDetailList.get(position).getPMQuestionDesc(), b);
                                pref.setAnswers(answers, "answers");
                                //count++;
                            }
                        } else if (holder.rb_3.isChecked()) {
                            selectedId = radioGroup.getCheckedRadioButtonId();
                            if(questionDetailList.get(position).getPMAnsware().equals("c"))
                            {
                                c=text;
                            }
                            if (text.equals(c)) {
                                //  a = String.valueOf(holder.rb_1.getId());
                                answers.put(questionDetailList.get(position).getPMQuestionDesc(), c);
                                pref.setAnswers(answers, "answers");
                                //count++;
                            }
                        } else if (holder.rb_4.isChecked()) {
//                                d.equals(answer);
                            if(questionDetailList.get(position).getPMAnsware().equals("d"))
                            {
                                d=text;
                            }
                            if (text.equals(d)) {
                                //  a = String.valueOf(holder.rb_1.getId());
                                answers.put(questionDetailList.get(position).getPMQuestionDesc(), d);
                                pref.setAnswers(answers, "answers");
                                //count++;
                            }
//                                count++;
                        }

                    }

                }


            }

        });


        holder.rb_1.setText(questionDetailList.get(position).getPMFirstOption());
        holder.rb_2.setText(questionDetailList.get(position).getPMSecondOption());
        holder.rb_3.setText(questionDetailList.get(position).getPMThirdOption());
        holder.rb_4.setText(questionDetailList.get(position).getPMFourthOption());
    }

    @Override
    public int getItemCount() {
        return questionDetailList.size();
    }

    public interface OnclickItem {
        void onItemClick(RadioGroup radioGroup, int count);
    }

}