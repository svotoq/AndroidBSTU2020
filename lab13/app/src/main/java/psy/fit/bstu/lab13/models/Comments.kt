
data class Comments (
    override var id: Int,
    val name: String,
    val email: String,
    val body: String,
) : IBaseModel {
    override fun toString(): String {
        return "Name: ${this.name} Email: ${this.email} Body: ${this.body}"
    }
}