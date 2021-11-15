package com.company.flow;

import com.company.constants.ClientConstants;

import java.nio.ByteBuffer;

public class ByteBufferPutFlow {

    public byte[] byteBufferPut(ByteBuffer byteBuffer, Integer matricula, Integer identificador) {
        byteBuffer.putShort((short) 1);
        byteBuffer.putInt(ClientConstants.MATRICULA);
        byteBuffer.putInt(ClientConstants.IDENTIFIER);
        return byteBuffer.array();
    }
}
