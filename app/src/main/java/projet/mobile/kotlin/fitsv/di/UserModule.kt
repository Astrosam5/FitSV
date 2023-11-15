/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import projet.mobile.kotlin.fitsv.data.remote.UserApi
import projet.mobile.kotlin.fitsv.data.repository.UserRepositoryImpl
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Class OnlineModule
 * TODO Comment use case of class OnlineModule
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    @Named("BASE_URL")
    fun getBaseUrl(): String{
        return "https://dezdez" // TODO Replace by real url
    }


    @Singleton
    @Provides
    fun provideUserApiInterface(@Named("BASE_URL") baseUrl: String) : UserApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUser(userApi: UserApi, app: Application ): UserRepository {
        return UserRepositoryImpl(userApi, app)
    }
}