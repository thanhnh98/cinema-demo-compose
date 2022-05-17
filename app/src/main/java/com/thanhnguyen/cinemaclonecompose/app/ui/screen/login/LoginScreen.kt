package com.thanhnguyen.cinemaclonecompose.app.ui.screen.login

import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.main.MainActivity
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.welcome.WelcomeActivity
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.ColorBlueAccent
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.CommonStyle
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.TextColor
import java.time.format.TextStyle

@ExperimentalMaterial3Api
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Destination
@Composable
fun LoginScreen(
    activity: LoginActivity,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val tfUsername = remember {
        mutableStateOf(TextFieldValue())
    }

    val tfPassword = remember {
        mutableStateOf(TextFieldValue())
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_logo_no_text),
            contentDescription = "",
            Modifier.size(88.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.padding(top = 12.dp))
        
        Text(text = "CINEMAX", style = CommonStyle.custom(
            color = Color.White,
            fontSize = 24.sp,
        ))

        Spacer(modifier = Modifier.padding(top = 24.dp))
        
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tfUsername.value,
            onValueChange = {
                tfUsername.value = it
            },
            label = {
                Text(text = "Username", style = CommonStyle.custom(
                    fontSize = 14.sp,
                ))
            },
            singleLine = true,
            placeholder = {
                Text(text = "Type username", style = CommonStyle.custom(
                    fontSize = 14.sp,
                    color = TextColor.Grey
                ))
            },
            shape = RoundedCornerShape(12.dp),
            textStyle = CommonStyle.normal()
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = tfPassword.value,
            onValueChange = {
                tfPassword.value = it
            },
            singleLine = true,
            label = {
                Text(text = "Password", style = CommonStyle.custom(
                    fontSize = 14.sp,
                ))
            },
            placeholder = {
                Text(text = "Type password", style = CommonStyle.custom(
                    fontSize = 14.sp,
                    color = TextColor.Grey
                ))
            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = RoundedCornerShape(12.dp),
            textStyle = CommonStyle.normal(),
            trailingIcon = {
                val icVisible = painterResource(id = if(passwordVisible.value) R.drawable.ic_visibility_off else R.drawable.ic_visibility)
                val des = if(passwordVisible.value) "Showed password" else "Hidden password"

                Icon(
                    painter = icVisible,
                    contentDescription = des,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            passwordVisible.value = !passwordVisible.value
                        }
                )
            }
        )
        Spacer(modifier = Modifier.padding(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(
                    color = ColorBlueAccent,
                    shape = RoundedCornerShape(24.dp)
                ),
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 12.dp
                    )
                    .align(Alignment.Center)
                    .clickable {
                        activity.startActivity(
                            Intent(activity, MainActivity::class.java)
                        )
                        loginViewModel.markAsLoggedIn()
                        activity.finish()
                    },
                style = CommonStyle.bold(),
                text = "Login",
                textAlign = TextAlign.Center
            )
        }
    }
}