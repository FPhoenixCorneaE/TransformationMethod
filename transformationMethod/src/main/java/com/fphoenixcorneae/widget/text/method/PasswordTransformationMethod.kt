package com.fphoenixcorneae.widget.text.method

/**
 * @desc：PasswordTransformationMethod
 *       示例：********
 * @date：2022/07/19 17:24
 */
class PasswordTransformationMethod private constructor(
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
        private var sInstance: PasswordTransformationMethod? = null

        @Synchronized
        fun getInstance(char: Char = '*') = sInstance ?: synchronized(this) {
            sInstance ?: PasswordTransformationMethod(char).also { sInstance = it }
        }
    }
}

