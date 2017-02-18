package com.doc.retrofitframedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.doc.retrofitframedemo.bean.MyHistory;
import com.doc.retrofitframedemo.retrofit.NetUtils;
import com.doc.retrofitframedemo.retrofit.base.BaseCallModel;
import com.doc.retrofitframedemo.retrofit.base.BaseCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.tv);
//        testGet();
//        testPost();
        testMapPost();





    }

    private void testMapPost() {
      /*  @Field("key") String key,
        @Field("v") String v,
        @Field("month") int month,
        @Field("day") int day*/

        Map<String, String> map = new HashMap<>();
        map.put("key", NetUtils.key);
        map.put("v", "1.0");
        map.put("month", 1 + "");
        map.put("day", 20 + "");
        Call<BaseCallModel<MyHistory>> call = NetUtils.service.postMapHistory(map);
        call.enqueue(new BaseCallback<MyHistory>() {
            @Override
            public void onSucess(Call<BaseCallModel<MyHistory>> call, Response<BaseCallModel<MyHistory>> response) {
                List<MyHistory> historyList = response.body().result;
                String str = "";
                for (MyHistory mHistory : historyList) {
                    String string = mHistory.getTitle() + "\n" +
                            mHistory.getDes() + "\n" +
                            mHistory.getLunar() + "\n\n";
                    str = str + string;
                }
                mTextView.setText(str);
            }

            @Override
            protected void onFail(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void testPost() {
        Call<BaseCallModel<MyHistory>> call = NetUtils.service.postHistory(NetUtils.key, "1.0", 1, 1);
        call.enqueue(new BaseCallback<MyHistory>() {
            @Override
            public void onSucess(Call<BaseCallModel<MyHistory>> call, Response<BaseCallModel<MyHistory>> response) {
                List<MyHistory> historyList = response.body().result;
                String str = "";
                for (MyHistory mHistory : historyList) {
                    String string = mHistory.getTitle() + "\n" +
                            mHistory.getDes() + "\n" +
                            mHistory.getLunar() + "\n\n";
                    str = str + string;
                }
                mTextView.setText(str);
            }

            @Override
            protected void onFail(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void testGet() {
        Call<BaseCallModel<MyHistory>> history = NetUtils.service.getHistory(NetUtils.key, "1.0", 1, 17);
        history.enqueue(new BaseCallback<MyHistory>() {
            @Override
            public void onSucess(Call<BaseCallModel<MyHistory>> call, Response<BaseCallModel<MyHistory>> response) {
                List<MyHistory> historyList = response.body().result;
                String str = "";
                for (MyHistory mHistory : historyList) {
                    String string = mHistory.getTitle() + "\n" +
                            mHistory.getDes() + "\n" +
                            mHistory.getLunar() + "\n";
                    str = str + string;
                }
                mTextView.setText(str);
            }

            @Override
            protected void onFail(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
