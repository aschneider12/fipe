package br.dev.as.fipe;

import br.dev.as.fipe.model.Brand;
import br.dev.as.fipe.model.Model;
import br.dev.as.fipe.model.Reference;
import br.dev.as.fipe.model.Year;
import br.dev.as.fipe.service.ConsumoAPI;
import br.dev.as.fipe.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MenuApp {

    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();

    private Scanner leitor = new Scanner(System.in);

    String vehicleType = "";
    String brandId = "";
    String modelId = "";
    String yearReferenceId = "";

    public void exibirMenu(){

        System.out.println("Informe o tipo de consulta:");
        System.out.println("( 1 ) Carros ");
        System.out.println("( 2 ) Motos ");
        System.out.println("( 3 ) Caminhões ");

         var tipoVeiculo = leitor.nextLine();

        switch (tipoVeiculo){
            case "1":
                System.out.println("Buscando carros... ");
                buscarTodasMarcas("cars");
                break;
            case "2":
                System.out.println("Buscando motos.. ");
                buscarTodasMarcas("motorcycles");
                break;
            case "3":
                System.out.println("Buscando caminhões.. ");
                buscarTodasMarcas("trucks");
                break;
            default: {
                System.out.println("Opção não permitida.");
                exibirMenu();
            }

        }
    }

    private void buscarTodasMarcas(String tipo) {

        vehicleType = tipo;

        String url = "https://fipe.parallelum.com.br/api/v2/"+vehicleType+"/brands";

        var json = consumoAPI.obterDados(url);
//        System.out.println(json);

        var marcas = converteDados.obterDadosLista(json, Brand.class);

        marcas.stream().sorted(Comparator.comparing(Brand::marca))
                .forEach(System.out::println);

        exibirMenuModelos(marcas);
    }

    private void exibirMenuModelos(List<Brand> marcas) {
        System.out.println("Informe o código da marca :");

        brandId = leitor.nextLine();

        var marca = marcas.stream().filter(f -> f.codigo().equals(brandId)).findFirst();

        if(marca.isPresent())
            System.out.println("Buscando marca ... "+marca.get().marca());
        else {
            System.out.println("Marca não encontrada!");
            exibirMenuModelos(marcas);
        }

        String url = "https://fipe.parallelum.com.br/api/v2/"+vehicleType+"/brands/"+brandId+"/models";
        var json = consumoAPI.obterDados(url);

        var modelos = converteDados.obterDadosLista(json, Model.class);

        modelos.stream().sorted(Comparator.comparing(Model::modelo))
                .forEach(System.out::println);

        exibirMenuAnos(modelos);
    }

    private void exibirMenuAnos(List<Model> modelos) {
        System.out.println("Selecione o modelo:");
        modelId = leitor.nextLine();

        var modelselecionado = modelos.stream().filter(f -> f.codigo().equals(modelId)).findFirst();
        if(modelselecionado.isPresent())
            System.out.println("Buscando modelo .. " +modelselecionado.get().modelo());
        else {
            System.out.println("Modelo não encontrado!");
            exibirMenuAnos(modelos);
        }

        String url = "https://fipe.parallelum.com.br/api/v2/"+vehicleType+"/brands/"+brandId+"/models/"+modelId+"/years";
        var json = consumoAPI.obterDados(url);

        var anos = converteDados.obterDadosLista(json, Year.class);

        anos.stream().sorted(Comparator.comparing(Year::ano))
                .forEach(System.out::println);

        exibirMenuAnoReferencia(anos);
    }

    private void exibirMenuAnoReferencia(List<Year> anos) {

        System.out.println("Selecione o ano que deseja consultar ('0' p/ 0km):");

        yearReferenceId = leitor.nextLine();

        yearReferenceId = yearReferenceId.equals("0") ? "32000" : yearReferenceId;

        var anosSele = anos.stream().filter(f -> f.ano().startsWith(yearReferenceId)).findFirst();
        if(anosSele.isPresent())
            System.out.println("Buscando ano ... "+anosSele.get().ano());
        else {
            System.out.println("Ano não encontrado!");
            exibirMenuAnoReferencia(anos);
        }

        String url = "https://fipe.parallelum.com.br/api/v2/"+vehicleType+"/brands/"+brandId+"/models/"+modelId+"/years/"+anosSele.get().codigo();
        var json = consumoAPI.obterDados(url);

        Reference fipeReference = converteDados.obterDadosObjeto(json, Reference.class);

        System.out.println(fipeReference);
    }
}

