package com.company;

import com.company.constants.ClientConstants;
import com.company.flow.*;
import com.company.models.AuthReq;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Main {

    public static DatagramSocketBuilderFlow datagramSocketBuilderFlow = new DatagramSocketBuilderFlow();
    public static InetAdressGetNameFlow inetAdressGetNameFlow = new InetAdressGetNameFlow();
    public static ByteBufferAllocateFlow byteBufferAllocateFlow = new ByteBufferAllocateFlow();
    public static ByteBufferPutFlow byteBufferPutFlow = new ByteBufferPutFlow();
    public static DatagramPacketBuilderFlow datagramPacketBuilderFlow = new DatagramPacketBuilderFlow();
    public static DatagramReceivedPacketBuilderFlow datagramReceivedPacketBuilderFlow = new DatagramReceivedPacketBuilderFlow();

    public static void main(String[] args) throws IOException, ParseException {

        AuthReq authReq = new AuthReq();
        JSONObject jsonObject = authReq.generateJsonBody();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(jsonObject.toJSONString());
        oos.flush();
        byte[] pump_on = baos.toByteArray();
        byte[] receiveData = new byte[82];

        DatagramPacket receivePacket = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);

        System.out.println(jsonObject);

        DatagramSocket clientSocket = datagramSocketBuilderFlow.datagramSocketbuilder();

        InetAddress IPAddress = inetAdressGetNameFlow.inetAddressGetName(ClientConstants.SERVER_HOST_NAME);

//        ByteBuffer byteBuffer = byteBufferAllocateFlow.byteBufferAllocate();

//        byte[] pump_on = byteBufferPutFlow.byteBufferPut(byteBuffer, authReq.getAuth(), authReq.getAuth()); // mensagem a ser enviada

        DatagramPacket datagramPacket1 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.serverPort1);
        DatagramPacket datagramPacket2 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.serverPort2);
        DatagramPacket datagramPacket3 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.serverPort3);
        DatagramPacket datagramPacket4 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.serverPort4);

        clientSocket.send(datagramPacket1);
        clientSocket.send(datagramPacket2);
        clientSocket.send(datagramPacket3);
        clientSocket.send(datagramPacket4);

        clientSocket.receive(receivePacket);
        String modifiedSentence = new String(receivePacket.getData());
        JSONParser parser = new JSONParser();
        JSONObject receivedJson = (JSONObject) parser.parse(modifiedSentence);

        System.out.println(receivedJson);


//        String modifiedSentence = new String(receivePacket.getData());
//
//        JSONObject json = new JSONObject();
//
//        json.put("type", modifiedSentence);
//
//        System.out.println(json);
//
//        byte[] byteBuffer1 = modifiedSentence.getBytes();
//
////        DatagramPacket datagramPacket1 = datagramPacketBuilderFlow.datagramPacketBuilder(byteBuffer1, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT);
//
//        System.out.println("Checking");
//
//        clientSocket.send(datagramPacket1);
//
//        DatagramPacket receivePacket1 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(receiveData, receiveData.length);
//
//        clientSocket.receive(receivePacket1);
//
//        String modifiedSentence1 = new String(receivePacket1.getData());
//
//        System.out.println(modifiedSentence1);

        clientSocket.close();
    }
}
