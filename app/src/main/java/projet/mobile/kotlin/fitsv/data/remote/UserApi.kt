package projet.mobile.kotlin.fitsv.data.remote

import android.location.Location
import projet.mobile.kotlin.fitsv.data.User
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT


/**
 * Interface UserApiInterface
 * TODO Comment use case of interface UserApiInterface
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface UserApi {

    @POST("/user")
    suspend fun newUser(id: Int, name: String, location: String, title: String)

    @GET("/user")
    suspend fun getUser(id: Int): User

    @PUT("/user")
    suspend fun updateUser(oldId: Int, newName: String, newLocation: String, newTitle: String)

    @DELETE("/user")
    suspend fun deleteUser(id: Int)

    @GET("/users")
    suspend fun getAllUsers(): List<User>

}
