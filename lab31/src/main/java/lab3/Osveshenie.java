package lab3;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

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
	
	private final String PATH = "D:\\apache-tomcat-9.0.0.M20\\webapps\\files";

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
					for (int j = height - 1; j > height - i; j--) {
						pWriter.setColor(i, j, color);
					}
				}
				for (int i = width / 2; i < width; i++) {
					for (int j = height - 1; j > i; j--) {
						pWriter.setColor(i, j, color);
					}
				}
				saveWritableImage(writableImage, new File(PATH, "temp0.png"));
				break;
			case 1:
				for (int j = 0; j < height / 2; j++) {
					for (int i = width - j; i < width; i++) {
						pWriter.setColor(i, j, color);
					}
				}
				for (int j = height / 2; j < height; j++) {
					for (int i = width / 2 + j - height / 2 - 1; i < width; i++) {
						pWriter.setColor(i, j, color);
					}
				}
				saveWritableImage(writableImage, new File(PATH, "temp1.png"));
				break;
			case 2:
				for (int i = 0; i < width / 2; i++) {
					for (int j = 0; j < i + 1; j++) {
						pWriter.setColor(i, j, color);
					}
				}
				for (int i = width / 2; i < width; i++) {
					for (int j = 0; j < -i + height + 1; j++) {
						pWriter.setColor(i, j, color);
					}
				}
				saveWritableImage(writableImage, new File(PATH, "temp2.png"));
				break;
			case 3:
				for (int j = 0; j < height / 2; j++) {
					for (int i = 0; i < j + 1; i++) {
						pWriter.setColor(i, j, color);
					}
				}
				for (int j = height / 2; j < height; j++) {
					for (int i = 0; i < -j + width + 1; i++) {
						pWriter.setColor(i, j, color);
					}
				}
				saveWritableImage(writableImage, new File(PATH, "temp3.png"));
				break;
			}
		}
		
		String name = String.valueOf(writableImage.hashCode());
		File file = new File(PATH, name + ".png");
//		System.out.println("GET ABS PATH == " + file.getAbsolutePath());
		saveWritableImage(writableImage, file);
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("pic", URLEncoder.encode("/files/"+ name + ".png", "UTF-8"));
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		return Response.ok().entity(jsonObj.toString().getBytes(Charset.forName("UTF-8"))).build();
	}

	// Полная
	// метод Гуро
	@POST
	@Path("get2")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/json")
	// Простейшая
	// Делает картинку и отправляет Джисоном её адрес
	public Response getOsv2(Pojo p) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("err", "Unimplemented method");
		return Response.ok().entity(jsonObj.toString().getBytes(Charset.forName("UTF-8"))).build();	
	}
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
