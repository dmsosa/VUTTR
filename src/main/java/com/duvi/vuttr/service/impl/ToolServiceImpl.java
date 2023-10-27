package com.duvi.vuttr.service.impl;

import com.duvi.vuttr.controller.exception.ToolAlreadyExistsException;
import com.duvi.vuttr.controller.exception.ToolNotFoundException;
import com.duvi.vuttr.domain.DTO.ToolDTO;
import com.duvi.vuttr.domain.Tool;
import com.duvi.vuttr.repository.ToolRepository;
import com.duvi.vuttr.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolServiceImpl implements ToolService {

    @Autowired
    ToolRepository toolRepository;

    @Override
    public Tool createTool(ToolDTO toolDTO) throws
        ToolAlreadyExistsException
     {
        Tool newTool = new Tool(toolDTO);
        if (toolRepository.existsByTitle(newTool.getTitle())) {
            throw ToolAlreadyExistsException.createWith(newTool.getTitle());
        }
        try {
            toolRepository.save(newTool);
        } catch (Exception ex) {

        }

        return newTool;
    }

    public Tool editTool(Long oldToolId, ToolDTO toolDTO) {
        Optional<Tool> old = toolRepository.findById(oldToolId);
        Tool newTool = new Tool(toolDTO);
        if (old.isEmpty()) {
            newTool.setId(oldToolId);
            toolRepository.save(newTool);
            return newTool;
        }
        old.get().updateTool(newTool);
        return toolRepository.save(old.get());
    }
    public Tool getTool(Long id) throws ToolNotFoundException {
        return toolRepository.findById(id).orElseThrow(() -> new ToolNotFoundException(id));
    }
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }
    public void deleteTool(Long id) {
        toolRepository.deleteById(id);
    }

}
