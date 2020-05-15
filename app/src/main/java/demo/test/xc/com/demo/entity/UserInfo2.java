package demo.test.xc.com.demo.entity;

import android.content.Intent;
import android.util.Log;

import demo.test.xc.com.demo.activity.TestActivity;

public class UserInfo2 {

    private String name;
    private int age;

    public UserInfo2(String name, int age) {
        this.name = name;
        this.age = age;

        Intent intent =new Intent(null, TestActivity.class);

    }

    public  static void  vvvv(){
        Intent intent =new Intent(null, TestActivity.class);
        int  a=0;
        for (int i = 0; i < 11; i++) {
            Log.d("TAG", "vvvv: ");
        }

    }
}
