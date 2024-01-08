fun main(args: Array<String>) {
    val list = listOf<String>("a","b")
    val multableList = multableList<String>("a","b")
    multableList.add("c")
    val set = setOf("a","b","a")
    val multableSet = multableSet<String>("a","b")
    multableSet.add("a")
}