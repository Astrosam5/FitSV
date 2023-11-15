package projet.mobile.kotlin.fitsv.data.remote

import retrofit2.http.GET


/**
 * Interface UserApiInterface
 * TODO Comment use case of interface UserApiInterface
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface UserApi {

    @GET("test")
    suspend fun doNetworkCall()

}
