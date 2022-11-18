package com.tp2.controller;
import java.awt.print.Book;
import java.util.List;
import com.tp2.entity.Career;
import com.tp2.service.CareerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/career")
public class CareerController {

	@Autowired
    private CareerService careerService;

   /* @GetMapping("/sortedByStudent")
    public List<Career> getCareersOrderByStudents() {
        return careerService.getCareersOrderByStudents();
    }*/

    @GetMapping("/")
    @Operation(summary = "Devolver carreras",
            description = "Devuelve todas las carreras",
            tags = "carreras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de carreras",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    public List<Career> getCareers() {
        return careerService.getCareers();
    }
}