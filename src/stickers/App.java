package stickers;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

	public static void main(String[] args) throws Exception {
		
		// String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
		//ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
		
		String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
		ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
		
		var http = new ClienteHttp();
		var json = http.buscaDados(url);
		
		// exibir e manipular os dados
		
		
		List<Conteudo> conteudos = extrator.extraiConteudos(json);
		
		var diretorio = new File("figurinhas/");
		diretorio.mkdir();
		
		var geradora = new GeradorDeFigurinhas();
		
		for(int i = 0;i<3;i++) {
			
			Conteudo conteudo = conteudos.get(i);
			
			String textoFigurinha = "TOPZERA";
			InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
			String nomeArquivo = "figurinhas/" + conteudo.getTitulo() + ".png";

			geradora.cria(inputStream, nomeArquivo, textoFigurinha);

			System.out.println(conteudo.getTitulo());
			System.out.println();
			
		}
	}

}
