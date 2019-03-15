fun main(args: Array<String>) {
    var swordsJuggling: Int? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if (isJugglingProficient) {
        swordsJuggling = 2
    }

    try {
        proficiencyCheck(swordsJuggling)
        swordsJuggling = swordsJuggling!!.plus(1)
    } catch (e: Exception) {
        println(e)
    }

    println("$swordsJuggling 개의 칼로 저글링합니다!")
}

fun proficiencyCheck(swordsJuggling: Int?) {
    checkNotNull(swordsJuggling, { "플레이어가 저글링을 할 수 없음" })
}

class UnskilledSwordJugglerException() :
    IllegalStateException("플레이어가 저글링을 할 수 없음")