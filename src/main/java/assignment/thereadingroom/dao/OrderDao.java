package assignment.thereadingroom.dao;

import java.sql.SQLException;
import java.util.List;

import assignment.thereadingroom.dto.OrderCreateDto;
import assignment.thereadingroom.dto.OrderItemCreateDto;
import assignment.thereadingroom.model.Order;
import assignment.thereadingroom.model.OrderJoinedItem;

public interface OrderDao {
    void setup() throws SQLException;
    List<Order> getOrdersByUsername(String username) throws SQLException;
    List<OrderJoinedItem> getOrderJoinedItemsByUsername(String username) throws SQLException;
    Order getOrder(String id) throws SQLException;
    Order createOrder(OrderCreateDto orderCreateDto, List<OrderItemCreateDto> orderItemCreateDtoList) throws SQLException;
}
