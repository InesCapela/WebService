package com.example.demo.services;
import java.util.HashMap;
import java.util.Map;

public class UniversidadeUrlService {

    private static Map<String,String> urlMapper=null;

    private static UniversidadeUrlService instance=null;

    private UniversidadeUrlService() {

    }

    public static UniversidadeUrlService getInstance(){
        if(instance==null){
            instance=new UniversidadeUrlService();
            instance.urlMapper=new HashMap<>();

            urlMapper.put("UFP", "http://localhost:8080/");
            urlMapper.put("UP", "http://localhost:8081/");
        }
        return instance;
    }

    public String getUrl(String universityName){
        return this.urlMapper.get(universityName);
    }
    public String getAlunosUrl(String universidadeName){
        return this.getUrl(universidadeName)+"/aluno";
    }
    public String getFacUrl(String universidadeName){
        return this.getUrl(universidadeName)+"/faculdade";
    }
    public String getExplicadorUrl(String universidadeName){
        return this.getUrl(universidadeName)+"/explicador";
    }
    public String getExplicacaoUrl(String universidadeName){
        return this.getUrl(universidadeName)+"/explicacao";
    }
    public String getCursoUrl(String universidadeName){
        return this.getUrl(universidadeName)+"/curso";
    }
}
