package android.bignerdranch.com
import java.io.Serializable

private fun <T> List<T>.rand() = shuffled().first()
private fun Int.roll() = (0 until this)
    .map { (1..6).toList().rand() }
    .sum()
    .toString()
private val firstName = listOf("Eli", "Alex", "Sophie")
private val lastName = listOf("Lightweaver", "Greatfoot", "Oakenfeld")
object CharacterGenerator {
    data class CharacterData(val name: String,
                             val race: String,
                             val dex: String,
                             val wis: String,
                             val str: String) : Serializable
    private fun name() = "${firstName.rand()} ${lastName.rand()}"
    private fun race() = listOf("dwarf", "elf", "human", "halfling").rand()
    private fun dex() = 4.roll()
    private fun wis() = 3.roll()
    private fun str() = 5.roll()
    fun generate() = CharacterData(name = name(),
        race = race(),
        dex = dex(),
        wis = wis(),
        str = str())
}