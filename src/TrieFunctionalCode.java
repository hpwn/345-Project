import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * TrieFunctionCode class runs a console based program to demonstrate a prefix
 * based auto-complete using Tries
 *
 * @authors Hayden Price, Kendra Jones, Nishant Athawale
 */

public class TrieFunctionalCode {
	public static void main(String args[]) {
		Trie word_data = new Trie();
		loadData("src/dict.txt", word_data);
		autocorrect(word_data);
	}

	static void autocorrect(Trie word_data) {
		while (true) {
			System.out.println("Enter a prefix to search or exit to exit the program");
			Scanner scan = new Scanner(System.in);
			String input = scan.nextLine();

			if (input.toLowerCase().equals("exit")) {
				scan.close();
				System.exit(0);
			} else {
				StringArray matchList = word_data.autocomplete(input);
				if (matchList.size() == 0) {
					System.out.println("No potential words found");
				} else {
					System.out.println("Following are the potential words");
					for (int i = 0; i < matchList.size(); i++) {
						System.out.println(matchList.get(i));
					}
				}

			}
		}

	}

	static void loadData(String fn, Trie trie) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fn));
			String line = reader.readLine();
			while (line != null) {
				String[] split = line.split(System.lineSeparator());
				if (split.length < 2) {
					trie.insert(split[0]);
				}
				line = reader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}