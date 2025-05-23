package streamapi;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

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

        // Task IV+V: Resources

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
        // TODO
        throw new UnsupportedOperationException();
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
        // TODO
        throw new UnsupportedOperationException();
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
        // TODO
        throw new UnsupportedOperationException();
    }
}
