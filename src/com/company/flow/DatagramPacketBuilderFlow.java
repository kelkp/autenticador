package com.company.flow;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class DatagramPacketBuilderFlow {

    public DatagramPacket datagramPacketBuilder(byte[] byteData, int bufferSize, InetAddress ipAdress, int port) {
        return new DatagramPacket(byteData, bufferSize, ipAdress, port);
    }
}
