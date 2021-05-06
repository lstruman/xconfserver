package com.comcast.xconf;

import org.junit.Test;

import java.util.zip.CRC32;
// import com.comcast.xconf.dcm.ruleengine.LogUploaderService;

public class Crc32Test {
    public String getHashValue(String jsonConfig) {
        CRC32 crc = new CRC32();
        crc.update(jsonConfig.getBytes());
        String hashValue = Long.toHexString(crc.getValue());
        return hashValue;
    }

    @Test
    public void testOne() {
        // String line = "hello world";
        // String line = "robin raven starfire beastboy cyborg";
        String line = "Test vector from febooti.com";
        String hashed = getHashValue(line);
        System.out.println("hashed = " + hashed);
    }
}
