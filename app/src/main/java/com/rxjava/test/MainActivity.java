package com.rxjava.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.cut.rxjava.Observable;
import rx.cut.rxjava.Observer;
import rx.cut.rxjava.Subscriber;
import rx.cut.rxandroid.schedulers.AndroidSchedulers;
import rx.cut.rxjava.Subscription;
import rx.cut.rxjava.schedulers.Schedulers;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = findViewById(R.id.tv_show);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subscription wo = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("99" + Thread.currentThread().getName());
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(String s) {
                                textView.setText(s);
                                Log.e("wo", s + Thread.currentThread().getName());
                            }
                        });
                wo.unsubscribe();

            }
        });


    }
}
