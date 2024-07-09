package br.dev.as.fipe.service;

import java.util.List;

public interface IConverteDados {

    <T> T obterDadosObjeto(String json, Class<T> tClass);
    <T> List<T> obterDadosLista(String json, Class<T> tClass);
}
