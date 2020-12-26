package com.example.mise.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mise.Activities.AboutUsActivity;
import com.example.mise.Activities.AwardsActivity;
import com.example.mise.Activities.ContactUsActivity;
import com.example.mise.Activities.ExamDetailsActivity;
import com.example.mise.Activities.FullRegistrationActivity;
import com.example.mise.Activities.GameActivity;
import com.example.mise.Activities.ProfileActivity;
import com.example.mise.Activities.RegisterActivity;
import com.example.mise.Activities.ResultsActivity;
import com.example.mise.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CardView cv_practice,cv_profile, cv_exm_reg,cv_abt_us,cv_full_reg,cv_rewards,cv_results,cv_exm_det;
    BottomNavigationView bottomNavigationView;
    ShimmerFrameLayout shimmerFrameLayout;
    FloatingActionButton cv_contact_us;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_homescreen, container, false);
        cv_practice = root.findViewById(R.id.cv_practice);
        cv_profile = root.findViewById(R.id.cv_profile);
        cv_exm_reg = root.findViewById(R.id.cv_exm_reg);
        cv_contact_us = root.findViewById(R.id.cv_contact_us);
        cv_abt_us = root.findViewById(R.id.cv_abt_us);
        cv_full_reg = root.findViewById(R.id.cv_full_reg);
        cv_rewards = root.findViewById(R.id.cv_rewards);
        cv_results = root.findViewById(R.id.cv_results);
        cv_exm_det = root.findViewById(R.id.cv_exm_det);
        bottomNavigationView = root.findViewById(R.id.bottomNavigationView);

        cv_practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                startActivity(intent);
            }
        });
        cv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        cv_exm_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        cv_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(intent);
            }
        });
        cv_abt_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(intent);
            }
        });
        cv_full_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FullRegistrationActivity.class);
                startActivity(intent);
            }
        });
        cv_rewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AwardsActivity.class);
                startActivity(intent);
            }
        });
        cv_results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ResultsActivity.class);
                startActivity(intent);
            }
        });
        cv_exm_det.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ExamDetailsActivity.class);
                startActivity(intent);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.Home) {
                    //do nothing
                    item.setIcon(R.drawable.ic_campus);
                }

                else if(item.getItemId() == R.id.Register) {
                     Intent intent = new Intent(getActivity(), FullRegistrationActivity.class);
                     startActivity(intent);

                }
                else if (item.getItemId() == R.id.Exams) {
                    Intent intent = new Intent(getActivity(), ExamDetailsActivity.class);
                    startActivity(intent);
                }
                else if (item.getItemId() == R.id.Profile) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });

        return root;
    }

}