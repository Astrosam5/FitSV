/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.states

import projet.mobile.kotlin.fitsv.domain.model.UserModel

/**
 * Class LoginState
 * TODO Comment use case of class LoginState
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
data class LoginState (
  val user: UserModel? = null,
  val logged: Boolean = false,
  val error: Boolean = false,
  val errorMessage: String = ""
)