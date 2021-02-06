package States;

public class UnOccupiedState implements SpaceState{

	@Override
	public String action() {
		return "UnOccupied - Reserve NOW";
	}
}
