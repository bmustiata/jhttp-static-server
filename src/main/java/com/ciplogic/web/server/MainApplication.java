package com.ciplogic.web.server;

public class MainApplication {
    public static void main(String[] args) {
        if (args.length < 3) {
            displayUsage();
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        String folder = args[2];

        new HttpStaticServer(host, port, folder).start();
    }

    private static void displayUsage() {
        System.out.println("Usage: java -jar jhttp-server.jar HOSTNAME PORT FOLDER\n" +
                "\n" +
                "Example:\n" +
                "    java -jar jhttp-server.jar 0.0.0.0 8080 g:/\n" +
                "or on a *nix machine:\n" +
                "    java -jar jhttp-server.jar 0.0.0.0 8080 /\n\n");
    }
}
