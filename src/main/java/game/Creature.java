package game;

import java.util.ArrayList;
import java.util.Random;

public class Creature implements CreatureActions {

	private Random random = new Random();

	protected CreatureType creatureType;
	private Creature opponent; // текущий враг
	protected int attack; // атака
	protected int defense; // защита
	private double health; // здоровье
	private int numberOfSelfHealing; // количество исцелений
	protected int[] damage; // урон
	private boolean isDead;

	public Creature(int health) {
		numberOfSelfHealing = 4;
		isDead = false;
		this.health = health;
	}

	private void setOpponent(Creature opponent) {
		this.opponent = opponent;
	}

	public int getDefense() {
		return defense;
	}

	public Creature getOpponent() {
		return opponent;
	}

	public double getHealth() {
		return health;
	}

	private void setHealth(double health) {
		this.health = health;
	}

	public CreatureType getCreatureType() {
		return creatureType;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isDead() {
		return isDead;
	}

	@Override
	public void identifyEnemy(Creature currentEnemy) {
		setOpponent(currentEnemy);
		currentEnemy.setOpponent(this);
	}

	@Override
	public void attack() {
		if (isSuccessful()) {
			System.out.println(creatureType + " attacks successfully.");
			int damageDone = countDamage();
			opponent.setHealth(opponent.getHealth() - damageDone);
			System.out.println("damage: " + damageDone);
		} else {
			System.out.println("The attack of the " + creatureType + " was unsuccessful.");
		}
	}

	@Override
	public void selfHealing() {
		if (health > 0 && numberOfSelfHealing > 0) {
			double addition = (double) Math.round(health * 30) / 100;
			System.out.println(creatureType + " heals itself successfully. health + " + addition);
			health += addition;
			numberOfSelfHealing--;
		}
	}

	private boolean isSuccessful() {
		int modifier = countAttackModifier();
		ArrayList<Integer> dice = new ArrayList<>(modifier);
		for (int i = 0; i < modifier; i++) {
			dice.add(random.nextInt(6) + 1);
		}
		return dice.contains(5) || dice.contains(6);
	}

	private int countAttackModifier() {
		int modifier = attack - opponent.getDefense() + 1;
		return Math.max(modifier, 1); // at least one die is always rolled
	}

	private int countDamage() {
		return random.nextInt(damage[1] + 1) + damage[0];
	}
}
