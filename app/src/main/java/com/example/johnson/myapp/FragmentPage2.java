package com.example.johnson.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class FragmentPage2 extends Fragment {

    @BindView(R.id.textView_1)
    TextView textView1;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.textView_2)
    TextView textView2;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    @BindView(R.id.textView_3)
    TextView textView3;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.spinner3)
    Spinner spinner3;
    String s1[],s2[][];//s1单位类型，s2单位名称
    ArrayAdapter<String> ad1;
    ArrayAdapter ad2;
    int i=0,sp2=0,sp3=0;
    DecimalFormat df  = new DecimalFormat("###.########");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("Tag","onCreateView");
        View view = inflater.inflate(R.layout.tab_2, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("Tag","onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        initView();
        setSpinner();
        setChangeEditText();
    }

    /**
     * 设置选项
     */
    private  void  initView(){
        Log.d("Tag","initView");
        editText.setText("0");
        s1=new String[]{"长度","温度","面积","质量"};
        s2=new String[][]{{"毫米","厘米","分米","米"},
                          {"摄氏度","华氏度"},
                          {"平方毫米","平方厘米","平方分米","平方米"},
                          {"毫克","克","千克"}};
        ad1=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,s1);//自动泛型
        ad2=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,s2[0]);
        spinner.setAdapter(ad1);
        spinner.setSelection(0,true);
        spinner2.setAdapter(ad2);
        spinner3.setAdapter(ad2);
        spinner2.setSelection(0,true);
        spinner3.setSelection(0,true);

    }
    private void setSpinner(){
        Log.d("Tag","setSpinner");

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                i=position;
                spinner2.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,s2[position]));
                spinner3.setAdapter(new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,s2[position]));
                handle_Sp_and_Ed_Change();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp2=position;
                handle_Sp_and_Ed_Change();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp3=position;
                handle_Sp_and_Ed_Change();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }




    /**
     * 设置改变文本框事件
     */
    private void setChangeEditText(){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("Tag","onTextChanged");
                if(editText.getText().toString().startsWith("0")&&editText.getText().length()>1){
                    editText.setText(editText.getText().delete(0,1));
                    editText.setSelection(1);
                }
                handle_Sp_and_Ed_Change();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void handle_Sp_and_Ed_Change(){

        switch (i){
            case 0:
                handleLength(sp2,sp3);
                Log.d("Tag","handleLength(sp2,sp3)");
                break;
            case 1:
                handleTemperature(sp2,sp3);
                Log.d("Tag"," handleTemperature(sp2,sp3)");
                break;
            case 2:
                handleArea(sp2,sp3);
                Log.d("Tag"," handleArea(sp2,sp3);");
                break;
            case 3:
                handleWeight(sp2,sp3);
                Log.d("Tag","handleWeight(sp2,sp3);");
                break;
        }


    }
    private void handleLength(int sp2_index,int sp3_index){//长度
        double[][] doubles={{1   ,0.1,0.01,0.001},//由复杂的ifelse变成简单的寻找系数
                           {10  ,1  ,0.1 ,0.01 },
                           {100 ,10 ,1   ,0.1  },
                           {1000,100,10  ,1    },};

            editText2.setText(String.valueOf(df.format(getNumberFromText() * doubles[sp2_index][sp3_index])));
    }
    private void handleTemperature(int sp2_index,int sp3_index){//温度
        if(sp2_index==1&&sp3_index==0){
            editText2.setText(String.valueOf(df.format((getNumberFromText()-32.0)/1.8)));
        } else if (sp2_index==0&&sp3_index==1) {
            editText2.setText(String.valueOf(df.format(1.8*getNumberFromText()+32.0)));
        }

    }
    private void handleArea(int sp2_index,int sp3_index){//面积
        double[][] doubles={{1   ,0.1,0.01,0.001},//由复杂的ifelse变成简单的寻找系数
                            {10  ,1  ,0.1 ,0.01 },
                            {100 ,10 ,1   ,0.1  },
                            {1000,100,10  ,1    },};

            editText2.setText(String.valueOf(df.format(getNumberFromText() * doubles[sp2_index][sp3_index]*doubles[sp2_index][sp3_index])));

    }
    private void handleWeight(int sp2_index,int sp3_index){//重量
        double[][] doubles={{1   ,0.1,0.01,0.001},//由复杂的ifelse变成简单的寻找系数
                            {10  ,1  ,0.1 ,0.01 },
                            {100 ,10 ,1   ,0.1  },
                            {1000,100,10  ,1    },};

            editText2.setText(String.valueOf(df.format(getNumberFromText() * Math.pow(doubles[sp2_index][sp3_index],3))));

    }
    private double getNumberFromText() {
        Log.d("Tag","getNumberFromText()");
        double result = 0;
        try {
            result = Double.parseDouble(editText.getText().toString());
            //editText.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "请不要输入数字以外内容", Toast.LENGTH_SHORT).show();
            editText.setText("0");
            editText.setSelection(1);
            Log.e("Tag", "NumberFormatException");



        }

        return result;
    }

}