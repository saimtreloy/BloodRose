package com.saim.bloodrose.Fragment;


import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.saim.bloodrose.R;

public class FragmentProfile extends Fragment {

    View view;

    LinearLayout layoutPro1, layoutPro2,layoutPro3, layoutPro4;

    public FragmentProfile() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        init();

        return view;
    }


    public void init(){
        layoutPro1 = (LinearLayout) view.findViewById(R.id.layoutPro1);
        layoutPro2 = (LinearLayout) view.findViewById(R.id.layoutPro2);
        layoutPro3 = (LinearLayout) view.findViewById(R.id.layoutPro3);
        layoutPro4 = (LinearLayout) view.findViewById(R.id.layoutPro4);

        LayoutAnimation();
    }

    public void LayoutAnimation(){
        layoutPro3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.SlideOutRight).duration(750).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        layoutPro4.setVisibility(View.VISIBLE);
                        layoutPro2.setVisibility(View.GONE);
                        YoYo.with(Techniques.SlideInRight).duration(750).playOn(view.findViewById(R.id.layoutPro4));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(view.findViewById(R.id.layoutPro2));
            }
        });

        layoutPro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.SlideOutRight).duration(750).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        layoutPro4.setVisibility(View.GONE);
                        layoutPro2.setVisibility(View.VISIBLE);
                        YoYo.with(Techniques.SlideInLeft).duration(750).playOn(view.findViewById(R.id.layoutPro2));
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(view.findViewById(R.id.layoutPro4));
            }
        });
    }
}
