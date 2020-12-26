package com.example.mise.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mise.Adapter.QuestionsAdapter;
import com.example.mise.Model.PlanPackageFetch;
import com.example.mise.Model.PlanPackageStatus;
import com.example.mise.Model.QuestionDetail;
import com.example.mise.Model.QuestionFetch;
import com.example.mise.Model.QuestionStatus;
import com.example.mise.Network.ApiInterface;
import com.example.mise.Network.Api_Client;
import com.example.mise.R;
import com.example.mise.Utils.PrefManager;
import com.example.miseexam.utility.Subjects;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameActivity extends AppCompatActivity implements QuestionsAdapter.OnclickItem {

    private List<QuestionDetail> questionsModelList;
    private RecyclerView recyclerView;
    private QuestionsAdapter mAdapter;
    Spinner sp_subjects, sp_level;
    Button btn_submit, btn_cancel;
    int Answercount;
    int score;
    float score_to_float;
    List<Subjects> subjects = new ArrayList<>();
    CollapsingToolbarLayout collapsingToolbarLayout;
    ApiInterface apiInterface;
    ArrayList<PlanPackageStatus> planPackageStatusArrayList = new ArrayList<>();
    ArrayList<String> stringArrayLists = new ArrayList<>();
    private static final String TAG = "this";
    String answer = "";
    int pos = 0;
    String spinner_id = "";
    int level = 0;
    HashMap<String, String> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        questionsModelList = new ArrayList<>();
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle("Practice Zone");
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_questions);
        btn_submit = findViewById(R.id.btn_submit);
        btn_cancel = findViewById(R.id.btn_cancel);
        sp_subjects = findViewById(R.id.sp_subjects);
        sp_level = findViewById(R.id.sp_level);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        planPackageFetch();

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubmissionDialog();
            }
        });

       sp_level.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               if(position == 1)
               {
                   divertToPlanPageIntermediate();

               }
               else if(position == 2)
               {
                   divertToPlanPackageAdvanced();

               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

    }

    private void divertToPlanPackageAdvanced() {
        QuestionFetch questionFetch = new QuestionFetch();
        questionFetch.setSRID("335");
        questionFetch.setSRClass("2");
        questionFetch.setSRMedium("English");
        questionFetch.setSRPackage("0");
        questionFetch.setSubjectId(spinner_id.toString());
        questionFetch.setLevel("3");
        Log.d("Login", "onClick: " + new Gson().toJson(questionFetch));
        apiInterface = Api_Client.getClient().create(ApiInterface.class);
        Call<QuestionStatus> call = apiInterface.getQuestions(questionFetch);
        call.enqueue(new Callback<QuestionStatus>() {

            @Override
            public void onResponse(Call<QuestionStatus> call, Response<QuestionStatus> response) {
                if (response.isSuccessful()) {
                    String msg = response.body().getRespMessage();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
                    builder1.setTitle("Info");
                    builder1.setMessage(msg);
                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent  = new Intent(GameActivity.this, PackageActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            }

            @Override
            public void onFailure(Call<QuestionStatus> call, Throwable t) {
                Toast.makeText(GameActivity.this, "Error in Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void divertToPlanPageIntermediate() {
        QuestionFetch questionFetch = new QuestionFetch();
        questionFetch.setSRID("335");
        questionFetch.setSRClass("2");
        questionFetch.setSRMedium("English");
        questionFetch.setSRPackage("0");
        questionFetch.setSubjectId(spinner_id.toString());
        questionFetch.setLevel("2");
        Log.d("Login", "onClick: " + new Gson().toJson(questionFetch));
        apiInterface = Api_Client.getClient().create(ApiInterface.class);
        Call<QuestionStatus> call = apiInterface.getQuestions(questionFetch);
        call.enqueue(new Callback<QuestionStatus>() {

            @Override
            public void onResponse(Call<QuestionStatus> call, Response<QuestionStatus> response) {
                if (response.isSuccessful()) {
                    String msg = response.body().getRespMessage();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
                    builder1.setTitle("Info");
                    builder1.setMessage(msg);
                    builder1.setPositiveButton(
                            "Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent  = new Intent(GameActivity.this, PackageActivity.class);
                                    startActivity(intent);
                                }
                            });
                    builder1.setNegativeButton(
                            "Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            }

            @Override
            public void onFailure(Call<QuestionStatus> call, Throwable t) {
                Toast.makeText(GameActivity.this, "Error in Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void planPackageFetch() {
        PlanPackageFetch planPackageFetch = new PlanPackageFetch();
        planPackageFetch.setSRClass("2");
        planPackageFetch.setSRSource("NA");
        planPackageFetch.setSRMedium("English");
        Log.d("Login", "onClick: " + new Gson().toJson(planPackageFetch));
        apiInterface = Api_Client.getClient().create(ApiInterface.class);
        Call<PlanPackageStatus> call = apiInterface.getsubjectsperplan(planPackageFetch);
        call.enqueue(new Callback<PlanPackageStatus>() {
            @Override
            public void onResponse(Call<PlanPackageStatus> call, Response<PlanPackageStatus> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getSubjects().size(); i++) {
                        stringArrayLists.add(response.body().getSubjects().get(i).getSMSubjectName());
                    }

                    ArrayAdapter<String> adapter =
                            new ArrayAdapter<String>(getApplicationContext(), R.layout.drop_down_item, stringArrayLists);
                    adapter.setDropDownViewResource(R.layout.drop_down_item);
                    sp_subjects.setAdapter(adapter);
                    sp_subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                            {
                                int id = sp_subjects.getSelectedItemPosition();
                                spinner_id = response.body().getSubjects().get(id).getSMID();
                            }

                            loadQuestions();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            //
                        }
                    });


                } else {
                    //
                }
            }

            @Override
            public void onFailure(Call<PlanPackageStatus> call, Throwable t) {
                Toast.makeText(GameActivity.this, "Error in Connection", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void loadQuestions() {
        QuestionFetch questionFetch = new QuestionFetch();
        questionFetch.setSRID("335");
        questionFetch.setSRClass("2");
        questionFetch.setSRMedium("English");
        questionFetch.setSRPackage("0");
        questionFetch.setSubjectId(spinner_id.toString());
        questionFetch.setLevel("1");
        Log.d("Login", "onClick: " + new Gson().toJson(questionFetch));
        apiInterface = Api_Client.getClient().create(ApiInterface.class);
        Call<QuestionStatus> call = apiInterface.getQuestions(questionFetch);
        call.enqueue(new Callback<QuestionStatus>() {

            @Override
            public void onResponse(Call<QuestionStatus> call, Response<QuestionStatus> response) {
                if (response.isSuccessful()) {
                    if (response.body().getQuestionDetails() != null) {
                        questionsModelList = response.body().getQuestionDetails();
                        mAdapter = new QuestionsAdapter(questionsModelList, GameActivity.this, GameActivity.this);
                        recyclerView.setAdapter(mAdapter);
                    }

                }
            }

            @Override
            public void onFailure(Call<QuestionStatus> call, Throwable t) {
                Toast.makeText(GameActivity.this, "Error in Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void showSubmissionDialog() {
        PrefManager pref = new PrefManager(this);
        answers = pref.getAnswers("answers");
        double amount = Double.parseDouble(String.valueOf(answers.size()));
        double res = (amount / 100.0f) * 20;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(GameActivity.this);
        builder1.setTitle("Note");
        builder1.setMessage("Total answered questions:" + "" + answers.size() + "/" + "20" + "\n" + "Score: " + "" + String.valueOf(res) + "%");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    @Override
    public void onItemClick(RadioGroup radioGroup, int count) {
        Answercount = count;
    }
}