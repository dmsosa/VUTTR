package com.duvi.vuttr.service;

import com.duvi.vuttr.controller.exception.ToolAlreadyExistsException;
import com.duvi.vuttr.controller.exception.ToolNotFoundException;
import com.duvi.vuttr.domain.DTO.ToolDTO;
import com.duvi.vuttr.domain.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToolService {

    Tool createTool(ToolDTO toolDTO) throws ToolAlreadyExistsException;
    Tool editTool(Long oldToolId, ToolDTO toolDTO);
    Tool getTool(Long id) throws ToolNotFoundException;
    List<Tool> getAllTools();
    void deleteTool(Long id);


}
