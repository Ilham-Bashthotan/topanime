package com.example.top_anime.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.top_anime.ui.theme.TopanimeTheme

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { 
                Text(
                    title,
                    color = MaterialTheme.colorScheme.onSurface
                ) 
            },
            text = { 
                Text(
                    message,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ) 
            },
            confirmButton = {
                Button(
                    onClick = onConfirm,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss
                ) {
                    Text("Tidak")
                }
            }
        )
    }
}

@Preview
@Composable
fun ConfirmationDialogPreview() {
    TopanimeTheme {
        ConfirmationDialog(
            showDialog = true,
            title = "Confirmation",
            message = "Are you sure?",
            onConfirm = {},
        ) { }
    }
}
