package pac;
public class Paczka {
		char from;
		char to;
		Integer value;
		@Override
		public String toString() {
			return "[ "+from+" -> "+to+" ]: "+value;
		}
}
