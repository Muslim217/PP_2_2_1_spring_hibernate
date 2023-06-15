package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      /*userService.add(new User("Bob", "Jonson", "user1@mail.ru",new Car("Chevrolet",111)));
      userService.add(new User("Javier", "Hernandez", "user2@mail.ru",new Car("Seat",222)));
      userService.add(new User("Alex", "Orlov", "user3@mail.ru",new Car("Lada",333)));
      userService.add(new User("Angelina", "Ivanova", "user4@mail.ru",new Car("Mercedes",444)));
*/


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar().getModel() + " " + user.getCar().getSeries());

         System.out.println();
      }

      User user = userService.returnUser("Lada",333);
      System.out.println(user.toString());
      context.close();
   }
}
