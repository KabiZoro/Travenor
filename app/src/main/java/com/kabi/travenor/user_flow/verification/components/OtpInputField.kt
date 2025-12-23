package com.kabi.travenor.user_flow.verification.components

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.kabi.travenor.core.presentation.design_system.theme.GrayColor
import com.kabi.travenor.core.presentation.design_system.theme.SfUiDisplay
import com.kabi.travenor.core.presentation.design_system.theme.TextColor

@Composable
fun OtpInputField(
    number: Int?,
    focusRequester: FocusRequester,
    onFocusChanged: (Boolean) -> Unit,
    onNumberChanged: (Int?) -> Unit,
    onKeyboardBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val text = TextFieldValue(
        text = number?.toString().orEmpty(),
        selection = TextRange(
            index = number?.toString()?.length ?: 0
        )
    )
    var isFocused by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(GrayColor),
        contentAlignment = Alignment.Center
    ) {
        BasicTextField(
            value = text,
            onValueChange = { newText ->
                val newNumber = newText.text
                if (newNumber.length <= 1 && newNumber.isDigitsOnly()) {
                    onNumberChanged(newNumber.toIntOrNull())
                }
            },
            cursorBrush = SolidColor(Color.Black),
            singleLine = true,
            textStyle = TextStyle(
                fontFamily = SfUiDisplay,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium,
                fontSize = 28.sp,
                color = TextColor
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            modifier = Modifier
                .padding(10.dp)
                .focusRequester(focusRequester)
                .onFocusChanged {
                    isFocused = it.isFocused
                    onFocusChanged(isFocused)
                }
                .onKeyEvent { event ->
                    val didPressedDelete = event.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_DEL
                    if (didPressedDelete && number == null) {
                        onKeyboardBack()
                    }
                    false
                },
            decorationBox = { innerBox ->
                if (!isFocused && number == null) {
                    Text(
                        text = "-",
                        textAlign = TextAlign.Center,
                        color = TextColor,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize()
                    )
                }
                innerBox()
            }
        )
    }
}

@Preview
@Composable
private fun OtpInputFieldPreview() {
    OtpInputField(
        number = null,
        focusRequester = remember { FocusRequester() },
        onFocusChanged = {},
        onNumberChanged = {},
        onKeyboardBack = {},
        modifier = Modifier
            .size(100.dp)
    )
}