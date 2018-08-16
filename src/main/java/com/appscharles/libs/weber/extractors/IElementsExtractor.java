package com.appscharles.libs.weber.extractors;

import com.appscharles.libs.weber.exceptions.WeberException;
import org.w3c.dom.Node;

import java.util.List;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.08.2018
 * Time: 15:38
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IElementsExtractor extends IWebViewable{

    List<Node> getNodes() throws WeberException;
}
