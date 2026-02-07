package ac4y.indicator.service.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import ac4y.base.http.Ac4yHttpService;

import ac4y.indicator.service.object.Ac4yIndicatorObjectService;
import ac4y.indicator.service.object.transfer.TransferRequest;

import javax.xml.bind.JAXBException;
/*
class Constants {
    static final String TEXTHTML = "text/html";
    static byte[] getIndexHTML(String name) throws IOException {

        if (name == null) {
            name = "Anonymous";
        }
        String content = new String(
                Files.readAllBytes(Paths.get("./static/index.html"))
        );
        content = content.replace("{name}", name);
        return content.getBytes();

    }
} // Constants
*/

class Constants {

	static final String TEXTHTML = "text/html";
	static final String APPLICATIONJSON = "application/json";
	static final String CONTENTTYPE = "Content-Type";
/*
	static byte[] getIndexHTML(String name) throws IOException {

		if (name == null) {
			name = "Anonymous";
		}
		String content = new String(
				Files.readAllBytes(Paths.get("./static/index.html"))
		);
		content = content.replace("{name}", name);
		return content.getBytes();

	}
	*/
} // Constants

public class Ac4yIndicatorHttpService extends Ac4yHttpService {

	private static final Logger LOG = LogManager.getLogger(Ac4yIndicatorHttpService.class);

	public Ac4yIndicatorHttpService(int aPort){
		setPort(aPort);
	}
	
	public Ac4yIndicatorHttpService(){}
	
	private int port = 8002;
	
	public int getPort() {
			return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPostRequest(HttpExchange request) throws IOException {

	    String requestBody =
				new BufferedReader(
						new InputStreamReader(
								request.getRequestBody()
						)
				)
				.lines()
				.collect(
						Collectors.joining("\n")
				);

		//Object d2 = request.getRequestBody();

		return requestBody;
/*
		return 
			new BufferedReader(
				new InputStreamReader(
					request.getRequestBody()
				)
			).readLine();
*/
	} // getPostRequest
	
	public static void main(String[] args) throws Exception {
		  
		Ac4yIndicatorHttpService ac4yIndicatorHttpService = 
			new Ac4yIndicatorHttpService(
					Integer.valueOf(args[0]) //new Ac4yTennisChampyHttpService().getPort()
			);

		HttpServer server = 
			HttpServer.create(
				new InetSocketAddress(
						ac4yIndicatorHttpService.getPort()
				)
				,0
			);

		server.createContext("/transfer", ac4yIndicatorHttpService.new Transfer());
		
		server.setExecutor(null); // creates a default executor
		
		server.start();
		  
	} // main
	
	class Transfer implements HttpHandler {
/*
		void sendResponse(HttpExchange t, int retCode, byte[] data) throws IOException {
			if (t != null) {
				t.sendResponseHeaders(retCode, data.length);
				OutputStream os = t.getResponseBody();
				os.write(data);
				os.close();
			}
		}
		*/

		public void writeResponse(HttpExchange httpExchange, String response) throws IOException {

			response = "{'name':'árvíztűrő tükörfúrógép'}";
			response = "{'name':'árvíztűrő árvíztűrő'}";
			//int size = (int)response.length();

			httpExchange.sendResponseHeaders(200, (long)response.length());

			OutputStream os = httpExchange.getResponseBody();

			//int size = response.getBytes(StandardCharsets.UTF_8).length;
			int size = response.getBytes().length;
			//size=13;
			os.write(response.getBytes(), 0, size-1 );

			os.close();

		} // writeResponse

	    public void handle(HttpExchange exchange) throws IOException {

	    	exchange.getResponseHeaders().set(Constants.CONTENTTYPE, Constants.APPLICATIONJSON);

			try {

				String request = getPostRequest(exchange);

				LOG.info("\nrequest:\n"+request);

				//Object d1 = getPostRequest(exchange);
				//TransferRequest request= (TransferRequest) new TransferRequest().getFromJson(getPostRequest(exchange));
				/*
				String response =
						new Ac4yIndicatorObjectService().transfer(
								(TransferRequest) new TransferRequest().getFromJson(getPostRequest(exchange))
						).getAsJson();
*/

				String response =
						new Ac4yIndicatorObjectService().transfer(
						//(TransferRequest) new TransferRequest().getFromJson(getPostRequest(exchange))
							(TransferRequest) new TransferRequest().getFromJson(request)
						).getAsJson();

				LOG.info("\nresponse:\n"+response);

				writeResponse(
						exchange
						//,"{\"result\":\"ok\"}"
						,response
				);

			//} catch (JAXBException e) {
			} catch (Exception e) {
				e.printStackTrace();
				writeResponse(
						exchange
						,"{\"error\":\""+e.getLocalizedMessage()+"\"}"
						//,e.getLocalizedMessage()
						//,new TransferRequest().getAsJson()
						//,"{\"res\":\"leírószövegféle\"}"
				);
			}
		}
	} // Transfer

} // Ac4yIndicatorHttpService