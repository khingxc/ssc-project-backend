package io.muzoo.ssc.project.backend.whoami;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WhoamiDTO {

    private boolean loggedin = false;

    private String email;

    private String displayName;

    private String role;
}
