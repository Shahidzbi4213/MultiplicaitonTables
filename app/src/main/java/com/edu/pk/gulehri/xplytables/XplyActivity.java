package com.edu.pk.gulehri.xplytables;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.edu.pk.gulehri.xplytables.databinding.ActivityXplyBinding;

import java.util.Objects;

public class XplyActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener, View.OnClickListener, View.OnTouchListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private ActivityXplyBinding binding;
    private GestureDetector mDetector;
    private int position;
    private String data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityXplyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        position = intent.getIntExtra(NumAdapter.CLICKED_POSITION, 0) + 1;


        setToolBar();
        setListeners();
        buttonVisible();
        assignData();


    }

    private void setToolBar() {

        try {
            setSupportActionBar(binding.toolbarInclude.mToolbar);
            binding.toolbarInclude.toolbarText.setText("Tables of " + position);

            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            final Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_back, null);
            getSupportActionBar().setHomeAsUpIndicator(drawable);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);


            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    private void buttonVisible() {
        binding.btnPre.setClickable(position != 1);
        binding.btnNext.setClickable(position != 100);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {
        mDetector = new GestureDetector(this, this);
        binding.btnPre.setOnClickListener(this);
        binding.btnNext.setOnClickListener(this);
        binding.tvTable.setOnTouchListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void assignData() {
        for (int i = 1; i <= 10; i++) {
            int value = position * i;
            data = data.concat(position + "  " + "X" + "  " + i + "  =" + "  " + value + "\n");
        }

        setAnimations();
        binding.toolbarInclude.toolbarText.setText("Tables of " + position);
        binding.tvTable.setText(data);
    }

    private void setAnimations() {
        YoYo.with(Techniques.ZoomIn).duration(2500).playOn(binding.tvTable);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        try {
            //Checking for the distance user finger covered
            float diffX = e2.getX() - e1.getX();

            //Check if covered distance > 100 px/unit
            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {

                data = "";
                //if diffX == positive then perform swipe right
                if (diffX > 0) {

                    if (position == 0) {
                        position = 1;
                    }
                    if (position > 1) {
                        position -= 1;
                    }
                }
                //if diffX is negative then perform swipe left
                else {

                    if (position == 101) {
                        position = 100;
                    }
                    if (position < 100) {
                        position += 1;

                    }
                }
                buttonVisible();
                assignData();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        data = "";
        if (id == R.id.btnPre) {
            if (position > 1) {
                position -= 1;
            }
            buttonVisible();
            assignData();
        }
        if (id == R.id.btnNext) {
            if (position < 100) {
                position += 1;
            }
            buttonVisible();
            assignData();
        }


    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}