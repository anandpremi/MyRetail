package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
public class Connection {

	@Bean
	@ConfigurationProperties("product")
	public ConnectionConfig productConnectionConfig() {
		return new ConnectionConfig();
	}
	
	
    private HttpClient configureHttpClient(int readtimeout, int writetimeout,
            int connectiontimeout) {

		return HttpClient.create().tcpConfiguration(client -> client
		.doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(readtimeout, TimeUnit.MILLISECONDS))
		.addHandlerLast(new WriteTimeoutHandler(writetimeout, TimeUnit.MILLISECONDS)))
		.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectiontimeout));
		}

    
	private Connection getConfiguredConnection(WebClient.Builder webClientBuilder,
			ConnectionConfig connectionConfig)  {

		webClientBuilder.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		 HttpClient httpClient
         = this.configureHttpClient(readTimeout, writeTimeout, connectTimeOut);
    	ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

		return Connection.builder()
		        .webClient(webClientBuilder.clone().clientConnector(connector)
		                .baseUrl(connectionConfig.getUrl()))
		                .build())
		        .connectionConfig(connectionConfig)
		        .build();
	}
	
	
}
