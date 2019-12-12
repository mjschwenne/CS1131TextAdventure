package Framework;

public class NPC {
	private int x = 0;

	private int getRandomNumber() {
		return ( int )( Math.random( ) * ( ( 3 - 1 ) + 1 ) ) + 1;
	}

	public String combat( String weapon ) {
		switch( weapon ) {
			case "KNIFE":
				x = getRandomNumber();
				if ( x == 1 ) {
					return "tie";
				} else if ( x == 2 ) {
					return "loss";
				} else if ( x == 3 ) {
					return "win";
				} else {
					return null;
				}
			case "TORCH":
				x = getRandomNumber();
				if ( x == 1 ) {
					return "win";
				} else if ( x == 2 ) {
					return "tie";
				} else if ( x == 3 ) {
					return "loss";
				} else {
					return null;
				}
			case "BUBBLES":
				x = getRandomNumber();
				if ( x == 1 ) {
					return "loss";
				} else if ( x == 2 ) {
					return "win";
				} else if ( x == 3 ) {
					return "tie";
				} else {
					return null;
				}
			default:
				return "Invalid Command";
		}
	}
}