fun main() {
	val footballPlayer1 = FootBallPlayer("player 1")
    val footballPlayer2 = FootBallPlayer("player 2")
    val baseballPlayer1 = BaseBallPlayer("player 3")
    val baseballPlayer2 = BaseBallPlayer("player 4")
    
    val footballTeam = Team<FootBallPlayer>("FootBall Team",mutableListOf(footballPlayer1))
    val baseballTeam = Team<BaseBallPlayer>("BaseBall Team",mutableListOf(baseballPlayer1))
    footballTeam.addPlayer(footballPlayer2)
    baseballTeam.addPlayer(baseballPlayer2)
}
class Team<T:Player>(val name: String,val players:MutableList<T>) {
    fun addPlayer(player: T) {
        if(players.contains(player)) {
            println("${player.name} already exists")
        }else {
            println("${player.name} got added to the team '$name'")
            players.add(player)
        }
    }
}

open class Player(val name:String) 
class BaseBallPlayer(name:String):Player(name)
class FootBallPlayer(name:String):Player(name)