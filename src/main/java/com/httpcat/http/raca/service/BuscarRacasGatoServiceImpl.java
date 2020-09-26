package com.httpcat.http.raca.service;

import com.httpcat.generic.client.AbstractGetClient;
import com.httpcat.http.raca.config.BuscarRacasGatoConfiguration;
import com.httpcat.http.raca.dto.RequestRacaGato;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscarRacasGatoServiceImpl
        extends AbstractGetClient<RequestRacaGato, ResponseRacaGato[], BuscarRacasGatoConfiguration>
        implements BuscarRacasGatoService{

    public BuscarRacasGatoServiceImpl(RestTemplate restTemplate, BuscarRacasGatoConfiguration configuration) {
        super(restTemplate, configuration);
    }

    @Override
    public List<ResponseRacaGato> buscar() {
        ResponseRacaGato[] responseRacaGato = this.get();
        return convertArrayToList(responseRacaGato);
    }

    @Override
    public List<ResponseRacaGatoImagem> getAndSave() {

        return null;
    }

    private List<ResponseRacaGato> convertArrayToList(ResponseRacaGato[] responseRacaGato) {
        List<ResponseRacaGato> responseRacaGatoList = new ArrayList<>();
        for (ResponseRacaGato racaGato : responseRacaGato){
            responseRacaGatoList.add(racaGato);
        }
        return responseRacaGatoList;
    }
}
