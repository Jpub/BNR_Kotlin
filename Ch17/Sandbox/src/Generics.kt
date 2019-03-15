class LootBox<T : Loot>(vararg item: T) {
    var open = false
    private var loot: Array<out T> = item

    operator fun get(index: Int): T? = loot[index].takeIf { open }

    fun fetch(item: Int): T? {
        return loot[item].takeIf { open }
    }

    fun <R> fetch(item: Int, lootModFunction: (T) -> R): R? {
        return lootModFunction(loot[item]).takeIf { open }
    }
}

open class Loot(val value: Int)

class Fedora(val name: String, value: Int) : Loot(value)

class Coin(value: Int) : Loot(value)

fun main(args: Array<String>) {
    val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("평범한 중절모", 15), // 제일 끝에 쉼표 추가
                                        Fedora("눈부신 자주색 중절모", 25))
    val lootBoxTwo: LootBox<Coin> = LootBox(Coin(15))

    lootBoxOne.open = true

    lootBoxOne.fetch(1)?.run {
        println("$name 를 LootBox에서 꺼냈습니다!")
    }

    val coin = lootBoxOne.fetch(0) {
        Coin(it.value * 3)
    }
    coin?.let { println(it.value) }

    val fedora = lootBoxOne[1]
    fedora?.let { println(it.name) }
}