
data class User (
    override var id: Int,
    val name: String,
    val username: String,
    val website: String
) : IBaseModel {
    override fun toString(): String {
        return "${this.username} - ${this.name}"
    }
}