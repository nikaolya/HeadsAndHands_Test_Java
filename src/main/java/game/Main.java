package game;

public class Main {
	public static void main(String[] args) {
		Creature player = new Player(30);
		Creature monster = new Monster(20);

		player.identifyEnemy(monster);
		Game game = new Game();
		game.action(player);
	}
}
