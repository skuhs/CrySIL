package org.crysil.instance.demo;

import java.security.cert.CertificateException;

import org.crysil.builders.KeyBuilder;
import org.crysil.builders.PayloadBuilder;
import org.crysil.communications.http.HttpJsonTransmitter;
import org.crysil.errorhandling.CrySILException;
import org.crysil.protocol.Request;
import org.crysil.protocol.Response;
import org.crysil.protocol.header.StandardHeader;
import org.crysil.protocol.payload.crypto.encrypt.PayloadEncryptRequest;

public class Demo_encrypt {

	String rawPublicKey  = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwQSeSuzJ9MndkJeBkymovvDiFjCOYsd8zPgBGw2+euFfZI3aNJ32dZWMfxW1A2qDF39MazLsCAsE6Z2Vo5BQXz2xEiPlmTko8cUrjM9Qsj0WXc0vUmYKbPsrghco9tQ98PMVQn95kUUTpLDOSPFTebL6rolU+WEtdC1LQtrLaNhrowWJ0OnxtGJINc//ZZFNroHWzdmg6rlu+BojePSBzEoR7/2UmeGQK47vE7KsOl4zMW99E6njCp7vanDnK92XlOfHkAH8pYpnBDzbBBwdKgTok6sfP18/HoQGN853qSq2PXLgk6CNrJoKHB5N9Lp1REO6yCZMarm4eRYCd2GIhwIDAQAB";
	String rawPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDBBJ5K7Mn0yd2Ql4GTKai+8OIWMI5ix3zM+AEbDb564V9kjdo0nfZ1lYx/FbUDaoMXf0xrMuwICwTpnZWjkFBfPbESI+WZOSjxxSuMz1CyPRZdzS9SZgps+yuCFyj21D3w8xVCf3mRRROksM5I8VN5svquiVT5YS10LUtC2sto2GujBYnQ6fG0Ykg1z/9lkU2ugdbN2aDquW74GiN49IHMShHv/ZSZ4ZArju8Tsqw6XjMxb30TqeMKnu9qcOcr3ZeU58eQAfylimcEPNsEHB0qBOiTqx8/Xz8ehAY3znepKrY9cuCToI2smgocHk30unVEQ7rIJkxqubh5FgJ3YYiHAgMBAAECggEAA4WwRFzAiLSpeJ0TKXX3ObQzv2l8IxzkGQ5KYJbK1PtbuaKqeFhV4POR5PymxuX3nJVfUWkvsL4rTkfEGu3tUYnlBGFemd1pF4ITO9+AVYzlZYTy8zDpW++dJJE66aNQ3ufR0rYvg4Og08fBU+y3dAhKTO/HDR2p7gkEIaUdeY+AxBdtYB9xEnZ7KNs9Tvcd8gYm7szcq5czNZ5g4kFZ1CM1RjUscG6KwfeTqpmmSDjjQy6PIHra5FCB5WzsuYqKxztXUUE5kdc3334evRJ7GG8SHvXHDllaX/MoDhCIqj9BCuaHyXv7H52KWUcl6gNB2MTuk5ssnHQEVymv9yELmQKBgQDtpGDabkuRdStXLMIpi0V2fJwo61+9tDrj+PNfKaQt/kVPN1L9Aj+3eRx9EBG0dWd8QGXgTiWca9iNKAfDFlbYp+YdUj+CdFBQjx4gaMmQPjS9shp+h8vyKbpe7gNnX5h2KKdt+Ioomeoz9AsU7wr/ddtP1/8bB0UhTBwQrvdnKQKBgQDP7cCclUhrfX6cKjlsH+WZsvRKV4TXa02U1hwQNhDPZWWQpkDMy00RooziPDnND7XrQ6RAD2Ls4K5VXnm+iQ+4sKWQHuknE8cnM2PSGMp25CnFMjnakAJVl2POUnV7fIT+OY0tZnaThMLF8TM20Q0cWrHjcgyC4ykSWAL75fPYLwKBgQDoaOCH/2JMaYjvgsiJFLnkfU3D/x3tS7xkhG6P3QvCJ3DlXjf9VRu3dezUqsiF8mQ48kowKn1CE37/3expcQmSbfHxLyUJknORtcZC7/hg51VxSCP9JxXgScsJWEFf8fALbwr/1BhaSNzx3nSQDpB08nCAD8BgUKXdQLAZ6OPwsQKBgGXt2BEqgUDoWSu260Vc8ZICDw1uj9mGaZa/yywLRPxWaY6aYYPDWbl+ZO/2tCMZQ4XcN+WLZWRX1D5XPPkxeXqBZfgbnxIf+O33nER/EKltuihIMeI53FsXBr863wq1BQEXN2T9KL2yREUCs6d4naO7th6YZxe2wgiTCotvs7TTAoGAJMzf8wYAJPhKGDd08KgXxDTtgntl7kegwnXJFNambg010X83i9yDKvkPZF4Nvy3FT2Bwb/qvWwN8vC9Jb55NotG0JbNVTdf75CS12G/U9YZxAHXAvdDdAUKI1jlL6n0lCU6vcrcCK35mde7lbnicq7TG8F/zEnIt82f7NzKXBDw=";
	static String rawCert       = "MIICzjCCAbagAwIBAgIGAVLViUrLMA0GCSqGSIb3DQEBCwUAMBExDzANBgNVBAMTBmNyeXNpbDAeFw0xNjAyMTExMjUxMzBaFw0xNjAxMjcyMTA5NDFaMCMxITAfBgNVBAMMGHN0YXRpY0tleUVuY3J5cHRpb25BY3RvcjCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAMEEnkrsyfTJ3ZCXgZMpqL7w4hYwjmLHfMz4ARsNvnrhX2SN2jSd9nWVjH8VtQNqgxd/TGsy7AgLBOmdlaOQUF89sRIj5Zk5KPHFK4zPULI9Fl3NL1JmCmz7K4IXKPbUPfDzFUJ/eZFFE6SwzkjxU3my+q6JVPlhLXQtS0Lay2jYa6MFidDp8bRiSDXP/2WRTa6B1s3ZoOq5bvgaI3j0gcxKEe/9lJnhkCuO7xOyrDpeMzFvfROp4wqe72pw5yvdl5Tnx5AB/KWKZwQ82wQcHSoE6JOrHz9fPx6EBjfOd6kqtj1y4JOgjayaChweTfS6dURDusgmTGq5uHkWAndhiIcCAwEAAaMaMBgwFgYDVR0lAQH/BAwwCgYIKwYBBQUHAwgwDQYJKoZIhvcNAQELBQADggEBAAh7motLy9RdpvFCEgqMidrgON+n3570OTBjgePsWxHLXzdWRiKevmAI1VAi7K+Qr7KqdZhE7CM5KM5tmhUJ+9SorPmEPbyeaA8SVMDF0whibena3KorBTqIlTkYLwZL9UXkTnOb876VlijxqABKt/rOTP7dZrgErqgcbbTo8KVi2BueiXjLwlV8CJK4s2BWYcLPdMO+Z0jGIjcI4/wuk+60oR8tb5vUwWH62pXw+1IgpnVrklkkM3tNQ0v38A9xKgrK3c1UL7F9KWpZgsCkUR8lfDP0wHAx+Yd5fDp4vTdxSyH/WydLxy2syo1hyoRSE4SXWJBj+N0C+IgGOX3GsNQ=";

