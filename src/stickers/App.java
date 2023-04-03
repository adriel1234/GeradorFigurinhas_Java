package stickers;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
	
	public static void main(String[] args) throws Exception {
		API api = API.IMDP_TOP_MOVIES;
		//API api = API.NASA;
		String url = api.getUrl();
		ExtratorDeConteudo extrator = api.getExtrator();
		
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
