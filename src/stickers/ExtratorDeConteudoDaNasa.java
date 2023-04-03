package stickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDaNasa  implements ExtratorDeConteudo {
	
	public List<Conteudo> extraiConteudos(String json){
		// String url = 
		// "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
		var parser = new JsonParser();
		List<Map<String, String>> listaDeAtributos= parser.parse(json);
		
		
		
		List<Conteudo> conteudos =  new ArrayList<>();
		
		//popular a lista de conteudos 
		for (Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImagem = atributos.get("url");
			var conteudo = new Conteudo(titulo, urlImagem);
			
			conteudos.add(conteudo);
		}
		return conteudos;
		
	}

}
