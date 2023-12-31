/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import projet.mobile.kotlin.fitsv.data.datasource.StepCounterDataSource
import projet.mobile.kotlin.fitsv.data.datasource.StepCounterDataSourceImp
import projet.mobile.kotlin.fitsv.data.datasource.UserDataSource
import projet.mobile.kotlin.fitsv.data.datasource.UserDataSourceImp
import projet.mobile.kotlin.fitsv.data.source.local.AppDatabase
import projet.mobile.kotlin.fitsv.data.source.local.StepCounterDao
import projet.mobile.kotlin.fitsv.data.source.local.UserDao
import projet.mobile.kotlin.fitsv.data.source.remote.StepCounterApi
import projet.mobile.kotlin.fitsv.data.source.remote.UserApi
import projet.mobile.kotlin.fitsv.domain.repository.StepCounterRepository
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Class AppModule
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // --------------------------------- Functions for API ---------------------------------

    @Provides
    @Named("BASE_URL")
    fun getBaseUrl(): String{
        return "http://10.0.2.2:8000"
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BASE_URL") baseUrl: String): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val okHttpClient: OkHttpClient =  OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val gsonConverterFactory: GsonConverterFactory = GsonConverterFactory.create()


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserApiInterface(retrofit: Retrofit) : UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Singleton
    @Provides
    fun provideStepCounterApiInterface(retrofit: Retrofit) : StepCounterApi {
        return retrofit.create(StepCounterApi::class.java)
    }


    // --------------------------------- Function for DAO ---------------------------------

//    @Provides
//    fun getContext(@ApplicationContext context: Context,): Context {
//        return context
//    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getAppDBInstance(context)
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    fun provideStepCounterDao(appDatabase: AppDatabase): StepCounterDao {
        return appDatabase.stepCounterDao()
    }

    // --------------------------------- DataSource ---------------------------------

    @Provides
    @Singleton
    fun provideUserDataSource(userApi: UserApi, userDao: UserDao) : UserDataSource {
        return UserDataSourceImp(userApi, userDao)
    }

    @Provides
    @Singleton
    fun provideStepCounterDataSource(stepCounterApi: StepCounterApi,
                                     stepCounterDao: StepCounterDao) : StepCounterDataSourceImp {
        return StepCounterDataSourceImp(stepCounterApi, stepCounterDao)
    }

    // --------------------------------- Repository ---------------------------------

    @Provides
    @Singleton
    fun provideUserRepository(userDataSource: UserDataSource) : UserRepository {
        return UserRepository(userDataSource)
    }

    @Provides
    @Singleton
    fun provideStepCounterRepository(stepCounterDataSource: StepCounterDataSource) : StepCounterRepository {
        return StepCounterRepository(stepCounterDataSource)
    }

}