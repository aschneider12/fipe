package br.dev.as.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Brand(@JsonAlias("code") String codigo,
                    @JsonAlias("name") String marca){

}
