package com.jigsaw.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Str2MD5 {

	public static void main(String[] args) {
        MD5("a111111", 32);
    }
	
	public static String MD5(String sourceStr, int length){
		String result = "";
		
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(sourceStr.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for(int offset = 0; offset < b.length; offset++){
				i = b[offset];
				if(i < 0)
					i += 256;
				if(i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			if(length == 32)
				result = buf.toString();
			else if(length == 16)
				result = buf.toString().substring(8, 24);
			System.out.println("MD5(" + sourceStr + ",32) = " + result);
			System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
			
		}catch(NoSuchAlgorithmException e){
			System.out.println(e);
		}
		return result;
	}
}
