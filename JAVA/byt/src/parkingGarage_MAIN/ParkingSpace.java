package parkingGarage_MAIN;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

import States.*;

public class ParkingSpace extends TimerTask {
	int ID;
	Timer timer;
	LocalDateTime rentDate;
	SpaceState state;
	
	public ParkingSpace(int ID, Timer staticTimer) {
		this.ID=ID;
		timer=staticTimer;
		state=new UnOccupiedState();
	}
	public void rentSpace(int addHours) {
		if(state.getClass().equals(UnOccupiedState.class)) {
			state=new OccupiedState();
			rentDate=LocalDateTime.now();
			timer.schedule(this, addHours*1000);
			System.out.println("\tzajales miejsce: "+(ID-1));
		}
	}
	public void releaseSpace() {
		state=new UnOccupiedState();
	}
	public void parkingAction() {
		System.out.println("\t--"+state.action());
	}
	@Override
	public void run() {
		System.out.println("\tParking rent for: "+(this.ID-1) +" has expired");
		state=new ExpiratedOccupatedState();
	}
}
