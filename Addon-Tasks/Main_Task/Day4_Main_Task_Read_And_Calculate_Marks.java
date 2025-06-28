package Main_Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Day4_Main_Task_Read_And_Calculate_Marks {
	public static void main(String[] args) {
		// Use the full paths for input and output files
		String inputFile = "C:\\Users\\Hariharan\\eclipse-workspace\\JAVA\\src\\Main_Task\\marks.txt"; // Input marks file
		String outputFile = "C:\\Users\\Hariharan\\eclipse-workspace\\JAVA\\src\\Main_Task\\result.txt"; // Output results file

		// Use a TreeMap to store results sorted by student name
		Map<String, String> results = new TreeMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;

			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty())
					continue; // skip empty lines
				String[] parts = line.split(",");
				if (parts.length < 2) {
					System.out.println("Invalid input line (expecting name and marks): " + line);
					continue;
				}
				String name = parts[0].trim(); // student name
				double total = 0;
				int count = 0;
				boolean invalidMarksFound = false;

				for (int i = 1; i < parts.length; i++) {
					try {
						double mark = Double.parseDouble(parts[i].trim());
						total += mark;
						count++;
					} catch (NumberFormatException e) {
						System.out.println("Invalid mark '" + parts[i].trim() + "' for student " + name);
						invalidMarksFound = true;
						break;
					}
				}
				if (invalidMarksFound || count == 0) {
					results.put(name, "Error,Error");
					continue;
				}
				double average = total / count;

				// Store the result in the TreeMap
				results.put(name, total + "," + String.format("%.2f", average));
			}

			// Write results to the output file
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
				// Write header in the result file
				bw.write("Name,Total,Average");
				bw.newLine();

				// Write each entry in the sorted order
				for (Map.Entry<String, String> entry : results.entrySet()) {
					bw.write(entry.getKey() + "," + entry.getValue());
					bw.newLine();
				}
			}

			System.out.println("Results written to " + outputFile);

		} catch (IOException e) {
			System.err.println("Error processing files: " + e.getMessage());
		}
	}
}
