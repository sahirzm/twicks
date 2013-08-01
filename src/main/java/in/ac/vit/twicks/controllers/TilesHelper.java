package in.ac.vit.twicks.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tiles.TilesContainer;
import org.apache.tiles.access.TilesAccess;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.request.Request;
import org.apache.tiles.request.servlet.ServletApplicationContext;
import org.apache.tiles.request.servlet.ServletRequest;

public class TilesHelper {

	public static void render(HttpServletRequest request, HttpServletResponse response, String name) {
		ApplicationContext context = new ServletApplicationContext(
				request.getServletContext());
		TilesContainer container = TilesAccess.getContainer(context);
		Request tilesRequest = new ServletRequest(
				container.getApplicationContext(), request, response);
		container.render(name, tilesRequest);
	}
 }
