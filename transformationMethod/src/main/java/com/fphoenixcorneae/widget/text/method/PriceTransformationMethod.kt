package com.fphoenixcorneae.widget.text.method

/**
 * @desc：PriceTransformationMethod
 *       示例：******
 * @date：2022/07/19 17:24
 */
class PriceTransformationMethod private constructor(
    private val char: Char,
) : ReplacementTransformationMethod() {

    override fun map(source: CharSequence, index: Int, start: Int, original: Char): Char {
        return char
    }

    override fun enableChanged(source: CharSequence): Boolean {
        return source.isNotEmpty()
    }

    companion object {
        @Volatile
        private var sInstance: PriceTransformationMethod? = null

        @Synchronized
        fun getInstance(char: Char = '*', forceChange: Boolean = false) = if (forceChange) {
            PriceTransformationMethod(char)
        } else {
            sInstance ?: synchronized(this) {
                sInstance ?: PriceTransformationMethod(char).also { sInstance = it }
            }
        }
    }
}

