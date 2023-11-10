package com.duvi.vuttr.controller;


import com.duvi.vuttr.controller.exception.ToolAlreadyExistsException;
import com.duvi.vuttr.controller.exception.ToolNotFoundException;
import com.duvi.vuttr.domain.DTO.ToolDTO;
import com.duvi.vuttr.domain.Tool;
import com.duvi.vuttr.service.ToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(description = "Les outils sont utiles et il est important de s'en souvenir",
        name = "Outils")
@RestController
@RequestMapping("api/tools")
public class ToolController {

    @Autowired
    ToolService toolService;


    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Tool>> getAll() {
        List<Tool> toolList = toolService.getAllTools();
        return new ResponseEntity<>(toolList, HttpStatus.OK);
    }

    @Operation(summary = "Get tool by id")
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Tool> get(@PathVariable Long id) throws ToolNotFoundException {
        try {
            Tool tool = toolService.getTool(id);
            return new ResponseEntity<>(tool, HttpStatus.OK);
        } catch (ToolNotFoundException ex) {
            throw ToolNotFoundException.createWith(id);
        }
    }
    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Tool> post(@RequestBody ToolDTO toolDTO) throws ToolAlreadyExistsException {
        Tool newTool = toolService.createTool(toolDTO);
        return new ResponseEntity<>(newTool, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    ResponseEntity<Tool> put(@RequestBody ToolDTO toolDTO, @PathVariable Long id) {
        Tool newTool = toolService.editTool(id, toolDTO);
        return new ResponseEntity<>(newTool, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        toolService.deleteTool(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
