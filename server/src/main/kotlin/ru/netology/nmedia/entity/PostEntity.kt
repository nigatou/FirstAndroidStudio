package ru.netology.nmedia.entity

import ru.netology.nmedia.dto.Post
import javax.persistence.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
data class PostEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    var author: String,
    var content: String,
    var published: String,
    var likes: Int = 0,
    var shares: Int = 0,
    var likedByMe: Boolean,
    var sharedByMe: Boolean,
    var views: Int = 0,
    val video: String
) {
    fun toDto() =
        Post(id, author, content, published, likes, shares, likedByMe, sharedByMe, views, video)

    companion object {
        fun fromDto(dto: Post) = PostEntity(
            dto.id,
            dto.author,
            dto.content,
            dto.published,
            dto.likes,
            dto.shares,
            dto.likedByMe,
            dto.sharedByMe,
            dto.views,
            dto.video
        )
    }
}