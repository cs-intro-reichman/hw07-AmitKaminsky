
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
	return str.substring(1, str.length());
	}

	public static char head(String str) {
		char firstChar = str.charAt(0);
		return firstChar;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word2.length() == 0) return word1.length(); 
		if (word1.length() == 0) return word2.length(); 
		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			int compTail1 = levenshtein(tail(word1), word2);
			int compTail2 = levenshtein(word1, tail(word2));
			int compTails = levenshtein(tail(word1), tail(word2));
			int checkMin = Math.min(Math.min(compTail1, compTail2), compTails);
			return 1 + checkMin;
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		int i = 0;
		while (!in.isEmpty()) {
			dictionary[i] = in.readLine();
			i++;			
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minDistance = threshold + 1;
		String correctWord = "";
		for(int i = 0; i < dictionary.length; i++){
			int levDistance = levenshtein(word, dictionary[i]);

			if (levDistance == 0) {
				return word;
			}
			
			else if (levDistance < minDistance) {
				correctWord = dictionary[i];
				minDistance = levDistance;
				} 
			}
		if (minDistance <= threshold){
			return correctWord;
		} else return word;
	}

}
