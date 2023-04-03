package stickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {

	public List<Conteudo> extraiConteudos(String json) {
		var parser = new JsonParser();
		// usar String url =
		// "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
		List<Map<String, String>> listaDeAtributos = parser.parse(json);

		return listaDeAtributos.stream().map(atributos -> new Conteudo(atributos.get("title"), atributos.get("image")))
				.toList();
	}
}
