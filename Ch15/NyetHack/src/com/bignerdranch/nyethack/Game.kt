package com.bignerdranch.nyethack

fun main() {
    Game.play()

    performCombat()
    performCombat("Ulrich")
    performCombat("Hildr", true)
}

object Game {
    private val player = Player("Madrigal")
    private var currentRoom: Room = TownSquare()

    private var worldMap = listOf(
        listOf(currentRoom, Room("Tavern"), Room("Back Room")),
        listOf(Room("Long Corridor"), Room("Generic Room")))

    init {
        println("방문을 환영합니다.")
        player.castFireball()
    }

    fun play() {
        while (true) {
            println(currentRoom.description())
            println(currentRoom.load())

            // 플레이어의 상태 출력
            printPlayerStatus(player)

            print("> 명령을 입력하세요: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println(
            "(Aura: ${player.auraColor()}) " +
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO"})"
        )
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            else -> commandNotFound()
        }

        private fun commandNotFound() = "적합하지 않은 명령입니다!"
    }

    private fun move(directionInput: String) =
        try {
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds) {
                throw IllegalStateException("$direction 쪽 방향이 범위를 벗어남.")
            }
            val newRoom = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, $direction 방향의 ${newRoom.name}로 이동했습니다."
        } catch (e: Exception) {
            "잘못된 방향임: $directionInput."
        }
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