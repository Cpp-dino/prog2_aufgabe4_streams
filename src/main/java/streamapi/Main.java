package streamapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Starter for the stream api task. */
public class Main {
    /**
     * And go.
     *
     * @param args command line parameters, not used
     */
    public static void main(String... args) {

        // Task I: Students
        System.out.println(
                students(
                        List.of(
                                new Student("A", 30, Enrollment.IFM),
                                new Student("B", 45, Enrollment.IFM),
                                new Student("C", 60, Enrollment.ELT),
                                new Student("D", 45, Enrollment.ARCH),
                                new Student("E", 80, Enrollment.IFM))));

        // Task II: Set of ECTS of all IFM students
        System.out.println(
                ifmCps(
                        List.of(
                                new Student("A", 35, Enrollment.IFM),
                                new Student("B", 35, Enrollment.IFM),
                                new Student("C", 60, Enrollment.ELT),
                                new Student("D", 45, Enrollment.ARCH),
                                new Student("E", 80, Enrollment.IFM))));

        // Task III: Random
        System.out.println(random());

        // Task IV+V: Resources
        System.out.println(resources("file.txt"));
    }

    /**
     * Task I: Students.
     *
     * <p>Calculate the total credits earned by all students.
     *
     * @param studentList List of students
     * @return Sum of credit points of all students
     */
    public static Integer students(List<Student> studentList) {
        // return sum of credit points over list of students:
        // '.stream()'               -> create 'Stream' from 'List'
        // '.mapToInt(Student::cps)' -> turn 'Stream' into 'IntStream' via 'mapToInt()' with
        //                              referenced method 'Student.cps()' - returning credit points (as int)
        // '.sum()'                  -> get sum over all values
        return studentList
                    .stream()
                    .mapToInt(Student::cps)
                    .sum();
    }

    /**
     * Task II: Set of ECTS of all IFM students.
     *
     * <p>Identify the different credit points of all IFM students.
     *
     * @param studentList List of students
     * @return Set of credit points of all IFM students
     */
    public static Set<Integer> ifmCps(List<Student> studentList) {
        // return set of unique credit point values over list of students:
        // '.stream()'               -> create 'Stream' from 'List'
        // '.filter(Student::isIFM)' -> filter to only get students enrolled in Computer Science
        // '.map(Student::cps)'      -> turn each 'Student' into 'int' with referenced
        //                              method 'Student.cps()' - returning credit points (as int)
        // '.collect(Collectors.toCollection(HashSet::new))'
        //                           -> create new 'HashSet' from all (unique) values of the 'Stream' via
        //                              'Collectors.toCollection()' with referenced constructor 'HashSet::new'
        return studentList
            .stream()
            .filter(Student::isIFM)
            .map(Student::cps)
            .collect(Collectors.toCollection(HashSet::new));
    }

    /**
     * Task III: Random.
     *
     * <p>Calculate ten random integers between 0 and 10.
     *
     * @return List of ten random integers (between 0 and 10)
     */
    public static List<Integer> random() {
        Random r = new Random();

        return Stream
            .generate(() -> r.nextInt(10))
            .limit(10)
            .filter(n -> ((n % 2) == 0))
            .map(n -> (n * n))
            .collect(Collectors.toList());
    }

    /**
     * Task IV: Open resources.
     *
     * <p>Open the file specified by the {@code path} parameter. This file is located in the
     * resources folder of the project.
     *
     * @param path Name of the file to be accessed within the resource folder.
     * @return An open {@link InputStream} for the resource file
     */
    private static InputStream getResourceAsStream(String path) {
        // ressource named 'path' is located in directory 'ressources'
        final String ressourcePath = "streamapi/" + path;
        // get 'ClassLoader' for this class ('Main') to access known class pathes,
        // java searches for 'streamapi/path' in class pathes for class 'Main'
        // ('ressources' directory is added as class path to class 'Main' by Gradle) 
        return Main.class.getClassLoader().getResourceAsStream(ressourcePath);
    }

    /**
     * Task V: Read resources.
     *
     * <p>Read all lines from the resource file (specified by the {@code path} parameter). Merge all
     * lines that start with the letter "a" and are at least two characters long. The lines are to
     * be separated in the resulting string by a line-end character {@code "\n"}.
     *
     * @param path Name of the file to be accessed within the resource folder
     * @return String of all matching lines, separated by {@code "\n"}
     */
    public static String resources(String path) {
        String result = "";

        try (InputStream stream = getResourceAsStream(path)) {
            BufferedReader r = new BufferedReader(new InputStreamReader(stream));

            // return lines concatinated and '\n'-separated only including lines starting with 'a' and min. length of 1:
            // '.lines()'                           -> create 'Stream' of lines from 'BufferedReader'
            // '.filter(s -> s.startsWith("a"))'    -> filter to only get lines starting with 'a'
            // '.filter(s -> !(s.length() < 2))'    -> filter to only get lines with min. length of 1
            // '.map(s -> (s + "\n"))'              -> append '\n' to end of each (remaining) line
            // '.reduce("", String::concat)'        -> turn 'Stream' of 'Strings' into single 'String' using
            //                                         referenced method 'String.concat()' to concatinating the lines
            result = r
                .lines()
                .filter(s -> s.startsWith("a"))
                .filter(s -> !(s.length() < 2))
                .map(s -> (s + "\n"))
                .reduce("", String::concat);
        } catch (IOException e) {
            System.err.println("Ouch, that didn't work: \n" + e.getMessage());
        }

        return result;
    }
}
