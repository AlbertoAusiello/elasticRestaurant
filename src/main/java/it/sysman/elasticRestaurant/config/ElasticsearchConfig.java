package it.sysman.elasticRestaurant.config;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.transport.Header;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "it.sysman.elasticRestaurant.repository")
public class ElasticsearchConfig {


    @Bean
    public RestHighLevelClient client() {
    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "-J6UZS_xrIgGz3X2c7To"));

    RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200))
            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
//            		.setDefaultHeaders(compatibilityHeaders()));

    RestHighLevelClient client = new RestHighLevelClient(builder);
    return client;
}

//    private Header[] compatibilityHeaders() {
//        return new Header[]{new Header(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"),
//        		new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
//        }

}

