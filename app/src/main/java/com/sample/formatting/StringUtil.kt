package com.sample.formatting

object StringUtil {
    @JvmStatic
    fun formatTitle(text: String?) : String {
        text?.let {
            val delim = " - "
            val list = text.split(delim)
            return list[0]
        }

        return ""
    }

    @JvmStatic
    fun formatDescription(text: String?) : String {
        text?.let {
            val delim = " - "
            val list = text.split(delim)
            return list[1]
        }
        return ""
    }
}