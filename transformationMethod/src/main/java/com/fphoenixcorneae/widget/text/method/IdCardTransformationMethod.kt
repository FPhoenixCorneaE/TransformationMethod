package com.fphoenixcorneae.widget.text.method

/**
 * @desc：IdCardTransformationMethod
 *       示例：2101************80
 * @date：2022/07/20 13:43
 */
class IdCardTransformationMethod private constructor() : ReplacementTransformationMethod() {

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
        private const val START_INDEX = 4
        private const val END_INDEX = 16

        @Volatile
        private var sInstance: IdCardTransformationMethod? = null

        @Synchronized
        fun getInstance() = sInstance ?: synchronized(this) {
            sInstance ?: IdCardTransformationMethod().also { sInstance = it }
        }
    }
}

