package com.appscharles.libs.weber;

import com.appscharles.libs.ioer.services.FileReader;
import com.appscharles.libs.logger.configurators.Log4j2Console;
import com.appscharles.libs.logger.configurators.Log4jConsole;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.logging.log4j.Level;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * The type Test case.
 */
public class TestCase {

    @Before
    public void before(){
        new Log4j2Console(Level.WARN).config();
       new Log4jConsole(org.apache.log4j.Level.WARN).config();
    }

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    public String readTestResource(String testResource) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(testResource);
        File file = new File(url.getPath());
        return FileReader.read(file);
    }

    public void addRoute(String route, String content, WireMockRule wireMockRule){
        addRoute(route, 0, content, wireMockRule);
    }

    public void addRoute(String route,Integer fixedDelay, String content, WireMockRule wireMockRule){
        wireMockRule.stubFor(get(urlEqualTo(route))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/html; charset=utf-8")
                        .withBody(content).withFixedDelay(fixedDelay)));
    }
}
