package com.example.johnson.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentPage1 extends Fragment {
    @BindView(R.id.msg)
    EditText msg;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.mc)
    Button mc;
    @BindView(R.id.m_plus)
    Button mPlus;
    @BindView(R.id.m_minus)
    Button mMinus;
    @BindView(R.id.mr)
    Button mr;
    @BindView(R.id.c)
    Button c;
    @BindView(R.id.plus_minus)
    Button plusMinus;
    @BindView(R.id.divide)
    Button divide;
    @BindView(R.id.multiply)
    Button multiply;
    @BindView(R.id._7)
    Button _7;
    @BindView(R.id._8)
    Button _8;
    @BindView(R.id._9)
    Button _9;
    @BindView(R.id.minus)
    Button minus;
    @BindView(R.id._4)
    Button _4;
    @BindView(R.id._5)
    Button _5;
    @BindView(R.id._6)
    Button _6;
    @BindView(R.id.plus)
    Button plus;
    @BindView(R.id._1)
    Button _1;
    @BindView(R.id._2)
    Button _2;
    @BindView(R.id._3)
    Button _3;
    @BindView(R.id._0)
    Button _0;
    @BindView(R.id._point)
    Button Point;
    @BindView(R.id.equals)
    Button equals;
    // 标志用户按的是否是整个表达式的第一个数字,或者是运算符后的第一个数字
    private boolean firstDigit = true;
    // 计算的中间结果。
    private double resultNum = 0.0;
    // 当前运算的运算符
    private String operator = "=";
    // 操作是否合法
    private boolean operateValidFlag = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_1, null);
        ButterKnife.bind(this, view);
        return view;
    }

    //添加监听


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }

    /**
     * 处理Backspace键被按下的事件
     */
    private void handleBackspace() {
        String text = msg.getText().toString();
        int i = text.length();
        if (i > 0) {
            // 退格，将文本最后一个字符去掉
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                // 如果文本没有了内容，则初始化计算器的各种值
                msg.setText("0");
                firstDigit = true;
                operator = "=";
            } else {
                // 显示新的文本
                msg.setText(text);
            }
        }
    }

    /**
     * 处理数字键被按下的事件
     *
     * @param key
     */
    private void handleNumber(String key) {
        if (firstDigit) {
            // 输入的第一个数字
            msg.setText(key);
        } else if ((key.equals(".")) && (msg.getText().toString().indexOf(".") < 0)) {
            // 输入的是小数点，并且之前没有小数点，则将小数点附在结果文本框的后面
            msg.setText(msg.getText() + ".");
        } else if (!key.equals(".")) {
            // 如果输入的不是小数点，则将数字附在结果文本框的后面
            msg.setText(msg.getText() + key);
        }
        // 以后输入的肯定不是第一个数字了
        firstDigit = false;
    }

    /**
     * 处理C键被按下的事件
     */
    private void handleC() {
        // 初始化计算器的各种值
        msg.setText("0");
        firstDigit = true;
        operator = "=";
    }

    /**
     * 处理运算符键被按下的事件
     *
     * @param key
     */
    private void handleOperator(String key) {
        if (operator.equals("/")) {
            // 除法运算
            // 如果当前结果文本框中的值等于0
            if (getNumberFromText() == 0.0) {
                // 操作不合法
                operateValidFlag = false;
                //msg.setText("除数不能为零");
                Toast.makeText(getActivity(), "除数不能为零", Toast.LENGTH_LONG).show();
            } else {
                resultNum /= getNumberFromText();
            }
        } else if (operator.equals("1/x")) {
            // 倒数运算
            if (resultNum == 0.0) {
                // 操作不合法
                operateValidFlag = false;
                //msg.setText("零没有倒数");
                Toast.makeText(getActivity(), "零没有倒数", Toast.LENGTH_LONG).show();
            } else {
                resultNum = 1 / resultNum;
            }
        } else if (operator.equals("+")) {
            // 加法运算
            resultNum += getNumberFromText();
        } else if (operator.equals("-")) {
            // 减法运算
            resultNum -= getNumberFromText();
        } else if (operator.equals("*")) {
            // 乘法运算
            resultNum *= getNumberFromText();
        } else if (operator.equals("sqrt")) {
            // 平方根运算
            resultNum = Math.sqrt(resultNum);
        } else if (operator.equals("%")) {
            // 百分号运算，除以100
            resultNum = resultNum / 100;
        } else if (operator.equals("+/-")) {
            // 正数负数运算
            resultNum = resultNum * (-1);
        } else if (operator.equals("=")) {
            // 赋值运算
            resultNum = getNumberFromText();
        }
        if (operateValidFlag) {
            // 双精度浮点数的运算
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                msg.setText(String.valueOf(t1));
            } else {
                msg.setText(String.valueOf(resultNum));
            }
        }
        // 运算符等于用户按的按钮
        operator = key;
        firstDigit = true;
        operateValidFlag = true;
    }

    /**
     * 从结果文本框中获取数字
     *
     * @return
     */
    private double getNumberFromText() {
        double result = 0;
        try {
            result = Double.parseDouble(msg.getText().toString());
        } catch (NumberFormatException e) {
            Log.e("getNumberFromText()", "NumberFormatException");
        }
        return result;
    }

    @OnClick({R.id.back, R.id.mc, R.id.m_plus, R.id.m_minus, R.id.mr, R.id.c, R.id.plus_minus, R.id.divide, R.id.multiply, R.id._7, R.id._8, R.id._9, R.id.minus, R.id._4, R.id._5, R.id._6, R.id.plus, R.id._1, R.id._2, R.id._3, R.id._0, R.id._point,R.id.equals})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                handleBackspace();
                break;
            case R.id.mc:
                break;
            case R.id.m_plus:
                break;
            case R.id.m_minus:
                break;
            case R.id.mr:
                break;
            case R.id.c:
                handleC();
                break;
            case R.id.plus_minus:
                handleOperator("+/-");
                break;
            case R.id.divide:
                handleOperator("/");
                break;
            case R.id.multiply:
                handleOperator("*");
                break;
            case R.id._7:
                handleNumber("7");
                break;
            case R.id._8:
                handleNumber("8");
                break;
            case R.id._9:
                handleNumber("9");
                break;
            case R.id.minus:
                handleOperator("-");
                break;
            case R.id._4:
                handleNumber("4");
                break;
            case R.id._5:
                handleNumber("5");
                break;
            case R.id._6:
                handleNumber("6");
                break;
            case R.id.plus:
                handleOperator("+");
                break;
            case R.id._1:
                handleNumber("1");
                break;
            case R.id._2:
                handleNumber("2");
                break;
            case R.id._3:
                handleNumber("3");
                break;
            case R.id._0:
                handleNumber("0");
                break;
            case R.id._point:
                handleNumber(".");
                break;
            case R.id.equals:
                handleOperator("=");
                break;
        }
    }
}
