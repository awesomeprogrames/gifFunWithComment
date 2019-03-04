/*
 * Copyright (C) guolin, Suzhou Quxiang Inc. Open source codes for study only.
 * Do not use for commercial purpose.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.quxianggif

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.quxianggif.core.GifFun
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import org.litepal.LitePal

/**
 * GifFun自定义Application，在这里进行全局的初始化操作。
 *
 * @author guolin
 * @since 17/2/15
 */
open class GifFunApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GifFun.initialize(this)
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null)
        MobclickAgent.setCatchUncaughtExceptions(false) // 关闭友盟的崩溃采集功能，使用腾讯Bugly
        LitePal.initialize(this)
    }

    // lj:
    /**
     * 由于Android平台的Java虚拟机Dalvik执行Dex(class文件转换的可被虚拟机执行的文件)程序时，使用的是short类型来索引Dex
     * 文件中的方法。也就意味着单个Dex文件的方法总数限制在65535个.
     *
     * 当工程中的方法数超限时，就要用multidex来生成多个dex来突破限制。
     *
     * 1. 先在gradle中配置multidex为true
     *     defaultConfig {
     *              ...
     *              multiDexEnabled true
     *              ...
     *     }
     *
     * 2. 配置Application的name为MultiDexApplication或者自定义的Application继承MultiDexApplication。
     *   如果不能修改父类，则必须重写attachBaseContext(...)方法，并调用MultiDex.install(this)
     * */
    //    MultiDexApplication
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}