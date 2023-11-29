/* 
 * Code made for course 8INF865 at UQAC
 * Copyright UQAC - Samuel Albareda Zumelzu - Valentin Ayroles
 */
package projet.mobile.kotlin.fitsv.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import projet.mobile.kotlin.fitsv.domain.repository.UserRepository
import javax.inject.Inject

/**
 * Class SettingsViewModel
 * TODO Comment use case of class SettingsViewModel
 * @author Samuel Albareda Zumelzu
 * @author Valentin Ayroles
 */
@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private var addPicture by mutableStateOf(false)


    fun openAddPicture() {
        addPicture = true
    }

    fun isOpenAddPicture(): Boolean {
        return addPicture
    }


}