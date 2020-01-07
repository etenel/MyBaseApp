package ${activityPackageName}

import android.os.Bundle
import com.eternal.baselib.base.BaseActivity
import ${viewModelPackageName}.${pageName}ViewModel
import ${packageName}.databinding.Activity${pageName}Binding;
import ${packageName}.R
import ${packageName}.BR


class ${pageName}Activity : BaseActivity<Activity${pageName}Binding,${pageName}ViewModel>()  {

    override fun initView():Int {
    return R.layout.${activityLayoutName}
    }



    override fun initVariableId():Int {
    return BR.vm
    }
    override fun initData(savedInstanceState:Bundle?) {

    }


    override fun hideRefresh() {

    }

    override fun hideLoadMore() {

    }
    override fun initViewObservable(){

    }
}
