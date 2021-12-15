package com.company;

import com.company.constants.ClientConstants;
import com.company.flow.*;
import com.company.models.AuthReq;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Main {

    public static DatagramSocketBuilderFlow datagramSocketBuilderFlow = new DatagramSocketBuilderFlow();
    public static InetAdressGetNameFlow inetAdressGetNameFlow = new InetAdressGetNameFlow();
    public static DatagramPacketBuilderFlow datagramPacketBuilderFlow = new DatagramPacketBuilderFlow();
    public static DatagramReceivedPacketBuilderFlow datagramReceivedPacketBuilderFlow = new DatagramReceivedPacketBuilderFlow();

    public static void main(String[] args) throws IOException, ParseException {

        AuthReq authReq = new AuthReq();
        JSONObject jsonObject = authReq.generateJsonBody();

        String jsonText = jsonObject.toJSONString();
        byte[] pump_on = jsonText.getBytes(StandardCharsets.US_ASCII);
        byte[] buffer = new byte[1000000000];

        System.out.println(jsonObject);

        DatagramSocket clientSocket = datagramSocketBuilderFlow.datagramSocketbuilder();

        InetAddress IPAddress = inetAdressGetNameFlow.inetAddressGetName(ClientConstants.SERVER_HOST_NAME);

        DatagramPacket receivePacketRiver1 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(buffer, buffer.length);
        DatagramPacket receivePacketRiver2 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(buffer, buffer.length);
        DatagramPacket receivePacketRiver3 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(buffer, buffer.length);
        DatagramPacket receivePacketRiver4 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(buffer, buffer.length);

        DatagramPacket datagramPacket1 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT1);
        DatagramPacket datagramPacket2 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT2);
        DatagramPacket datagramPacket3 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT3);
        DatagramPacket datagramPacket4 = datagramPacketBuilderFlow.datagramPacketBuilder(pump_on, ClientConstants.BUFFER_SIZE, IPAddress, ClientConstants.SERVER_PORT4);
//
        clientSocket.send(datagramPacket1);
        clientSocket.receive(receivePacketRiver1);

        String modifiedSentence = new String(receivePacketRiver1.getData());
        JSONParser parser = new JSONParser();
        JSONObject receivedJson = (JSONObject) parser.parse(modifiedSentence);

        System.out.println(receivedJson);

        clientSocket.send(datagramPacket2);
        clientSocket.receive(receivePacketRiver2);

        clientSocket.send(datagramPacket3);
        clientSocket.receive(receivePacketRiver3);

        clientSocket.send(datagramPacket4);
        clientSocket.receive(receivePacketRiver4);

        System.out.println("Autenticações realizadas");




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
//        DatagramPacket receivePacket1 = datagramReceivedPacketBuilderFlow.datagramPacketReceived(buffer, buffer.length);
//
//        clientSocket.receive(receivePacket1);
//
//        String modifiedSentence1 = new String(receivePacket1.getData());
//
//        System.out.println(modifiedSentence1);

        clientSocket.close();
    }
}
