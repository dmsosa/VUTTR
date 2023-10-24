package com.duvi.vuttr.service.impl;

import com.duvi.vuttr.controller.exception.ToolAlreadyExistsException;
import com.duvi.vuttr.domain.DTO.ToolDTO;
import com.duvi.vuttr.domain.Tool;
import com.duvi.vuttr.repository.ToolRepository;
import com.duvi.vuttr.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    ToolRepository toolRepository;

    @Override
    public Tool createTool(ToolDTO toolDTO) throws ToolAlreadyExistsException {
        Tool newTool = new Tool(toolDTO);
        if (toolRepository.existsByTitle(newTool.getTitle())) {
            throw new ToolAlreadyExistsException(newTool.getTitle());
        }
        toolRepository.save(newTool);
        return newTool;
    }

    public Tool editTool(Long oldToolId, Tool newTool) throws
}
