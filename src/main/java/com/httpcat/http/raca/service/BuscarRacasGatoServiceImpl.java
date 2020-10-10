package com.httpcat.http.raca.service;

import com.httpcat.generic.client.AbstractGetClient;
import com.httpcat.http.raca.config.BuscarRacasGatoConfiguration;
import com.httpcat.http.raca.dto.RequestRacaGato;
import com.httpcat.http.raca.dto.ResponseRacaGato;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class BuscarRacasGatoServiceImpl
        extends AbstractGetClient<RequestRacaGato, ResponseRacaGato[], BuscarRacasGatoConfiguration>
        implements BuscarRacasGatoService{

    @Override
    public List<ResponseRacaGato> buscar() {
        ResponseRacaGato[] responseRacaGato = this.get();
        return convertArrayToList(responseRacaGato);
    }

    @Override
    public List<ResponseRacaGatoImagem> getAndSave(Map<String, String> expand) {

        return null;
    }

    private List<ResponseRacaGato> convertArrayToList(ResponseRacaGato[] responseRacaGato) {
        List<ResponseRacaGato> responseRacaGatoList = new ArrayList<>();
        responseRacaGatoList.addAll(Arrays.asList(responseRacaGato));
        return responseRacaGatoList;
    }
}
