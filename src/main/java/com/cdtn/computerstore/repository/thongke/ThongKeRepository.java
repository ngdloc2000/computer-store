package com.cdtn.computerstore.repository.thongke;

import com.cdtn.computerstore.dto.thongke.request.ThongKeSanPhamRequest;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamBanChayResponse;
import com.cdtn.computerstore.dto.thongke.response.ThongKeSanPhamTheoDanhMucResponse;
import com.cdtn.computerstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
