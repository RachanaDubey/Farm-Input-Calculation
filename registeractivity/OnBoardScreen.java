package com.rait.registeractivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class OnBoardScreen extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotsLayout;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;



    private Button nextButton;
    private Button prevButton;
    private int mCurrentPage;
    SessionManager sessionManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        SessionManager sessionManager;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board_screen);

        sessionManager = new SessionManager(this);
        //sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mName = user.get(sessionManager.NAME);
        String mEmail = user.get(sessionManager.EMAIL);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotsLayout = (LinearLayout)findViewById(R.id.dotsLayout);

        nextButton = (Button)findViewById(R.id.next);
        prevButton = (Button)findViewById(R.id.prev);

        sliderAdapter= new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage +1 );

            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSlideViewPager.setCurrentItem(mCurrentPage -1 );
            }
        });



    }

    public void addDotsIndicator(int position) {
        mDotsLayout.removeAllViews();
        mDots = new TextView[3];
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotsLayout.addView(mDots[i]);
        }

        if(mDots.length>0)
        {
            System.out.print(position);
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);
            mCurrentPage =i;

            if(i==0){
                nextButton.setEnabled(true);
                prevButton.setEnabled(false);
                prevButton.setVisibility(View.INVISIBLE);

                nextButton.setText("NEXT");
                prevButton.setText("");
            }
            else if(i== mDots.length -1){
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                prevButton.setVisibility(View.VISIBLE);

                nextButton.setText("FINISH");
                nextButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                            Intent intent = new Intent(OnBoardScreen.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                    }
                });
                prevButton.setText("BACK");
            }
            else{
                nextButton.setEnabled(true);
                prevButton.setEnabled(true);
                prevButton.setVisibility(View.VISIBLE);

                nextButton.setText("NEXT");
                prevButton.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {


        }
    };

}
