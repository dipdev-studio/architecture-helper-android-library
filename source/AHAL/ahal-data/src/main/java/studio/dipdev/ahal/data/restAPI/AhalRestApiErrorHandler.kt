package studio.dipdev.ahal.data.restAPI

import com.google.gson.Gson
import retrofit2.HttpException

public open class AhalRestApiErrorHandler<T> : IAhalRestApiErrorHandler<T> {
    private val gson: Gson = Gson()

    override fun processError(throwable: Throwable, entity: Class<T>): Throwable {
        if (throwable !is HttpException) {
            return throwable
        }

        val response = throwable.response()
        val errorBody = response.errorBody() ?: return throwable

        val errorResponse: T = gson.fromJson(errorBody.charStream(), entity)
                ?: return throwable

        return AhalRestApiException(throwable.message, throwable, errorResponse).error
    }
}
