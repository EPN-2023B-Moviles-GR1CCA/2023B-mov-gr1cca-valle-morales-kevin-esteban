package com.example.deber03.data

import com.example.deber03.R
import com.example.deber03.model.Post
class Datasource {

    fun loadPosts(): List<Post> {
        return listOf(
            Post(1,
                R.string.profile_name,
                R.string.profile_dec,
                R.string.post_timestamp,
                R.drawable.foto_kevin,
                R.string.post_content,
                R.string.social_count,
                R.string.comment_count,
                "null",
                "null"
            ),
            Post(2,
                R.string.profile_name_1,
                R.string.profile_dec_1,
                R.string.post_timestamp_1,
                R.drawable.google,
                R.string.post_content_1,
                R.string.social_count_1,
                R.string.comment_count_1,
                R.drawable.post1_google.toString(),
                "null"
            ),
            Post(2,
                R.string.profile_name_3,
                R.string.profile_dec_3,
                R.string.post_timestamp_3,
                R.drawable.samsung,
                R.string.post_content_3,
                R.string.social_count_3,
                R.string.comment_count_3,
                R.drawable.post2_samsung.toString(),
                "null"
            ),
            Post(1,
                R.string.profile_name_2,
                R.string.profile_dec_2,
                R.string.post_timestamp_2,
                R.drawable.apple,
                R.string.post_content_2,
                R.string.social_count_2,
                R.string.comment_count_2,
                "null",
                "null"
            ),
            Post(3,
                R.string.profile_name_4,
                R.string.profile_dec_4,
                R.string.post_timestamp_4,
                R.drawable.stackbuilders,
                R.string.post_content_4,
                R.string.social_count_4,
                R.string.comment_count_4,
                "null",
                R.raw.video_1.toString()
            ),
        )
    }
}