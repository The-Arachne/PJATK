package parkingGarage_MAIN;

import java.util.ArrayList;
import java.util.Timer;

public class ParkingGarage {
	
	static ArrayList<ParkingSpace> parkingSpaces;
	static Timer timer;
	public static void main(String[] args) {
		initiate();
		test();
		
		//Object Pool - powyzsza arrayList z TimerTaskami
		//StateDesign - package States i jego zawartosc (mozna rozszerzyc o metody bo string nie wyglada dobrze)
		//Mediator - nadrzedna instancja klasy TIMER do zarzadzania timerTaskami - taka wieza kontroli ruchu powietrznego :)
	}
	private static void test() {
		
		//czas nieznacznie moze sie rozjechac bo tutaj wykorzystany 
		//jest thread a tam timer i w innych miejscach te watki uruchamiany
		new Thread(()-> {
			int i=1;
			
			while(i<15) {
				try {
					System.out.println("godzina: "+i);
					switch (i) {
					case 1:
						System.out.println("\tPan wiesio chce zostawic auto na parkingu");
						System.out.println("\tw tym celu korzysta z aplikacji i sprawdza ktore miejsca sa wolne");
						parkingSpaces.get(3).parkingAction();
						System.out.println("\tna jego szczescie miejsce 3 bylo aktualnie wolne");
						System.out.println("\twiec je szybko \"zajal\" na \"4 godziny\" - tutaj 4 sec bo nie mamy tyle czasu :)");
						parkingSpaces.get(3).rentSpace(4);		
						break;
					case 3:
						System.out.println("\tW miedzyczasie w godzinach szczytu przyjechal pan czesiek i takze chcial zaparkowac swoj pojazd");
						System.out.println("\tniestety wszystkie miejsca byly zajete");
						parkingSpaces.get(3).parkingAction();
						System.out.println("\tw tym miejsce 3");
						System.out.println("\takurat zwolnilo sie inne miejsce wiec pan czeslaw je zajal na 10h");
						parkingSpaces.get(9).rentSpace(10);
						break;
					case 6:
						System.out.println("\tna koniec przyjechal pan kamil");
						parkingSpaces.get(3).parkingAction();
						System.out.println("\tale miejsce 3 nadal bylo zajete pomimo uniewaznienia biletu");
						break;
					case 7:
						System.out.println("\tooo wrocil pan wiesio");
						parkingSpaces.get(3).releaseSpace();
						System.out.println("\tna jego miejsce wjechal kto inny");
						parkingSpaces.get(3).parkingAction();
						break;
					}
					i++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			timer.cancel();
		}).start();
	}
	private static void initiate() {
		parkingSpaces=new ArrayList<ParkingSpace>();
		timer=new Timer();
		for(int i=1;i<=10;i++) {
			parkingSpaces.add(new ParkingSpace(i,timer));
		}
	}
}
