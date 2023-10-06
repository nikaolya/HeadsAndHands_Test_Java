package game;

import java.util.Random;

public class Game {

	private Creature attacker;
	private Creature opponent;

	public void action(Creature fighter) {
		// Randomly chose which of the fighters will start: the first one will be the attacker and the second one its opponent
		Random random = new Random();
		if (random.nextInt(51) > 25) {
			attacker = fighter;
		} else {
			attacker = fighter.getOpponent();
		}
		opponent = attacker.getOpponent();

		System.out.println("\nThe fight between " + attacker.getCreatureType() + " and " + opponent.getCreatureType() + " is started.");

		// Continue to fight until one of the creatures dies
		while (attacker.getHealth() > 0 && opponent.getHealth() > 0) {
			printCurrentGameState();
			attacker.attack();
			opponent.selfHealing();
			// The opponent takes the next step
			attacker = opponent;
			opponent = attacker.getOpponent();
		}
		printCurrentGameState();
	}

	private void printCurrentGameState() {
		if (checkIfDead(attacker) || checkIfDead(opponent))
			return;

		System.out.printf("%17s %n", "HEALTH");
		System.out.printf("%-10s %-5.2f %n", attacker.getCreatureType().getType().toUpperCase(), attacker.getHealth());
		System.out.printf("%-10s %-5.2f %n%n", opponent.getCreatureType().getType().toUpperCase(), opponent.getHealth());
	}

	// Want to print a message when either of the fighters is dead
	private boolean checkIfDead(Creature creature) {
		if (creature.getHealth() <= 0) {
			creature.setDead(true);
			System.out.println(creature.getCreatureType() + " is dying... OMG. It is dead.");
			System.out.println("The game is over.\n");
		}
		return creature.isDead();
	}
}
