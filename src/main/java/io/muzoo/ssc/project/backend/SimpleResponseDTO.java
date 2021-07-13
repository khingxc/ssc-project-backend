package io.muzoo.ssc.project.backend;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@Builder
public class SimpleResponseDTO {

    private boolean success;
    private String message;
}
