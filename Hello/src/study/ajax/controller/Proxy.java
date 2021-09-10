package study.ajax.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/api/proxy.do")
public class Proxy extends HttpServlet {

	private static final long serialVersionUID = 736756732087173598L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String reqUrl = null;
		String targetParams = "";

		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			if (name.equals("csurl")) {
				reqUrl = request.getParameter(name);
			} else {
				targetParams += name + "=" + URLEncoder.encode(request.getParameter(name), "utf-8") + "&";
			}
		}

		if (targetParams.length() > 1) {
			reqUrl += "?" + targetParams;
		} else {
			return;
		}

		try {
			URL url = new URL(reqUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod(request.getMethod());
			int clength = request.getContentLength();
			if (clength > 0) {
				con.setDoInput(true);
				byte[] idata = new byte[clength];
				request.getInputStream().read(idata, 0, clength);
				con.getOutputStream().write(idata, 0, clength);
			}
			response.setContentType(con.getContentType());

			BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			PrintWriter out = response.getWriter();
			while ((line = rd.readLine()) != null) {
				out.println(line);
			}
			rd.close();

		} catch (Exception e) {
			response.setStatus(500);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	

}
