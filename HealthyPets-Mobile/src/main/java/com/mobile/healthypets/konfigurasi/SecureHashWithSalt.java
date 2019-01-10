package com.mobile.healthypets.konfigurasi;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Penerapan algoritam Secure Hash dengan memakai Salt.
 * Metode: PBKDF2 dan diberi salt yang dibangkitkan dengan metode SHA-1. 
 * 
 * Sumber kode tulisan Jerry Orr di Java Code Geeks: 
 * http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
 */

public class SecureHashWithSalt {
	// Algoritma untuk membangkitkan bilangan random
	private final static String RANDOM_NUMBER_GENERATOR = "SHA1PRNG";
	// PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
	// specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
	private final static String ALGORITMA_ENKRIPSI = "PBKDF2WithHmacSHA1";
	// Panjang salt yang harus dibangkitkan dalam bit
	private final static int SALT_LENGTH = 64; // Dalam bit
	// SHA-1 generates 160 bit hashes, so that's what makes sense here
	private final static int PANJANG_ENKRIPSI = 160; // Dalam bit
	// Pick an iteration count that works for you. 
	// + The NIST recommends at least 1,000 iterations:
	//   http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
	// + iOS 4.x reportedly uses 10,000:
	//   http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
	private final static int JUMLAH_ITERASI = 20000;

    /**
	 * Buat salt untuk dipakai pada proses enkripsi
	 * Salt adalah bilangan random yang ditambahkan ke proses enkripsi untuk 
	 * menaikkan tingkat keamanannya.
	 * 
	 * @return Salt dengan panjang yang ditentukan
	 *  
	 * @throws NoSuchAlgorithmException Jika algoritma untuk membangkitkan 
	 * 									bilangan random tidak ada
	 */
    public byte[] buatSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance(RANDOM_NUMBER_GENERATOR);
        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[SALT_LENGTH / 8];
        
        random.nextBytes(salt);
        
        return salt;
    }    
    
    /**
     * Lakukan secure hash dengan memakai salt yang diberikan
     * 
     * @param pesan Pesan yang hendak di secure hash
     * @param salt  Salt yang akan dipakai untuk secure hash agar tingkat 
     * 				keamanannya lebih baik
     * 
     * @return Hasil secure hash sebagai array byte
     * 
	 * @throws NoSuchAlgorithmException Jika algoritma untuk secure hash tidak 
	 * 									ada
     * @throws InvalidKeySpecException Jika spesifikasi enkripsi yang diminta 
     * 								   tidak sah
     */
    public byte[] enkripsi(String pesan, byte[] salt)
            	  throws NoSuchAlgorithmException, InvalidKeySpecException {    	
    	
    	KeySpec spec = new PBEKeySpec(pesan.toCharArray(),
    								  salt, 
    								  JUMLAH_ITERASI, 
    								  PANJANG_ENKRIPSI);
    	
    	return SecretKeyFactory.getInstance(ALGORITMA_ENKRIPSI)
    						   .generateSecret(spec)
    						   .getEncoded();
    }    

    /**
     * Membandingkan apakah suatu pesan sama dengan pesan lain yang telah 
     * dienkripsi dengan memakai algoritma ini 
     * 
     * @param pesan			Password yang mau diperiksa
     * @param pesanEnkripsi  Password enkripsi yang tersimpan
     * @param salt				Salt yang dipakai untuk proses secure hash
     * 
     * @return True jika sama
     * 
	 * @throws NoSuchAlgorithmExcem,ption Jika algoritma untuk secure hash tidak 
	 * 									ada
     * @throws InvalidKeySpecException Jika spesifikasi enkripsi yang diminta 
     * 								   tidak sah
     */
    public boolean sama(String pesan, 
    					byte[] pesanEnkripsi, 
    					byte[] salt)
                   throws NoSuchAlgorithmException, InvalidKeySpecException {
    	
    	// Encrypt the clear-text password using the same salt that was used to
    	// encrypt the original password
    	byte[] encryptedAttemptedPassword = enkripsi(pesan, salt);
    	// Authentication succeeds if encrypted password that the user entered
    	// is equal to the stored hash
    	return Arrays.equals(pesanEnkripsi, encryptedAttemptedPassword);
     }    
}
