package arcade.database;
/*
 * Copyright 2010-2013 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.UUID;
import util.Pixmap;

import arcade.games.GameData;
import arcade.games.UserGameData;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * This sample demonstrates how to make basic requests to Amazon S3 using
 * the AWS SDK for Java.
 * <p>
 * <b>Prerequisites:</b> You must have a valid Amazon Web Services developer
 * account, and be signed up to use Amazon S3. For more information on
 * Amazon S3, see http://aws.amazon.com/s3.
 * <p>
 * <b>Important:</b> Be sure to fill in your AWS access credentials in the
 *                   AwsCredentials.properties file before you try to run this
 *                   sample.
 * http://aws.amazon.com/security-credentials
 */
public class S3Connections {
    
    private static final String BUCKET_NAME = "mycs308database";  


    private AmazonS3 myS3Instance;
    
    public S3Connections() {
        myS3Instance = new AmazonS3Client(new ClasspathPropertiesFileCredentialsProvider("AwsCredentials.properties"));
        Region usWest2 = Region.getRegion(Regions.US_WEST_2);
        myS3Instance.setRegion(usWest2);
    }
    
    public void listAllBuckets() {
        System.out.println("Listing buckets");
        for (Bucket bucket : myS3Instance.listBuckets()) {
            System.out.println(" - " + bucket.getName());
        }
    }
    
    public void putAvatarIntoBucket(String username, String filepath) {
        putFileIntoBucket("avatar" + username, filepath);
    }
    
    public void putGameThumbnailIntoBucket(String gameName, String filepath) {
        putFileIntoBucket("thumbnail" + gameName, filepath);
    }
    
    public void putAdScreenIntoBucket (String gameName, String filepath) {
        putFileIntoBucket("adscreen" + gameName, filepath);
        
    }
    
    public String getAvatar(String username) {
         return downloadObjectToFile("avatar" + username);
    }
    
    public String getThumbnail(String gameName) {
        return downloadObjectToFile("thumbnail" + gameName);
    }
    
    public String getAdScreen(String gameName) {
        return downloadObjectToFile("adscreen" + gameName);
    }
    
    public void putUserGameDataIntoBucket(String username, String gameName, UserGameData usd) {
        putFileIntoBucket("usergamedata" + username + gameName, createFileFromByteArray(serializeObject(usd)));
    }
    
    public UserGameData getUserGameDataFromBucket(String username, String gameName) {
        String tempFilePath = downloadObjectToFile("usergamedata" + username + gameName);
        byte[] data = read(createFileFromFilePath(tempFilePath));
        return (UserGameData) deserialize(data);
    }
    
    public void putGameDataIntoBucket(String gameName, GameData gd) {
        putFileIntoBucket("gamedata" + gameName, createFileFromByteArray(serializeObject(gd)));
    }
    
    public GameData getGameDataFromBucket(String gameName) {
        String tempFilePath = downloadObjectToFile("gamedata" + gameName);
        byte[] data = read(createFileFromFilePath(tempFilePath));
        return (GameData) deserialize(data);
    }
    
    public File createFileFromFilePath(String filepath) {
        return new File(filepath);
    }
    
    public byte[] read(File file) {
        byte []buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);     
        }
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally { 
            try {
                if ( ios != null ) 
                    ios.close();
            } catch ( IOException e) {
            }
        }

        return buffer;
    }

    public String createFileFromByteArray(byte[] bytes) {
        
        FileOutputStream out;
        String tempFilePath = getTempFilePath();
        try {
            out = new FileOutputStream(tempFilePath);
            out.write(bytes);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return tempFilePath;
    }
    
    public byte[] serializeObject(Object obj) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o;
        try {
            o = new ObjectOutputStream(b);
            o.writeObject(obj);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return b.toByteArray();
    }
    
    public Object deserialize(byte[] bytes) {
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o;
        try {
            o = new ObjectInputStream(b);
            return o.readObject();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void putFileIntoBucket(String key, String filepath) {
        File file = new File(filepath);
        try {
            myS3Instance.putObject(new PutObjectRequest(BUCKET_NAME, key, file));
        }
        catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        }
        catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());        
        }
    }
    
    
    public String downloadObjectToFile(String key) {
        File tempFile;
        try {
            tempFile = File.createTempFile("tempFileCS308", ".png");
            String absolutePath = tempFile.getAbsolutePath();
            System.out.println(absolutePath);
//            String tempFilePath = absolutePath.
//                substring(0,absolutePath.lastIndexOf(File.separator));
//            System.out.println(tempFilePath);
            ObjectMetadata object = myS3Instance.getObject(new GetObjectRequest(BUCKET_NAME, key), tempFile);
            return absolutePath;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String getTempFilePath() {
        File tempFile;
        try {
            tempFile = File.createTempFile("myTemp", ".png");
            String absolutePath = tempFile.getAbsolutePath();
            String tempFilePath = absolutePath.
                    substring(0,absolutePath.lastIndexOf(File.separator));
            return tempFilePath;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void deleteObject(String key) {
      myS3Instance.deleteObject(BUCKET_NAME, key);
    }
}
