package com.fahad.testassignment.utils

import android.content.Context
import android.content.SharedPreferences

import com.fahad.testassignment.models.responses.results
import com.google.gson.Gson


class SharedPrefs {
    companion object {
      val SHARED_PREFS_FILE_NAME= "testAssignment"
        val News_Article = "News_Article"





        fun setArticleData(context: Context, newsArticle: results) {
            SharedPrefs.save(context, SharedPrefs.News_Article, Gson().toJson(newsArticle))
        }


        fun removeArticleData(context: Context) {
            SharedPrefs.removeKey(context, SharedPrefs.News_Article)

        }




        fun getArticleData(context: Context): results? {

            return if (SharedPrefs.getPrefs(context).contains(News_Article)) {
                Gson().fromJson<results>(SharedPrefs.getString(context, News_Article), results::class.java::class.java)

            } else null

        }




        //get the SharedPreferences object instance
        //create SharedPreferences file if not present


        fun getPrefs(context: Context): SharedPreferences {
            return context.getSharedPreferences(SHARED_PREFS_FILE_NAME, Context.MODE_MULTI_PROCESS)
        }



        //Strings
        fun save(context: Context, key: String, value: String) {
            getPrefs(context).edit().putString(key, value).commit()
        }


        fun getString(context: Context, key: String): String? {
            return getPrefs(context).getString(key, "")
        }






        fun removeKey(context: Context, key: String) {
            getPrefs(context).edit().remove(key).commit()
        }

    }
}