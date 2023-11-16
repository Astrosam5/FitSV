/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.states

/**
 * Class RessourcesState
 * TODO Comment use case of class RessourcesState
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
sealed class ResourcesState<T> {
    class Loading<T>: ResourcesState<T>()
    data class Success<T>(val data: T): ResourcesState<T>()
    data class Error<T>(val error: String): ResourcesState<T>()
}