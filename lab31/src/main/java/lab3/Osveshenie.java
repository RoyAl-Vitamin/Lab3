package lab3;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

@Path("get")
public class Osveshenie {

	private final float norm[][] = { { 0, 0, 1 }, {-0.24f, 0.24f, 0.94f}, {0.24f, 0.24f, 0.94f}, {0.24f, -0.24f, 0.94f}, {-0.24f, -0.24f, 0.94f}}; // нормали в вершинах

	private final int width = 300;
	private final int height = 300;
	
	private final String PATH = "D:\\apache-tomcat-8.0.36\\webapps\\files";

	private double cos_alp(Pojo p) {
		float R[] = { -p.getLi(), -p.getLj(), p.getLk() };
		return (p.getSi() * R[0] + p.getSj() * R[1] + p.getSk() * R[2])
				/ (Math.sqrt(R[0] * R[0] + R[1] * R[1] + R[2] * R[2])
						* Math.sqrt(p.getSi() * p.getSi() + p.getSj() * p.getSj() + p.getSk() * p.getSk()));
	}

	private float cos_phi(Pojo p, float norm[]) {
		return (float) ((p.getLi() * norm[0] + p.getLj() * norm[1] + p.getLk() * norm[2])
				/ (Math.sqrt(norm[0] * norm[0] + norm[1] * norm[1] + norm[2] * norm[2])
						* Math.sqrt(p.getLi() * p.getLi() + p.getLj() * p.getLj() + p.getLk() * p.getLk())));
	}

	@POST
	@Path("get1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	// Простейшая
	// Делает картинку и отправляет Джисоном её адрес
	public Response getOsv(Pojo p) {
		System.out.println(p);
		float normP[][] = {{0, 0.2474f, 0.9689f}, {0.2474f, 0, 0.9689f}, {0, -0.2474f, 0.9689f}, {-0.2474f, 0, 0.9689f}};
		WritableImage writableImage = new WritableImage(width, height);
		PixelWriter pWriter = writableImage.getPixelWriter();
		
		for (int k = 0; k < normP.length; k++) {
			float I = (float) (p.getIa() * p.getKa() + p.getIl()
					* (p.getKd() * cos_phi(p, normP[k]) + p.getKs() * Math.pow(cos_alp(p), p.getN())) / (p.getD() + p.getK()));
			float I_u = I / p.getIl();
//			System.out.println("I_u == " + I_u);
			final Color color = new Color(I_u, I_u, I_u, 1);

			switch (k) {
			case 0:
				for (int i = 0; i < width / 2; i++) {
					for (int j = 299; j > width - i; j--) {
						pWriter.setColor(i, j, color);
					}
				}
				for (int i = width / 2; i < width; i++) {
					for (int j = 299; j > width - i; j--) {
						pWriter.setColor(i, j, color);
					}
				}
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
		
		String name = String.valueOf(writableImage.hashCode());
		File file = new File(PATH, name + ".png");
//		System.out.println("GET ABS PATH == " + file.getAbsolutePath());
		saveWritableImage(writableImage, file);
		return Response.ok().entity(("{\"pic\": \"" + PATH + name + ".png" + "\"}").getBytes(Charset.forName("UTF-8"))).build();
	}

	// Полная
	// метод Гуро
	// метод Фонга

	/**
	 * Сохраняет картинку в пнг
	 * 
	 * @param writableImage
	 * @param name
	 */
	private void saveWritableImage(Image writableImage, File file) {
		try {
			RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
			ImageIO.write(renderedImage, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
