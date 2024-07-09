package br.dev.as.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Reference(@JsonAlias("price") String preco,
                        @JsonAlias("brand") String marca,
                    @JsonAlias("model") String modelo ){


}
