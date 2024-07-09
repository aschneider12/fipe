package br.dev.as.fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements  IConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDadosObjeto(String json, Class<T> tClass) {
        try {

            return mapper.readValue(json, tClass);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterDadosLista(String json, Class<T> tClass) {
        try {

            CollectionType mapperToList = mapper.getTypeFactory()
                    .constructCollectionType(List.class, tClass);

            return mapper.readValue(json, mapperToList);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
