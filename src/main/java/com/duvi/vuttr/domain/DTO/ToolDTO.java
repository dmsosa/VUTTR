package com.duvi.vuttr.domain.DTO;

import com.duvi.vuttr.domain.Tool;

import java.util.List;

public record ToolDTO(String title, String link, String description, List<String> tags) {
    public ToolDTO(Tool tool) {
        this(tool.getTitle(), tool.getLink(), tool.getDescription(), , tool.getTags());
    }
}
