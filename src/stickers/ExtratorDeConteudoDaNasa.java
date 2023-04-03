package stickers;

import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa implements ExtratorDeConteudo {

	public List<Conteudo> extraiConteudos(String json){
		// String url = 
		// "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos= parser.parse(json);
	
		return listaDeAtributos.stream()
		.map( atributos ->new Conteudo(atributos.get("title"),atributos.get("url")))
		.toList();
		
	}

}
