package com.thanhnguyen.cinemaclonecompose.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thanhnguyen.cinemaclonecompose.model.AccountType
import com.thanhnguyen.cinemaclonecompose.theme.ColorBlueAccent
import com.thanhnguyen.cinemaclonecompose.theme.ColorOrangeDark
import com.thanhnguyen.cinemaclonecompose.theme.custom

@Composable
fun AccountType(
    accountType: AccountType
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .background(
                color = when (accountType) {
                    AccountType.Premium -> ColorOrangeDark
                    AccountType.Free -> ColorBlueAccent
                },
                shape = RoundedCornerShape(8.dp)
            )
    ){
        Text(
            modifier = Modifier
                .padding(
                    vertical = 4.dp,
                    horizontal = 6.dp
                )
                .wrapContentWidth()
                .wrapContentHeight(),
            text = when(accountType){
                AccountType.Premium -> "Premium"
                AccountType.Free -> "Free"
            },
            style = TextStyle().custom(
                fontSize = 12.sp
            )
        )
    }
}