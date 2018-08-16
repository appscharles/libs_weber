package com.appscharles.libs.weber.extractors;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.08.2018
 * Time: 15:49
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class By {

    public static IElementsExtractor id(String id){
        return new ByIdElementsExtractor(id);
    }
}
