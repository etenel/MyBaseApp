package ${viewModelPackageName}
import android.app.Application
import ${modelPackageName}.${pageName}Model
import com.eternal.baselib.base.BaseViewModel



class ${pageName}ViewModel(application: Application) : BaseViewModel<${pageName}Model>(application) {


    init{
        model=${pageName}Model()
    }
    override fun onCleared() {
          super.onCleared()
    }

}
