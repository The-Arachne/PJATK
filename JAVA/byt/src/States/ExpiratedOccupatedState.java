package States;

public class ExpiratedOccupatedState implements SpaceState{
	@Override
	public String action() {
		return "the notification in SMS about expirated parking space was sended";
	}
}
