package br.dev.as.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Year(@JsonAlias("code") String codigo,
                    @JsonAlias("name") String ano){

}
