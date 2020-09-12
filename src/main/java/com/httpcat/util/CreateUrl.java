package com.httpcat.util;

import java.util.Map;

public interface CreateUrl {
	String getUrl();
	String getUrl(String buscaPeloNome);
	String getUrl(Map<String, String> raca);
}
