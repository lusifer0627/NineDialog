package com.example.dialog9;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    int choice;
    int progressValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.button);
        btn2=findViewById(R.id.button2);
        btn3=findViewById(R.id.button3);
        btn4=findViewById(R.id.button4);
        btn5=findViewById(R.id.button5);
        btn6=findViewById(R.id.button6);
        btn7=findViewById(R.id.button7);
        btn8=findViewById(R.id.button8);
        btn9=findViewById(R.id.button9);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setIcon(R.drawable.ic_baseline_mood); //設置圖示
                dialog.setTitle("標題");
                dialog.setMessage("內文1");
                dialog.setNegativeButton("關閉", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {// TODO: ...
                         }
                });
//設置右邊按鈕和點擊事件
                dialog.setPositiveButton("確定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {// TODO: ...
                         }
                });
//顯示Dialog
                dialog.show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("標題");
                dialog.setMessage("這是內文2");
                dialog.setIcon(R.drawable.ic_baseline_mood2);
                dialog.setNeutralButton("按鈕1", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: ...
                    }
                });

                dialog.setNegativeButton("按鈕2", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: ...
                    }
                });

                dialog.setPositiveButton("按鈕3", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //TODO: ...
                    }
                });
                dialog.show(); // 顯示dialog
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                String[] items = {"Item1", "Item2", "Item3", "Item4"};
                dialog.setTitle("標題_列表Dialog");
// 設置item點擊事件處理
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "你點擊了" + items[i], Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(); // 顯示dialog
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                choice = 0; //預設選擇Item2
                String[] items = {"Item1", "Item2", "Item3", "Item4"};
// 參數1：設置選項List;  參數2:設置預設選中項
                dialog.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        choice = i;
                    }
                });
                dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "你選了" + items[choice], Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(); // 顯示dialog
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(MainActivity.this);
                String[] items = {"Item1", "Item2", "Item3", "Item4"};
                // 設置默認選中項, false表示未選中, true反之
                boolean choiceSets[] = {true, true, true, true};

                dialog.setTitle("標題");
                dialog.setMultiChoiceItems(items, choiceSets,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                                choiceSets[i] = b;
                            }
                        }
                );
                dialog.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String result = "";
                        for (int k = 0; k < items.length; k++) {
                            if (choiceSets[k]) {
                                result += items[k] + ",";
                            }
                        }
                        Toast.makeText(MainActivity.this, "你選中了" + result, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(); // 顯示dialog
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProgressDialog dialog= new ProgressDialog(MainActivity.this);
                dialog.setTitle("進度");
                dialog.setMessage("等待中...");
                dialog.show();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ProgressDialog dialog= new ProgressDialog(MainActivity.this);
                dialog.setMessage("載入中...");
//關閉
                dialog.setCancelable(false);
//設置進度條樣式 - 水平
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMax(100); // 設定進度條最大值

// 模擬進度條跑動
                progressValue = 0;
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        progressValue += new Random().nextInt(10);
                        dialog.setProgress(progressValue);
                        if (progressValue > 100) {
                            timer.cancel();
                            dialog.dismiss(); //關閉對話框
                        }
                    }
                },0,200);
                dialog.show();
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    // 當日期選擇完畢並按下"OK"按鈕 的事件觸發處理
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        StringBuilder result = new StringBuilder();
                        result.append(y).append("-").append(m+1).append("-").append(d);
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Calendar calendar1 = Calendar.getInstance();
                TimePickerDialog dialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    // 當時間選擇完畢並按下"OK"按鈕 的事件觸發處理
                    @Override
                    public void onTimeSet(TimePicker timePicker, int h, int m) {
                        StringBuilder result = new StringBuilder();
                        result.append("H:").append(h).append("-M:").append(m);
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                }, calendar1.get(Calendar.HOUR_OF_DAY), calendar1.get(Calendar.MINUTE), true);
                dialog.show();
            }
        });
    }
}
