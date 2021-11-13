package com.company.flow;

import java.net.DatagramPacket;

public class DatagramReceivedPacketBuilderFlow {

    public DatagramPacket datagramPacketReceived(byte[] byteReceived, int bufferSize) {
        return new DatagramPacket(byteReceived, bufferSize);
    }
}
