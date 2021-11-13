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

    public static void main(String[] args) throws IOException {

        byte[] receiveData = new byte[74];

        DatagramSocket clientSocket = datagramSocketBuilderFlow.datagramSocketbuilder();

        InetAddress IPAddress = inetAdressGetNameFlow.inetAddressGetName();

        ByteBuffer byteBuffer = byteBufferAllocateFlow.byteBufferAllocate();

        byte[] pump_on = byteBufferPutFlow.byteBufferPut(byteBuffer); // mensagem a ser enviada

        System.out.println(Arrays.toString(pump_on)); //apens para verificação

        DatagramPacket datagramPacket = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT);

        clientSocket.send(datagramPacket);

        DatagramPacket receivePacket = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);

        clientSocket.receive(receivePacket);

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println(modifiedSentence);

//        StringBuilder stringBuffer = new StringBuilder();


//        char[] hexaDecimal = modifiedSentence.toCharArray();
//        for (char c : hexaDecimal) {
//            String hexString = Integer.toHexString(c);
//            stringBuffer.append(hexString);
//        }
//
//        String result = stringBuffer.toString();
//

        clientSocket.close();
    }
}
