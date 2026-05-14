package dev.mcallisaya.techchallenge.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.mcallisaya.techchallenge.R
import dev.mcallisaya.techchallenge.domain.model.ErrorCode

@Composable
fun ErrorDialog(
    errorCode: ErrorCode = ErrorCode.UNKNOWN,
    onDismissRequest: () -> Unit = {}
) {
    Dialog(
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ErrorDialogContent(
                modifier = Modifier.align(Alignment.Center),
                errorCode = errorCode,
                onClick = {
                    onDismissRequest.invoke()
                }
            )
        }
    }
}

@Composable
private fun ErrorDialogContent(
    modifier: Modifier = Modifier,
    errorCode: ErrorCode = ErrorCode.UNKNOWN,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = errorCode.name,
            fontSize = 15.sp,
        )
        Button(
            onClick = {
                onClick.invoke()
            },
        ) {
            Text(stringResource(R.string.close))
        }
    }
}