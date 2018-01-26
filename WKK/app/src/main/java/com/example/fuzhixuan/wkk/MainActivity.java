package com.example.fuzhixuan.wkk;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private TextView grade, record, random;

    private SeekBar seekBar;

    private int BAR_MAX_LENGTH = 100;

    private int RANDOM = 0;

    private Button ok, exit, update;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);

        grade = (TextView) findViewById(R.id.grade);

        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);

        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(this);

        record = (TextView) findViewById(R.id.record);
        SharedPreferences read = getSharedPreferences("record", MODE_PRIVATE);
        record.setText("" + read.getFloat("record_100", 0f));

        random = (TextView) findViewById(R.id.random);

        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        radioGroup.check(R.id.radio01);

        initRandom();
    }

    private void initRandom() {
        RANDOM = (int) (Math.random() * BAR_MAX_LENGTH);
        random.setText("" + RANDOM);
    }


    @Override
    public void onClick(View v) {
        SharedPreferences read = getSharedPreferences("record", MODE_PRIVATE);

        switch (v.getId()) {
            case R.id.ok:
                float res = (1 - Math.abs(seekBar.getProgress() - RANDOM) * 1.0f / BAR_MAX_LENGTH);
                res = (float) ((int) (res * BAR_MAX_LENGTH) * 1.0 / BAR_MAX_LENGTH);
                grade.setText("" + res);
                ok.setEnabled(false);

                switch (BAR_MAX_LENGTH) {
                    case 100:
                        float tmp1 = read.getFloat("record_100", 0f);
                        if (res > tmp1) {
                            SharedPreferences.Editor editor = read.edit();
                            editor.putFloat("record_100", res);
                            editor.commit();
                            record.setText("" + res);
                        }
                        break;

                    case 200:
                        float tmp2 = read.getFloat("record_200", 0f);
                        if (res > tmp2) {
                            SharedPreferences.Editor editor = read.edit();
                            editor.putFloat("record_200", res);
                            editor.commit();
                            record.setText("" + res);
                        }
                        break;

                    case 500:
                        float tmp3 = read.getFloat("record_500", 0f);
                        if (res > tmp3) {
                            SharedPreferences.Editor editor = read.edit();
                            editor.putFloat("record_500", res);
                            editor.commit();
                            record.setText("" + res);
                        }
                        break;

                    case 1000:
                        float tmp4 = read.getFloat("record_1000", 0f);
                        if (res > tmp4) {
                            SharedPreferences.Editor editor = read.edit();
                            editor.putFloat("record_1000", res);
                            editor.commit();
                            record.setText("" + res);
                        }
                        break;

                    case 10000:
                        float tmp5 = read.getFloat("record_10000", 0f);
                        if (res > tmp5) {
                            SharedPreferences.Editor editor = read.edit();
                            editor.putFloat("record_10000", res);
                            editor.commit();
                            record.setText("" + res);
                        }
                        break;
                }

                break;

            case R.id.exit:
                finish();
                break;

            case R.id.update:
                initRandom();
                ok.setEnabled(true);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio01:
                // 100
                BAR_MAX_LENGTH = 100;
                seekBar.setMax(100);
                record.setText("" + getSharedPreferences("record", MODE_PRIVATE).getFloat("record_100", 0));
                break;

            case R.id.radio02:
                // 200
                BAR_MAX_LENGTH = 200;
                seekBar.setMax(200);
                record.setText("" + getSharedPreferences("record", MODE_PRIVATE).getFloat("record_200", 0));
                break;

            case R.id.radio03:
                // 500
                BAR_MAX_LENGTH = 500;
                seekBar.setMax(500);
                record.setText("" + getSharedPreferences("record", MODE_PRIVATE).getFloat("record_500", 0));
                break;

            case R.id.radio04:
                // 1000
                BAR_MAX_LENGTH = 1000;
                seekBar.setMax(1000);
                record.setText("" + getSharedPreferences("record", MODE_PRIVATE).getFloat("record_1000", 0));
                break;

            case R.id.radio05:
                // 1000
                BAR_MAX_LENGTH = 10000;
                seekBar.setMax(10000);
                record.setText("" + getSharedPreferences("record", MODE_PRIVATE).getFloat("record_10000", 0));
                break;
        }

        initRandom();
        seekBar.setProgress(0);
        grade.setText("0");
        ok.setEnabled(true);
    }
}
