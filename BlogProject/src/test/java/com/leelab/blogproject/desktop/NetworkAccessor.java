package com.leelab.blogproject.desktop;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class NetworkAccessor {

	public byte[] getProfileImage(String id) {
		byte[] image = null;
		
		CloseableHttpClient client = HttpClients.createDefault();
		
		HttpGet get = new HttpGet("http://localhost:8080/blog/ajax/profileImage/"+id);
		
		try
		{
			CloseableHttpResponse response = client.execute(get);
			
			HttpEntity entity = response.getEntity();
			
			image = new byte[579525];
			
			
			entity.getContent().read(image);
			
			client.close();
		}
		catch(Exception e)
		{
			try
			{
				client.close();
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}
		}
		
		return image;
	}

}
