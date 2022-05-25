package com.test.automation.LetGetQAed.GenericUtilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadWriteCSV {
	static BufferedReader br;
	static BufferedWriter bw;
	static FileReader fr;
	static FileWriter fw;
	static String csvPath;

	public static List<String> readDetailsFromCSVFile(String input) throws IOException {
		List<String> rowContent = new ArrayList<>();

		try {
			csvPath = System.getProperty("user.dir") + File.separator + input + ".csv";

			fr = new FileReader(csvPath);
			br = new BufferedReader(fr);

			br.readLine();
			String readLine;
			while ((readLine = br.readLine()) != null) {
				rowContent.add(readLine);

			}
		} catch (Exception e) {
			System.out.println("File Not Found");
		} finally {
			if (br != null) {
				br.close();
			}
			if (fr != null) {
				fr.close();
			}
		}

		return rowContent;
	}

	public static void writeDetailsIntoCSVFile(Object obj, String input) throws IOException {

		csvPath = System.getProperty("user.dir") + File.separator + input + ".csv";

		try {
			fw = new FileWriter(csvPath, true);
			bw = new BufferedWriter(fw);

			String rowContent = obj.toString();
			bw.write(rowContent + "\n");

		} finally {
			if (bw != null) {
				bw.close();
			}
			if (fw != null) {
				fw.close();
			}

		}

	}

	public static void writeDetailsIntoCSVFileAfterDeletingRecord(List<String> details, String input)
			throws IOException {

		csvPath = System.getProperty("user.dir") + File.separator + input + ".csv";

		try {
			fw = new FileWriter(csvPath);
			bw = new BufferedWriter(fw);

			bw.write("headers");

			for (String rowContent : details) {
				bw.write(rowContent + "\n");
			}

		} finally {
			if (bw != null) {
				bw.close();
			}
			if (fw != null) {
				fw.close();
			}

		}
	}
}
