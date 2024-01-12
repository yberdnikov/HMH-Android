package com.hmh.hamyeonham.feature.lock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hmh.hamyeonham.feature.lock.ui.theme.Blackground
import com.hmh.hamyeonham.feature.lock.ui.theme.HMHAndroidTheme
import com.hmh.hamyeonham.feature.lock.ui.theme.HmhTypography
import com.hmh.hamyeonham.feature.lock.ui.theme.WhiteText

class LockActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HMHAndroidTheme {
                LockScreen()
            }
        }
    }
}


@Composable
fun LockScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blackground)
    ) {
        val appName = "앱 이름"
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = com.hmh.hamyeonham.core.designsystem.R.drawable.ic_launcher_background),
                contentDescription = "Centered Image"
            )
            Spacer(modifier = Modifier.height(44.dp))
            Text(
                text = stringResource(R.string.target_usage_time_end),
                fontSize = 20.sp,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFDBDAE7),
                )
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                stringResource(R.string.use_it_anymore, appName, appName),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF8D8D9F),
                )
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3D17D3)),
                onClick = {
                    // TODO : 닫기
                },
            ) {
                Text(
                    text = stringResource(R.string.close),
                    modifier = Modifier.padding(horizontal = 70.dp, vertical = 10.dp),
                    style = HmhTypography.titleMedium
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                stringResource(R.string.do_unlock),
                style = HmhTypography.titleSmall,
                color = WhiteText
            )
            Spacer(modifier = Modifier.height(38.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HMHAndroidTheme {
        LockScreen()
    }
}
