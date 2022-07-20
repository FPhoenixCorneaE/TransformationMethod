# TransformationMethod

## 优雅的实现文字掩码

[![](https://jitpack.io/v/FPhoenixCorneaE/TransformationMethod.svg)](https://jitpack.io/#FPhoenixCorneaE/TransformationMethod)

<div align="center"> 
    <img src="https://github.com/FPhoenixCorneaE/TransformationMethod/blob/main/images/picture_hide.png" width="320" height="480" align="top" />
    <img src="https://github.com/FPhoenixCorneaE/TransformationMethod/blob/main/images/picture_show.png" width="320" height="480" align="top" style="margin-left:15px" />
</div>

### How to include it in your project:

**Step 1.** Add the JitPack repository to your build file.

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        maven {
            url "https://jitpack.io"
        }
    }
}
```

**Step 2.** Add the dependency.

```groovy
dependencies {
    implementation("com.github.FPhoenixCorneaE:TransformationMethod:$latest")
}
```

### 特性

* **PhoneNumberTransformationMethod**：手机号掩码处理
* **UserNameTransformationMethod**：用户名掩码处理
* **PriceTransformationMethod**：金额掩码处理
* **IdCardTransformationMethod**：身份证掩码处理
* **PasswordTransformationMethod**：密码掩码处理