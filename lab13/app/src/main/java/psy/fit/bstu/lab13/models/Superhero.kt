
data class Superhero (
    override var id: Int,
    val name: String,
    val realname: String,
    val team: String,
    val firstappearance: Int,
    val createdby: String,
    val publisher: String,
    val bio: String
) : IBaseModel {
    override fun toString(): String {
        return "Name: ${this.name} Real name: ${this.realname} Team: ${this.team}"
    }
}