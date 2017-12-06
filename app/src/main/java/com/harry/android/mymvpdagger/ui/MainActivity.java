package com.harry.android.mymvpdagger.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.harry.android.mymvpdagger.R;
import com.harry.android.mymvpdagger.dagger.DaggerPresenterComponent;
import com.harry.android.mymvpdagger.dagger.PresenterModule;
import com.harry.android.mymvpdagger.model.MainModelBean;
import com.harry.android.mymvpdagger.presenter.MainPresenter;
import com.harry.android.mymvpdagger.view.MainView;
import javax.inject.Inject;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements MainView {
    public static final String EXTRA_MESSAGE = "com.harry.android.mymvpdagger.ui.MESSAGE";

    private ProgressBar mProgressBar;
    private TextView text;

    @Inject
    MainPresenter mMainPresenter;

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerPresenterComponent.builder()
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);//complete inject

        initView();
    }
    private void initView() {
        text = (TextView) findViewById(R.id.text);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar_cyclic);

        btn = (Button) findViewById(R.id.button0) ;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delay 2 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMainPresenter.loadData();
                    }
                }, 3000);

            }
        });

    }
    @Override
    protected void onDestroy() {
        mMainPresenter.detachView();
        super.onDestroy();
    }
    @Override
    public void showData(MainModelBean mainModelBean) {
        final String showData = getResources().getString(R.string.city) + mainModelBean.getCity()
                + getResources().getString(R.string.wd) + mainModelBean.getWd()
                + getResources().getString(R.string.ws) + mainModelBean.getWs()
                + getResources().getString(R.string.time) + mainModelBean.getTime();

//        MainActivity.this.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                text.setText(showData);
//            }
//        });

        Intent intent = new Intent(this, ContentActivity.class);

        String message = showData;
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }
    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }
    @Override
    public void hideProgress() {
        //mProgressBar.setVisibility(View.GONE);
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressBar.setVisibility(View.GONE);
            }


        });
    }
}
