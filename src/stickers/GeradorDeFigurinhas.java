package stickers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {

	public void cria(InputStream inputStream, String nomeArquivo,String texto) throws Exception {
		
		// leitura da imagem
		// BufferedImage imagemOriginal= ImageIO.read(new File("entrada/filme.jpg"));
		// InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
		/*
		 * InputStream inputStream = new URL(
		 * "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_4.jpg"
		 * ).openStream();
		 */
		BufferedImage imagemOriginal = ImageIO.read(inputStream);

		// cria nova imagem em memória com transparência e com tamanho novo
		int largura = imagemOriginal.getWidth();
		int altura = imagemOriginal.getHeight();
		int novaAltura = altura + 200;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// copiar a imagem original para novo imagem (em memória)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(imagemOriginal, 0, 0, null);

		// configurar a fonte
		var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 82);
		graphics.setColor(Color.YELLOW);
		graphics.setFont(fonte);

		// escrever uma frase na nova imagem
		
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
		int larguraTexto = (int) retangulo.getWidth();
		int posicaoTextoX = (largura - larguraTexto) / 2;
		int posicaoTextoY = novaAltura - 100;
		graphics.drawString(texto, posicaoTextoX, posicaoTextoY);

		FontRenderContext fontRenderContext = graphics.getFontRenderContext();
		var textLayout=new TextLayout(texto, fonte, fontRenderContext);
		
		Shape outline = textLayout.getOutline(null);
		AffineTransform tranform = graphics.getTransform();
		tranform.translate(posicaoTextoX,posicaoTextoY);
		graphics.setTransform(tranform);
		
		var outlineStroke = new BasicStroke(largura * 0.005f);
		graphics.setStroke(outlineStroke);
		
		graphics.setColor(Color.BLACK);
		graphics.draw(outline);
		graphics.setClip(outline);
		// escrever a nova imagem em um arquivo

		ImageIO.write(novaImagem, "png", new File(nomeArquivo));

	}

}