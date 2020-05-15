package demo.test.xc.com.demo.kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import demo.test.xc.com.demo.entity.UserInfo

class TestViewModel  : ViewModel(){
    var userData : MutableLiveData<UserInfo> =MutableLiveData();
    var switchData : MutableLiveData<UserInfo> =MutableLiveData();
    fun  getUserInfo(){
        var user=UserInfo("张三",(10000..25000).random())
        userData.postValue(user)
    }


    fun getUserName(userInfo: UserInfo) :LiveData<UserInfo>{
        userInfo.name=userInfo.name+"switchMap"
//        switchData.postValue(userInfo)
        return switchData;
    }

}