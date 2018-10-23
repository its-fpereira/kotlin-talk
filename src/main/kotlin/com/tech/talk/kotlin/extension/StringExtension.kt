package com.tech.talk.kotlin.extension

import java.text.Normalizer
import java.util.regex.Pattern

private val VALID_CHARACTERS = Pattern.compile("[^a-z\\d\\-]", 2)
private val WHITESPACE = Pattern.compile("(\\s+|_+)")
private val DASH_REPLACE_PATTERN = Pattern.compile("(^-+|-+$)")
private val MULTIPLE_DASHES_PATTERN = Pattern.compile("-+")

fun String.toSlug(): String {
    var value = this
    return if (value.trim { it <= ' ' } != "") {
        value = value.toLowerCase()
        value = value.replace("\\.".toRegex(), " ")
        value = value.replace("\\+".toRegex(), " ")
        value = WHITESPACE.matcher(value).replaceAll("-")
        value = value.replace("&".toRegex(), "e")
        value = DASH_REPLACE_PATTERN.matcher(value).replaceAll("")
        value = Normalizer.normalize(value, Normalizer.Form.NFD)
        value = VALID_CHARACTERS.matcher(value).replaceAll("")
        value = MULTIPLE_DASHES_PATTERN.matcher(value).replaceAll("-")
        value
    } else {
        ""
    }
}
