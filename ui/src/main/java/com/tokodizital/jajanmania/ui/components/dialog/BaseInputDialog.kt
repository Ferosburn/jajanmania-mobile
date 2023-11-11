package com.tokodizital.jajanmania.ui.components.dialog

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.maxkeppeker.sheets.core.models.base.UseCaseState
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.input.models.InputTextField

@ExperimentalMaterial3Api
@Composable
fun BaseInputDialog(
    state: UseCaseState = rememberUseCaseState(),
    textField: InputTextField,
    onPositiveClicked: () -> Unit = {},
) {
    val inputOptions = listOf(
        textField
    )

    InputDialog(
        state = state,
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { onPositiveClicked() },
        )
    )
}