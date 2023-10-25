import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введите данные через пробел - Фамилия Имя Отчество ДатаРождения НомерТелефона Пол: \n");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных!");
            }

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            long phoneNumber = Long.parseLong(data[4]);
            char gender = data[5].charAt(0);

            // Проверки формата данных
            if (!birthDate.matches("\\d{2}.\\d{2}.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты рождения! Введите его в формате dd.mm.yyyy.");
            }

            if (gender != 'F' && gender != 'M') {
                throw new IllegalArgumentException("Неверный формат пола! Допускаются значения M и F");
            }

            // Запись данных в файл
            BufferedWriter writer = new BufferedWriter(new FileWriter(surname + ".txt", true));
            writer.write(surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender);
            writer.newLine();
            writer.close();

            System.out.println("Данные успешно записаны в файл " + surname + ".txt");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода данных: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Произошла ошибка:");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
