package com.finance.android.ui.screens.groupAccount

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.finance.android.R
import com.finance.android.ui.components.ButtonType
import com.finance.android.ui.components.TextButton
import com.finance.android.viewmodels.GroupAccountViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GroupAccountFriendScreen(groupAccountViewModel: GroupAccountViewModel, navController: NavController, modifier: Modifier) {

    val permissionState =
        rememberPermissionState(permission = Manifest.permission.READ_CONTACTS)

    when {
        permissionState.status.isGranted -> {
            ContactFriendListScreen(groupAccountViewModel = groupAccountViewModel, navController = navController, modifier = modifier)

        }
        else -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize().background(Color.White),
                //verticalArrangement = Arrangement.Center
            ) {

                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_remit_contact))
                Spacer(modifier = modifier.padding(40.dp))
                LottieAnimation(
                    composition = composition,

                    modifier = modifier.size(100.dp),
                    iterations = LottieConstants.IterateForever,
                )

                Spacer(modifier = modifier.padding(20.dp))

                Text(text = "연락처를 불러올까요?")
                Spacer(modifier = modifier.padding(10.dp))
                TextButton(
                    onClick = { permissionState.launchPermissionRequest()},
                    text ="불러오기",
                    buttonType = ButtonType.ROUNDED,
                )
            }
        }
    }
}



