package com.howei.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
public class ImgUploadUtil {

	public static String upload(MultipartFile file, String StorageAddress) {
		String message = "";
		try {
			if(!file.isEmpty()) {
				log.info(file.getOriginalFilename());
				message = System.currentTimeMillis() +"_"+ file.getOriginalFilename();//现在的文件名是时间戳加原文件名，出现图片相同时，读取不出来的bug
				String realPath = StorageAddress;//将文件保存在当前工程下的一个upload文件
				log.info(realPath);
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath, message));//将文件的输入流输出到一个新的文件
			}
		} catch (Exception e) {
			message = "error";
		}
		return message;
	}
	
}
