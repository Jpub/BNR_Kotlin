package com.bignerdranch.nyethack

fun main() {
    val player = Player("Madrigal")
    player.castFireball()

    // 플레이어의 상태 출력
    printPlayerStatus(player)

    performCombat()
    performCombat("Ulrich")
    performCombat("Hildr", true)
}

private fun printPlayerStatus(player: Player) {
    println(
        "(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
    )
    println("${player.name} ${player.formatHealthStatus()}")
}

fun performCombat() {
    println("적군이 없다!")
}
fun performCombat(enemyName: String) {
    println("$enemyName 과 전투를 시작함.")
}
fun performCombat(enemyName: String, isBlessed: Boolean) {
    if (isBlessed) {
        println("$enemyName 과 전투를 시작함. 축복을 받음!")
    } else {
        println("$enemyName 과 전투를 시작함.")
    }
}