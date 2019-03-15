val String.numVowels
    get() = count { "aeiouy".contains(it) }

fun String.addEnthusiasm(amount: Int = 1) = this + "!".repeat(amount)

fun <T> T.easyPrint() : T {
    println(this)
    return this
}

infix fun String?.printWithDefault(default: String) = print(this ?: default)

fun main(args: Array<String>) {
    "마드리갈이 그 건물에서 나왔습니다".easyPrint().addEnthusiasm().easyPrint()
    42.easyPrint()
    "How many vowels?".numVowels.easyPrint()

    val nullableString: String? = null
    nullableString printWithDefault "기본 문자열"
}