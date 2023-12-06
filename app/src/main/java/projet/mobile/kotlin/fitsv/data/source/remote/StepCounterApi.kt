package projet.mobile.kotlin.fitsv.data.source.remote

import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Interface UserApiInterface
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface StepCounterApi {

    @GET("/users")
    suspend fun getAllStepCounter(): Response<List<StepCounterModel>>

    @GET("/user/{id}")
    suspend fun getStepCounter(@Path("id") id: String): Response<StepCounterModel>

}
