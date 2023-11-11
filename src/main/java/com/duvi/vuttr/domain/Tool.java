package com.duvi.vuttr.domain;


import com.duvi.vuttr.domain.DTO.ToolDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Table(name = "tools")
@Entity(name = "tool")
@Getter
@Setter
@NoArgsConstructor
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String description;
    private List<String> tags;

    public Tool(ToolDTO toolDTO) {
        this.title = toolDTO.title();
        this.link = toolDTO.link();
        this.description = toolDTO.description();
        this.tags = toolDTO.tags();
    }

    public void updateTool(Tool newTool) {
        this.title = newTool.getTitle();
        this.link = newTool.getLink();
        this.description = newTool.getDescription();
        this.tags = newTool.getTags();
    }
}
