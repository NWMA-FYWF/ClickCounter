# ClickCounter

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0+-blue?logo=kotlin)](https://kotlinlang.org)
[![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-2.0+-pink?logo=android)](https://developer.android.com/jetpack/compose)
[![License](https://img.shields.io/badge/License-MIT-green)](LICENSE)

---

## 🌟 项目简介

ClickCounter 是一个简洁而功能完备的计数器应用，灵感来源于敲木鱼手表游戏。作为 Jetpack Compose 初学者的实践项目，它展示了现代 Android 开发的最佳实践。

---

## ✨ 主要功能

* **点击计数** - 轻点按钮增加计数，简单直观
* **数据持久化** - 使用 DataStore 技术，确保计数在应用重启后依然保留
* **一键重置** - 底部左侧按钮可将计数归零
* **现代化 UI** - 采用 Jetpack Compose 构建，界面简洁美观
* **Material Design 3** - 使用现代设计语言及动态颜色 (Material You)
* **响应式设计** - 适配不同屏幕尺寸的设备
* **自适应主题** - 支持深色/浅色模式，跟随系统设置

---

## 🚀 快速开始

### 环境要求
* Android Studio
* Android SDK API 级别 24 或更高
* Kotlin 2.0+
* Gradle 8.9+

### 安装步骤

1. 克隆项目：
   ```bash
   git clone https://github.com/NWMA-FYWF/ClickCounter.git
   ```

2. 在 Android Studio 中打开项目：
   - 启动 Android Studio
   - 选择 "Open an existing project"
   - 导航到项目文件夹并选择

3. 同步 Gradle 依赖：
   - 点击 "Sync Now" 或在 Terminal 中运行 `./gradlew build`

4. 运行应用：
   - 连接 Android 设备或启动模拟器
   - 点击 "Run" 按钮或按 Shift+F10

---

## 📱 使用说明

1. 启动应用后，你会看到当前计数显示在屏幕中央
2. 点击中央的 **+** 按钮增加计数
3. 点击左下角的重置按钮将计数归零
4. 计数会自动保存，即使关闭应用后重启也不会丢失
5. 数据通过 Android 的 DataStore 技术持久化存储

---

## 🔧 技术栈

* **Kotlin** - 现代 Android 开发语言
* **Jetpack Compose** - 现代 UI 框架
* **DataStore** - 数据持久化存储解决方案
* **Material Design 3** - 设计语言和组件库
* **Dynamic Colors** - Android 12+ 动态颜色支持
* **Coroutines** - 异步编程支持

---

## 📄 License

MIT License - 随意使用、修改、分发（但请保留原作者信息）🙏

---

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来帮助改进这个项目！