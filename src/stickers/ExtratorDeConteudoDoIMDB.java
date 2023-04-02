package stickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{

	public List<Conteudo> extraiConteudos(String json) {
		var parser = new JsonParser();
		// usar String url =
		// "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
		List<Map<String, String>> listaDeAtributos = parser.parse(json);

		List<Conteudo> conteudos = new ArrayList<>();

		// popular a lista de conteudos
		for (Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImagem = atributos.get("image");
			var conteudo = new Conteudo(titulo, urlImagem);

			conteudos.add(conteudo);
		}
		return conteudos;

	}

}
