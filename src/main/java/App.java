import entity.Item;
import entity.Order;
import entity.User;
import service.ItemDaoImpl;
import service.OrderDaoImpl;
import service.UserDaoImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Calendar;

public class App {

    public static void main(String[] args) {
        ItemDaoImpl itemDao = new ItemDaoImpl();
        UserDaoImpl userDao = new UserDaoImpl();
        OrderDaoImpl orderDao = new OrderDaoImpl();

        Item item = new Item();
        item.setId(1L);
        item.setTitle("Book");
        item.setDescription("Hello");
        item.setPrice(BigDecimal.valueOf(5));

        User user = new User();
        user.setId(1L);
        user.setLogin("rogue");
        user.setFirstName("John");
        user.setLastName("Cena");
        user.setDeliveryAddress("USA, Boston");
        user.setContactPhone("+188005553535");

        Order order = new Order();
        order.setId(1L);
        order.setUserId(1L);
        order.setItemId(1L);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, Calendar.SEPTEMBER, 21);
        order.setDate(new Date(calendar.getTime().getTime()));
        order.setQuantity(10);

        try {
//            itemDao.add(item);
//            userDao.add(user);
//            orderDao.add(order);
//            itemDao.update(item);
            System.out.println(userDao.getByLogin("rogue"));
            System.out.println(itemDao.getByTitle("Book"));
            System.out.println(orderDao.getByUserId(1L));
            System.out.println(userDao.getAllUsers());
            System.out.println(itemDao.getAllItems());
            System.out.println(orderDao.getAllOrders());
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
