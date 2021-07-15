package io.muzoo.ssc.project.backend.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationProperties {

    private String databaseDriverClassName;
    private String databaseConnectionUrl;
    private String databaseUsername;
    private String databasePassword;

}
