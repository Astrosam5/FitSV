/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.states

/**
 * Class LoginState
 * TODO Comment use case of class LoginState
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
data class LoginState (
  val username : String = "",
  val password : String = "",
  val error: Boolean = false,
  val errorMessage: String
)