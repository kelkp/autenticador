package com.company.flow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ScannerFlow {

    public void read(String hostName, Integer serverPort, String command, Integer matricula, Integer identifier) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[5];
        input = in.readLine().split(" ");


        hostName = input[0];
        serverPort = Integer.parseInt(input[1]);
        command = input[2];
        matricula = Integer.parseInt(input[3]);
        identifier = Integer.parseInt(input[4]);

    }
}
