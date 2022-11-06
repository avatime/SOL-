package com.finance.android.ui.screens.remit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.finance.android.R
import com.finance.android.ui.components.ButtonType
import com.finance.android.utils.Const
import com.finance.android.utils.ext.withBottomButton
import com.finance.android.viewmodels.RemitViewModel

@Composable
fun RemitOKScreen(
    remitViewModel: RemitViewModel, navController : NavController
) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_done))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = MaterialTheme.colorScheme.primary.toArgb(),
            keyPath = arrayOf(
                "**"
            )
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.STROKE_COLOR,
            value = MaterialTheme.colorScheme.primary.toArgb(),
            keyPath = arrayOf(
                "**"
            )
        )
    )
    Column(  modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.padding(20.dp))
        LottieAnimation(
            composition,
            progress = { progress },
            modifier = Modifier.size(100.dp),
            dynamicProperties = dynamicProperties
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Text(text = "${remitViewModel.moneyValue.value}원",  fontSize = dimensionResource(id = R.dimen.font_size_title_desc).value.sp,)
        Text(text = "송금완료",  fontSize = dimensionResource(id = R.dimen.font_size_title_desc).value.sp,)
        Spacer(modifier = Modifier.padding(10.dp))
        com.finance.android.ui.components.TextButton(
            onClick = {
                navController.navigate(Const.INPUT_RECEIVER_SCREEN)
            },
            text = "완료",
            modifier = Modifier.withBottomButton(),
            buttonType = ButtonType.ROUNDED,

            )
    }
}

