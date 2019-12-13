package Framework;

public class NPC {
	private int x = 0;

	public String getRandomWeapon() {
		switch((int)( Math.random( ) * 2 + 1)) {
			default:
				return "KNIFE";
			case 2:
				return "BUBBLES";
			case 3:
				return "TORCH";
		}
	}

	public String combat(PC pc, String weapon, String enemyWeapon) {
		switch( weapon ) {
			case "KNIFE":
				if(enemyWeapon == "BUBBLES") {
					pc.inCombat = false;
					pc.beastSlain = true;
					return "You quickly stab the beast.";
				} else if(enemyWeapon == "TORCH") {
					pc.inCombat = false;
					pc.gameOver = true;
					return "You have been burned to a crisp. Use RESET to restart or QUIT to disconnect.";
				} else {
					return "You both clash your weapons. You are in combat. Enter FIGHT to pick a weapon.";
				}
			case "TORCH":
				if(enemyWeapon == "KNIFE") {
					pc.inCombat = false;
					pc.beastSlain = true;
					return "You burned the beast to a crisp.";
				} else if(enemyWeapon == "BUBBLES") {
					pc.inCombat = false;
					pc.gameOver = true;
					return "Your light has been put out. Use RESET to restart or QUIT to disconnect.";
				} else {
					return "You both now have a bigger fire. You are in combat. Enter FIGHT to pick a weapon.";
				}
			case "BUBBLES":
				if(enemyWeapon == "TORCH") {
					pc.inCombat = false;
					pc.beastSlain = true;
					return "You dowsed the beast's flame.";
				} else if(enemyWeapon == "KNIFE") {
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