package com.mathroda.dynamicvillage.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.mathroda.dynamicvillage.R
import com.mathroda.dynamicvillage.ui.theme.CustomGreen
import com.mathroda.dynamicvillage.ui.theme.CustomRed

@OptIn(ExperimentalMotionApi::class)
@Composable
fun DynamicVillage() {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    var animateSwitch by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animateSwitch) 1f else 0f,
        animationSpec = tween(700)
    )

        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = progress,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = Color.Black,
                        shape = RoundedCornerShape(50)
                    )
                    .layoutId("box")
                    .clickable {
                        animateSwitch = !animateSwitch
                    }
            )

            ScaleInComponents()
            ScaleOutComponents()
    }
}

@Composable
fun ScaleInComponents() {

    Icon(
        imageVector = Icons.Default.Call,
        contentDescription = null,
        tint = Color.White,
        modifier = Modifier
            .layoutId("call_icon")
            .size(20.dp)
    )

    Text(
        text = "5 : 55",
        color = Color.White,
        fontSize = 14.sp,
        modifier = Modifier
            .layoutId("call_time")
    )

    Icon(
        imageVector = Icons.Default.MicOff,
        contentDescription = null,
        tint = CustomRed,
        modifier = Modifier
            .layoutId("mic_icon")
            .size(20.dp)
    )

}

@Composable
fun ScaleOutComponents() {

    Card (
        shape = CircleShape,
        backgroundColor = CustomGreen,
        border = BorderStroke(2.dp, Color.White),
        modifier = Modifier
            .layoutId("caller_pic")
            .size(50.dp)
            ){
        Image(
            painter = painterResource(id = R.drawable.pic),
            contentDescription = null
        )
    }

    Text(
        text = "MathRoda",
        color = Color.White,
        fontSize = 12.sp,
        modifier = Modifier
            .layoutId("first_name")
    )
    Text(
        text = "Codes",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        modifier = Modifier
            .layoutId("last_name")
    )

    CustomCallButton(
        id = "end_call",
        color = CustomRed,
        icon = Icons.Default.CallEnd
    )

    CustomCallButton(
        id = "accept_call",
        color = CustomGreen,
        icon = Icons.Default.Call
    )

 }

@Composable
fun CustomCallButton(
    id: String,
    color: Color,
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .layoutId(id)
            .background(
                color = color,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = icon,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.Center)
        )
    }
}
