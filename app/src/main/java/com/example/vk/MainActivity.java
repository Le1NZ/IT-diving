package com.example.vk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.view.View;

import com.example.vk.databinding.ActivityMainBinding;

import jp.wasabeef.blurry.Blurry;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private boolean isCameraOn = true;
    private boolean isMicOn = true;

    private boolean isYouTop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Blurry.with(this).radius(30).sampling(3).from(BitmapFactory.decodeResource(getResources(),R.drawable.avatar_2)).into(binding.imageBottom);
        Blurry.with(this).radius(30).sampling(3).from(BitmapFactory.decodeResource(getResources(),R.drawable.avatar_1)).into(binding.imageTop);


        binding.endCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAndRemoveTask();
            }
        });

        binding.camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCameraOn) {
                    binding.camera.setImageResource(R.drawable.videocam_off);
                    binding.camera.setBackgroundResource(R.drawable.circle_btn_off);
                    isCameraOn = false;
                }
                else {
                    binding.camera.setImageResource(R.drawable.videocam);
                    binding.camera.setBackgroundResource(R.drawable.circle_btn_on);
                    isCameraOn = true;
                }
            }
        });

        binding.mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMicOn) {
                    binding.mic.setImageResource(R.drawable.mic_off);
                    binding.mic.setBackgroundResource(R.drawable.circle_btn_off);
                    binding.userMicTop.setImageResource(R.drawable.mic_off_color);
                    isMicOn = false;
                }
                else {
                    binding.mic.setImageResource(R.drawable.mic);
                    binding.mic.setBackgroundResource(R.drawable.circle_btn_on);
                    binding.userMicTop.setImageResource(R.drawable.mic );
                    isMicOn = true;
                }
            }
        });

        binding.hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlert();
            }
        });

        binding.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
                startActivity(intent);
            }
        });

        binding.group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerView.class);
                startActivity(intent);
            }
        });

        binding.gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isYouTop) {
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(binding.linearLayout);
                    constraintSet.connect(R.id.userBottom,ConstraintSet.TOP,constraintSet.PARENT_ID,ConstraintSet.TOP,0);
                    constraintSet.connect(R.id.userBottom,ConstraintSet.BOTTOM,R.id.userTop,ConstraintSet.TOP,4);
                    constraintSet.connect(R.id.userTop,ConstraintSet.TOP,R.id.userBottom,ConstraintSet.BOTTOM,4);
                    constraintSet.connect(R.id.userTop,ConstraintSet.BOTTOM,constraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
                    constraintSet.applyTo(binding.linearLayout);
                    isYouTop = false;
                } else {
                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(binding.linearLayout);
                    constraintSet.connect(R.id.userBottom,ConstraintSet.TOP,R.id.userTop,ConstraintSet.BOTTOM,4);
                    constraintSet.connect(R.id.userBottom,ConstraintSet.BOTTOM,constraintSet.PARENT_ID,ConstraintSet.BOTTOM,0);
                    constraintSet.connect(R.id.userTop,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP,0);
                    constraintSet.connect(R.id.userTop,ConstraintSet.BOTTOM,R.id.userBottom,ConstraintSet.TOP,4);
                    constraintSet.applyTo(binding.linearLayout);
                    isYouTop = true;
                }
            }
        });
    }


    public void showAlert() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Привет!").
                setCancelable(true).
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

}