package com.tokodizital.jajanmania.ui.components.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tokodizital.jajanmania.ui.components.buttons.BaseButton
import com.tokodizital.jajanmania.ui.components.buttons.BaseOutlinedButton
import com.tokodizital.jajanmania.ui.theme.JajanManiaTheme

@ExperimentalMaterial3Api
@Composable
fun BaseModalBottomSheet(
    modifier: Modifier = Modifier,
    title: String,
    body: String,
    positiveButtonTitle: String,
    onPositiveButtonClicked: () -> Unit,
    negativeButtonTitle: String? = null,
    onNegativeButtonClicked: () -> Unit = {},
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    positiveButtonContainerColor: Color = MaterialTheme.colorScheme.primary,
    positiveButtonContentColor: Color = Color.White,
) {
    ModalBottomSheet(
        onDismissRequest = onNegativeButtonClicked,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        sheetState = sheetState
    ) {
        BaseBottomContent(
            title = title,
            body = body,
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        BaseButton(
            text = positiveButtonTitle,
            onClicked = onPositiveButtonClicked,
            containerColor = positiveButtonContainerColor,
            contentColor = positiveButtonContentColor,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
        )
        if (negativeButtonTitle != null) {
            BaseOutlinedButton(
                text = negativeButtonTitle,
                onClicked = onNegativeButtonClicked,
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(modifier = Modifier.height(52.dp))
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseBottomSheet() {
    JajanManiaTheme {
        Surface {
            BaseModalBottomSheet(
                title = "Proses?",
                body = "Apakah anda yakin ingin memproses pesanan?",
                positiveButtonTitle = "Ya, Yakin",
                onPositiveButtonClicked = {},
                negativeButtonTitle = "Tutup"
            )
        }
    }
}
