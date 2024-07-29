package ru.yandex.practicum.catsgram;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatsgramApp {
    public static void main(final String[] args) {
        SpringApplication.run(CatsgramApp.class, args);
//        String url = "jdbc:postgresql://localhost:5432/postgres";
//        String user = "dbuser";
//        String password = "12345";
//
//        PGConnectionPoolDataSource ds = new PGConnectionPoolDataSource();
//        ds.setURL(url);
//        ds.setUser(user);
//        ds.setPassword(password);
//        PooledConnection conn = null;
//        try (Statement stmt = ds.getPooledConnection().getConnection().createStatement()) {
//
//        } catch (SQLException e) {
//            // Обрабатываем ошибки
//        }
//        final Gson gson = new Gson();
//        final Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите JSON => ");
//        final String input = scanner.nextLine();
//        try {
//            gson.fromJson(input, Map.class);
//            System.out.println("Был введён корректный JSON");
//        } catch (JsonSyntaxException exception) {
//            System.out.println("Был введён некорректный JSON");
//        }
    }
}
