package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.aaa:
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
                break;
            case R.id.bbb:
                Intent intent2 = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent2);
                break;
            case R.id.ccc:
                Intent intent3 = new Intent(MainActivity.this,MainActivity4.class);
                startActivity(intent3);
                break;
            default:
        }
        return true;
    }


    //创建Button对象   也就是activity_main.xml里所设置的ID
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_pt;
    Button btn_mul, btn_div, btn_add, btn_sub;
    Button btn_clr, btn_del, btn_eq;
    EditText et_input;

    boolean clr_flag;    //判断et编辑文本框中是否清空
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化对象
        btn_0 = (Button) findViewById(R.id.ling);
        btn_1        = (Button) findViewById(R.id.yi);
        btn_2 = (Button) findViewById(R.id.er);
        btn_3 = (Button) findViewById(R.id.san);
        btn_4 = (Button) findViewById(R.id.si);
        btn_5 = (Button) findViewById(R.id.wu);
        btn_6 = (Button) findViewById(R.id.liu);
        btn_7 = (Button) findViewById(R.id.qi);
        btn_8 = (Button) findViewById(R.id.ba);
        btn_9 = (Button) findViewById(R.id.jiu);
        btn_pt = (Button) findViewById(R.id.dian);
        btn_add = (Button) findViewById(R.id.jia);
        btn_sub = (Button) findViewById(R.id.jian);
        btn_mul = (Button) findViewById(R.id.cheng);
        btn_div = (Button) findViewById(R.id.chu);
        btn_clr = (Button) findViewById(R.id.qingchu);
        btn_del = (Button) findViewById(R.id.houtui);
        btn_eq = (Button) findViewById(R.id.deng);
        et_input = (EditText) findViewById(R.id.result);
        et_input.setSaveEnabled(false);
        //给按钮设置的点击事件
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_pt.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        et_input.setSaveEnabled(false);
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.ling:
            case R.id.yi:
            case R.id.er:
            case R.id.san:
            case R.id.si:
            case R.id.wu:
            case R.id.liu:
            case R.id.qi:
            case R.id.ba:
            case R.id.jiu:
            case R.id.dian:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break;
            case R.id.jia:
            case R.id.jian:
            case R.id.cheng:
            case R.id.chu:
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                }
                if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/")) {
                    str = str.substring(0, str.indexOf(" "));
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.qingchu:
                if (clr_flag)
                    clr_flag = false;
                str = "";
                et_input.setText("");
                break;
            case R.id.houtui: //判断是否为空，然后在进行删除
                if (clr_flag) {
                    clr_flag = false;
                    str = "";
                    et_input.setText("");
                } else if (str != null && !str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.deng: //单独运算最后结果
                getResult();//调用下面的方法
                break;
        }
    }

    private void getResult() {
        et_input.setSaveEnabled(false);
        String exp = et_input.getText().toString();
        if (exp == null || exp.equals("")) return;
        //因为没有运算符所以不用运算
        if (!exp.contains(" ")) {
            return;
        }
        if (clr_flag) {
            clr_flag = false;
            return;
        }
        /* clr_flag = true;*/
        //截取运算符前面的字符串
            String s1 = exp.substring(0, exp.indexOf(" "));
            //截取的运算符
            String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
            //截取运算符后面的字符串
            String s2 = exp.substring(exp.indexOf(" ") + 3);
            double cnt = 0;
            if (!s1.equals("") && !s2.equals("")) {
                double d1 = Double.parseDouble(s1);
                double d2 = Double.parseDouble(s2);
                if (op.equals("+")) {
                    cnt = d1 + d2;
                }
                if (op.equals("-")) {
                    cnt = d1 - d2;
                }
                if (op.equals("*")) {
                    cnt = d1 * d2;
                }
                if (op.equals("/")) {
                    if (d2 == 0) {
                        et_input.setText("错误");
                    } else cnt = d1 / d2;
                }
                if (!s1.contains(".") && !s2.contains(".") && !op.equals("/")) {
                    int res = (int) cnt;
                    et_input.setText(res + "");

                } else if (op.equals("/") && d2 == 0) et_input.setText("错误");
                else {
                    et_input.setText(cnt + "");
                }
            }

            //如果s1是空    s2不是空  就执行下一步
            else if (!s1.equals("") && s2.equals("")) {
                double d1 = Double.parseDouble(s1);
                if (op.equals("+")) {
                    cnt = d1;
                }
                if (op.equals("-")) {
                    cnt = d1;
                }
                if (op.equals("*")) {
                    cnt = 0;
                }
                if (op.equals("/")) {
                    cnt = 0;
                }
                if (!s1.contains(".")) {
                    int res = (int) cnt;
                    et_input.setText(res + "");
                } else {
                    et_input.setText(cnt + "");
                }
            }
            //如果s1是空    s2不是空  就执行下一步
            else if (s1.equals("") && !s2.equals("")) {
                double d2 = Double.parseDouble(s2);
                if (op.equals("+")) {
                    cnt = d2;
                }
                if (op.equals("-")) {
                    cnt = 0 - d2;
                }
                if (op.equals("×")) {
                    cnt = 0;
                }
                if (op.equals("÷")) {
                    cnt = 0;
                }
                if (!s2.contains(".")) {
                    int res = (int) cnt;
                    et_input.setText(res + "");
                } else {
                    et_input.setText(cnt + "");
                }
            } else {
                et_input.setText("");
            }
        }
    }