package com.fphoenixcorneae.widget.text.method

/**
 * @desc：PhoneNumberTransformationMethod
 *       示例：138****8888
 * @date：2022/07/19 17:24
 */
class PhoneNumberTransformationMethod private constructor() : ReplacementTransformationMethod() {

    override fun map(source: CharSequence, index: Int, start: Int, original: Char): Char {
        return if (index in START_INDEX until END_INDEX) {
            '*'
        } else {
            original
        }
    }

    override fun enableChanged(source: CharSequence): Boolean {
        return source.isNotEmpty()
    }

    companion object {
        private const val START_INDEX = 3
        private const val END_INDEX = 7

        @Volatile
        private var sInstance: PhoneNumberTransformationMethod? = null

        @Synchronized
        fun getInstance() = sInstance ?: synchronized(this) {
            sInstance ?: PhoneNumberTransformationMethod().also { sInstance = it }
        }
    }
}

