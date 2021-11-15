package com.company;

import com.company.constants.ClientConstants;
import com.company.flow.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public class Main {

    public static DatagramSocketBuilderFlow datagramSocketBuilderFlow = new DatagramSocketBuilderFlow();
    public static InetAdressGetNameFlow inetAdressGetNameFlow = new InetAdressGetNameFlow();
    public static ByteBufferAllocateFlow byteBufferAllocateFlow = new ByteBufferAllocateFlow();
    public static ByteBufferPutFlow byteBufferPutFlow = new ByteBufferPutFlow();
    public static DatagramPacketBuilderFlow datagramPacketBuilderFlow = new DatagramPacketBuilderFlow();
    public static DatagramReceivedPacketBuilderFlow datagramReceivedPacketBuilderFlow = new DatagramReceivedPacketBuilderFlow();

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] input = new String[5];
        input = in.readLine().split(" ");


        String hostName = input[0];
        Integer serverPort = Integer.parseInt(input[1]);
        String command = input[2];
        Integer matricula = Integer.parseInt(input[3]);
        Integer identifier = Integer.parseInt(input[4]);

        byte[] receiveData = new byte[74];

        DatagramSocket clientSocket = datagramSocketBuilderFlow.datagramSocketbuilder();

        InetAddress IPAddress = inetAdressGetNameFlow.inetAddressGetName(hostName);

        ByteBuffer byteBuffer = byteBufferAllocateFlow.byteBufferAllocate();

        byte[] pump_on = byteBufferPutFlow.byteBufferPut(byteBuffer, matricula, identifier); // mensagem a ser enviada

        DatagramPacket datagramPacket = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, serverPort);

        clientSocket.send(datagramPacket);

        DatagramPacket receivePacket = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println(modifiedSentence);

        byte[] byteBuffer1 = modifiedSentence.getBytes();

        DatagramPacket datagramPacket1 = datagramPacketBuilderFlow.datagramPacketBuilder(byteBuffer1, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT);

        clientSocket.send(datagramPacket1);

        DatagramPacket receivePacket1 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);

        clientSocket.receive(receivePacket1);

        String modifiedSentence1 = new String(receivePacket1.getData());

        System.out.println(modifiedSentence1);

        clientSocket.close();
    }
}
