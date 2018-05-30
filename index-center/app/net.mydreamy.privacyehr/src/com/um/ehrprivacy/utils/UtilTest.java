package com.um.ehrprivacy.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import com.auth0.jwt.JWTVerifyException;

public class UtilTest {
	
	
	public String getProperties(String prop) throws IOException{
		
		Properties p = new Properties();
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("daoConfig.properties");
		System.out.println(this.getClass().getResource("/"));
		if(in != null){
			p.load(in);
			System.out.println("success");
		}
		System.out.println(p.size());
		return p.getProperty(prop);
	}
	

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, IOException, JWTVerifyException {
		// TODO Auto-generated method stub
//		String CLIENT_SECRET = "PGD-EHR";
//		String tokens = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImRvY3RvcmdvdiIsImhvc3BpdGFsIjoiR09WIiwiaWF0IjoxNDQzMTA2MTY0LCJleHAiOjE0NDMxMDk3NjQsImF1ZCI6ImRvY3RvciJ9.mv-viZtVbM6TvLKmow9S5UoOcVRa4To8rStXI-6LBC0";
//		try {
//            Map<String,Object> decodedPayload =
//                new JWTVerifier(CLIENT_SECRET,"doctor").verify(tokens.trim());
//
//            // Get custom fields from decoded Payload
//            System.out.println(decodedPayload);
//        } catch (SignatureException signatureException) {
//            System.err.println("Invalid signature!");
//        } catch (IllegalStateException illegalStateException) {
//            System.err.println("Invalid Token! " + illegalStateException);
//        }
		InputStream in = new FileInputStream("daoConfig.properties");
		Properties p = new Properties();
		p.load(in);
		
	}

}
