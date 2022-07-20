package com.fphoenixcorneae.widget.text.method

import android.graphics.Rect
import android.text.*
import android.text.method.TransformationMethod
import android.view.View

/**
 * @desc：优雅的实现文字掩码
 * @date：2022/07/19 16:22
 */
abstract class ReplacementTransformationMethod : TransformationMethod, ReplacementMapping {

    override fun getTransformation(source: CharSequence, v: View?): CharSequence {
        /*
         * Short circuit for faster display if the text will never change.
         */
        if (source !is Editable) {
            /*
             * Check whether the text does not contain any of the
             * source characters so can be used unchanged.
             */
            if (!enableChanged(source)) {
                return source
            }
            if (source !is Spannable) {
                /*
                 * The text contains some of the source characters,
                 * but they can be flattened out now instead of
                 * at display time.
                 */
                return if (source is Spanned) {
                    SpannedString(SpannedReplacementCharSequence(source, this))
                } else {
                    ReplacementCharSequence(source, this).toString()
                }
            }
        }

        return if (source is Spanned) {
            SpannedReplacementCharSequence(source, this)
        } else {
            ReplacementCharSequence(source, this)
        }
    }

    override fun onFocusChanged(p0: View?, p1: CharSequence?, p2: Boolean, p3: Int, p4: Rect?) {
        // This callback isn't used.
    }

    /**
     * @desc：ReplacementCharSequence
     * @date：2022/07/19 17:06
     */
    private open inner class ReplacementCharSequence(
        private val mSource: CharSequence,
        private var mMapping: ReplacementMapping,
    ) : CharSequence, GetChars {

        override val length: Int
            get() = mSource.length

        override fun get(index: Int): Char {
            var c = mSource[index]
            c = mMapping.map(mSource, index, 0, c)
            return c
        }

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            val c = CharArray(endIndex - startIndex)
            getChars(startIndex, endIndex, c, 0)
            return String(c)
        }

        override fun toString(): String {
            val c = CharArray(length)
            getChars(0, length, c, 0)
            return String(c)
        }

        override fun getChars(start: Int, end: Int, dest: CharArray, off: Int) {
            TextUtils.getChars(mSource, start, end, dest, off)
            val offend = end - start + off
            for (i in 0 until offend) {
                val c = dest[i]
                dest[i] = mMapping.map(mSource, i + start, start, c)
            }
        }
    }

    /**
     * @desc：SpannedReplacementCharSequence
     * @date：2022/07/19 17:12
     */
    private open inner class SpannedReplacementCharSequence(
        private val mSpanned: Spanned,
        mapping: ReplacementMapping,
    ) : ReplacementCharSequence(mSpanned, mapping), Spanned {

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return SpannedString(this).subSequence(startIndex, endIndex)
        }

        override fun <T> getSpans(start: Int, end: Int, type: Class<T>): Array<T> {
            return mSpanned.getSpans(start, end, type)
        }

        override fun getSpanStart(tag: Any): Int {
            return mSpanned.getSpanStart(tag)
        }

        override fun getSpanEnd(tag: Any): Int {
            return mSpanned.getSpanEnd(tag)
        }

        override fun getSpanFlags(tag: Any): Int {
            return mSpanned.getSpanFlags(tag)
        }

        override fun nextSpanTransition(start: Int, end: Int, type: Class<*>?): Int {
            return mSpanned.nextSpanTransition(start, end, type)
        }
    }
}

/**
 * @desc：ReplacementMapping
 * @date：2022/07/19 16:58
 */
interface ReplacementMapping {
    fun enableChanged(source: CharSequence): Boolean

    fun map(source: CharSequence, index: Int, start: Int, original: Char): Char
}