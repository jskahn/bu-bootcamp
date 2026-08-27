import java.io.*; 
import java.util.ArrayList;
 
public class GradeAnalyzer {    

    public static void main(String[] args) {
	
	String outputFile = null;
	if (args.length > 0) {
            outputFile = args[0];
	}
	if (outputFile == null || outputFile.length() == 0) {
	    outputFile = "report.txt";
	}
        
	// Step 1: read scores from file
	String fileName = "scores.txt";
	ArrayList<Integer> scores = readScores(fileName);
        

        // Step 2: calculate statistics
        ArrayList<Integer> cleanedScores = new ArrayList<>();
        int errCount = 0;        
        Integer min = Integer.MAX_VALUE;
        Integer max = Integer.MIN_VALUE;
        for (int i = 0; i < scores.size(); i++) {
            Integer score = scores.get(i);

	    if (score < 0) {
                errCount++;
                continue;
            }
	    cleanedScores.add(score);
            
            if (score < min) {
                min = score;
            }
            
            if (score > max) {
                max = score;
            }
        }
	double avg = calculateAverage(cleanedScores);

        // Step 3: write and print report
	writeReport(cleanedScores, avg, max, min, errCount, outputFile);
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
	ArrayList<Integer> scores = new ArrayList<>();
 
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
    		    Integer score = Integer.valueOf(line);
		    scores.add(score);
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Current line text input cannot be turned into a number: \"" + line + "\"");
		    scores.add(-1);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }

        return scores;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) return 0.0;

	int totalScores = 0;
	for (int i = 0; i < scores.size(); i++) {
	   totalScores += scores.get(i);
	}

        return (double) (totalScores / scores.size());
    }
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low, int errCount,
                                   String outputFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("=== Grade Analysis Report ===");
            System.out.println("=== Grade Analysis Report ===");
            writer.newLine();
            writer.write("Total scores processed: " + scores.size());
            System.out.println("Total scores processed: " + scores.size());
            writer.newLine();
            writer.write("Invalid lines skipped: " + errCount);
            System.out.println("Invalid lines skipped: " + errCount);
	    System.out.println();
	    writer.newLine();
            writer.newLine();
 	    writer.write("Average score: " + avg);
 	    System.out.println("Average score: " + avg);
            writer.newLine();
            writer.write("Highest score: " + high);
            System.out.println("Highest score: " + high);
	    writer.newLine();
	    writer.write("Lowest score: " + low);
	    System.out.println("Lowest score: " + low);
	    System.out.println();
            writer.newLine();
            writer.newLine();

	    int nineties = 0;
	    int eighties = 0;
  	    int seventies = 0;
	    int sixties = 0;
	    int belowSixty = 0;
	    for (int i = 0; i < scores.size(); i++) {
               int score = scores.get(i);
               if (score >= 90) {
                   nineties++;
	       } else if (score >= 80) {
	           eighties++;
  	       } else if (score >= 70) {
	           seventies++;
	       } else if (score >= 60) {
	           sixties++;
	       } else {
	           belowSixty++;
	       }
            }

            writer.write("Grade distributions:");
            System.out.println("Grade distributions:");
            writer.newLine();
	    writer.write("   A (90-100): " + nineties);
	    System.out.println("   A (90-100): " + nineties);
	    writer.newLine();
	    writer.write("   B (80-89): " + eighties);
	    System.out.println("   B (80-89): " + eighties);
	    writer.newLine();
	    writer.write("   C (70-79): " + seventies);
	    System.out.println("   C (70-79): " + seventies);
  	    writer.newLine();
	    System.out.println("   D (60-69): " + sixties);
	    writer.newLine();
	    System.out.println("   F (below 60): " + belowSixty);
	    writer.newLine();
        } catch (IOException e) {
            System.out.println("Could not write file: " + e.getMessage());
        }
    }
}
