package Framework;

/**
 * This method handles battle interacts between the player and the "beast"
 */
public class NPC {
	/**
	 * Determines which weapon is going to be used by the "beast"
	 * @return Random weapon
	 */
	public String getRandomWeapon() {
		switch((int)((Math.random() * 2) + 1)) {
			default:
				return "KNIFE";
			case 2:
				return "BUBBLES";
			case 3:
				return "TORCH";
		}
	}

	/**
	 * Takes in the player, weapon, and enemy weapon and returns the appropriate battle interaction.
	 * @param pc
	 * @param weapon
	 * @param enemyWeapon
	 * @return battle interaction
	 */
	public String combat(PC pc, String weapon, String enemyWeapon) {
		switch( weapon ) {
			case "KNIFE":
				if(enemyWeapon.equals("BUBBLES")) {
					pc.inCombat = false;
					pc.beastSlain = true;
					return "You quickly stab the beast. As the beast exhales its last breath, the live in the crypt seems to leave in a one final sigh.";
				} else if(enemyWeapon.equals("TORCH")) {
					pc.inCombat = false;
					pc.gameOver = true;
					return "You have been burned to a crisp. Use RESET to restart or QUIT to disconnect.";
				} else {
					return "You both clash your weapons. You are in combat. Enter FIGHT to pick a weapon.";
				}
			case "TORCH":
				if(enemyWeapon.equals("KNIFE")) {
					pc.inCombat = false;
					pc.beastSlain = true;
					return "You burned the beast to a crisp. As the beast exhales its last breath, the live in the crypt seems to leave in a one final sigh.";
				} else if(enemyWeapon.equals("BUBBLES")) {
					pc.inCombat = false;
					pc.gameOver = true;
					return "Your light has been put out. Use RESET to restart or QUIT to disconnect.";
				} else {
					return "You both now have a bigger fire. You are in combat. Enter FIGHT to pick a weapon.";
				}
			case "BUBBLES":
				if(enemyWeapon.equals("TORCH")) {
					pc.inCombat = false;
					pc.beastSlain = true;
					return "You dowsed the beast's flame. As the beast exhales its last breath, the live in the crypt seems to leave in a one final sigh.";
				} else if(enemyWeapon.equals("KNIFE")) {
					pc.inCombat = false;
					pc.gameOver = true;
					return "The beast pops your bubble. Use RESET to restart or QUIT to disconnect.";
				} else {
					return "Your bubbles just bounce off each other.";
				}
			default:
				return "Invalid Command";
		}
	}

	/**
	 * Returns the sign of what weapon the monster will use.
	 * @param weapon
	 * @return weapon selection dialog.
	 */
	public String monsterWeaponDialog(String weapon) {
		switch (weapon) {
			case "TORCH":
				return "The Beast looks as if it wants you to burn.";
			case "KNIFE":
				return "The Beast wants to fillet you.";
			case "BUBBLES":
				return "The Beast wants you to float away";
			default:
				return "The Beast wants to kill you.";
		}
	}
}