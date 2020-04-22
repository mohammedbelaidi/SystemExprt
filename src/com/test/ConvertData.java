/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author MOHAMMED-PC
 */
public class ConvertData {
    public static byte[] convertFileContentToBlob(String filePath) throws IOException {
   byte[] fileContent = null;
   // initialize string buffer to hold contents of file
   StringBuffer fileContentStr = new StringBuffer("");
   BufferedReader reader = null;
   try {
        // initialize buffered reader  
	reader = new BufferedReader(new FileReader(filePath));
	String line = null;
        // read lines of file
	while ((line = reader.readLine()) != null) {
           //append line to string buffer
           fileContentStr.append(line).append("\n");
	}
        // convert string to byte array
	fileContent = fileContentStr.toString().trim().getBytes();
   } catch (IOException e) {
	throw new IOException("Unable to convert file to byte array. " + 
              e.getMessage());
   } finally {
	if (reader != null) {
           reader.close();
	}
   }
   return fileContent;
}
    public synchronized static byte[] convertAudioInputStream2ByteArray(AudioInputStream stream) {
        byte[] array;
        try {
            array = new byte[(int)(stream.getFrameLength() * stream.getFormat().getFrameSize())];   // initialize the byte array with the length of the stream
            stream.read(array);         // write the stream's bytes into the byte array
        } catch (IOException e) {       // in case of an IOException
            e.printStackTrace();        // output error
            return new byte[0];         // return empty array
        }
        return array;
    }
    public synchronized static AudioInputStream convertByteArray2AudioInputStream(byte[] array, AudioFormat format) {
        ByteArrayInputStream bis = new ByteArrayInputStream(array);                 // byte array to ByteArrayInputStream
        AudioInputStream ais = new AudioInputStream(bis, format, (array.length / (2 * format.getChannels()))); // byteArrayInputStream to AudioInputStream
        return ais;                                                                 // return it
    }
    public static AudioInputStream writeBytesBackToStream(byte[] bytes) {
    ByteArrayInputStream baiut = new ByteArrayInputStream(bytes);
    AudioInputStream stream = null;
    try {
        stream = AudioSystem.getAudioInputStream(baiut);
    } catch (UnsupportedAudioFileException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    if(stream == null ) {
        System.out.println("WARNING: Stream read by byte array is null!");
    }
    return stream;
}
}
