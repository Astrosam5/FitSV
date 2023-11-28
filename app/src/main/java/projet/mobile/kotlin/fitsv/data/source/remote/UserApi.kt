package projet.mobile.kotlin.fitsv.data.source.remote

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
interface UserApi {

    @GET("/users")
    suspend fun getAllUsers(): Response<List<UserModel>>

    @POST("/user")
    suspend fun newUser(@Body userModel: UserModel)

    @GET("/user/{id}")
    suspend fun getUser(@Path("id") id: String): Response<UserModel>

    @PUT("/user/{id}")
    suspend fun updateUser(@Path("id") id: String, @Body userModel: UserModel)

    @DELETE("/user/{id}")
    suspend fun deleteUser(@Path("id") id: String)

}
