package com.cdtn.computerstore.repository.thongke;

import com.cdtn.computerstore.dto.thongke.request.ThongKeSanPhamRequest;
import com.cdtn.computerstore.dto.thongke.response.ProductStatisticsByDay;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamBanChayResponse;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamTheoDanhMucResponse;
import com.cdtn.computerstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThongKeRepository extends JpaRepository<Product, Long> {

    @Query(value = "select new com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamTheoDanhMucResponse" +
            "(c.name, sum(oi.quantity), sum(oi.price)) " +
            "from Order o " +
            "join OrderItem oi on o.id = oi.orderId " +
            "join Product p on p.id = oi.productId " +
            "join Category c on p.categoryId = c.id " +
            "where o.status = 2 " +
            "and date(o.completedAt) >= :#{#request.dateFrom} " +
            "and date(o.completedAt) <= :#{#request.dateTo} " +
            "group by p.categoryId")
    List<ThongKeSanPhamTheoDanhMucResponse> thongKeTheoDanhMuc(@Param("request") ThongKeSanPhamRequest request);

    @Query(value = "select new com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamBanChayResponse" +
            "(p.name, sum(oi.quantity), p.brand) " +
            "from Order o " +
            "join OrderItem oi on o.id = oi.orderId " +
            "join Product p on p.id = oi.productId " +
            "join Category c on p.categoryId = c.id " +
            "where o.status = 2 " +
            "and date(o.completedAt) >= :#{#request.dateFrom} " +
            "and date(o.completedAt) <= :#{#request.dateTo} " +
            "group by p.id " +
            "order by sum(oi.quantity) desc")
    List<ThongKeSanPhamBanChayResponse> thongKeSanPhamBanChay(@Param("request") ThongKeSanPhamRequest request);


    @Query(value = "select DATE(o.completed_at) as dateTime, sum(oi.quantity) as totalSold " +
            "from orders o " +
            "join order_item oi on o.id = oi.order_id " +
            "join product p on p.id = oi.product_id " +
            "where o.completed_at >= current_date - interval :day day and o.status = 2 " +
            "group by DATE(o.completed_at)", nativeQuery = true)
    Iterable<ProductStatisticsByDay> productStatisticsByDay(Integer day);
}
