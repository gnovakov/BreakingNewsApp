package com.gnova.breakingnewsapp.ui

// Removes a couple unneeded characters from the publishedAt String, namely a T and a Z which are not needed to be displayed.
fun String?.replaceUnusedChars(
    oldCharOne: String, newCharOne: String, oldCharTwo: String, newCharTwo: String, ): CharSequence? {

    return this?.replace(oldCharOne, newCharOne)?.replace(oldCharTwo, newCharTwo)

}