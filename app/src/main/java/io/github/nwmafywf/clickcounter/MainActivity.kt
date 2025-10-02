package io.github.nwmafywf.clickcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.nwmafywf.clickcounter.ui.theme.ClickCounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           CounterApp() // 启动 Compose UI
        }
    }
}

@Composable
fun CounterApp() {
    // 声明 count 是可变状态，且初始值为 0
    var count by remember { mutableStateOf(0) }

    // UI 布局
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 显示文本
        Text(
            text = "CounterApp",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 显示计数
        Text(
            text = "Count: $count",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 点击增加计数的按钮
        Button(onClick = { count++ }) {
            Text("Click here")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    // 使用 Material 3 主题包裹预览
    MaterialTheme {
        CounterApp()
    }
}
