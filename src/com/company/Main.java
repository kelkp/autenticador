package com.company;

import com.company.constants.ClientConstants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket();

        InetAddress IPAddress = InetAddress.getByName(ClientConstants.SERVER_HOST_NAME);

        ByteBuffer byteBuffer = ByteBuffer.allocate(ClientConstants.BUFFER_SIZE);

        byteBuffer.putShort((short) 1);
        byteBuffer.putInt(ClientConstants.MATRICULA);
        byteBuffer.putInt(ClientConstants.IDENTIFIER);
        byte[] pump_on = byteBuffer.array();

        System.out.println(Arrays.toString(pump_on));

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[74];
        String sentence = Integer.toString(ClientConstants.MATRICULA);
        sendData = sentence.getBytes();

        DatagramPacket datagramPacket = new DatagramPacket(pump_on, sendData.length, IPAddress, 51212);

        System.out.println("Enviando pacote UDP para " + ClientConstants.SERVER_HOST_NAME + ":" + ClientConstants.SERVER_PORT);
        clientSocket.send(datagramPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);
        System.out.println("Pacote UDP recebido...");

        String modifiedSentence = new String(receivePacket.getData());
        StringBuilder stringBuffer = new StringBuilder();
        char[] hexaDecimal = modifiedSentence.toCharArray();
        for (char c : hexaDecimal) {
            String hexString = Integer.toHexString(c);
            stringBuffer.append(hexString);
        }

        String result = stringBuffer.toString();



        System.out.println("Texto recebido do servidor:" + result);


        clientSocket.close();
        System.out.println("Socket cliente fechado!");
    }
}
