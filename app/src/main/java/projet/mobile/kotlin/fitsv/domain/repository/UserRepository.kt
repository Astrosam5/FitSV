/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.domain.repository

/**
 * Interface MyRepository
 * TODO Comment use case of interface MyRepository
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
interface UserRepository {
    suspend fun doNetworkCall()
}