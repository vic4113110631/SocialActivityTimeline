package com.model;

/**
 * Created by WeiRenChen on 2016/6/18.
 */
//reference: http://samsharehome.blogspot.tw/2009/01/utf-8-bominputstreamreader.html
/*
    http://langgufu.iteye.com/blog/2177659
    http://www.rgagnon.com/javadetails/java-handle-utf8-file-with-bom.html
    http://puremonkey2010.blogspot.tw/2010/10/java-javabomutf-8.html
*/
import java.io.IOException;
import java.io.PushbackInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import sun.nio.cs.StreamDecoder;

public class UnicodeInputStreamReader extends InputStreamReader
{
    private static final int BOM_SIZE = 4;
    private final StreamDecoder decoder;
    private PushbackInputStream pushBack;
    private String encode;
    private String defaultEnc;

    public UnicodeInputStreamReader(InputStream input, String defaultEnc) throws UnsupportedEncodingException
    {
        super(input);
        try {
            this.defaultEnc = defaultEnc;
            pushBack = new PushbackInputStream(input, BOM_SIZE);
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        decoder = StreamDecoder.forInputStreamReader(pushBack, this, encode);
    }

    private void init() throws IOException
    {
        byte[] bom = new byte[BOM_SIZE];
        int n, unread;

        // 初始讀取一次
        n = pushBack.read(bom, 0, bom.length);

        // 比對表頭
        if ((bom[0] == (byte) 0x00) && (bom[1] == (byte) 0x00) && (bom[2] == (byte) 0xFE) && (bom[3] == (byte) 0xFF)) {
            encode = "UTF-32BE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE) && (bom[2] == (byte) 0x00) && (bom[3] == (byte) 0x00)) {
            encode = "UTF-32LE";
            unread = n - 4;
        } else if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB) && (bom[2] == (byte) 0xBF)) {
            encode = "UTF-8";
            unread = n - 3;
        } else if ((bom[0] == (byte) 0xFE) && (bom[1] == (byte) 0xFF)) {
            encode = "UTF-16BE";
            unread = n - 2;
        } else if ((bom[0] == (byte) 0xFF) && (bom[1] == (byte) 0xFE)) {
            encode = "UTF-16LE";
            unread = n - 2;
        } else {
            // 如果沒有找到任何表頭, 則退回長度等於原先總長
            encode = defaultEnc;
            unread = n;
        }
        System.out.println("has BOM=" + ((unread == n) ? false : true) + ", encode=" + encode + ", read=" + n + ", unread=" + unread);
        // 計算應該退回多少byte
        if (unread > 0)
            pushBack.unread(bom, (n - unread), unread);
    }

    public String getEncoding()
    {
        return encode;
    }

    public int read() throws IOException
    {
        return decoder.read();
    }

    public int read(char cbuf[], int offset, int length) throws IOException
    {
        return decoder.read(cbuf, offset, length);
    }

    public boolean ready() throws IOException
    {
        return decoder.ready();
    }

    public void close() throws IOException
    {
        decoder.close();
    }
}