package com.company.flow;

import java.nio.ByteBuffer;

public class ByteBufferPutFlow {

    public byte[] byteBufferPut(ByteBuffer byteBuffer, String matricula, String identificador) {
        byteBuffer.putShort((short) 1);
        byteBuffer.putInt(Integer.parseInt(matricula));
        byteBuffer.putInt(Integer.parseInt(identificador));
        return byteBuffer.array();
    }
}
