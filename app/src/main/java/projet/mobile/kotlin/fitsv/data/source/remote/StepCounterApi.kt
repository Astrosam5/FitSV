package projet.mobile.kotlin.fitsv.data.source.remote

import projet.mobile.kotlin.fitsv.domain.model.StepCounterModel
import projet.mobile.kotlin.fitsv.domain.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


/**
 * Interface UserApiInterface
 * TODO Comment use case of interface UserApiInterface
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface StepCounterApi {

    @GET("/users")
    suspend fun getAllStepCounter(): Response<List<StepCounterModel>>

    @GET("/user/{id}")
    suspend fun getStepCounter(@Path("id") id: String): Response<StepCounterModel>

}
