package com.company;

import com.company.constants.ClientConstants;
import com.company.flow.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {

    public static DatagramSocketBuilderFlow datagramSocketBuilderFlow = new DatagramSocketBuilderFlow();
    public static InetAdressGetNameFlow inetAdressGetNameFlow = new InetAdressGetNameFlow();
    public static ByteBufferAllocateFlow byteBufferAllocateFlow = new ByteBufferAllocateFlow();
    public static ByteBufferPutFlow byteBufferPutFlow = new ByteBufferPutFlow();
    public static DatagramPacketBuilderFlow datagramPacketBuilderFlow = new DatagramPacketBuilderFlow();
    public static DatagramReceivedPacketBuilderFlow datagramReceivedPacketBuilderFlow = new DatagramReceivedPacketBuilderFlow();
    public static ScannerFlow scannerFlow = new ScannerFlow();

    public static void main(String[] args) throws IOException {

        String hostName = null;
        Integer serverPort = null;
        String command = null;
        Integer matrícula = null;
        Integer identifier = null;

        scannerFlow.read(hostName, serverPort, command, matrícula, identifier);

        System.out.println(matrícula);

        byte[] receiveData = new byte[74];

        DatagramSocket clientSocket = datagramSocketBuilderFlow.datagramSocketbuilder();

        InetAddress IPAddress = inetAdressGetNameFlow.inetAddressGetName(hostName);

        ByteBuffer byteBuffer = byteBufferAllocateFlow.byteBufferAllocate();

        byte[] pump_on = byteBufferPutFlow.byteBufferPut(byteBuffer, matrícula, identifier); // mensagem a ser enviada

        System.out.println(Arrays.toString(pump_on)); //apens para verificação

        DatagramPacket datagramPacket = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, serverPort);

        clientSocket.send(datagramPacket);

        DatagramPacket receivePacket = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println(modifiedSentence);

        byte[] byteBuffer1 = modifiedSentence.getBytes();

        DatagramPacket datagramPacket1 = datagramPacketBuilderFlow.datagramPacketBuilder(byteBuffer1, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT);

        clientSocket.send(datagramPacket1);

        System.out.println("Pacote enviado...");

        DatagramPacket receivePacket1 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);

        clientSocket.receive(receivePacket1);

        String modifiedSentence1 = new String(receivePacket1.getData());

        System.out.println(modifiedSentence1);



        StringBuilder stringBuffer = new StringBuilder();


        char[] hexaDecimal = modifiedSentence.toCharArray();
        for (char c : hexaDecimal) {
            String hexString = Integer.toHexString(c);
            stringBuffer.append(hexString);
        }

        String result = stringBuffer.toString();

//        System.out.println(result);


        clientSocket.close();
    }
}
