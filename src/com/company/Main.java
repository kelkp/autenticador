package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        ByteBuffer pump_on_buf = ByteBuffer.allocate(10);

        int matricula = 2014066803;
        int identificador = 2323133;

        pump_on_buf.putShort((short) 1);
        pump_on_buf.putInt(matricula);
        pump_on_buf.putInt(identificador);
        byte[] pump_on = pump_on_buf.array();
        System.out.println(Arrays.toString(pump_on));

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("auth20212.dcc023.2advanced.dev");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = "2014066803";
        sendData = sentence.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(pump_on);


        System.out.println("Digite o texto a ser enviado ao servidor: ");


        DatagramPacket sendPacket = new DatagramPacket(pump_on, sendData.length, IPAddress, 51212);

        System.out.println("Enviando pacote UDP para " + "auth20212.dcc023.2advanced.dev" + ":" + 51212);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);
        System.out.println("Pacote UDP recebido...");

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("Texto recebido do servidor:" + modifiedSentence);
        clientSocket.close();
        System.out.println("Socket cliente fechado!");
    }
}
