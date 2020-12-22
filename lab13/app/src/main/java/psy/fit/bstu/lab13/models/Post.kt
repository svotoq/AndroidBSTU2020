
data class Post (
    override var id: Int,
    val userId: Int,
    val title: String,
    val body: String
) : IBaseModel {
    override fun toString(): String {
        return "Title: ${this.title}, Body: ${this.body}"
    }
}