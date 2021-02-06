public class Paczka {
		int nrPrzedmiotu;
		Integer size;
		Integer value;
		@Override
		public String toString() {
			return "[ "+nrPrzedmiotu+" ]-> {"+size+", "+value+"}";
		}
}
