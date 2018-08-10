package com.appscharles.libs.weber.finders;

import com.appscharles.libs.weber.exceptions.WeberException;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The type Available port finder.
 */
public class AvailablePortFinder {

    /**
     * Find between integer.
     *
     * @param from the from
     * @param to   the to
     * @return the integer
     * @throws WeberException the weber exception
     */
    public static Integer findBetween(Integer from, Integer to) throws WeberException {
        for (int i = from; i <=to ; i++) {
            try(ServerSocket socket = new ServerSocket(i)) {
                socket.close();
                return i;
            } catch (IOException e) {
                continue;
            }
        }
        throw new WeberException("Available port is not found");
    }
}
