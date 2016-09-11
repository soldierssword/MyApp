package com.example.johnson.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.OkHttpClient;

public class FragmentPage3 extends Fragment {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.spinner4)
    Spinner spinner4;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.spinner5)
    Spinner spinner5;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.editText6)
    EditText editText6;
    @BindView(R.id.button)
    Button button;
    int sp4,sp5;
    ArrayAdapter<String> ad;
    final static String url="http://apis.baidu.com/apistore/currencyservice/currency";
    final static String apikey="cc4fd4038869c117b962cd90607c9c34";
    final static String[][] s1={ { "CNY",  "USD","JPY" ,"EUR" ,"GBP" ,"KRW" ,"HKD" ,"AUD" ,"CAD",},
                                {"人民币","美元","日元","欧元","英镑","韩元","港元","澳元","加元"}};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_3, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initView();
    }

    private void initView() {
        ad=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,s1[1]);
        spinner4.setAdapter(ad);
        spinner5.setAdapter(ad);
        setSpinner();
        spinner4.setSelection(0);
        spinner5.setSelection(1);
        setButton();
    }
    private void setSpinner(){
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp4=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sp5=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private  void setButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(sp4,sp5);
            }
        });
    }

    private void setResult(int sp4, int sp5) {
        OkHttpUtils.get().url(url)
                .addHeader("apikey",apikey)//添加
                .addParams("fromCurrency",s1[0][sp4])
                .addParams("toCurrency",s1[0][sp5])
                .addParams("amount",editText3.getText().toString())
                .build()
                .execute(new ApiValueCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(getActivity(), "q", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(ApiValue apiValue, int i) {
                        Log.d("TAG",apiValue.getErrMsg());
                        if(apiValue.getErrNum()==0){

                            editText6.setText(apiValue.getRetdata().getConvertedamount().toString());
                        }
                    }
                });
    }
}