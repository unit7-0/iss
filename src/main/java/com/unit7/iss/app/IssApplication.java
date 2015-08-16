package com.unit7.iss.app;

import com.unit7.iss.app.conf.GrizzlyServer;

import java.io.IOException;

/**
 * IssApplication class.
 *
 */
public class IssApplication {

    /**
     * IssApplication method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        final long start = System.currentTimeMillis();

        final GrizzlyServer server = new GrizzlyServer();
        server.start();

        System.out.println("Start time " + (System.currentTimeMillis() - start) + " ms");

        System.out.println(String.format(
                "Jersey app started with WADL available at %sapplication.wadl\nHit enter to stop it...",
                        GrizzlyServer.BASE_URI));


        System.in.read();

        server.stop();
    }
}

