package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        int matricula = 2014066803;
        int identificador = 2323563;

        byteBuffer.putShort((short) 1);
        byteBuffer.putInt(matricula);
        byteBuffer.putInt(identificador);
        byte[] pump_on = byteBuffer.array();


        System.out.println(Arrays.toString(pump_on));

        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName("auth20212.dcc023.2advanced.dev");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = "2014066803";
        sendData = sentence.getBytes();


        System.out.println("Digite o texto a ser enviado ao servidor: ");


        DatagramPacket datagramPacket = new DatagramPacket(pump_on, sendData.length, IPAddress, 51212);

        System.out.println("Enviando pacote UDP para " + "auth20212.dcc023.2advanced.dev" + ":" + 51212);
        clientSocket.send(datagramPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);
        System.out.println("Pacote UDP recebido...");

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("Texto recebido do servidor:" + modifiedSentence);
        clientSocket.close();
        System.out.println("Socket cliente fechado!");
    }
}
