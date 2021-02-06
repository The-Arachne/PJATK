package States;

public class OccupiedState implements SpaceState{

	@Override
	public String action() {
		return "Occupied - Wait until spot will be free";
	}

}
