package com.david.disshappserver.thread;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.david.disshappserver.beans.Joke;
import com.david.disshappserver.service.IJokeImgService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @ClassName: GetImgSizeThread
 * @Description: 获取图片大小的线程
 */
public class GetImgSizeThread extends Thread {
	private static final Log log = LogFactory.getLog(GetImgSizeThread.class);
	private Joke joke;
	private IJokeImgService jokeImgService;
	
	public GetImgSizeThread(Joke joke, IJokeImgService jokeImgService) {
		this.joke = joke;
		this.jokeImgService = jokeImgService;
	}
	

	@Override
	public void run() {
		if(joke == null) {
			return;
		}
		BufferedImage image = getBufferedImage(joke.getImgUrl()); 
        if (image != null) { 
            joke.setImgWidth(image.getWidth());
			joke.setImgHeight(image.getHeight());
            jokeImgService.update(joke);
			image = null;
        } else { 
            log.info("图片不存在！, url is " + joke.getImgUrl());
        } 
	}
	
	/**
     * 根据图片地址获取BufferedImage
     * @param imgUrl 图片地址
     * @return 
     */ 
    public static BufferedImage getBufferedImage(String imgUrl) { 
        URL url = null; 
        InputStream is = null; 
        BufferedImage img = null; 
        try { 
            url = new URL(imgUrl); 
            is = url.openStream(); 
            img = ImageIO.read(is); 
        } catch (MalformedURLException e) { 
        	log.error("GetImgSizeThread Exception.", e); 
        } catch (IOException e) { 
        	log.error("GetImgSizeThread Exception.", e);
        } finally { 
            try { 
                is.close(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
        return img; 
    } 
}
