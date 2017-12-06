package com.harry.android.mymvpdagger.model;

import android.preference.PreferenceActivity;

import com.harry.android.mymvpdagger.presenter.IMainPresenter;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Zhang on 2017-11-26.
 */

public class MainModel {
    IMainPresenter mIMainPresenter;
    public MainModel(IMainPresenter iMainPresenter) {
        this.mIMainPresenter = iMainPresenter;
    }
    public void loadData() {
        // AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        OkHttpClient client = new OkHttpClient();
        String url = "http://www.weather.com.cn/adat/sk/101010100.html";
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response responses = null;

            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            mIMainPresenter.loadDataFailure();
                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            String jsonData = response.body().string();
                            JSONObject Jobject = null;
                            try {
                                Jobject = new JSONObject(jsonData);

                                JSONObject weatherinfo = Jobject.getJSONObject("weatherinfo");
                                if(weatherinfo != null){
                                    MainModelBean mainModelBean = new MainModelBean();
                                    mainModelBean.setCity(weatherinfo.getString("city"));
                                    mainModelBean.setWd(weatherinfo.getString("WD"));
                                    mainModelBean.setWs(weatherinfo.getString("WS"));
                                    mainModelBean.setTime(weatherinfo.getString("time"));
                                    mIMainPresenter.loadDataSuccess(mainModelBean);
                                }else{
                                    mIMainPresenter.loadDataFailure();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                    });



    }
}