package com.httpcat.controller;

import com.httpcat.http.GetCatImagem.dto.RequestGatoImagem;
import com.httpcat.http.GetCatImagem.dto.ResponseRacaGatoImagem;
import com.httpcat.http.GetCatImagem.service.BuscaImagemGatoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/imagem")
public class ImagemRacaGatoControllerImpl {


	private final BuscaImagemGatoService service;

	@Autowired
	public ImagemRacaGatoControllerImpl(BuscaImagemGatoService service) {
		this.service = service;
	}

	@GetMapping("search")
	@ApiOperation(value = "Retorna uma lista de  link e detalhes sobre a raça do gato a partir dos parametros passados na url")
	public List<Optional<ResponseRacaGatoImagem>> getImagem(@RequestParam Map<String, String> params){
		RequestGatoImagem request = new RequestGatoImagem();
		request.setExpand(params);
		return service.buscar(request);
	}

	@GetMapping
	@ApiOperation(value = "Retorna uma lista de,  link e detalhes sobre a raça de um gato")
	public List<ResponseRacaGatoImagem> getImagem() {
		return service.buscar();
	}

}
