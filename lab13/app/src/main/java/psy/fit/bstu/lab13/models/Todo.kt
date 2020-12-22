
data class Todo (
    override var id: Int,
    val title: String,
    val completed: String,
) : IBaseModel {
    override fun toString(): String {
        return "${this.title} - ${this.completed}"
    }
}