	static PayloadEncryptRequest[] get_payloads() throws CertificateException {
		return new PayloadEncryptRequest[] {
			PayloadBuilder.buildEncryptRequest("is ignored anyways", "data", KeyBuilder.buildKeyHandle("testkey", "1")),
			PayloadBuilder.buildEncryptRequest("is ignored anyways", "data", KeyBuilder.buildInternalCertificate("testkey", "1", rawCert)),
			PayloadBuilder.buildEncryptRequest("is ignored anyways", "data", KeyBuilder.buildExternalCertificate(rawCert)) 
		};
	}
	
	public static void main(final String[] args) throws CrySILException, CertificateException {
	
	for (PayloadEncryptRequest payload_encrypt : get_payloads()) {
		
		//request
	    final Request request = new Request();
	    
	    //create and set header
	    StandardHeader header = new StandardHeader();
	    header.setSessionId("sessionId");
	    request.setHeader(header);
	    
	    //create and set payload
	    //PayloadEncryptRequest payload_encrypt = PayloadBuilder.buildEncryptRequest("algorithm", "data to encrypt", KeyBuilder.buildKeyHandle("testkey", "1"));
	    request.setPayload(payload_encrypt);
	    
	    //json transmitter (http)
	    HttpJsonTransmitter httpJsonTransmitter = new HttpJsonTransmitter();
	    httpJsonTransmitter.setTargetURI("http://localhost:8080/demo_webservice/json");
	    final Response authedRequest = httpJsonTransmitter.take(request);
	    
	    System.out.println(authedRequest.getPayload());	
	}    
  }

}
