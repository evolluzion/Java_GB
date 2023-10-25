import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите данные через пробел: Фамилия Имя Отчество ДатаРождения НомерТелефона Пол");
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных!");
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            if (!isValidDateFormat(birthDate)) {
                throw new IllegalArgumentException("Неверный формат даты рождения, введите в формате dd.mm.yyyy!");
            }

            if (!isValidGender(gender)) {
                throw new IllegalArgumentException("Неверный пол! Введите F или M!");
            }

            String filename = lastName + ".txt";
            writeToTextFile(filename, input);

            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isValidDateFormat(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }

    private static boolean isValidGender(char gender) {
        return gender == 'F' || gender == 'M';
    }

    private static void writeToTextFile(String filename, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(data);
            writer.newLine();
        }
    }
}
