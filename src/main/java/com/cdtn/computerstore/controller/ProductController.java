package com.cdtn.computerstore.controller;

import com.cdtn.computerstore.dto.base.BaseResponseData;
import com.cdtn.computerstore.dto.product.request.ProductCreationForm;
import com.cdtn.computerstore.dto.product.request.ProductQuerySearchForm;
import com.cdtn.computerstore.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ResourceLoader resourceLoader;

    @PostMapping("/create")
    public ResponseEntity<BaseResponseData> createCategory(@RequestBody @Valid ProductCreationForm creationForm) {

        return ResponseEntity.ok(productService.createProduct(creationForm));
    }

    @GetMapping("/admin/search")
    public ResponseEntity<BaseResponseData> searchProductByAdmin(
            @ModelAttribute @Valid ProductQuerySearchForm form,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errorMessage = "Bad Request: " + bindingResult.getAllErrors().get(0).getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new BaseResponseData(400, errorMessage, null));
        }

        return ResponseEntity.ok(productService.getProductInfoAdminSearchList(form));
    }

    @GetMapping("/export/pdf")
    public void exportReport(HttpServletResponse response) throws JRException, IOException {
        try {
            // Tải mẫu Jasper Reports
            Resource resource = resourceLoader.getResource("classpath:report_template.jrxml");
            InputStream inputStream = resource.getInputStream();

            // Compile mẫu thành báo cáo JasperPrint
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());

            // Xuất báo cáo ra tập tin PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=report.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
