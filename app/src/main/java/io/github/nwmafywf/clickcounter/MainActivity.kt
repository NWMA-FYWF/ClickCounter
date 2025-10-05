package io.github.nwmafywf.clickcounter

import android.content.Context
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
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.github.nwmafywf.clickcounter.ui.theme.ClickCounterTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource

// 为 Context 添加 DataStore 扩展属性
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "counter_preferences")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                CounterApp() // 启动 Compose UI
            }
        }
    }
}

@Composable
fun CounterApp() {
    // 获取 Context 用于访问 DataStore
    val context = androidx.compose.ui.platform.LocalContext.current
    val scope = androidx.compose.runtime.rememberCoroutineScope()

    // 定义用于存储计数的键
    val counterKey = intPreferencesKey("counter_key")

    // 使用 State 来持有当前计数
    var count by remember { mutableStateOf(0) }

    // 从 DataStore 读取初始计数
    LaunchedEffect(Unit) {
        val preferences = context.dataStore.data.first()
        count = preferences[counterKey] ?: 0
    }

    // UI 布局
    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 显示计数
            Text(
                text = "$count",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 52.sp, // 自定义字体大小
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 点击增加计数的按钮
            Button(
                onClick = {
                    count++
                    // 同时更新 DataStore
                    scope.launch {
                        context.dataStore.edit { preferences ->
                            preferences[counterKey] = count
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.size(64.dp), // 设置正方形大小
                contentPadding = PaddingValues(0.dp) // 移除默认内边距
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_48px),
                    contentDescription = "This is a add icon" // 用于无障碍访问，必填
                )
            }
        }

        // 点击重置计数的按钮
        Button(
            onClick = {
                count = 0
                scope.launch {
                    context.dataStore.edit { preferences ->
                        preferences[counterKey] = count
                    }
                }
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .size(42.dp).align(Alignment.BottomStart),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.delete_history_48px),
                contentDescription = "This is a reset icon"
            )
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
