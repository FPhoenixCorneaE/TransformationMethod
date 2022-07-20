package com.fphoenixcorneae.widget.text.method

/**
 * @desc：UserNameTransformationMethod
 *       示例：李*、张*丰、慕容**
 * @date：2022/07/19 17:24
 */
class UserNameTransformationMethod private constructor() : ReplacementTransformationMethod() {

    override fun map(source: CharSequence, index: Int, start: Int, original: Char): Char {
        return if ((source.length < MAX_LENGTH && index in START_INDEX until END_INDEX)
            || (source.length >= MAX_LENGTH && index in START_INDEX +1 until END_INDEX + 2)
        ) {
            '*'
        } else {
            original
        }
    }

    override fun enableChanged(source: CharSequence): Boolean {
        return source.isNotEmpty()
    }

    companion object {
        private const val MAX_LENGTH = 4
        private const val START_INDEX = 1
        private const val END_INDEX = 2

        @Volatile
        private var sInstance: UserNameTransformationMethod? = null

        @Synchronized
        fun getInstance() = sInstance ?: synchronized(this) {
            sInstance ?: UserNameTransformationMethod().also { sInstance = it }
        }
    }
}

