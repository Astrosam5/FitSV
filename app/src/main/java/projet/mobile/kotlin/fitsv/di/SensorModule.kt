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
import projet.mobile.kotlin.fitsv.data.sensors.LightSensor
import projet.mobile.kotlin.fitsv.data.sensors.StepCounterSensor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    // --------------------------------- Sensors ---------------------------------
    @Provides
    @Singleton
    fun provideLightSensor(app: Application): LightSensor {
        return LightSensor(app)
    }

    @Provides
    @Singleton
    fun provideStepSensor(app: Application): StepCounterSensor {
        return StepCounterSensor(app)
    }
}