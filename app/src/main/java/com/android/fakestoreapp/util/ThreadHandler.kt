import androidx.lifecycle.MutableLiveData
import com.android.core.util.resource.ResourceCore
import com.android.core.util.resource.ResourceState
import java.lang.Exception

fun <T> MutableLiveData<ResourceCore<T>>.setLoading() {
    postValue(
        ResourceCore(
        resourceState = ResourceState.Loading,
        data = null
    )
    )
}
fun <T> MutableLiveData<ResourceCore<T>>.setResult(data: ResourceCore<T>?) {
    postValue(data)
}

fun <T> MutableLiveData<ResourceCore<T>>.setError(exception: Exception) {
    postValue(
        ResourceCore(
        resourceState = ResourceState.Error.GeneralError(exception.message.toString()),
        data = null
    )
    )
}